package com.shalini.verma.manager;

import java.sql.Date;

import java.util.List;

import com.shalini.verma.model.ExchangeOrderPojo;

public interface ExchangeOrderManager {
	public boolean applyExchangeOrder(List<ExchangeOrderPojo> dataObjects);

	public List<ExchangeOrderPojo> fetchExchangeOrderByDate(Date sqlDate);

}
