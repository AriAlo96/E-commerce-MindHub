package MindHub.ecommerce.repositories;


import MindHub.ecommerce.models.PurchaseFragance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface PurchaseFraganceRepository extends JpaRepository<PurchaseFragance, Long> {
}


