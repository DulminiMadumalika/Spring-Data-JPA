package pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pos.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

//    Order getTopOrdersByOrderByIdDesc() throws Exception;

    @Query(value = "SELECT o.id FROM `Order` o ORDER BY o.id DESC LIMIT 1", nativeQuery = true)
    int getLastOrderId() ;

}
