package com.me.src.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.me.src.pojo.Patient;
import com.me.src.pojo.Person;

@Component
public class PatientDao extends MappedModelDao<Patient> {

	@Override
	public Class<Patient> getActualClass() {
		// TODO Auto-generated method stub
		return Patient.class;
	}
	
	//nihar changes
	@SuppressWarnings("unchecked")
	public List<Patient> listPatient(long hospitalId) {
		// TODO Auto-generated method stub
	
		Query query = sessionFactory.getCurrentSession().createQuery("from Person person where person.hospital.id=:hospitalId");
		query.setParameter("hospitalId",hospitalId);	
		List<Patient> resultList = new ArrayList<Patient>();
		List<Person> personList=query.list();
		List<Patient> list = findAll();
		for (Person p : personList) {
			for (Patient patient : list) {
				if(patient.getPerson().getId()==p.getId())
				{
					resultList.add(patient);
				}
			}
			
		}
		
		return resultList;
		/*List<Patient> resultList = new ArrayList<Patient>();
		for(Patient p : list) {
			if(p.getPerson().getHospital().getId() == hospitalId) {
				resultList.add(p);
			}
		}
		
		return resultList;*/
	}
	
	
	//nihar changes
}
