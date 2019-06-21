package pos.service.custom;

import pos.dto.CustomerDTO;
import pos.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {

    public List<CustomerDTO> getAllCustomers() ;

    public String saveCustomer(CustomerDTO dto) ;

    public void updateCustomer(CustomerDTO dto);

    public void removeCustomer(String id);

    public CustomerDTO getCustomerById(String id);

    public boolean isCustomerExists(String id);

}
