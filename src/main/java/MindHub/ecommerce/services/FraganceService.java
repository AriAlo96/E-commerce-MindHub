package MindHub.ecommerce.services;

import MindHub.ecommerce.models.Fragance;

import java.util.List;

public interface FraganceService {
    void saveFragance(Fragance fragance);
    List<Fragance> findAllFragances();
    Fragance findFraganceById (Long id);
}
