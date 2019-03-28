package com.inmar.api.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.inmar.api.model.Category;
import com.inmar.api.model.Department;

@Component
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

	@Override
	public List<Category> getCategories(Department department) {
		String queryString = "FROM Category WHERE department = :department";
		Query<Category> query = getSession().createQuery(queryString, Category.class);
		query.setParameter("department", department);
		return query.getResultList();
	}

	@Override
	public Category findByCategory(String categoryName) {
		String queryString = "FROM Category WHERE category = :category";
		Query<Category> query = getSession().createQuery(queryString, Category.class);
		query.setParameter("category", categoryName);
		return query.getSingleResult();
	}
}
