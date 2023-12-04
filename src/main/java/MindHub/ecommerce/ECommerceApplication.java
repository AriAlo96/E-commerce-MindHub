package MindHub.ecommerce;

import MindHub.ecommerce.models.*;
import MindHub.ecommerce.repositories.ClientRepository;
import MindHub.ecommerce.repositories.CreamRepository;
import MindHub.ecommerce.repositories.FlavoringRepository;
import MindHub.ecommerce.repositories.FragranceRepository;
import MindHub.ecommerce.services.FragranceService;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, FragranceRepository fragranceRepository,
                                      FlavoringRepository flavoringRepository, CreamRepository creamRepository)
    {
        return args -> {

//-------------------------------------------CLIENTS-------------------------------------------
            Client client1 = new Client("Ariana", "Alochis", "ari@velvetadm.com", passwordEncoder.encode("1234"));
            clientRepository.save(client1);

            Client client2 = new Client("Isaac", "Alfonso", "ifa@velvetadm.com", passwordEncoder.encode("isaac"));
            clientRepository.save(client2);

//-------------------------------------------FRAGRANCES-------------------------------------------
            Fragrance fragrance1 = new Fragrance("Reine Eau de parfum",
                    "A pure and bright aroma.\n" + " The fragrance of a conquering, empowered and strong woman, who expresses a new era for those who dream big.\n" + " Unstoppable, you can make your own path and achieve what you want.\n" + " Head: pear and bergamot.\n" + " Heart: Turkish rose, Mai rose and Indian jasmine.\n" + " Base: white musk and vanilla",
                    Gender.WOMEN, OlfactoryFamily.CITRUS,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701103421/reine1-28bf2c4ffbfad8b6fb16859777988887-1024-1024_ru9afi.jpg",
                    39.99, Presentation.CLASICPACKAGE, 50, 10, true);
            fragranceRepository.save(fragrance1);

            Fragrance fragrance2 = new Fragrance("Manhattan Rose Eau de parfum",
                    "Its glamorous spirit makes you shine day and night. A fresh, dynamic and essentially feminine fragrance, which expresses the rhythm of the big city.\n" + "- Top notes: rosé champagne and pink pepper\n" + "- Heart notes: peach blossom and bouquet of roses\n" + "- Base notes: queenwood and musk",
                    Gender.WOMEN, OlfactoryFamily.FLOWERY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701103419/manhattan-rose1-7194d2de97952d1a9716859814625038-480-0_dapnhn.jpg",
                    39.99, Presentation.CLASICPACKAGE, 50, 8, true);
            fragranceRepository.save(fragrance2);

            Fragrance fragrance3 = new Fragrance("Marsella Eau de parfum",
                    "A breath of fresh air and freedom. Intoxicating, unbridled and enigmatic, an addictive aroma that invites you to an irreverent adventure in the middle of the night.\n" + "- Top notes: honey, bitter orange and citrus\n" + "- Heart notes: cherry, tuberose and orange blossom\n" + "- Base notes: tonka bean, vanilla, patchouli and sandalwood",
                    Gender.WOMEN, OlfactoryFamily.FLOWERY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701103415/5975f1c6777d862572f61e59ce85c7b7932ecb30e57f50a795cb231f472b154685272_gcayci.png",
                    69.99, Presentation.CLASICPACKAGE, 100, 3, true);
            fragranceRepository.save(fragrance3);

            Fragrance fragrance4 = new Fragrance("Las Vegas Eau de Parfum",
                    "Charisma and insolent, provocative energy. The fragrance of those who get what they want.\n" + "\n" + "Head: grapefruit, mint, red mandarin.\n" + "Heart: rose, cinnamon, spicy notes.\n" + "Base: leather, woody notes, Indian patchouli.",
                    Gender.MAN, OlfactoryFamily.WOODY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701691468/las-vegas1-06109c28078b94699416868369344433-480-0_m97hf2.jpg",
                    39.99, Presentation.CLASICPACKAGE, 50, 5, true);
            fragranceRepository.save(fragrance4);

//-------------------------------------------CREAMS-------------------------------------------
            Cream cream1 = new Cream("Exotic Cream",
                    "Returns the necessary hydration and softness to your skin. " + "Formulated with jojoba, it helps renewal and leaves it radiant and scented with the unmistakable citrus aroma of Exotic.",
                    25.99, 300, 5, Type.BODY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701347624/exoticcremacorporal1-0f5f697422c8125da416859756562113-1024-1024_yepahd.png",
                    true);
            creamRepository.save(cream1);

//-------------------------------------------FLAVORING-------------------------------------------
            Flavoring flavoring1 = new Flavoring("Pink and violet air freshener",
                    "Scent of roses and violets. Ideal for perfuming every corner of your home.", 500, 15.99, 9,
                    AMBIENT,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701276850/aromatizante-rosa-y-violeta_zduwqa.png",
                    true);
            flavoringRepository.save(flavoring1);

            Flavoring flavoring2 = new Flavoring("Strawberry and plum air freshener",
                    "A fruity essence that seduces your senses and transmits the freshness of summer.", 500, 14.99, 4,
                    AMBIENT,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701691772/aromatizante-frutilla-y-ciruela_ayyipu.png",
                    true);
            flavoringRepository.save(flavoring2);

            Flavoring flavoring3 = new Flavoring("Fabric air freshener caress of freshness",
                    "Energy and freshness between your sheets and pillows to start the day with the best aromas.", 500,
                    16.99, 7, FABRICS,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701691760/aromatizante-cama-caricia-de-frescura_t81iuq.png",
                    true);
            flavoringRepository.save(flavoring3);

            Flavoring flavoring4 = new Flavoring("Linen air freshener",
                    "An unmistakable fragrance of clean clothes that permeates all your environments.", 500, 14.99, 6,
                    AMBIENT,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701691764/aromatizante-lino_bvxhq8.png", true);
            flavoringRepository.save(flavoring4);
        };
    }
}