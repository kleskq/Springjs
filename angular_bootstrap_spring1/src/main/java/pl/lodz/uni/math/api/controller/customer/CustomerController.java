package pl.lodz.uni.math.api.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import pl.lodz.uni.math.dto.Customer;
import pl.lodz.uni.math.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/customers/retrieve", method = RequestMethod.GET, produces = "application/json")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public boolean deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public boolean saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }
}
