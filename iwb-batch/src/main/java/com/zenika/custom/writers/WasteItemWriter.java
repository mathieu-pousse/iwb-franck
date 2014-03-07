package com.zenika.custom.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.zenika.bo.Waste;

public class WasteItemWriter implements ItemWriter<Waste> {
	
	private ApplicationContext ctx;
	private MongoOperations mongoOperation;
	
	public WasteItemWriter(){
		super();
		this.ctx = new GenericXmlApplicationContext("spring/batch/config/database.xml");
		this.mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	}
	@Override
	public void write(List<? extends Waste> items) throws Exception {
		for(Waste waste : items){
			mongoOperation.save(waste,"wastes");
		}
	}

}
