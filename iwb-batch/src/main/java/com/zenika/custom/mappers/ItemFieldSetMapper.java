package com.zenika.custom.mappers;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.google.common.collect.Lists;
import com.zenika.bo.Constituent;
import com.zenika.bo.Item;

public class ItemFieldSetMapper implements FieldSetMapper<Item>{

	@Override
	public Item mapFieldSet(FieldSet fs) throws BindException {
		Item item = new Item();
		item.setName(fs.readString("name"));
		item.setBarcode(fs.readString("barcode"));
		item.setImage(fs.readString("image"));
		
		List<Constituent> constituents = Lists.newArrayList();
		
		if(!fs.readString("constituents").isEmpty() && fs.readString("constituents") != null){
			for(String constName : Arrays.asList(fs.readString("constituents").split("-"))){
				constituents.add(new Constituent(constName));
			}
			item.setConstituents(constituents);
		}
		return item;
	}

}
