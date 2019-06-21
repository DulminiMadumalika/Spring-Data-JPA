package pos.service.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.dto.ItemDTO;
import pos.dto.OrderDTO;
import pos.dto.OrderDetailDTO;
import pos.dto.OrderNewDTO;
import pos.entity.*;
import pos.repository.*;
import pos.service.custom.OrderService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private QueryRepository queryRepository;

    public void placeOrder(OrderDTO order) {

        Customer customer = customerRepository.getOne(order.getCustomerId());

        orderRepository.save(new Order(order.getOrderId(), order.getOrderDate(), customer));

        for (OrderDetailDTO dto : order.getOrderDetails()) {

            OrderDetail save = orderDetailRepository.save(new OrderDetail(dto.getOrderId(), dto.getItemCode(), dto.getQty(), dto.getUnitPrice()));
            Item item = itemRepository.getOne(dto.getItemCode());

            int qty = item.getQtyOnHand() - dto.getQty();

            item.setQtyOnHand(qty);
        }

    }


    public int generateOrderId() {
        try {
            return orderRepository.getLastOrderId() + 1;
        } catch (NullPointerException e) {
            return 1;
        }
    }

    @Override
    public boolean isExistsOrder(int oid) {
        return orderRepository.existsById(oid);
    }

    @Override
    public List<OrderNewDTO> getAllOrders() {

        List<CustomEntity> allOrders = queryRepository.getAllOrders();
        List<OrderNewDTO> orderNewDTOS = new ArrayList<>();

        for (int i=0; i<allOrders.size();i++){

            orderNewDTOS.add(new OrderNewDTO(allOrders.get(i).getOrderId(),allOrders.get(i).getCustomerId(),allOrders.get(i).getCustomerName(),allOrders.get(i).getOrderDate(),
                    allOrders.get(i).getOrderDetailList().stream().map(orderDetail -> new OrderDetailDTO(orderDetail.getOrderDetailPK().getOrderId(), orderDetail.getOrderDetailPK().getItemCode(), orderDetail.getQty(), orderDetail.getUnitPrice())).collect(Collectors.toList())));
        }

        return orderNewDTOS;

//       List<OrderDTO> collect = orderRepository.findAll().stream().map(order -> new OrderDTO(order.getId(), order.getDate(),
//                order.getCustomer().getId(),
//                order.getOrderDetails().stream().map(orderDetail -> new OrderDetailDTO(orderDetail.getOrder().getId(), orderDetail.getItem().getCode(), orderDetail.getQty(), orderDetail.getUnitPrice())).collect(Collectors.toList()))).collect(Collectors.toList());
//
//        return collect;
    }

    @Override
    public List<ItemDTO> getOrder(int id) {
//        Order order = orderRepository.getOne(id);

        List<Item> items = queryRepository.getOrder(id);
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }

        return itemDTOS;

//        OrderDTO orderDTO = new OrderDTO(order.getId(),order.getDate(),
//                order.getCustomer().getId(),
//                order.getOrderDetails().stream()
//                        .map(orderDetail -> new OrderDetailDTO(orderDetail.getOrder().getId(),orderDetail.getItem().getCode(),orderDetail.getQty(),orderDetail.getUnitPrice())).collect(Collectors.toList()));
//
//        return orderDTO;
    }

}
