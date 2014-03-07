package com.zenika.custom.writers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.google.common.collect.Lists;
import com.mongodb.DB;
import com.zenika.bo.City;
import com.zenika.bo.CityCustom;
import com.zenika.bo.Mapping;
import com.zenika.bo.Metropolis;

public class MetropolisItemWriter implements ItemWriter<Mapping>{

	
	public void write(List<? extends Mapping> items) throws Exception {
		
		ApplicationContext ctx = new GenericXmlApplicationContext("spring/batch/config/database.xml");
		MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
		Map<String,Metropolis> metropolises = new HashMap<String,Metropolis>();
		
		for(Mapping mapObject : items){			
			if(!(mapObject.getMetropolisCode().contains("ZZZZZZZZZ") || mapObject.getMetropolisLabel().equals("nd"))){
				Metropolis metropolis = metropolises.get(mapObject.getMetropolisCode());
					if( metropolis != null){
						metropolis.getCities().add(new CityCustom(mapObject.getCityCode(), mapObject.getCityLabel()));
					}else{
						metropolis = new Metropolis(mapObject.getMetropolisCode(), mapObject.getMetropolisLabel());
						metropolis.setCities(Lists.newArrayList(new CityCustom(mapObject.getCityCode(), mapObject.getCityLabel())));
						metropolises.put(mapObject.getMetropolisCode(), metropolis);
					}
			}
		}
		for(Metropolis  mtp : metropolises.values()){
			mongoOperation.save(mtp, "metropolises");
		}
	}

}
