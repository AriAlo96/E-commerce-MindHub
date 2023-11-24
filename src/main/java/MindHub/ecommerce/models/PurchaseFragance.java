package MindHub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PurchaseFragance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @ManyToOne
    private Purchase purchase;
    @ManyToOne
    private Fragance fragance;
    private Integer amount;
    private Double subtotal;


    public PurchaseFragance() {
    }

    public PurchaseFragance(Purchase purchase, Fragance fragance, Integer amount, Double subtotal) {
        this.purchase = purchase;
        this.fragance = fragance;
        this.amount = amount;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Fragance getFragance() {
        return fragance;
    }

    public void setFragance(Fragance fragance) {
        this.fragance = fragance;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
