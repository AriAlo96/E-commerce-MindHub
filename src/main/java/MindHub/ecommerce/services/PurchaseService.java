package MindHub.ecommerce.services;

import MindHub.ecommerce.models.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAllPurchases();
    Purchase findPurchaseById (Long id);
    Purchase findPurchaseByEmail (String email);
    void savePurchase (Purchase purchase);
    Boolean existsPurchaseByEmail(String email);
}
