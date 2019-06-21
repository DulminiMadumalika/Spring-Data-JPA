package pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {


}
