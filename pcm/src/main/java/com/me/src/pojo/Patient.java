package com.me.src.pojo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Patient extends MappedModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@OneToOne
	@OneToOne(fetch = FetchType.EAGER)
    @Cascade({CascadeType.ALL})
	private Person person;
	//private String photoFile;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
//	public String getPhotoFile() {
//		return photoFile;
//	}
//	public void setPhotoFile(String photoFile) {
//		this.photoFile = photoFile;
//	}	
	
	
}
