package com.zenika.custom.mappers;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.zenika.bo.Trash;

public class TrashBinFieldSetMapper implements FieldSetMapper<Trash>{

	@Override
	public Trash mapFieldSet(FieldSet fs) throws BindException {
		if(fs == null){
			return null;
		}	
		Trash trash =  new Trash();
		trash.setType("BIN");
		trash.setCityCode(fs.readString("codeinsee"));
		trash.setAddress(fs.readString("adresse"));
		String tmp = fs.readString("flux");
		List<String> handledWastes = Arrays.asList(adaptWastesToModel(tmp.split("-")));
		trash.setWastesHandled(handledWastes);
		trash.setLocation(getLocationArray(fs));
		return trash;
	}
	
	public String[] adaptWastesToModel(String[] handledWastes){
		for(int i=0; i<handledWastes.length; i++){
			if(handledWastes[i].equals("OM") || handledWastes[i].equals("VE")){
				handledWastes[i] = handledWastes[i].concat("R");
			}
		}
		return handledWastes;
	}
	
	public Iterable<Double> getLocationArray(FieldSet fs){
		String longitudeString = fs.readString("longitude");
		String latitudeString = fs.readString("latitude");
		Double[] location = {Double.parseDouble(longitudeString),Double.parseDouble(latitudeString)};
		return Arrays.asList(location);
	}

}
