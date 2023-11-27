package MindHub.ecommerce.services;

import MindHub.ecommerce.models.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAllPurchases();
    Purchase findPurchaseById (Long id);
    void savePurchase (Purchase purchase);
}
