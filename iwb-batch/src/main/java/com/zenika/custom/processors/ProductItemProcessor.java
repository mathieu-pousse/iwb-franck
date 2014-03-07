package com.zenika.custom.processors;

import org.springframework.batch.item.ItemProcessor;

import com.zenika.bo.Item;

public class ProductItemProcessor implements ItemProcessor<Item,Item>{

	@Override
	public Item process(Item item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
