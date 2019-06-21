package pos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.dto.CustomerDTO;
import pos.service.custom.CustomerService;

import java.util.List;

@CrossOrigin
@RequestMapping("api/v1/customers")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers(){

        return customerService.getAllCustomers();
    }

    @GetMapping(value ="/{id:C\\d{3}}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") String id){

        CustomerDTO customerDTO = null;

        if(customerService.isCustomerExists(id)){
            customerDTO = customerService.getCustomerById(id);
        }
        return new ResponseEntity<CustomerDTO>(customerDTO, (customerDTO != null)? HttpStatus.OK: HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDTO customer){

        if(customer.getId().isEmpty() || customer.getName().isEmpty() || customer.getAddress().isEmpty()){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }else{
            if(customerService.isCustomerExists(customer.getId())){
                throw  new RuntimeException("This customer is already exists");
            }else{
                String cusId = customerService.saveCustomer(customer);
                ResponseEntity<String> stringResponseEntity = new ResponseEntity<String>("\""+cusId+"\"", HttpStatus.CREATED);
                return stringResponseEntity;
            }
        }
    }

    @PutMapping(path = "/{id:C\\d{3}}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") String id,@RequestBody CustomerDTO customer){

        if(!customerService.isCustomerExists(id)){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        if(customer.getName().isEmpty() || customer.getAddress().isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }else{
            customer.setId(id);
            customerService.updateCustomer(customer);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path = "/{id:C\\d{3}}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") String id){

        if (!customerService.isCustomerExists(id)){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        customerService.removeCustomer(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
