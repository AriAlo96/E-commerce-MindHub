package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.Fragance;
import MindHub.ecommerce.models.Purchase;
import MindHub.ecommerce.models.PurchaseFragance;

public class PurchaseFraganceDTO {
    private Long id;
    private Purchase purchase;
    private Fragance fragance;
    private Integer quantity;
    private Double subtotal;

    public PurchaseFraganceDTO(PurchaseFragance purchaseFragance) {
        this.id = purchaseFragance.getId();
        this.purchase = purchaseFragance.getPurchase();
        this.fragance = purchaseFragance.getFragance();
        this.quantity = purchaseFragance.getQuantity();
        this.subtotal = purchaseFragance.getSubtotal();
    }

    public Long getId() {
        return id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public Fragance getFragance() {
        return fragance;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }
}
