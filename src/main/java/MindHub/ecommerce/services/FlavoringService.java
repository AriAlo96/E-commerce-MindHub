package MindHub.ecommerce.services;

import MindHub.ecommerce.models.Flavoring;

import java.util.List;

public interface FlavoringService {
    List<Flavoring> findAllFlavorings ();
    Flavoring findFlavoringByID(Long id);
    void saveFlavoring(Flavoring flavoring);
    void deleteFlavoringById(Long id);
    boolean existFlavoringById(Long id);
}
