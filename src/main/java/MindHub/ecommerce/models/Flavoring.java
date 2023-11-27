package MindHub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Flavoring {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private String description;
    private Integer content;
    private Double price;
    private Integer stock;
    private Presentation presentation;
    private String image;
    @OneToMany(mappedBy = "flavoring", fetch = FetchType.EAGER)
    private Set<PurchaseFlavoring> purchaseFlavorings = new HashSet<>();




    public Flavoring() {
    }

    public Flavoring(String name, String description, Integer content, Double price, Integer stock,
                     Presentation presentation, String image)
    {
        this.name = name;
        this.description = description;
        this.content = content;
        this.price = price;
        this.stock = stock;
        this.presentation = presentation;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<PurchaseFlavoring> getPurchaseFlavorings() {
        return purchaseFlavorings;
    }

    public void setPurchaseFlavorings(Set<PurchaseFlavoring> purchaseFlavorings) {
        this.purchaseFlavorings = purchaseFlavorings;
    }
}