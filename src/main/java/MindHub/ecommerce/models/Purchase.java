package MindHub.ecommerce.models;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

public class Purchase {
    private Long id;
    @ManyToOne
    private Long clientId;
    private Double totalPurchases;
    @OneToMany(mappedBy = "fraganceId", fetch = FetchType.EAGER)
    private Set<PurchaseFragance> purchaseFragances = new HashSet<>();

    @OneToMany(mappedBy = "flavoringId", fetch = FetchType.EAGER)
    private Set<PurchaseFlavoring> purchaseFlavorings = new HashSet<>();
    @OneToMany(mappedBy = "creamId", fetch = FetchType.EAGER)
    private Set<PurchaseCream> purchaseCreams = new HashSet<>();

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

    public Set<PurchaseFragance> getPurchaseFragances() {
        return purchaseFragances;
    }

    public void setPurchaseFragances(Set<PurchaseFragance> purchaseFragances) {
        this.purchaseFragances = purchaseFragances;
    }

    public Set<PurchaseFlavoring> getPurchaseFlavorings() {
        return purchaseFlavorings;
    }

    public void setPurchaseFlavorings(Set<PurchaseFlavoring> purchaseFlavorings) {
        this.purchaseFlavorings = purchaseFlavorings;
    }

    public Set<PurchaseCream> getPurchaseCreams() {
        return purchaseCreams;
    }

    public void setPurchaseCreams(Set<PurchaseCream> purchaseCreams) {
        this.purchaseCreams = purchaseCreams;
    }
}
