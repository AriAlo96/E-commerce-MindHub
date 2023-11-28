package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.PurchaseCreamDTO;
import MindHub.ecommerce.dtos.PurchaseDTO;
import MindHub.ecommerce.dtos.PurchaseFlavoringDTO;
import MindHub.ecommerce.dtos.PurchaseFraganceDTO;
import MindHub.ecommerce.models.*;
import MindHub.ecommerce.services.ClientService;
import MindHub.ecommerce.services.FraganceService;
import MindHub.ecommerce.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/purcheses")
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

    @PostMapping("purchases/create")
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

}
