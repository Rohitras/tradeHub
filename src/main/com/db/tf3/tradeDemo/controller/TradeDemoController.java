package com.db.tf3.tradeDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.tf3.tradeDemo.exception.ValidationFailedException;
import com.db.tf3.tradeDemo.model.Trade;
import com.db.tf3.tradeDemo.service.TradeFacade;

@RestController
public class TradeDemoController {
	@Autowired
	TradeFacade tradeFacade;

	@PostMapping("/saveTrade")
	public ResponseEntity<String> saveTrade(@RequestBody Trade trade) {
		if (tradeFacade.isValid(trade)) {
			tradeFacade.persist(trade);
		} else {
			throw new ValidationFailedException(trade.getTradeId() + "  Validation Failed");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/getTrade")
	public List<Trade> findAllTrades() {
		return tradeFacade.findAll();
	}
}
