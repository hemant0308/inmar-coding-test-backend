package com.inmar.api.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<E> {

	Serializable save(E entity);

	void saveOrUpdate(E entity, int userId);

	E findById(Serializable id);

	Serializable save(E entity, int userId);

	List<E> findAll();

	void saveOrUpdate(E entity);

	void delete(E entity);

	void clear();

	void flush();

}
