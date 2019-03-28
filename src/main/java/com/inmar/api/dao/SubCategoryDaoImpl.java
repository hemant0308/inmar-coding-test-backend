package com.inmar.api.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.inmar.api.model.Category;
import com.inmar.api.model.SubCategory;

@Component
public class SubCategoryDaoImpl extends BaseDaoImpl<SubCategory> implements SubCategoryDao {

	@Override
	public List<SubCategory> getSubCategories(Category category) {
		String queryString = "FROM SubCategory WHERE category = :category";
		Query<SubCategory> query = getSession().createQuery(queryString, SubCategory.class);
		query.setParameter("category", category);
		return query.getResultList();
	}

	@Override
	public SubCategory findBySubCategory(String subCategoryName) {
		String queryString = "FROM SubCategory WHERE subCategory = :subCategory";
		Query<SubCategory> query = getSession().createQuery(queryString, SubCategory.class);
		query.setParameter("subCategory", subCategoryName);
		return query.getSingleResult();
	}

}
