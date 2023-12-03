package MindHub.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsBuyDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;

    public ProductsBuyDTO() {
    }

    public ProductsBuyDTO(Long id, String name, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "ProductsBuyDTO{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", quantity=" + quantity + '}';
    }
}
