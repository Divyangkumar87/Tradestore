package com.deutsche.tradestore.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.deutsche.tradestore.businessRules.TradeException;
import com.deutsche.tradestore.model.Trade;
import com.deutsche.tradestore.service.TradeService;

@RestController
public class TradeController {
	
	@Autowired
	private TradeService tradeService;
	
	// This call will return all trades which are stored in TradeStore app DB/in memory list after validations
	@GetMapping("/tradestore/getAllTrades")
	public Set<Trade> getAllStoredTrades() throws TradeException {

		List<Trade> receivedTrades = tradeService.receiveTrades();

		return tradeService.validateAndStoreReceivedTrades(receivedTrades);

	}

}
