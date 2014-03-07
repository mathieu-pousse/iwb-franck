package com.zenika.custom.processors;

import org.springframework.batch.item.ItemProcessor;

import com.zenika.bo.Waste;

public class WasteItemProcessor implements ItemProcessor<Waste, Waste> {

	@Override
	public Waste process(Waste waste) throws Exception {
		return waste;
	}

}
