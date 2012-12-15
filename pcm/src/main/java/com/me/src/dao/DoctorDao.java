package com.me.src.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.me.src.pojo.Doctor;

@Component
public class DoctorDao extends MappedModelDao<Doctor> {
	@Override
	public Class<Doctor> getActualClass() {
		// TODO Auto-generated method stub
		return Doctor.class;
	}

	@SuppressWarnings("unchecked")
	public Doctor findByPersonId(long personId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Doctor d where d.person=:personId");
		query.setParameter("personId",personId);
		List<Doctor> list = query.list();

		if(list.size() > 0) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
}
