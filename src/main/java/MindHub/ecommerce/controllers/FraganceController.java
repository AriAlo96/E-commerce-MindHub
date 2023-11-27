package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.FraganceDTO;
import MindHub.ecommerce.models.Fragance;
import MindHub.ecommerce.models.Gender;
import MindHub.ecommerce.models.OlfactoryFamily;
import MindHub.ecommerce.models.Presentation;
import MindHub.ecommerce.services.FraganceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/velvet")
public class FraganceController {
    @Autowired
    private FraganceService fraganceService;

    @GetMapping("/fragances")
    public List<FraganceDTO> getAllFragances() {
        List<FraganceDTO> fragances = fraganceService.findAllFragances().stream().map(
                fragance -> new FraganceDTO(fragance)).collect(Collectors.toList());
        return fragances;
    }

    @GetMapping("/fragances/{id}")
    public FraganceDTO getFragance(@PathVariable Long id) {
        FraganceDTO fragance = new FraganceDTO(fraganceService.findFraganceById(id));
        return fragance;
    }

    @PostMapping("/fragances/create")
    public ResponseEntity<String> createNewFragance(@RequestParam String name, @RequestParam String description,
                                                    @RequestParam Gender gender,
                                                    @RequestParam OlfactoryFamily olfactoryFamily,
                                                    @RequestParam Double price, @RequestParam Presentation presentation,
                                                    @RequestParam Integer content, @RequestParam Integer stock,
                                                    @RequestParam String image)
    {
        List<String> fragancesName = fraganceService.findAllFragances().stream().map(
                fragance -> fragance.getName()).collect(Collectors.toList());

        if (name.isEmpty()) {
            return new ResponseEntity<>("The fragrance name is required.", HttpStatus.BAD_REQUEST);
        }
        if (description.isEmpty()) {
            return new ResponseEntity<>("The fragrance description is required.", HttpStatus.BAD_REQUEST);
        }
        if (price == null) {
            return new ResponseEntity<>("The fragrance price is required.", HttpStatus.BAD_REQUEST);
        }
        if (content == null) {
            return new ResponseEntity<>("The fragrance content is required.", HttpStatus.BAD_REQUEST);
        }
        if (stock == null) {
            return new ResponseEntity<>("The fragrance stock is required.", HttpStatus.BAD_REQUEST);
        }
        if (fragancesName.contains(name)) {
            return new ResponseEntity<>("The fragrance name already use", HttpStatus.BAD_REQUEST);
        }
        if (image.isEmpty()) {
            return new ResponseEntity<>("The fragrance image is required.", HttpStatus.BAD_REQUEST);
        } else {
            Fragance newFragance = new Fragance(name, description, gender, olfactoryFamily, image, price, presentation,
                    content, stock, true);
            fraganceService.saveFragance(newFragance);
            return new ResponseEntity<>("Fragance create successfully", HttpStatus.OK);
        }
    }

    @PatchMapping("/fragances/update")
    public ResponseEntity<String> updateFragance(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String description,
                                                 @RequestParam(required = false) Gender gender,
                                                 @RequestParam(required = false) OlfactoryFamily olfactoryFamily,
                                                 @RequestParam(required = false) Double price,
                                                 @RequestParam(required = false) Presentation presentation,
                                                 @RequestParam(required = false) Integer content,
                                                 @RequestParam(required = false) Integer stock,
                                                 @RequestParam(required = false) String image, @RequestParam Long id)
    {
        Fragance fragance = fraganceService.findFraganceById(id);
        List<String> fragancesName = fraganceService.findAllFragances().stream().map(
                fraganceName -> fraganceName.getName()).collect(Collectors.toList());
        if (fragancesName.contains(name)) {
            return new ResponseEntity<>("The fragrance name already use", HttpStatus.BAD_REQUEST);
        } else {
            if(name != null){
                fragance.setName(name);
            }
            if(description != null){
                fragance.setDescription(description);
            }
            if(gender != null){
                fragance.setGender(gender);
            }
            if(olfactoryFamily != null){
                fragance.setOlfactoryFamily(olfactoryFamily);
            }
            if(price != null){
                fragance.setPrice(price);
            }
            if(presentation != null){
                fragance.setPresentation(presentation);
            }
            if(content != null){
                fragance.setContent(content);
            }
            if(stock != null){
                fragance.setStock(stock);
            }
            if(image != null){
                fragance.setImage(image);
            }
            fraganceService.saveFragance(fragance);
            return new ResponseEntity<>("Fragance update successfully", HttpStatus.OK);
        }
    }

    @PatchMapping("fragances/update")
    public ResponseEntity<Object> updateFragance(Authentication authentication, @RequestParam Long id) {
        return new ResponseEntity<>("The fragrance has been modified correctly.", HttpStatus.CREATED);
    }

    @PatchMapping("fragances/delete")
    public ResponseEntity<Object> deleteFragance(Authentication authentication, @RequestParam Long id) {
        Fragance fragance = fraganceService.findFraganceById(id);
        if (fragance == null) {
            return new ResponseEntity<>("The fragance doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (fragance.getStock() != 0) {
            return new ResponseEntity<>("You can not delete an fragance with a stock greater than zero",
                    HttpStatus.FORBIDDEN);
        }
        if (!fragance.getActive()) {
            return new ResponseEntity<>("The fragance is inactive", HttpStatus.FORBIDDEN);
        } else {
            fragance.setActive(false);
            fraganceService.saveFragance(fragance);
            return new ResponseEntity<>("Fragance delete successfully", HttpStatus.CREATED);
        }
    }
}
