package MindHub.ecommerce.models;

import javax.persistence.Entity;

@Entity
public class FragancePurchase {
    private Long id;
    private Long purchaseId;
    private Long fraganceId;
    private Integer amount;
    private Double subtotal;

    public FragancePurchase() {
    }

    public FragancePurchase(Long purchaseId, Long fraganceId, Integer amount, Double subtotal) {
        this.purchaseId = purchaseId;
        this.fraganceId = fraganceId;
        this.amount = amount;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getFraganceId() {
        return fraganceId;
    }

    public void setFraganceId(Long fraganceId) {
        this.fraganceId = fraganceId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
