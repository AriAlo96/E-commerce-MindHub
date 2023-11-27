package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.PurchaseCreamDTO;
import MindHub.ecommerce.dtos.PurchaseDTO;
import MindHub.ecommerce.dtos.PurchaseFlavoringDTO;
import MindHub.ecommerce.dtos.PurchaseFraganceDTO;
import MindHub.ecommerce.models.*;
import MindHub.ecommerce.services.ClientService;
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
    public ResponseEntity<Object> createNewPuchase(Authentication authentication,
                                                   @RequestBody(required = false) PurchaseCreamDTO purchaseCreamDTO,
                                                   @RequestBody(required = false) PurchaseFlavoringDTO purchaseFlavoringDTO,
                                                   @RequestBody(required = false) PurchaseFraganceDTO purchaseFraganceDTO)
    {
        Client client = clientService.findClientByEmail(authentication.getName());
        PurchaseFragance purchaseFragance = new PurchaseFragance(purchaseFraganceDTO.getQuantity(),
                purchaseFraganceDTO.getSubtotal());

        Purchase purchase = new Purchase(
                (purchaseCreamDTO.getSubtotal() + purchaseFlavoringDTO.getSubtotal() + purchaseFraganceDTO.getSubtotal()));
        client.addPurchase(purchase);
        clientService.saveClient(client);
        purchaseService.savePurchase(purchase);
        return new ResponseEntity<>("Your purchase was made successfully.", HttpStatus.OK);
    }
}
