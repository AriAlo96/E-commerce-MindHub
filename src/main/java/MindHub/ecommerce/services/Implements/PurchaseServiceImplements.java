package MindHub.ecommerce.services.Implements;

import MindHub.ecommerce.models.Client;
import MindHub.ecommerce.models.Purchase;
import MindHub.ecommerce.repositories.PurchaseRepository;
import MindHub.ecommerce.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImplements implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase findPurchaseById(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    @Override
    public Purchase findPurchaseByEmail(String email) {
        return purchaseRepository.findByEmail(email);
    }

    @Override
    public void savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    public Boolean existsPurchaseByEmail(String email) {
        return purchaseRepository.existsPurchaseByEmail(email);
    }
}
