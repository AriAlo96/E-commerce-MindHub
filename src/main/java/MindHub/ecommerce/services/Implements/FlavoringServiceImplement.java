package MindHub.ecommerce.services.Implements;

import MindHub.ecommerce.models.Flavoring;
import MindHub.ecommerce.repositories.FlavoringRepository;
import MindHub.ecommerce.services.FlavoringService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FlavoringServiceImplement implements FlavoringService {
    @Autowired
    private FlavoringRepository flavoringRepository;
    @Override
    public List<Flavoring> findAllFlavorings() {
        return flavoringRepository.findAll();
    }

    @Override
    public Flavoring findFlavoringByID(Long id) {
        return flavoringRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void saveFlavoring(Flavoring flavoring) {
        flavoringRepository.save(flavoring);
    }

    @Override
    public void deleteFlavoringById(Long id) {
        flavoringRepository.deleteById(id);
    }

    @Override
    public boolean existFlavoringById(Long id) {
        return flavoringRepository.existsById(id);
    }
}
