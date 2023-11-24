package MindHub.ecommerce.models;

import javax.persistence.ManyToOne;

public class Purchase {

    private Long id;
    @ManyToOne
    private Long clientId;
    private Double totalPurchases;
//    private

    public Purchase() {
    }

    public Purchase(Long clientId, Double totalPurchases) {
        this.clientId = clientId;
        this.totalPurchases = totalPurchases;
    }


}
