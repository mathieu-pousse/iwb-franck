package com.zenika.custom.mappers;

import java.util.Arrays;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.zenika.bo.Trash;

public class TrashHomeFieldSetMapper implements FieldSetMapper<Trash> {
	
	@Override
	public Trash mapFieldSet(FieldSet fs) throws BindException {
		if(fs == null){
			return null;
		}	
		Trash trash =  new Trash();
		trash.setType(fs.readString("type"));
		trash.setColor(fs.readString("color"));
		trash.setCityCode(fs.readString("codeCity"));
		String tmp = fs.readString("flux");
		trash.setWastesHandled(Arrays.asList(tmp.split("-")));
		return trash;
	}
	
}
