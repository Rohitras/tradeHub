package com.db.tf3.tradeDemo.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.db.tf3.tradeDemo.model.Trade;

public interface ITradeDao {

	public static Map<String, Trade> map = new ConcurrentHashMap<>();

	public void save(Trade trade);

	List<Trade> findAll();

	Trade getTrade(String tradeId);
}
