package com.me.src.dao;

import org.springframework.stereotype.Component;

import com.me.src.pojo.MedicalRecord;

@Component
public class MedicalRecordDao extends MappedModelDao<MedicalRecord> {
	@Override
	public Class<MedicalRecord> getActualClass() {
		// TODO Auto-generated method stub
		return MedicalRecord.class;
	}
}
