package com.zenika.custom.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.zenika.bo.Trash;

public class TrashItemWriter implements ItemWriter<Trash> {
	
	private ApplicationContext ctx;
	private MongoOperations mongoOperation;
	
	public TrashItemWriter(){
		super();
		this.ctx = new GenericXmlApplicationContext("spring/batch/config/database.xml");
		this.mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	}

	@Override
	public void write(List<? extends Trash> items) throws Exception {
		
		for(Trash trash : items){
			mongoOperation.save(trash, "trashes");
		}
		
	}

}
