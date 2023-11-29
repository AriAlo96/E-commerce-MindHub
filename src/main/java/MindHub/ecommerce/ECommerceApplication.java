package MindHub.ecommerce;

import MindHub.ecommerce.models.*;
import MindHub.ecommerce.repositories.ClientRepository;
import MindHub.ecommerce.repositories.CreamRepository;
import MindHub.ecommerce.repositories.FlavoringRepository;
import MindHub.ecommerce.repositories.FraganceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ECommerceApplication {

//    @Autowired
//    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner initData(ClientRepository clientRepository , FraganceRepository fraganceRepository, FlavoringRepository flavoringRepository,
//                                      CreamRepository creamRepository)
//    {
//        return args -> {
//        Client client = new Client("Ariana", "Alochis", "ari@velvetAdm.com" , passwordEncoder.encode("1234"));
//        clientRepository.save(client);
//        };
//    }
}