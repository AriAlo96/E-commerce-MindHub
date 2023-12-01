package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.CreamDTO;
import MindHub.ecommerce.dtos.FlavoringDTO;
import MindHub.ecommerce.dtos.UpdateFlavoringDTO;
import MindHub.ecommerce.models.*;
import MindHub.ecommerce.repositories.FlavoringRepository;
import MindHub.ecommerce.services.ClientService;
import MindHub.ecommerce.services.FlavoringService;
import MindHub.ecommerce.services.PurchaseService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.ByteArrayOutputStream;


@RestController
@RequestMapping("/velvet")
public class FlavoringController {
    //@Autowired
    //private JavaMailSender mailSender;
    @Autowired
    private FlavoringService flavoringService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/flavorings")
    public List<FlavoringDTO> getAllFlavoring(){
        List<Flavoring> flavorings = flavoringService.findAllFlavorings();
        return  flavorings.stream()
                .map(FlavoringDTO::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/flavorings/{id}")
    public FlavoringDTO getFlavoringId(@PathVariable Long id){
        Flavoring flavoring = flavoringService.findFlavoringByID(id);

        if (flavoring != null){

            return  new FlavoringDTO(flavoring);
        }
        else {
            throw new ResourceNotFoundException("This Id flavoring not exist");
        }
    }
    @PostMapping("/flavorings/create")
    public ResponseEntity<Object> createNewFlavoring(@RequestParam String name, @RequestParam String description,
                                                 @RequestParam Double price, @RequestParam Integer content, @RequestParam Integer stock,
                                                 @RequestParam Presentation presentation, @RequestParam String image){
        if (name.isBlank()){
            return new ResponseEntity<>("complete the name", HttpStatus.FORBIDDEN);
        }
        if (description.isBlank()){
            return new ResponseEntity<>("complete the description", HttpStatus.FORBIDDEN);
        }
        if (image.isBlank()){
            return new ResponseEntity<>("complete the image URL", HttpStatus.FORBIDDEN);
        }
        if (price.isNaN()){
            return new ResponseEntity<>("complete the Price", HttpStatus.FORBIDDEN);
        }
        if (price <= 0){
            return new ResponseEntity<>("The price cant be 0 or less", HttpStatus.FORBIDDEN);
        }
        if (stock == null){
            return new ResponseEntity<>("complete the Stock", HttpStatus.FORBIDDEN);
        }
        if (stock < 0){
            return new ResponseEntity<>("The Stock cant be less to 0", HttpStatus.FORBIDDEN);
        }
        if (content == null){
            return new ResponseEntity<>("complete the content", HttpStatus.FORBIDDEN);
        }
        if (content <= 0){
            return new ResponseEntity<>("The Stock cant be 0 or less ", HttpStatus.FORBIDDEN);
        }
        if (presentation == null){
            return new ResponseEntity<>("Complete the Presentation", HttpStatus.FORBIDDEN);
        }

        Flavoring flavoring = new Flavoring(name,description,content,price,stock,presentation,image, true);

        flavoringService.saveFlavoring(flavoring);

        //arreglado el controlador
        return new ResponseEntity<>("Flavoring created", HttpStatus.CREATED);
    }

    @PatchMapping("/flavorings/update")
    public ResponseEntity<String> updateFlavoring(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String description,
                                                 @RequestParam(required = false) Double price,
                                                 @RequestParam(required = false) Presentation presentation,
                                                 @RequestParam(required = false) Integer content,
                                                 @RequestParam(required = false) Integer stock,
                                                 @RequestParam(required = false) String image, @RequestParam Long id)
    {
        Flavoring flavoring = flavoringService.findFlavoringByID(id);

        List<String> flavoringName = flavoringService.findAllFlavorings().stream().map(
                Flavoring::getName).collect(Collectors.toList());
        if (flavoringName.contains(name)) {
            return new ResponseEntity<>("The fragrance name already use", HttpStatus.BAD_REQUEST);
        } else {
            if(name != null){
                flavoring.setName(name);
            }
            if(description != null){
                flavoring.setDescription(description);
            }
            if(price != null){
                flavoring.setPrice(price);
            }
            if(presentation != null){
                flavoring.setPresentation(presentation);
            }
            if(content != null){
                flavoring.setContent(content);
            }
            if(stock != null){
                flavoring.setStock(stock);
            }
            if(image != null){
                flavoring.setImage(image);
            }
            flavoringService.saveFlavoring(flavoring);
            return new ResponseEntity<>("flavoring update successfully", HttpStatus.OK);
        }
    }

    @PatchMapping("/flavorings/delete")
    public ResponseEntity<Object> deleteFlavoring(@RequestParam Long id) {
        Flavoring flavoring = flavoringService.findFlavoringByID(id);
        if (flavoring == null) {
            return new ResponseEntity<>("The cream doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (flavoring.getStock() > 0) {
            return new ResponseEntity<>("You can not delete an cream with a stock greater than zero",
                    HttpStatus.FORBIDDEN);
        }
        if (!flavoring.getActive()) {
            return new ResponseEntity<>("The cream is inactive", HttpStatus.FORBIDDEN);
        } else {
            flavoring.setActive(false);
            flavoringService.saveFlavoring(flavoring);
            return new ResponseEntity<>("Cream delete successfully", HttpStatus.CREATED);
        }


    }
}
