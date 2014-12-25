package pl.lodz.uni.math.service;

import java.util.List;

import pl.lodz.uni.math.dto.Customer;

public interface CustomerService {
	List<Customer> getCustomers();

    boolean deleteCustomer(Long id);

    boolean saveCustomer(Customer customer);
}
