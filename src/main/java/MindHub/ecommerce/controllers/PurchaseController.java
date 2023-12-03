package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.*;
import MindHub.ecommerce.models.*;
import MindHub.ecommerce.repositories.PurchaseCreamRepository;
import MindHub.ecommerce.repositories.PurchaseFlavoringRepository;
import MindHub.ecommerce.repositories.PurchaseFraganceRepository;
import MindHub.ecommerce.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @PostMapping("/purchases/create")
    public ResponseEntity<Object> createNewPurchase(Authentication authentication,
                                                    @RequestBody PurchaseDTO purchaseDTO)
    {
        if (purchaseDTO == null) {
            return new ResponseEntity<>("Purchase details are required.", HttpStatus.BAD_REQUEST);
        }
        Client client = clientService.findClientByEmail(authentication.getName());
        if (client == null) {
            return new ResponseEntity<>("Client not found.", HttpStatus.NOT_FOUND);
        }
        double total = 0.0;
        Purchase purchase = new Purchase();
        if (purchaseDTO.getPurchaseCreams() != null) {
            for (PurchaseCream cream : purchaseDTO.getPurchaseCreams()) {
                double creamSubtotal = cream.getSubtotal();
                total += creamSubtotal;
                int purchasedQuantity = cream.getQuantity();
                Cream creamObj = cream.getCream();
                int currentStock = creamObj.getStock();
                int updatedStock = currentStock - purchasedQuantity;
                creamObj.setStock(updatedStock);
                creamService.saveCream(creamObj);
                cream.setPurchase(purchase);
            }
        }
        if (purchaseDTO.getPurchaseFlavorings() != null) {
            for (PurchaseFlavoring flavoring : purchaseDTO.getPurchaseFlavorings()) {
                double flavoringSubtotal = flavoring.getSubtotal();
                total += flavoringSubtotal;
                int purchasedQuantity = flavoring.getQuantity();
                Flavoring flavoringObj = flavoring.getFlavoring();
                int currentStock = flavoringObj.getStock();
                int updatedStock = currentStock - purchasedQuantity;
                flavoringObj.setStock(updatedStock);
                flavoringService.saveFlavoring(flavoringObj);
                flavoring.setPurchase(purchase);
            }
        }
        if (purchaseDTO.getPurchaseFragances() != null) {
            for (PurchaseFragance fragance : purchaseDTO.getPurchaseFragances()) {
                double fraganceSubtotal = fragance.getSubtotal();
                total += fraganceSubtotal;
                int purchasedQuantity = fragance.getQuantity();
                Fragance fraganceObj = fragance.getFragance();
                int currentStock = fraganceObj.getStock();
                int updatedStock = currentStock - purchasedQuantity;
                fraganceObj.setStock(updatedStock);
                fraganceService.saveFragance(fraganceObj);
                fragance.setPurchase(purchase);
            }
        }
        purchase.setTotalPurchases(total);
        purchase.setClient(client);
        purchaseService.savePurchase(purchase);
        return new ResponseEntity<>("Your purchase was made successfully.", HttpStatus.OK);
    }

    @PostMapping("/create/purchase/2")
    public ResponseEntity<Object> createPurchaseDTO(Authentication authentication, @RequestBody BuyPurchaseDTO buyPurchaseDTO) {
        Client client = clientService.findClientByEmail(authentication.getName());

        Purchase purchase = new Purchase(0.0);
        purchase.setClient(client);

        double total = processProducts(buyPurchaseDTO.getProductsDTO(), purchase);

        purchase.setTotalPurchases(total);

        purchaseService.savePurchase(purchase);

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
        fraganceService.saveFragance(fragance);
        PurchaseFragance purchaseFragance = new PurchaseFragance(product.getQuantity(), subtotal);
        fragance.setStock(fragance.getStock() - product.getQuantity());
        purchase.addPurchaseFragance(purchaseFragance);
        purchaseFraganceRepository.save(purchaseFragance);
        return subtotal;
    }

    private double processCreamProduct(ProductsBuyDTO product, Cream cream, Purchase purchase) {
        double subtotal = cream.getPrice() * product.getQuantity();
        creamService.saveCream(cream);
        PurchaseCream purchaseCream = new PurchaseCream(product.getQuantity(), subtotal);
        cream.setStock(cream.getStock() - product.getQuantity());
        purchase.addPurchaseCream(purchaseCream);
        purchaseCreamRepository.save(purchaseCream);
        return subtotal;
    }

    private double processFlavoringProduct(ProductsBuyDTO product, Flavoring flavoring, Purchase purchase) {
        double subtotal = flavoring.getPrice() * product.getQuantity();
        flavoringService.saveFlavoring(flavoring);
        PurchaseFlavoring purchaseFlavoring = new PurchaseFlavoring(product.getQuantity(), subtotal);
        flavoring.setStock(flavoring.getStock() - product.getQuantity());
        purchase.addPurchaseFlavoring(purchaseFlavoring);
        purchaseFlavoringRepository.save(purchaseFlavoring);
        return subtotal;
    }



}
