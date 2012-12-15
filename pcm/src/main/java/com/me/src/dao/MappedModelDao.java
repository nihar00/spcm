package com.me.src.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.me.src.pojo.MappedModel;

@Repository
@Transactional
public abstract class MappedModelDao<T extends MappedModel> {
	@Autowired
	protected SessionFactory sessionFactory;

	public abstract Class<T> getActualClass();

	@SuppressWarnings("unchecked")
	public T findById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.get(getActualClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(getActualClass());
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	public void saveOrUpdate(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}

	public void delete(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
	}
}
