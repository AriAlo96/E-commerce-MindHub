package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.Client;
import MindHub.ecommerce.models.Purchase;

import java.util.Set;

public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Set<Purchase> totalPurchases;

    public ClientDTO(Client client) {
        id = client.getId();
        firstName = client.getFirstName();
        lastName = client.getLastName();
        email = client.getEmail();
        address = client.getAddress();
        totalPurchases = client.getTotalPurchases();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Set<Purchase> getTotalPurchases() {
        return totalPurchases;
    }
}
