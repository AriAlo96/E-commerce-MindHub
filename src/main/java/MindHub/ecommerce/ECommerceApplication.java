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

import static MindHub.ecommerce.models.Presentation.AMBIENT;
import static MindHub.ecommerce.models.Presentation.FABRICS;

@SpringBootApplication
public class ECommerceApplication {

//    @Autowired
//    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

   @Bean
    public CommandLineRunner initData(ClientRepository clientRepository , FraganceRepository fraganceRepository, FlavoringRepository flavoringRepository,
                                      CreamRepository creamRepository)
    {
        return args -> {
        Flavoring flavoring1 = new Flavoring("Pink and violet air freshener","Scent of roses and violets. Ideal for perfuming every corner of your home.",500,15.99,9,AMBIENT,"https://res.cloudinary.com/dndulhlws/image/upload/v1701276850/aromatizante-rosa-y-violeta_zduwqa.png",true);
        Flavoring flavoring2 = new Flavoring("Strawberry and plum air freshener","A fruity essence that seduces your senses and transmits the freshness of summer.",500,14.99,4,AMBIENT,"https://res.cloudinary.com/dndulhlws/image/upload/v1701691772/aromatizante-frutilla-y-ciruela_ayyipu.png", true);
        Flavoring flavoring3 = new Flavoring("Fabric air freshener caress of freshness","Energy and freshness between your sheets and pillows to start the day with the best aromas.",500,16.99,7,FABRICS,"https://res.cloudinary.com/dndulhlws/image/upload/v1701691760/aromatizante-cama-caricia-de-frescura_t81iuq.png", true);
        Flavoring flavoring4 = new Flavoring("Linen air freshener","An unmistakable fragrance of clean clothes that permeates all your environments.",500,14.99,6,AMBIENT,"https://res.cloudinary.com/dndulhlws/image/upload/v1701691764/aromatizante-lino_bvxhq8.png", true);
        
        };
    }
}