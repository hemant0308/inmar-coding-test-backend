package com.inmar.api.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.inmar.api.model.Category;
import com.inmar.api.model.Department;
import com.inmar.api.model.Location;
import com.inmar.api.model.Product;
import com.inmar.api.model.SubCategory;

@Component
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

	@Override
	public List<Product> getProducts(Location location, Department department, Category category,
			SubCategory subCategory) {
		String queryString = "FROM Product WHERE location = :location AND department = :department AND category = :category AND subCategory =:subCategory";
		Query<Product> query = getSession().createQuery(queryString, Product.class);
		query.setParameter("location", location);
		query.setParameter("department", department);
		query.setParameter("category", category);
		query.setParameter("subCategory", subCategory);
		return query.getResultList();
	}

}
