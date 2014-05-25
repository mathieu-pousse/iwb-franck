package com.zenika;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;



public class App {
	private static ApplicationContext appContext;

	public static void main(String[] args){
		String[] springConfig  = 
			{	
				"spring/batch/config/context.xml",
				"spring/batch/config/database.xml",
				"spring/batch/jobs/jobs.xml" 
			};
		appContext = new ClassPathXmlApplicationContext(springConfig);

		DB db = (DB) appContext.getBean("db");
		db.dropDatabase();
		
		JobLauncher jobLauncher = (JobLauncher) appContext.getBean("jobLauncher");
		Job job =(Job) appContext.getBean("loadDatatJob");

		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
			createLocationIndexes(db);
			createNameIndex(db);
			createBarCodeIndex(db);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createLocationIndexes(DB db){
		DBCollection trashes = db.getCollection("trashes");
		DBObject query = new BasicDBObject("location", "2d").append("wastesHandled", 1);
		trashes.createIndex(query);	
	}
	
	public static void createNameIndex(DB db){
		DBCollection items = db.getCollection("items");
		DBObject query = new BasicDBObject("name", 1);
		items.createIndex(query);
	}
	
	public static void createBarCodeIndex(DB db){
		DBCollection items = db.getCollection("items");
		DBObject query = new BasicDBObject("barcode", 1);
		items.createIndex(query);
	}
}