package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Set;

public class PurchaseDTO {
    private Long id;
    @JsonBackReference
    private Client client;
    private Double totalPurchases;
    private Set<PurchaseFragance> purchaseFragances;
    private Set<PurchaseFlavoring> purchaseFlavorings;
    private Set<PurchaseCream> purchaseCreams;


    public PurchaseDTO(Purchase purchase)
    {
        id = purchase.getId();
        client = purchase.getClient();
        totalPurchases = purchase.getTotalPurchases();
        purchaseFragances = purchase.getPurchaseFragances();
        purchaseFlavorings = purchase.getPurchaseFlavorings();
        purchaseCreams = purchase.getPurchaseCreams();
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Double getTotalPurchases() {
        return totalPurchases;
    }

    public Set<PurchaseFragance> getPurchaseFragances() {
        return purchaseFragances;
    }

    public Set<PurchaseFlavoring> getPurchaseFlavorings() {
        return purchaseFlavorings;
    }

    public Set<PurchaseCream> getPurchaseCreams() {
        return purchaseCreams;
    }
}
