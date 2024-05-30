package com.com.csv.db.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;

import com.com.csv.db.model.ZeTransaction;
import com.com.csv.db.repository.ZeTransactionRepository;

@Configuration
public class BatchConfig {
	
@Value("input/transactions.csv")	
public 	String inputCsv;

@Bean
 FlatFileItemReader<ZeTransaction> itemReader(){
	   FlatFileItemReader<ZeTransaction> reader = new FlatFileItemReader<>();
       reader.setResource(new ClassPathResource(inputCsv)); 
       reader.setLinesToSkip(1);
       
       DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    // numero_lot;id_client;nom_produit;quantite;date_transaction;montant
       String[] tokens = new String[] { "numero_lot", "id_client", "nom_produit", "quantite", "date_transaction", "montant" };
       tokenizer.setNames(tokens);
       tokenizer.setDelimiter(";");
       
       DefaultLineMapper<ZeTransaction> lineMapper = new DefaultLineMapper<>();
       lineMapper.setLineTokenizer(tokenizer);
       lineMapper.setFieldSetMapper(new ZeTransactionFieldSetMapper());
       
       reader.setLineMapper(lineMapper);
       return reader;	 
 }
 
 @Bean
 ItemProcessor<ZeTransaction, ZeTransaction> itemProcessor(){
	 return new ZeTransactionItemProcessor();
 }
 
 @Bean
 ItemWriter<ZeTransaction> itemWriter(ZeTransactionRepository zeTransactionRepository){
	 return new TransactionItemWriter(zeTransactionRepository);
 }
 
 @Bean
 Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                  FlatFileItemReader<ZeTransaction> reader, ItemWriter<ZeTransaction> writer,
                  ItemProcessor<ZeTransaction, ZeTransaction> processor) {
     return new StepBuilder("step", jobRepository)
             .<ZeTransaction, ZeTransaction>chunk(10, transactionManager)
             .reader(reader)
             .processor(processor)
             .writer(writer)
             .build();
 }
 @Bean
  Job job(JobRepository jobRepository, Step step) {
     return new JobBuilder("job", jobRepository)
             .start(step)
             .listener(batchJobListener())
             .build();
 }
 @Bean
 BatchJobListener batchJobListener() {
     return new BatchJobListener();
 }
 @Bean
  PlatformTransactionManager transactionManager() {
     return new JpaTransactionManager();
 }
 

}
