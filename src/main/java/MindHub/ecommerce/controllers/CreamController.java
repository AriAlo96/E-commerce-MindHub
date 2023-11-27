package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.CreamDTO;

import MindHub.ecommerce.models.Cream;
import MindHub.ecommerce.models.Type;
import MindHub.ecommerce.repositories.CreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.Position;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/velvet")
public class CreamController {
    @Autowired
    private CreamRepository creamRepository;

    @GetMapping("/creams")
    public List<CreamDTO> getAllCreams(){
        List<Cream> creams = creamRepository.findAll();
        return  creams.stream()
                .map(CreamDTO::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/creams/{id}")
    public CreamDTO getCreamId(@PathVariable Long id){
         Optional<Cream> cream =  creamRepository.findById(id);

         if (cream.isPresent()){
             Cream cream1 = cream.get();
             return  new CreamDTO(cream1);
         }
         else {
             throw new ResourceNotFoundException("This Id Cream not exist");
         }
    }

    @PostMapping("/creams/create")
    public ResponseEntity<Object> createNewCream(@RequestParam String name, @RequestParam String description,
       @RequestParam Double price, @RequestParam Integer content, @RequestParam Integer stock,
       @RequestParam Type type, @RequestParam String image){

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

        Cream cream = new Cream(name,description,price,content,stock,type,image);
        return new ResponseEntity<>("cream created!", HttpStatus.CREATED);
    }

    @PatchMapping("/creams/update")
    public ResponseEntity<Object> updateCream(@RequestParam Long id, @RequestParam Double price){


        if (price.isNaN()){
            return new ResponseEntity<>("complete the Price", HttpStatus.FORBIDDEN);
        }
        if (price <= 0){
            return new ResponseEntity<>("The price cant be 0 or less", HttpStatus.FORBIDDEN);
        }
        if (!creamRepository.existsById(id)){
            return new ResponseEntity<>("The cream Id dosent Exist", HttpStatus.FORBIDDEN);
        }
        Optional<Cream> cream =  creamRepository.findById(id);


        Cream cream1 = cream.get();

        cream1.setPrice(price);

        creamRepository.save(cream1);

        return new ResponseEntity<>("Price update!", HttpStatus.FORBIDDEN);

    }

    @PatchMapping("/creams/delete")
    public ResponseEntity<Object> deleteCream(@RequestParam Long id){
        if (!creamRepository.existsById(id)){
            return new ResponseEntity<>("The Id dosent exist", HttpStatus.FORBIDDEN);
        }
        creamRepository.deleteById(id);
        return new ResponseEntity<>("Cream removed!", HttpStatus.OK);
    }



}