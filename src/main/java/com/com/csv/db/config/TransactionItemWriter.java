package com.com.csv.db.config;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import com.com.csv.db.model.ZeTransaction;
import com.com.csv.db.repository.ZeTransactionRepository;

public class TransactionItemWriter implements ItemWriter<ZeTransaction> {

	private  final ZeTransactionRepository zeTransactionRepository;

	public TransactionItemWriter(ZeTransactionRepository zeTransactionRepository) {
		this.zeTransactionRepository = zeTransactionRepository;		
	}
	
	
	@Override
	public void write(Chunk<? extends ZeTransaction> chunk) throws Exception {
		zeTransactionRepository.saveAll(chunk);
	}

}
