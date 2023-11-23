package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.Client;
import MindHub.ecommerce.models.Purchase;

import java.util.List;

public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private List<Purchase> totalPurchases;

    public ClientDTO(Client client) {
        id = client.getId();
        firstName = client.getFirstName();
        lastName = client.getLastName();
        email = client.getEmail();
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

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public List<Purchase> getTotalPurchases() {
        return totalPurchases;
    }
}
