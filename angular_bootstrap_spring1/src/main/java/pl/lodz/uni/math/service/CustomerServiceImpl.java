package pl.lodz.uni.math.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lodz.uni.math.dto.Customer;
import pl.lodz.uni.math.persistence.dao.CustomerDao;
import pl.lodz.uni.math.persistence.dao.exception.UpdateDeleteException;

import java.util.List;

@Service(value = "CustomerService")
public class CustomerServiceImpl implements CustomerService {

	private static Logger log = LoggerFactory
			.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> getCustomers() {
		return customerDao.getCustomers();
	}

	@Override
	public boolean deleteCustomer(Long id) {
		return customerDao.deleteCustomer(id);
	}

	@Override
	public boolean saveCustomer(Customer customer) {
			return customerDao.saveCustomer(customer);
	}
}
