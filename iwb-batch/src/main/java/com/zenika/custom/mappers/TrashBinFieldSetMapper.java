package com.zenika.custom.mappers;

import java.util.Arrays;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.zenika.bo.Trash;

public class TrashBinFieldSetMapper implements FieldSetMapper<Trash>{

	@Override
	public Trash mapFieldSet(FieldSet fs) throws BindException {
		if(fs == null)
			return null;
		Trash trash =  new Trash();
		trash.setType("PAV");
		trash.setCityCode(fs.readString("codeinsee"));
		trash.setAddress(fs.readString("localisati"));
		String tmp = fs.readString("flux");
		trash.setWastesHandled(Arrays.asList(tmp.split("-")));
		trash.setLongitude(fs.readString("longitude"));
		trash.setLatitude(fs.readString("latitude"));
		return trash;
	}

}
