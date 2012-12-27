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

	//nihar 4 changes

	@SuppressWarnings("unchecked")
	public Patient getPatientFromPersonId(long personId){

		Query query = sessionFactory.getCurrentSession().createQuery("from Patient patient where patient.person.id=:personId");
		query.setParameter("personId",personId);
		List<Patient> patient = query.list();
		if(patient!=null)
		{
			return patient.get(0);
		}
		else{
			return null;
		}
	}

	//nihar 4 changes


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
	}
}
