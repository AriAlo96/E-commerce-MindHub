package MindHub.ecommerce.services.Implements;

import MindHub.ecommerce.models.Fragance;
import MindHub.ecommerce.repositories.FraganceRepository;
import MindHub.ecommerce.services.FraganceService;
import org.springframework.beans.factory.annotation.Autowired;

public class FraganceServiceImplements implements FraganceService {
    @Autowired
    private FraganceRepository fraganceRepository;
    @Override
    public void saveNewFragance(Fragance fragance){
        fraganceRepository.save(fragance);
    }
}
