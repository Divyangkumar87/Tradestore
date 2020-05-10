package com.deutsche.tradestore.businessRules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.deutsche.tradestore.model.Trade;

public class LowerVersionRule implements Rule {

	private List<Trade> receivedTrades;
	private Set<Trade> storedTrades;
	private Map<String,Trade> tempMap = new HashMap<>();
	
	public LowerVersionRule(List<Trade> receivedTrades,Set<Trade> storedTrades) {
		this.receivedTrades = receivedTrades;
		this.storedTrades = storedTrades;
	}

	@Override
	public void validate() throws TradeException {

		if(receivedTrades != null && (!receivedTrades.isEmpty())) {
			for(Trade trade: receivedTrades) {
				if(tempMap.get(trade.getTradeId()) != null) {
					
					if(trade.getVersion() == tempMap.get(trade.getTradeId()).getVersion()) {
						tempMap.put(trade.getTradeId(), trade);
					}else if(trade.getVersion() < tempMap.get(trade.getTradeId()).getVersion()){
						throw new TradeException("Trade rejected due to lower version received");
					}
					
				}else {
					tempMap.put(trade.getTradeId(), trade);
				}
			}
			
			storedTrades.addAll(tempMap.values().stream().collect(Collectors.toList()));
		}
		
		
	}
}
