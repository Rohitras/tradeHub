package com.db.tradeDemo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.db.tf3.tradeDemo.controller.TradeDemoController;
import com.db.tf3.tradeDemo.exception.ValidationFailedException;
import com.db.tf3.tradeDemo.model.Trade;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TradestorageApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TradeDemoController tradeController;

	@Test
	void testSaveTrade() {
		tradeController.saveTrade(createTrade("TRADE1", 1, Calendar.getInstance().getTime()));
		List<Trade> tradeList = tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T1", tradeList.get(0).getTradeId());
	}

	@Test
	void testSaveTradeWhenMaturityDatePast() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			tradeController.saveTrade(createTrade("TRADE2", 1, calendar.getTime()));
		} catch (ValidationFailedException ie) {
			Assertions.assertEquals("TRADE2 Validation Failed", ie.getMessage());
		}
	}

	@Test
	void testSaveTradeWhenOldVersion() {
		tradeController.saveTrade(createTrade("T1", 2, Calendar.getInstance().getTime()));
		List<Trade> tradeList = tradeController.findAllTrades();
		Assertions.assertEquals(2, tradeList.get(0).getVersion());

		try {
			tradeController.saveTrade(createTrade("T1", 1, Calendar.getInstance().getTime()));
		} catch (ValidationFailedException e) {
		}
		List<Trade> tradeList1 = tradeController.findAllTrades();
		Assertions.assertEquals(2, tradeList1.get(0).getVersion());
	}

	@Test
	void testSaveTradeWhenSameVersionTrade() {
		Trade trade1 = createTrade("T1", 2, Calendar.getInstance().getTime());
		trade1.setBookId("BOOK1");
		tradeController.saveTrade(createTrade("T1", 2, Calendar.getInstance().getTime()));
		Assertions.assertEquals("BOOK1", tradeController.findAllTrades().get(0).getBookId());

		Trade trade2 = createTrade("T1", 2, Calendar.getInstance().getTime());
		trade2.setBookId("BOOK2");
		tradeController.saveTrade(trade2);
		Assertions.assertEquals("BOOK2", tradeController.findAllTrades().get(0).getBookId());
	}

	private Trade createTrade(String tradeId, int version, Date maturityDate) {
		Trade trade = new Trade();
		trade.setTradeId(tradeId);
		trade.setBookId(tradeId + "BOOK");
		trade.setVersion(version);
		trade.setCounterParty(tradeId + "CounterPart");
		trade.setMaturityDate(maturityDate);
		trade.setExpiredFlag("Y");
		return trade;
	}
}
