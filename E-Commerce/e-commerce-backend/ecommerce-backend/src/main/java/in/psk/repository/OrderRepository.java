package in.psk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import in.psk.entitys.Order;

@RepositoryRestResource
@CrossOrigin("http://localhost:4200")
public interface OrderRepository extends JpaRepository<Order,Long> {

}
