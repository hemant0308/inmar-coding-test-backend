package com.inmar.api.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.inmar.api.model.Department;
import com.inmar.api.model.Location;

@Component
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

	@Override
	public List<Department> getDepartments(Location location) {
		String queryString = "FROM Department WHERE location = :location";
		Query<Department> query = getSession().createQuery(queryString, Department.class);
		query.setParameter("location", location);
		return query.getResultList();
	}

	@Override
	public Department findByDepartment(String departmentName) {
		String queryString = "FROM Department WHERE department = :department";
		Query<Department> query = getSession().createQuery(queryString, Department.class);
		query.setParameter("department", departmentName);
		return query.getSingleResult();
	}

}
