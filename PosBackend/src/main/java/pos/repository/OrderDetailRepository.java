package pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.entity.OrderDetail;
import pos.entity.OrderDetailPK;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {

}
