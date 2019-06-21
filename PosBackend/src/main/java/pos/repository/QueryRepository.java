package pos.repository;


import pos.entity.CustomEntity;
import pos.entity.Item;

import java.util.List;

public interface QueryRepository {

    List<CustomEntity> getOrdersTotal();

    List<CustomEntity> getAllOrders();

    List<Item> getOrder(int id);

}
