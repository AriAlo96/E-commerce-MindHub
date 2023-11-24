package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.FlavoringDTO;
import MindHub.ecommerce.models.Flavoring;
import MindHub.ecommerce.repositories.FlavoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FlavoringConrtroller {
    @Autowired
    private FlavoringRepository flavoringRepository;

    @GetMapping("/flavorings")
    public List<FlavoringDTO> getAllFlavoring(){
        List<Flavoring> flavorings = flavoringRepository.findAll();
        return  flavorings.stream()
                .map(FlavoringDTO::new)
                .collect(Collectors.toList());
    }
}
