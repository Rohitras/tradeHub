
package com.db.tf3.tradeDemo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.db.tf3.tradeDemo.service.TradeFacade;

@Component
public class TradeExpiryScheduler {
	@Autowired
	TradeFacade tradeFacade;

	@Scheduled(cron = "${trade.expiry.schedule}")
	public void reportCurrentTime() {
		tradeFacade.markExpiry();
	}
}