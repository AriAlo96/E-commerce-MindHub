package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.*;

import java.util.Set;

public class FraganceDTO {private Long id;
    private String name;
    private String description;
    private Gender gender;
    private OlfactoryFamily olfactoryFamily;
    private String image;
    private Double price;
    private Presentation presentation;
    private Integer content;
    private Integer stock;
    private Set<PurchaseFragance> purchesFragances;
    private Boolean active;

    public FraganceDTO(Fragance fragance)
    {
        this.name = fragance.getName();
        this.description = fragance.getDescription();
        this.gender = fragance.getGender();
        this.olfactoryFamily = fragance.getOlfactoryFamily();
        this.image = fragance.getImage();
        this.price = fragance.getPrice();
        this.presentation = fragance.getPresentation();
        this.content = fragance.getContent();
        this.stock = fragance.getStock();
        this.purchesFragances = fragance.getPurchesFragances();
        this.active = fragance.getActive();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Gender getGender() {
        return gender;
    }

    public OlfactoryFamily getOlfactoryFamily() {
        return olfactoryFamily;
    }

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public Integer getContent() {
        return content;
    }

    public Integer getStock() {
        return stock;
    }

    public Set<PurchaseFragance> getPurchesFragances() {
        return purchesFragances;
    }

    public Boolean getActive() {
        return active;
    }
}
