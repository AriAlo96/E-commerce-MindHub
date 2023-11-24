package MindHub.ecommerce.models;

import MindHub.ecommerce.models.Cream;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.awt.image.BufferedImage;

@Entity
public class PurchaseCream {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private Integer quantity;
    private Double subtotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cream_id")
    private Cream cream;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;


    public PurchaseCream() {
    }

    public PurchaseCream(Integer quantity, Double subtotal, Cream cream, Purchase purchase) {
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.cream = cream;
        this.purchase = purchase;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Cream getCream() {
        return cream;
    }

    public void setCream(Cream cream) {
        this.cream = cream;
    }
}
