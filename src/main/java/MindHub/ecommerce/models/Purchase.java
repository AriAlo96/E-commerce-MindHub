package MindHub.ecommerce.models;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

public class Purchase {
    private Long id;
    @ManyToOne
    private Long clientId;
    private Double totalPurchases;
    @OneToMany(mappedBy = "fraganceId", fetch = FetchType.EAGER)
    private Set<PurchaseFragance> purchaseFragances;

    @OneToMany(mappedBy = "flavoringId", fetch = FetchType.EAGER)
    private Set<PurchaseFlavoring> purchaseFlavorings;
    @OneToMany(mappedBy = "creamId", fetch = FetchType.EAGER)
    private Set<PurchaseCream> purchaseCreams;

    public Purchase() {
    }

    public Purchase(Long clientId, Double totalPurchases) {
        this.clientId = clientId;
        this.totalPurchases = totalPurchases;
    }

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getTotalPurchases() {
        return totalPurchases;
    }

    public void setTotalPurchases(Double totalPurchases) {
        this.totalPurchases = totalPurchases;
    }
}
