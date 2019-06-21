package pos.service.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.dto.CustomerDTO;
import pos.entity.Customer;
import pos.repository.CustomerRepository;
import pos.service.custom.CustomerService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

//    public CustomerServiceImpl(){
//        String repository = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }

    @Override
    public CustomerDTO getCustomerById(String id) {

        Customer customer = customerRepository.getOne(id);
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
        return customerDTO;

    }

    @Override
    public boolean isCustomerExists(String id) {
        return customerRepository.existsById(id);
    }

    public List<CustomerDTO> getAllCustomers() {

        List<CustomerDTO> customers = customerRepository.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress())).collect(Collectors.toList());
        return customers;

    }

    public String saveCustomer(CustomerDTO dto) {

        Customer save = customerRepository.save(new Customer(dto.getId(), dto.getName(), dto.getAddress()));
        return save.getId();

    }

    public void updateCustomer(CustomerDTO dto) {

        customerRepository.save(new Customer(dto.getId(), dto.getName(), dto.getAddress()));

    }

    public void removeCustomer(String id) {

        customerRepository.deleteById(id);

    }

}
