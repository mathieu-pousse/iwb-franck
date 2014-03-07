package com.zenika.custom.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.zenika.bo.Trash;

public class TrashCenterFieldSetMapper implements FieldSetMapper<Trash> {

	@Override
	public Trash mapFieldSet(FieldSet fs) throws BindException {
		
		if(fs == null)
			return null;
		
		Trash trash = new Trash();
		trash.setType("decheterie");
		trash.setName(fs.readString("trashCenterUsualName"));
		trash.setCityCode(fs.readString("cityCode"));
		trash.setAddress(fs.readString("trashCenterUsualName"));
		
		return trash;
	}

}
