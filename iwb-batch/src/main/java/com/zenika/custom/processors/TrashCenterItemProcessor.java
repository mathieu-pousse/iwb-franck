package com.zenika.custom.processors;

import org.springframework.batch.item.ItemProcessor;

import com.zenika.bo.Trash;

public class TrashCenterItemProcessor implements ItemProcessor<Trash, Trash> {

	@Override
	public Trash process(Trash trash) throws Exception {
		// TODO Auto-generated method stub
		return trash;
	}

}
