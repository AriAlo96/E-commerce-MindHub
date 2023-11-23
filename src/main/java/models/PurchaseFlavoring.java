package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PurchaseFlavoring {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private  Integer quantity;
    private  Double subtotal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flavoring_id")
    private Flavoring flavoring;

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "purchase_id")
    //private Purchase purchase;

    public PurchaseFlavoring() {
    }

    public PurchaseFlavoring(Integer quantity, Double subtotal, Flavoring flavoring) {
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.flavoring = flavoring;

    }

    public Long getId() {
        return id;
    }

    public Flavoring getFlavoring() {
        return flavoring;
    }

    public void setFlavoring(Flavoring flavoring) {
        this.flavoring = flavoring;
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
}