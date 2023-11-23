package MindHub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

public class Fragance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private String description;
    private Gender gender;
    private OlfactoryFamily olfactoryFamily;
    private byte[] image;
    private Double price;
    private Presentation presentation;
    private Integer content;
    private Integer stock;
    @OneToMany(mappedBy = "purchaseId", fetch = FetchType.EAGER)
    private Set<PurchaseFragance> purchesFragances;
    public Fragance() {
    }

    public Fragance(String name, String description, Gender gender, byte[] image) {
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.image = image;
    }

}
