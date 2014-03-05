package com.zenika.custom.processors;

import org.springframework.batch.item.ItemProcessor;

import com.zenika.bo.City;

public class CityItemProcessor implements ItemProcessor<City,City>{

	public City process(City city) throws Exception {
		return city;
	}

}
