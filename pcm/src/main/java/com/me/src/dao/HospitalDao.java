package com.me.src.dao;

import org.springframework.stereotype.Component;

import com.me.src.pojo.Hospital;

@Component
public class HospitalDao extends MappedModelDao<Hospital> {
	public HospitalDao() {
		super();
		
	}

	@Override
	public Class<Hospital> getActualClass() {
		// TODO Auto-generated method stub
		return Hospital.class;
	}

}
