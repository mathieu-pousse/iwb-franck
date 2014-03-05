package com.zenika.custom.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.zenika.bo.City;

public class CityItemWriter implements ItemWriter<City>{
	
	public void write(List<? extends City> items) throws Exception {
		ApplicationContext ctx = new GenericXmlApplicationContext("spring/batch/config/database.xml");
		MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
		int i = 0;
		for(City city : items){
			mongoOperation.save(city, "cities");
			i++;
			System.out.println("city "+i+" created");
		}
		
	}

}
