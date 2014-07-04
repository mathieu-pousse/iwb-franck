package com.zenika.custom.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.zenika.bo.Item;

public class ItemFieldSetMapper implements FieldSetMapper<Item>{

	@Override
	public Item mapFieldSet(FieldSet fs) throws BindException {
		Item item = new Item();
		item.setJson(fs.readString("json"));
		
		return item;
	}

}
