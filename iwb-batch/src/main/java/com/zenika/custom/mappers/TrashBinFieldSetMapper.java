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
			switch (handledWastes[i]) {
	            case "OM":  handledWastes[i] = "RECYCLABLE";
	            	break;
	            case "VE":  handledWastes[i] = "GLASS";
	    			break;
	            case "JM":  handledWastes[i] = "PAPER";
	            	break;
	            case "MM":  handledWastes[i] = "MULTI";
	        		break;
	            default: 
	                break;
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
