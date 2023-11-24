package MindHub.ecommerce.repositories;

import MindHub.ecommerce.models.Fragance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FraganceRepository extends JpaRepository<Fragance, Long> {
}
