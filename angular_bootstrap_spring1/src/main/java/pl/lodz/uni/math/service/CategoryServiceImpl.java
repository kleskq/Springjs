package pl.lodz.uni.math.service;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lodz.uni.math.engine.CategoryEngine;
import pl.lodz.uni.math.persistence.dao.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public Collection<String> getCategories() {
		Collection<CategoryEngine> categories = categoryDao.getCategories();
		Collection<String> resoult = new ArrayList<>();
		for (CategoryEngine categoryEngine : categories) {
			resoult.add(categoryEngine.getCategoryName());
		}
		return resoult;
	}

}
