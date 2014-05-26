package com.zenika.custom.readers;

import org.springframework.batch.item.file.FlatFileItemReader;

import com.zenika.bo.Mapping;

public class MetropolisItemReader extends FlatFileItemReader<Mapping>{
	
	public MetropolisItemReader(){
		super();
		this.setEncoding("UTF-8");
	}
}
