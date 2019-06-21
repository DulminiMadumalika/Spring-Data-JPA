package pos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.dto.ItemDTO;
import pos.dto.OrderDTO;
import pos.dto.OrderNewDTO;
import pos.service.custom.OrderService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> placeOrder(@RequestBody OrderDTO orderDTO){

        if(orderDTO.getCustomerId().isEmpty() || orderDTO.getOrderDetails().size()<=0){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }else{

            System.out.println(orderDTO);

            if(orderService.isExistsOrder(orderDTO.getOrderId())){
                throw new RuntimeException("Order is already exists");
            }else{
                orderService.placeOrder(orderDTO);
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            }
        }
    }

    @GetMapping
    public List<OrderNewDTO> getAllOrders(){
        List<OrderNewDTO> allOrders = orderService.getAllOrders();
        return allOrders;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<ItemDTO>> getOrder(@PathVariable int id){
        if(!orderService.isExistsOrder(id)){
            return new ResponseEntity<List<ItemDTO>>(HttpStatus.BAD_REQUEST);
        }else{
            List<ItemDTO> order = orderService.getOrder(id);
            return new ResponseEntity<List<ItemDTO>>(order,HttpStatus.OK);
        }
    }
}

