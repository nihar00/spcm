package com.me.src.dao;

import org.springframework.stereotype.Component;

import com.me.src.pojo.Doctor;

@Component
public class DoctorDao extends MappedModelDao<Doctor> {
	@Override
	public Class<Doctor> getActualClass() {
		// TODO Auto-generated method stub
		return Doctor.class;
	}
}
