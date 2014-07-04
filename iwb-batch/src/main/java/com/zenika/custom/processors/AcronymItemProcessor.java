package com.zenika.custom.processors;

import org.springframework.batch.item.ItemProcessor;

import com.zenika.bo.Acronym;

public class AcronymItemProcessor implements ItemProcessor<Acronym,Acronym>{

	@Override
	public Acronym process(Acronym item) throws Exception {
		return item;
	}

}
