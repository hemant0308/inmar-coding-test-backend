package com.inmar.api.dao;

import java.util.List;

import com.inmar.api.model.Category;
import com.inmar.api.model.Department;

public interface CategoryDao extends BaseDao<Category> {

	List<Category> getCategories(Department department);

	Category findByCategory(String categoryName);

}
