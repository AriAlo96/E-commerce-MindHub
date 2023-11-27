package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.ClientDTO;
import MindHub.ecommerce.dtos.FraganceDTO;
import MindHub.ecommerce.models.Fragance;
import MindHub.ecommerce.models.Gender;
import MindHub.ecommerce.models.OlfactoryFamily;
import MindHub.ecommerce.models.Presentation;
import MindHub.ecommerce.services.FraganceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/velvet")
public class FraganceController {
    @Autowired
    private FraganceService fraganceService;

    @GetMapping("/fragances")
    public List<FraganceDTO> getAllFragances() {
        List<FraganceDTO> fragances = fraganceService.findAllFragances().stream().map(fragance -> new FraganceDTO(fragance)).collect(
                Collectors.toList());
        return fragances;
    }

    @PostMapping("/newFragance")
    public ResponseEntity<String> newFragance(@RequestParam String name, @RequestParam String description,
                                              @RequestParam Gender gender,
                                                  @RequestParam OlfactoryFamily olfactoryFamily, @RequestParam Double price,
                                              @RequestParam Presentation presentation, @RequestParam Integer content,
                                              @RequestParam Integer stock, @RequestParam("file") MultipartFile image)
    {
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
        if (image.isEmpty()) {
            return new ResponseEntity<>("The fragrance image is required.", HttpStatus.BAD_REQUEST);
        }
        try {
            byte[] imageBytes = image.getBytes(); // Obtener los bytes de la imagen
            Fragance newFragance = new Fragance(name, description, gender, olfactoryFamily, "", price, presentation, content, stock);
            fraganceService.saveNewFragance(newFragance);
            return new ResponseEntity<>("Fragancia cargada exitosamente", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al cargar la imagen de la fragancia.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
