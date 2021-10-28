package com.shalini.verma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shalini.verma.model.ExchangeOrderPojo;

public interface ExchangeOrderDao extends JpaRepository<ExchangeOrderPojo,String> {
	
	

}
