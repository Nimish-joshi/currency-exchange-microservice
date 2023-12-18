package com.microservices.currencyexchangeservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.microservices.currencyexchangeservice.repository.CurrencyExchangeDao;



@RestController
public class CurrencyExchangeController {
	
	private Logger logger=LoggerFactory.getLogger(CurrencyExchangeController.class);

	
	@Autowired
	private CurrencyExchangeDao curExdao;
	
	@Autowired
	private Environment env;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangevalue(@PathVariable String from, @PathVariable String to) {
//		return new CurrencyExchange(101,from, to, BigDecimal.valueOf(50 ));
		
		logger.info("retrieveExchangevalue called with {} to {}", from,to);
//		CurrencyExchange currencyExchange= new CurrencyExchange(101,from, to, BigDecimal.valueOf(50));
		CurrencyExchange currencyExchange = curExdao.findByFromAndTo(from, to);
		
		if(currencyExchange == null) {
			throw new RuntimeException("unable to find data for"+"from" + to+"to" );
		}
//		
		String port= env.getProperty("local.server.port");
		
		//kubernetes-changes
		String host= env.getProperty("hostname");
		String version ="v11";
		currencyExchange.setEnvironment(port+"  "+host+" "+version);
		
		return currencyExchange;
	}
}
