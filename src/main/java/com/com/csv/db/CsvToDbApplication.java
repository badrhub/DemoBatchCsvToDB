package com.com.csv.db;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvToDbApplication implements CommandLineRunner{
	
	private final JobLauncher jobLauncher;
	private final Job job;
	
	CsvToDbApplication(JobLauncher jobLauncher, Job job){
		this.jobLauncher = jobLauncher;
		this.job = job;		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CsvToDbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 jobLauncher.run(job, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
		
	}
	
}
