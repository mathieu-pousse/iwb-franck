package com.zenika.custom.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.zenika.bo.Trash;

public class TrashCenterItemWriter implements ItemWriter<Trash> {

	@Override
	public void write(List<? extends Trash> items) throws Exception {
		
		ApplicationContext ctx = new GenericXmlApplicationContext("spring/batch/config/database.xml");
		MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
		
		for(Trash trash : items){
			mongoOperation.save(trash, "trashes");
		}
		
	}

}