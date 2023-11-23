package dtos;

import models.Cream;
import models.PurchaseCream;

public class PurchaseCreamDTO {
    private Long id;

    private Integer quantity;
    private Double subtotal;
    private Long creamId;
    //private Long purchaseId;


    public PurchaseCreamDTO(PurchaseCream purchaseCream) {
        id = purchaseCream.getId();
        quantity = purchaseCream.getQuantity();
        subtotal = purchaseCream.getSubtotal();
        creamId = purchaseCream.getCream().getId();
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

    public Long getCreamId() {
        return creamId;
    }
}
