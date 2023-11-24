package MindHub.ecommerce;

import MindHub.ecommerce.models.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(Client client) {
		return args -> {
			System.out.println("app launching");
			Client client1 = new Client("Elsa", "Patilla","123@123.com", "123", "Cuba, 45");
			Client client2 = new Client("Eldo", "Minicano", "234@234.com", "234", "Cuba,46");

		};
	}
}