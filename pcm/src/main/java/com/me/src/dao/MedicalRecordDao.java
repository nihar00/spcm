package com.me.src.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.me.src.pojo.MedicalRecord;

@Component
public class MedicalRecordDao extends MappedModelDao<MedicalRecord> {
	@Override
	public Class<MedicalRecord> getActualClass() {
		// TODO Auto-generated method stub
		return MedicalRecord.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<MedicalRecord> listMedicalRecord(long patientId,int recordType) {
		// TODO Auto-generated method stub

		Query query = (Query)sessionFactory.getCurrentSession().createQuery("from MedicalRecord medicalrecord where medicalrecord.patient.id=:patientId and medicalrecord.recordType=:recordType");
		query.setParameter("patientId",patientId);	
		query.setParameter("recordType", recordType);
		return query.list();	
	}
}
