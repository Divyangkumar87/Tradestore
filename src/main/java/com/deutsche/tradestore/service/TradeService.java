package com.deutsche.tradestore.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deutsche.tradestore.businessRules.LowerVersionRule;
import com.deutsche.tradestore.businessRules.MaturityDateRule;
import com.deutsche.tradestore.businessRules.RuleEngine;
import com.deutsche.tradestore.businessRules.TradeException;
import com.deutsche.tradestore.model.Trade;

@Component
public class TradeService {

	private Set<Trade> storedTrades = new HashSet<>();
	private List<Trade> receivedTrades = null;

	public Set<Trade> validateAndStoreReceivedTrades(List<Trade> receivedTrades) throws TradeException {
		
		RuleEngine.newInstance()
		
		.addRule(new LowerVersionRule(receivedTrades,storedTrades))
		.addRule(new MaturityDateRule(storedTrades)) // comment this rule and call getAllTrades to check how cron job works because the record on which Expired will be set to Y, will be removed by this rule. 
		
		.execute();
		
		return storedTrades;
	}
	
	public List<Trade> receiveTrades() {
		
		//transmitted trades - assumed that it will be available here
		receivedTrades = new ArrayList<Trade>();
		receivedTrades.add(new Trade("T1",1,"CP-1","B1",LocalDate.of(2020, Month.MAY, 20),LocalDate.now(),"N"));
		receivedTrades.add(new Trade("T2",2,"CP-2","B1",LocalDate.of(2021, Month.MAY, 20),LocalDate.now(),"N"));
		receivedTrades.add(new Trade("T2",2,"CP-4","B3",LocalDate.of(2021, Month.MAY, 20),LocalDate.now(),"N"));

		//Set the expired flag to N and call getAllTrades then let cron job to run to demo cron job which will update flag to Y for this record
		receivedTrades.add(new Trade("T3",3,"CP-3","B2",LocalDate.of(2014, Month.MAY, 20),LocalDate.now(),"N"));
		
		// This trade record will create exception for lower version - comment this too see trades stored in storedTrades after validations on browser
		//receivedTrades.add(new Trade("T2",1,"CP-1","B1",LocalDate.of(2021, Month.MAY, 20),LocalDate.of(2015, Month.MARCH, 14),"N"));
		
		return receivedTrades;
	}
	
	/* Scheduled Cron job to update expire flag to Y
	//
	 * @Scheduled(cron="0/30 * * * * *") - cron job for demo purpose to run every 30 seconds
	 * @Scheduled(cron="0 0 0 * * ?") - cron job to run every midnight
	 * */
	@Scheduled(cron="0/30 * * * * *")
	public void updateExpireFlag() {
			System.out.println("cron called");
			storedTrades.stream().forEach(trade -> {
				if(trade.getMaturityDate().isBefore(LocalDate.now())) {
					trade.setExpired("Y");
				};
			});
			System.out.println(storedTrades);
	}
}
