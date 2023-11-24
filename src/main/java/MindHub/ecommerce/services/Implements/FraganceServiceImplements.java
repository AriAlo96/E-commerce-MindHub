package MindHub.ecommerce.services.Implements;

import MindHub.ecommerce.models.Fragance;
import MindHub.ecommerce.repositories.FraganceRepository;
import MindHub.ecommerce.services.FraganceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraganceServiceImplements implements FraganceService {
    @Autowired
    private FraganceRepository fraganceRepository;
    @Override
    public void saveNewFragance(Fragance fragance){
        fraganceRepository.save(fragance);
    }

    @Override
    public List<Fragance> findAllFragances() {
        return fraganceRepository.findAll();
    }
}
