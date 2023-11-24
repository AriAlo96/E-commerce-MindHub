package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.CreamDTO;

import MindHub.ecommerce.models.Cream;
import MindHub.ecommerce.repositories.CreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
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

}
