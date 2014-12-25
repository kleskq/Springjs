package pl.lodz.uni.math.dao;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.lodz.uni.math.dto.Customer;
import pl.lodz.uni.math.persistence.dao.CustomerDao;
import pl.lodz.uni.math.persistence.dao.exception.UpdateDeleteException;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:WEB-INF/spring/test-application-context-config.xml",
		"classpath:WEB-INF/spring/test-security-context-config.xml",
        "classpath:WEB-INF/spring/test-persistence-context-config.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoUnitTest {

	@Autowired
	private CustomerDao customerDao;

	@Test
	public void getCustomersSuccess() {
		List<Customer> customers = customerDao.getCustomers();

		Assert.assertNotNull(customers);
		Assert.assertEquals(4, customers.size());
	}

    @Test
    public void saveCustomerSuccess() throws UpdateDeleteException {
        boolean saved = customerDao.saveCustomer(new Customer(null, "Robert", "Leggett"));

        Assert.assertTrue(saved);
    }

    @Test
    public void saveCustomerFailure() throws UpdateDeleteException {
        boolean saved = customerDao.saveCustomer(new Customer());

        Assert.assertFalse(saved);
    }

    @Test
    public void removeCustomerSuccess() {
        boolean deleted = customerDao.deleteCustomer(1L);

        Assert.assertTrue(deleted);
    }

    @Test
    public void removeCustomerFailure() {
        boolean deleted = customerDao.deleteCustomer(21L);

        Assert.assertFalse(deleted);
    }
}
