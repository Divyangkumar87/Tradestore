package com.deutsche.tradestore;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.deutsche.tradestore.businessRules.TradeException;
import com.deutsche.tradestore.controller.TradeController;
import com.deutsche.tradestore.model.Trade;
import com.deutsche.tradestore.service.TradeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeControllerTest {

	@InjectMocks
	TradeController tradeController;
	
	@Spy
	private TradeService tradeService;
	
	@Test
	public void contextLoads() {
	}

	@Test()
	public void testGetAllStoredTrades() {
		Set<Trade> storedTrades =  new HashSet<>();
    	storedTrades.add(new Trade("T1",1,"CP-1","B1",LocalDate.of(2020, Month.MAY, 20),LocalDate.now(),"N"));
    	storedTrades.add(new Trade("T2",2,"CP-4","B3",LocalDate.of(2021, Month.MAY, 20),LocalDate.now(),"N"));
    	
    	List<Trade> receivedTrades =  new ArrayList<>();
    	receivedTrades.add(new Trade("T1",1,"CP-1","B1",LocalDate.of(2020, Month.MAY, 20),LocalDate.now(),"N"));
    	receivedTrades.add(new Trade("T2",2,"CP-2","B1",LocalDate.of(2021, Month.MAY, 20),LocalDate.now(),"N"));
    	receivedTrades.add(new Trade("T3",3,"CP-3","B2",LocalDate.of(2014, Month.MAY, 20),LocalDate.now(),"Y"));
		receivedTrades.add(new Trade("T2",2,"CP-4","B3",LocalDate.of(2021, Month.MAY, 20),LocalDate.now(),"N"));

    	
    	when(tradeService.receiveTrades()).thenReturn(receivedTrades);
    	
    	try {
			assertEquals(storedTrades, tradeController.getAllStoredTrades());
		} catch (TradeException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected=TradeException.class)
	public void testGetAllStoredTrades_Exception() throws TradeException {

		List<Trade> receivedTrades =  new ArrayList<>();
    	receivedTrades.add(new Trade("T1",1,"CP-1","B1",LocalDate.of(2020, Month.MAY, 20),LocalDate.now(),"N"));
    	receivedTrades.add(new Trade("T2",2,"CP-2","B1",LocalDate.of(2021, Month.MAY, 20),LocalDate.now(),"N"));
    	receivedTrades.add(new Trade("T3",3,"CP-3","B2",LocalDate.of(2014, Month.MAY, 20),LocalDate.now(),"Y"));
    	receivedTrades.add(new Trade("T2",1,"CP-1","B1",LocalDate.of(2021, Month.MAY, 20),LocalDate.of(2015, Month.MARCH, 14),"N"));
    	
    	when(tradeService.receiveTrades()).thenReturn(receivedTrades);

    	tradeController.getAllStoredTrades();
	}
	
	@Test()
	public void testGetAllStoredTrades_Null_Input() {
		
    	try {
    		
        	when(tradeService.receiveTrades()).thenReturn(null);
			assertEquals(new HashSet<>(), tradeController.getAllStoredTrades());
			
		} catch (TradeException e) {
			e.printStackTrace();
		}
	}
	
}
