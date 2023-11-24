package MindHub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
@Entity
public class Fragance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private String description;
    private Gender gender;
    private OlfactoryFamily olfactoryFamily;
    private String image;
    private Double price;
    private Presentation presentation;
    private Integer content;
    private Integer stock;
    @OneToMany(mappedBy = "fragance", fetch = FetchType.EAGER)
    private Set<PurchaseFragance> purchesFragances;

    public Fragance() {
    }

    public Fragance(String name, String description, Gender gender, OlfactoryFamily olfactoryFamily,
                    String image, Double price, Presentation presentation, Integer content, Integer stock)
    {
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.olfactoryFamily = olfactoryFamily;
        this.price = price;
        this.presentation = presentation;
        this.content = content;
        this.stock = stock;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public OlfactoryFamily getOlfactoryFamily() {
        return olfactoryFamily;
    }

    public void setOlfactoryFamily(OlfactoryFamily olfactoryFamily) {
        this.olfactoryFamily = olfactoryFamily;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Set<PurchaseFragance> getPurchesFragances() {
        return purchesFragances;
    }

    public void setPurchesFragances(Set<PurchaseFragance> purchesFragances) {
        this.purchesFragances = purchesFragances;
    }
}
