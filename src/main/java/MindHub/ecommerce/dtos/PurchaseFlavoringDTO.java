package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.PurchaseFlavoring;

public class PurchaseFlavoringDTO {
    private Long id;
    private  Integer quantity;
    private  Double subtotal;
    private Long flavoringId;
    //private Long purchaseId;


    public PurchaseFlavoringDTO(PurchaseFlavoring purchaseFlavoring) {
        id = purchaseFlavoring.getId();
        quantity = purchaseFlavoring.getQuantity();
        subtotal= purchaseFlavoring.getSubtotal();
        flavoringId = purchaseFlavoring.getFlavoring().getId();
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Long getFlavoringId() {
        return flavoringId;
    }
}
