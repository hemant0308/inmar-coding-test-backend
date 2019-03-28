package com.inmar.api.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.inmar.api.model.Location;

@Component
public class LocationDaoImpl extends BaseDaoImpl<Location> implements LocationDao {

	@Override
	public Location findByLocation(String locationName) {
		String queryString = "FROM Location Where locationi = :location";
		Query<Location> query = getSession().createQuery(queryString, Location.class);
		query.setParameter("location", locationName);
		return query.getSingleResult();
	}

}
