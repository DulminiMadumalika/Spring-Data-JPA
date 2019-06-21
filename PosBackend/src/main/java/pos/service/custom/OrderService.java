package pos.service.custom;

import pos.dto.ItemDTO;
import pos.dto.OrderDTO;
import pos.dto.OrderNewDTO;
import pos.service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {

    public void placeOrder(OrderDTO order) ;

    public int generateOrderId() ;

    public boolean isExistsOrder(int oid);

    public List<OrderNewDTO> getAllOrders();

    public List<ItemDTO> getOrder(int id);

}
