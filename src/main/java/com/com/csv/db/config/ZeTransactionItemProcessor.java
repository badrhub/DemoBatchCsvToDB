package com.com.csv.db.config;

import org.springframework.batch.item.ItemProcessor;

import com.com.csv.db.model.ZeTransaction;

public class ZeTransactionItemProcessor implements ItemProcessor<ZeTransaction, ZeTransaction> {

	@Override
	public ZeTransaction process(ZeTransaction item) throws Exception {
		return item;
	}

}
