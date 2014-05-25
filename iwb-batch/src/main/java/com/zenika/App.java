package com.zenika;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mongodb.DB;



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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}