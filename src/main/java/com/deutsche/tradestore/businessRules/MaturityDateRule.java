package com.deutsche.tradestore.businessRules;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import com.deutsche.tradestore.model.Trade;

public class MaturityDateRule implements Rule {

	private Set<Trade> storedTrades;
	
	
	public MaturityDateRule(Set<Trade> storedTrades) {
		this.storedTrades = storedTrades;
	}


	@Override
	public void validate() throws TradeException {

		Iterator<Trade> itr = storedTrades.iterator();
		while(itr.hasNext()) {
				Trade trade = itr.next();
				if(trade.getMaturityDate().isBefore(LocalDate.now())) {
					itr.remove();
				}
		}
	}

}
