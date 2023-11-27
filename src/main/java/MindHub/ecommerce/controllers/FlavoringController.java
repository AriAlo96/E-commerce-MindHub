package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.CreamDTO;
import MindHub.ecommerce.dtos.FlavoringDTO;
import MindHub.ecommerce.models.Cream;
import MindHub.ecommerce.models.Flavoring;
import MindHub.ecommerce.models.Presentation;
import MindHub.ecommerce.models.Type;
import MindHub.ecommerce.repositories.FlavoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/velvet")
public class FlavoringController {
    @Autowired
    private FlavoringRepository flavoringRepository;

    @GetMapping("/flavorings")
    public List<FlavoringDTO> getAllFlavoring(){
        List<Flavoring> flavorings = flavoringRepository.findAll();
        return  flavorings.stream()
                .map(FlavoringDTO::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/flavorings/{id}")
    public FlavoringDTO getFlavoringId(@PathVariable Long id){
        Optional<Flavoring> flavoring = flavoringRepository.findById(id);

        if (flavoring.isPresent()){
            Flavoring flavoring1 = flavoring.get();
            return  new FlavoringDTO(flavoring1);
        }
        else {
            throw new ResourceNotFoundException("This Id flavoring not exist");
        }
    }
    @PostMapping("/flavorings/create")
    public ResponseEntity<Object> createNewCream(@RequestParam String name, @RequestParam String description,
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

        Flavoring flavoring = new Flavoring(name,description,content,price,stock,presentation,image);

        return new ResponseEntity<>("Flavoring created", HttpStatus.CREATED);
    }

    @PatchMapping("/flavorings/update")
    public ResponseEntity<Object> updateFlavoring(@RequestParam Long id, @RequestParam Double price){


        if (price.isNaN()){
            return new ResponseEntity<>("complete the Price", HttpStatus.FORBIDDEN);
        }
        if (price <= 0){
            return new ResponseEntity<>("The price cant be 0 or less", HttpStatus.FORBIDDEN);
        }
        if (!flavoringRepository.existsById(id)){
            return new ResponseEntity<>("The cream Id dosent Exist", HttpStatus.FORBIDDEN);
        }
        Optional<Flavoring> flavoring =  flavoringRepository.findById(id);


        Flavoring flavoring1 = flavoring.get();

        flavoring1.setPrice(price);

        flavoringRepository.save(flavoring1);

        return new ResponseEntity<>("Price update!", HttpStatus.FORBIDDEN);

    }

    @PatchMapping("/flavorings/delete")
    public ResponseEntity<Object> deleteFlavoring(@RequestParam Long id){
        if (!flavoringRepository.existsById(id)){
            return new ResponseEntity<>("The Id dosent exist", HttpStatus.FORBIDDEN);
        }
        flavoringRepository.deleteById(id);
        return new ResponseEntity<>("flavoring removed!", HttpStatus.OK);
    }
}
