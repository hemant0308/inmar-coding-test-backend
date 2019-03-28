package com.inmar.api.dao;

import java.util.List;

import com.inmar.api.model.Category;
import com.inmar.api.model.SubCategory;

public interface SubCategoryDao extends BaseDao<SubCategory> {

	List<SubCategory> getSubCategories(Category category);

	SubCategory findBySubCategory(String subCategoryName);

}
