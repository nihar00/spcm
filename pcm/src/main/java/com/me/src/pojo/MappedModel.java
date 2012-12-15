package com.me.src.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class MappedModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
}
