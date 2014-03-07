package com.zenika.custom.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.zenika.bo.Item;

public class ProductItemWriter implements ItemWriter<Item> {
	
	private ApplicationContext ctx;
	private MongoOperations mongoOperation;
	
	public ProductItemWriter(){
		super();
		this.ctx = new GenericXmlApplicationContext("spring/batch/config/database.xml");
		this.mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	}
	
	@Override
	public void write(List<? extends Item> items) throws Exception {
		for(Item item : items){
			mongoOperation.save(item, "items");
		}
	}

}
