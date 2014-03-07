package com.zenika.custom.processors;

import org.springframework.batch.item.ItemProcessor;

import com.zenika.bo.Trash;

public class TrashBinItemProcessor implements ItemProcessor<Trash, Trash> {

	@Override
	public Trash process(Trash item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
