package com.me.src.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.me.src.pojo.ConsentRequest;

@Component
public class ConsentRequestDao extends MappedModelDao<ConsentRequest> {

	@Override
	public Class<ConsentRequest> getActualClass() {
		// TODO Auto-generated method stub
		return ConsentRequest.class;
	}
	
	//nihar changes
		@SuppressWarnings("unchecked")
		public List<ConsentRequest> listConsentRequest(int patientId) {
			// TODO Auto-generated method stub
			
			Query query = (Query) sessionFactory.getCurrentSession().createQuery("from ConsentRequest where patient_id=:patientId");
			query.setParameter("patientId", patientId);		
			return query.list();
		
		}
		//nihar changes
}
