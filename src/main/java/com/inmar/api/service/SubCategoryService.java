package com.inmar.api.service;

import java.util.List;
import java.util.Map;

import com.inmar.api.model.SubCategory;

public interface SubCategoryService {

	List<SubCategory> getSubCategories(int locationId, int departmentId, int categoryId);

	SubCategory saveSubCategory(int locationId, int departmentId, int categoryId, Map<String, Object> payload);

}
