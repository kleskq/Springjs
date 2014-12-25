package pl.lodz.uni.math.persistence.dao;

import java.util.Collection;

import pl.lodz.uni.math.engine.CategoryEngine;

public interface CategoryDao {
	public CategoryEngine getCategoryById(int id);

	public CategoryEngine getCategoryByCategoryName(String categoryName);
	
	public Collection<CategoryEngine> getCategories();
}
