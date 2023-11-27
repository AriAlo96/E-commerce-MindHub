package MindHub.ecommerce;

import MindHub.ecommerce.models.*;
import MindHub.ecommerce.repositories.CreamRepository;
import MindHub.ecommerce.repositories.FlavoringRepository;
import MindHub.ecommerce.repositories.FraganceRepository;
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
    public CommandLineRunner initData(FraganceRepository fraganceRepository, FlavoringRepository flavoringRepository,
                                      CreamRepository creamRepository)
    {
        return args -> {
            //-------------CLIENTS-------------


            //-------------FRAGANCES-------------
         //  Fragance marsella = new Fragance("Marsella", "ef", Gender.WOMEN, OlfactoryFamily.CHYPRE,
           //        "../resources/static/web/assets/images/BAGUES-Rio-ML-X-50.jpg", 8000.00, Presentation.CLASICPACKAGE,
             //      50,10);
         //   fraganceRepository.save(marsella);
        };
    }
}