package com.me.src.dao;

import org.springframework.stereotype.Component;

import com.me.src.pojo.Consent;

@Component
public class ConsentDao extends MappedModelDao<Consent> {

	@Override
	public Class<Consent> getActualClass() {
		// TODO Auto-generated method stub
		return Consent.class;
	}
}
