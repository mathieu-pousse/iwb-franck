package com.zenika.custom.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.zenika.bo.City;

public class CityFieldSetMapper implements FieldSetMapper<City>{

	public City mapFieldSet(FieldSet fs) throws BindException {
		
		if(fs == null){
			return null;
		}
		
		City city = new City();
		city.setName(fs.readString("ville_nom_reel"));
		city.setUcName(fs.readString("ville_nom"));
		city.setCode(fs.readString("ville_code_commune"));
		city.setZipcode(fs.readString("ville_code_postal"));
		city.setLongitude(fs.readString("ville_longitude_deg"));
		city.setLatitude(fs.readString("ville_latitude_deg"));
		return city;	
	}
}
