package com.zenika.custom.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.zenika.bo.Acronym;

public class AcronymFieldSetMapper implements FieldSetMapper<Acronym>{

	@Override
	public Acronym mapFieldSet(FieldSet fs) throws BindException {
		
		if(fs == null){
			return null;
		}
		
		Acronym acronym = new Acronym();
		acronym.setName(fs.readString("label"));
		acronym.setDescription(fs.readString("description"));
		return acronym;
	}

}
