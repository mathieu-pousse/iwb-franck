package com.zenika.custom.processors;

import org.springframework.batch.item.ItemProcessor;

import com.zenika.bo.Trash;

public class TrashItemProcessor implements ItemProcessor<Trash, Trash> {

	@Override
	public Trash process(Trash trash) throws Exception {
		return trash;
	}

}
