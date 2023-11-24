package MindHub.ecommerce.controllers;

import MindHub.ecommerce.models.Gender;
import MindHub.ecommerce.models.OlfactoryFamily;
import MindHub.ecommerce.models.Presentation;
import MindHub.ecommerce.services.FraganceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/velvet")
public class FraganceController {
    @Autowired
    private FraganceService fraganceService;

    @PostMapping("/newFragance")
    public ResponseEntity<String> newFragance(@RequestParam String name, @RequestParam String description,
                                              @RequestParam Gender gender,
                                              @RequestParam OlfactoryFamily olfactoryFamily, @RequestParam Double price,
                                              @RequestParam Presentation presentation, @RequestParam Integer content,
                                              @RequestParam Integer stock, @RequestParam MultipartFile image)
    {
//        if (name.isEmpty()) {
//            return new ResponseEntity<>("The fragrance name is required.", HttpStatus.BAD_REQUEST);
//        }
//        if (description.isEmpty()) {
//            return new ResponseEntity<>("The fragrance description is required.", HttpStatus.BAD_REQUEST);
//        }
//        if (gender.isEmpty()) {
//            return new ResponseEntity<>("The fragrance gender is required.", HttpStatus.BAD_REQUEST);
//        }
//        if (olfactoryFamily.isEmpty()) {
//            return new ResponseEntity<>("The fragrance olfactoryFamily is required.", HttpStatus.BAD_REQUEST);
//        }
//        if (price.isEmpty()) {
//            return new ResponseEntity<>("The fragrance price is required.", HttpStatus.BAD_REQUEST);
//        }
//        if (presentation.isEmpty()) {
//            return new ResponseEntity<>("The fragrance presentation is required.", HttpStatus.BAD_REQUEST);
//        }
//        if (content.isEmpty()) {
//            return new ResponseEntity<>("The fragrance content is required.", HttpStatus.BAD_REQUEST);
//        }
//        if (stock.isEmpty()) {
//            return new ResponseEntity<>("The fragrance stock is required.", HttpStatus.BAD_REQUEST);
//        }
//        if (image.isEmpty()) {
//            return new ResponseEntity<>("The fragrance image is required.", HttpStatus.BAD_REQUEST);
//        }
//        byte[] imageBytes = image.getBytes();
//        Fragance newFrgance = new Fragance(name, description, gender, olfactoryFamily, image,price, presentation, content, stock);

//        fraganceService.saveNewFragance(newFrgance);
        return new ResponseEntity<>("Fragancia cargada exitosamente", HttpStatus.OK);
    }
}
