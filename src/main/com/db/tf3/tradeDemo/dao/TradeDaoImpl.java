package com.db.tf3.tradeDemo.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.db.tf3.tradeDemo.model.Trade;

@Repository
public class TradeDaoImpl implements ITradeDao {

	@Override
	public void save(Trade trade) {
		trade.setCreatedDate(Calendar.getInstance().getTime());
		map.put(trade.getTradeId(), trade);
	}

	@Override
	public List<Trade> findAll() {
		List<Trade> list = new ArrayList<Trade>();
		for (Trade trade : map.values()) {
			list.add(trade);
		}
		return list;
	}

	@Override
	public Trade getTrade(String tradeId) {
		return map.get(tradeId);
	}
}
