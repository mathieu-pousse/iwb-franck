package com.zenika.custom.processors;

import org.springframework.batch.item.ItemProcessor;

import com.zenika.bo.Mapping;

public class MetropolisItemProcessor implements ItemProcessor<Mapping,Mapping>{

	@Override
	public Mapping process(Mapping mapObject) throws Exception {
		// TODO Auto-generated method stub
		return mapObject;
	}

}
