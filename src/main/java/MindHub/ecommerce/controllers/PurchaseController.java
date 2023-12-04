package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.*;
import MindHub.ecommerce.models.*;
import MindHub.ecommerce.repositories.PurchaseCreamRepository;
import MindHub.ecommerce.repositories.PurchaseFlavoringRepository;
import MindHub.ecommerce.repositories.PurchaseFraganceRepository;
import MindHub.ecommerce.services.*;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/velvet")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private FraganceService fraganceService;
    @Autowired
    private FlavoringService flavoringService;
    @Autowired
    private CreamService creamService;
    @Autowired
    private PurchaseFlavoringRepository purchaseFlavoringRepository;
    @Autowired
    private PurchaseFraganceRepository purchaseFraganceRepository;
    @Autowired
    private PurchaseCreamRepository purchaseCreamRepository;

    @GetMapping("/purchases")
    public List<PurchaseDTO> getAllPurchases() {
        List<PurchaseDTO> purchases = purchaseService.findAllPurchases().stream().map(
                purchase -> new PurchaseDTO(purchase)).collect(Collectors.toList());
        return purchases;
    }

    @GetMapping("/purchases/{id}")
    public PurchaseDTO getPurchase(@PathVariable Long id) {
        PurchaseDTO purchase = new PurchaseDTO(purchaseService.findPurchaseById(id));
        return purchase;
    }
    @Transactional
    @PostMapping("/purchases/create")
    public ResponseEntity<Object> newPurchase(Authentication authentication, @RequestBody BuyPurchaseDTO buyPurchaseDTO) throws MessagingException, DocumentException, IOException {
        Client client = clientService.findClientByEmail(authentication.getName());

//        if(buyPurchaseDTO.getProductsDTO().stream().anyMatch(productsBuyDTO -> productsBuyDTO.getStock()<productsBuyDTO.getQuantity())){
//            return new ResponseEntity<>("You have a product that is out of stock", HttpStatus.FORBIDDEN);
//        }

        Purchase purchase = new Purchase(0.0);
        purchase.setClient(client);

        double total = processProducts(buyPurchaseDTO.getProductsDTO(), purchase);

        purchase.setTotalPurchases(total);

        purchaseService.savePurchase(purchase);

        purchaseService.createAndSendPDFMail(authentication,purchase);

        return new ResponseEntity<>("Purchase Created!", HttpStatus.CREATED);
    }


    private double processProducts(List<ProductsBuyDTO> productsDTO, Purchase purchase) {
        double total = 0.0;

        for (ProductsBuyDTO product : productsDTO) {
            if (product.getName().contains("parfum")) {
                Fragance fragance = fraganceService.findFraganceById(product.getId());
                total += processFraganceProduct(product, fragance, purchase);
            } else if (product.getName().contains("cream")) {
                Cream cream = creamService.findCreamById(product.getId());
                total += processCreamProduct(product, cream, purchase);
            } else if (product.getName().contains("air freshener")) {
                Flavoring flavoring = flavoringService.findFlavoringByID(product.getId());
                total += processFlavoringProduct(product, flavoring, purchase);
            }
        }
        return total;
    }

    private double processFraganceProduct(ProductsBuyDTO product, Fragance fragance, Purchase purchase) {
            double subtotal = fragance.getPrice() * product.getQuantity();
            PurchaseFragance purchaseFragance = new PurchaseFragance(product.getQuantity(),
                    subtotal);
            purchase.addPurchaseFragance(purchaseFragance);
            purchaseFragance.setFragance(fragance);
            fragance.setStock(fragance.getStock() - product.getQuantity());
            fraganceService.saveFragance(fragance);
            purchaseFraganceRepository.save(purchaseFragance);
            return subtotal;
    }

    private double processCreamProduct(ProductsBuyDTO product, Cream cream, Purchase purchase) {
        double subtotal = cream.getPrice() * product.getQuantity();
        PurchaseCream purchaseCream = new PurchaseCream(product.getQuantity(), subtotal);
        purchase.addPurchaseCream(purchaseCream);
        purchaseCream.setCream(cream);
        cream.setStock(cream.getStock() - product.getQuantity());
        creamService.saveCream(cream);
        purchaseCreamRepository.save(purchaseCream);
        return subtotal;
    }

    private double processFlavoringProduct(ProductsBuyDTO product, Flavoring flavoring, Purchase purchase) {
        double subtotal = flavoring.getPrice() * product.getQuantity();
        PurchaseFlavoring purchaseFlavoring = new PurchaseFlavoring(product.getQuantity(), subtotal);
        purchase.addPurchaseFlavoring(purchaseFlavoring);
        purchaseFlavoring.setFlavoring(flavoring);
        flavoring.setStock(flavoring.getStock() - product.getQuantity());
        flavoringService.saveFlavoring(flavoring);
        purchaseFlavoringRepository.save(purchaseFlavoring);
        return subtotal;
    }



}
