package MindHub.ecommerce.repositories;

import MindHub.ecommerce.models.Cream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CreamRepository extends JpaRepository<Cream, Long> {
    List<Cream> findAll();
}
