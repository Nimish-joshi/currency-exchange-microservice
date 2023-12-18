package com.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.currencyexchangeservice.bean.CurrencyExchange;

@Repository
public interface CurrencyExchangeDao extends JpaRepository<CurrencyExchange, Integer> {

	CurrencyExchange findByFromAndTo(String from, String to);
}
