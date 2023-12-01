package MindHub.ecommerce.dtos;

public class FraganceBuyDTO {
    private Long id;
    private Double price;
    private Integer quantity;

    public FraganceBuyDTO(Long id, Double price, Integer quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }



    public Double getPrice() {
        return price;
    }



    public Integer getQuantity() {
        return quantity;
    }


}
