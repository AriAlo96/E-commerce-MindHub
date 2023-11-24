package MindHub.ecommerce.services;

import MindHub.ecommerce.models.Fragance;

import java.util.List;

public interface FraganceService {
    void saveNewFragance(Fragance fragance);
    List<Fragance> findAllFragances();
}
