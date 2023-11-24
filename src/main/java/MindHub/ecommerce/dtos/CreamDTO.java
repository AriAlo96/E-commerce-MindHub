package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.Cream;
import MindHub.ecommerce.models.Type;

import java.awt.image.BufferedImage;

public class CreamDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Integer stock;
    private Type type;
    private BufferedImage image;

    public CreamDTO(Cream cream){
        id = cream.getId();
        name = cream.getName();
        description = cream.getDescription();
        price = cream.getPrice();
        quantity = cream.getQuantity();
        stock = cream.getStock();
        type = cream.getType();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getStock() {
        return stock;
    }

    public Type getType() {
        return type;
    }
}
