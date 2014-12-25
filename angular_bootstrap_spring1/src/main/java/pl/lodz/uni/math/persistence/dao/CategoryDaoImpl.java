package pl.lodz.uni.math.persistence.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.lodz.uni.math.engine.CategoryEngine;
import pl.lodz.uni.math.persistence.base.BaseDao;

@Repository
public class CategoryDaoImpl extends BaseDao implements CategoryDao {

	private static Logger log = LoggerFactory.getLogger(CategoryDaoImpl.class);

	@Override
	@Transactional
	public CategoryEngine getCategoryById(int id) {
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createQuery(
						"SELECT c FROM CategoryEngine c WHERE c.categoryId = :id")
				.setParameter("id", id).setMaxResults(1);
		List<CategoryEngine> categories = (List<CategoryEngine>) loadData(
				CategoryEngine.class, query);

		if (categories == null || categories.isEmpty()) {
			log.info("No category found");
			return null;
		}
		return categories.get(0);
	}

	@Override
	@Transactional
	public CategoryEngine getCategoryByCategoryName(String categoryName) {
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createQuery(
						"SELECT c FROM CategoryEngine c WHERE c.categoryName = :categoryName")
				.setParameter("categoryName", categoryName).setMaxResults(1);
		List<CategoryEngine> categories = (List<CategoryEngine>) loadData(
				CategoryEngine.class, query);

		if (categories == null || categories.isEmpty()) {
			log.info("No category found");
			return null;
		}
		return categories.get(0);
	}

	@Override
	@Transactional
	public Collection<CategoryEngine> getCategories() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT c FROM CategoryEngine c");

		List<CategoryEngine> categories = (List<CategoryEngine>) loadData(
				CategoryEngine.class, query);

		if (categories == null || categories.isEmpty()) {
			log.info("No categories found");
			return new ArrayList<CategoryEngine>();
		}
		return categories;
	}

}
