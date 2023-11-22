package dtos;

import models.Flavoring;
import models.Presentation;

public class FlavoringDTO {
    private Long id;
    private String name;
    private String description;
    private Integer content;
    private Double price;
    private Integer stock;
    private Presentation presentation;

    public FlavoringDTO(Flavoring flavoring){
        id = flavoring.getId();
        name = flavoring.getName();
        description = flavoring.getDescription();
        content = flavoring.getContent();
        price = flavoring.getPrice();
        stock = flavoring.getStock();
        presentation = flavoring.getPresentation();
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

    public Integer getContent() {
        return content;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Presentation getPresentation() {
        return presentation;
    }
}
