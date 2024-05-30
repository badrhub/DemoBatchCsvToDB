package com.com.csv.db.config;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class BatchJobListener implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println(">>>>>>>>>>>>>>>>>>> start at : " + jobExecution.getStartTime());
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println(">>>>>>>>>>>>>>>>>>> end at : " + jobExecution.getEndTime() + " , with status : "+ jobExecution.getStatus());
	}
}
