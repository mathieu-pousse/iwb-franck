package com.zenika.custom.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.zenika.bo.City;

public class CityItemWriter implements ItemWriter<City>{
	
	private ApplicationContext ctx;
	private MongoOperations mongoOperation;
	
	public CityItemWriter(){
		super();
		this.ctx = new GenericXmlApplicationContext("spring/batch/config/database.xml");
		this.mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	}
	
	public void write(List<? extends City> items) throws Exception {
		int i = 0;
		for(City city : items){
			mongoOperation.save(city, "cities");
			i++;
			System.out.println("city "+i+" created");
		}
		
	}

}
