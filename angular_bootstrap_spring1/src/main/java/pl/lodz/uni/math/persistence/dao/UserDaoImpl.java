package pl.lodz.uni.math.persistence.dao;

import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.lodz.uni.math.engine.UserEngine;
import pl.lodz.uni.math.persistence.base.BaseDao;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao{
	private static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Transactional()
	@Override
	public UserEngine getUserByName(String name) {
	        EntityManager em = emf.createEntityManager();
	        Query query = em.createQuery("SELECT u FROM UserEngine u WHERE u.userName = :name").setParameter("name", name).setMaxResults(1);
	        
	        List<UserEngine> users = (List<UserEngine>)loadData(UserEngine.class, query);

	        if (users == null || users.isEmpty()) {
	            log.info("No User found");
	            return null;
	        }

	        return users.get(0);
	}

}
