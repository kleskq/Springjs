package pl.lodz.uni.math.persistence.dao;

import java.util.List;

import pl.lodz.uni.math.dto.Customer;

public interface CustomerDao {
    List<Customer> getCustomers();

    boolean deleteCustomer(Long id);

    boolean saveCustomer(Customer customer);
}
