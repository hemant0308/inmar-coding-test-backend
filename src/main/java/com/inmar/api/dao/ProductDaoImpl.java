package com.inmar.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
		Criteria criteria = getSession().createCriteria(Product.class);
		if (location != null) {
			criteria.add(Restrictions.eq("location", location));
		}
		if (department != null) {
			criteria.add(Restrictions.eq("department", department));
		}
		if (category != null) {
			criteria.add(Restrictions.eq("category", category));
		}
		if (subCategory != null) {
			criteria.add(Restrictions.eq("subCategory", subCategory));
		}
		return criteria.list();
	}

	@Override
	public List<Object[]> getMetaData() {
		String queryString = "SELECT location,department,category,subCategory FROM Location location JOIN location.departments department JOIN department.categories category JOIN category.subCategories subCategory";
		Query<Object[]> query = getSession().createQuery(queryString, Object[].class);
		return query.getResultList();
	}

}
