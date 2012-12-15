package com.me.src.pojo;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Doctor extends MappedModel {
	@OneToOne
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
