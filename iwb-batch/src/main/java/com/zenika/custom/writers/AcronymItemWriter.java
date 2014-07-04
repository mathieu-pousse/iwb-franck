package com.zenika.custom.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.zenika.bo.Acronym;

public class AcronymItemWriter implements ItemWriter<Acronym>{
	private ApplicationContext ctx;
	private MongoOperations mongoOperation;
	
	public AcronymItemWriter(){
		super();
		this.ctx = new GenericXmlApplicationContext("spring/batch/config/database.xml");
		this.mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	}
	
	public void write(List<? extends Acronym> acronyms) throws Exception {
		for(Acronym acronym : acronyms){
			mongoOperation.save(acronym, "acronyms");
		}
	}
}
