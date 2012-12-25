package com.me.src.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.me.src.pojo.Consent;

@Component
public class ConsentDao extends MappedModelDao<Consent> {

	@Override
	public Class<Consent> getActualClass() {
		// TODO Auto-generated method stub
		return Consent.class;
	}
	

	@SuppressWarnings("unchecked")
	public Consent getConsentFromPatientId(long patientId){

		Query query = sessionFactory.getCurrentSession().createQuery("from Consent consent where consent.patient.id=:patientId");
		query.setParameter("patientId",patientId);
		List<Consent> consents = query.list();
		if(consents!=null)
		{
			return consents.get(0);
		}
		else{
			return null;
		}
	}
}
