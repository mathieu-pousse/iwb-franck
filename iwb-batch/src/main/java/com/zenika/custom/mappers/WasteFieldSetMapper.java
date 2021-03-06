package com.zenika.custom.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.zenika.bo.Waste;

public class WasteFieldSetMapper implements FieldSetMapper<Waste> {

	@Override
	public Waste mapFieldSet(FieldSet fs) throws BindException {
		Waste waste = new Waste();
		waste.setName(fs.readString("name"));
		waste.setDescription(fs.readString("description"));
		return waste;
	}

}
