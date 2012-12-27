package com.me.src.dao;

import org.springframework.stereotype.Component;

import com.me.src.pojo.Person;

@Component
public class PersonDao extends MappedModelDao<Person> {

	@Override	
	public Class<Person> getActualClass() {
		// TODO Auto-generated method stub
		return Person.class;
	}
}
