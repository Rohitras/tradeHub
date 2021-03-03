package com.db.tf3.tradeDemo.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.tf3.tradeDemo.dao.ITradeDao;
import com.db.tf3.tradeDemo.model.Trade;

@Service
public class TradeFacade {

	@Autowired
	ITradeDao tradeDao;

	public boolean isValid(Trade trade) {
		if (validateMaturityDate(trade)) {
			Trade exsitingTrade = tradeDao.getTrade(trade.getTradeId());
			if (exsitingTrade != null) {
				return validateVersion(trade, exsitingTrade);
			} else {
				return true;
			}
		}
		return false;
	}

	private boolean validateVersion(Trade trade, Trade oldTrade) {
		if (trade.getVersion() >= oldTrade.getVersion()) {
			return true;
		}
		return false;
	}

	private boolean validateMaturityDate(Trade trade) {
		return trade.getMaturityDate().before(Calendar.getInstance().getTime()) ? false : true;
	}

	public void persist(Trade trade) {
		trade.setCreatedDate(Calendar.getInstance().getTime());
		tradeDao.save(trade);
	}

	public List<Trade> findAll() {
		return tradeDao.findAll();
	}

	public void markExpiry() {
		for (Trade trade : tradeDao.findAll()) {
			if (!validateMaturityDate(trade)) {
				trade.setExpiredFlag("Y");
				tradeDao.save(trade);
			}
		}
	}
}
