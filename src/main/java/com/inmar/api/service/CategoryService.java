package com.inmar.api.service;

import java.util.List;
import java.util.Map;

import com.inmar.api.model.Category;

public interface CategoryService {

	List<Category> getCategories(int locationId, int departmentId);

	Category saveCategory(int locationId, int departmentId, Map<String, Object> payload, int userId);

	List<Category> getAllCategories();

	Category deleteCategory(int locationId, int departmentId, int categoryId) throws Exception;

}
