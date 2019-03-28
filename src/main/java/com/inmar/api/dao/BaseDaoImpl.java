package com.inmar.api.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inmar.api.model.BaseModel;

@Component
public abstract class BaseDaoImpl<E extends BaseModel> implements BaseDao<E> {

	private Class<E> clazz;

	public BaseDaoImpl() {
		this.clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public E findById(final Serializable id) {
		return (E) getSession().get(this.clazz, id);
	}

	@Override
	public Serializable save(E entity, int userId) {
		entity.setAddedOn(new Date());
		entity.setAddedBy(userId);
		return save(entity);
	}

	@Override
	public Serializable save(E entity) {
		return getSession().save(entity);
	}

	@Override
	public void saveOrUpdate(E entity, int userId) {
		if (entity.getAddedOn() == null) {
			entity.setAddedOn(new Date());
			entity.setAddedBy(userId);
		}
		entity.setUpdatedBy(userId);
		entity.setUpdatedOn(new Date());
		saveOrUpdate(entity);
	}

	@Override
	public List<E> findAll() {
		return getSession().createQuery("FROM " + this.clazz.getSimpleName()).getResultList();
	}

	@Override
	public void saveOrUpdate(E entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(E entity) {
		getSession().delete(entity);
	}

	@Override
	public void clear() {
		getSession().clear();

	}

	@Override
	public void flush() {
		getSession().flush();

	}

}
