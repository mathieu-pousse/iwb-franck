package com.zenika.custom.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.zenika.bo.City;
import com.zenika.bo.Mapping;

public class MetropolisFieldSetMapper implements FieldSetMapper<Mapping>{

	@Override
	public Mapping mapFieldSet(FieldSet fs) throws BindException {
		if(fs == null){
			return null;
		}
		Mapping mapping =  new Mapping();
		mapping.setCityCode(fs.readString("cityCode"));
		mapping.setCityLabel(fs.readString("cityLabel"));
		mapping.setMetropolisCode(fs.readString("metropolisCode"));
		mapping.setMetropolisLabel(fs.readString("metropolisLabel"));
		mapping.setMetropolisType(fs.readString("metropolisType"));
		return mapping;
	}

}
