package com.shalini.verma.manager;

import java.util.List;

import com.shalini.verma.model.ExchangeOrderPojo;
import com.shalini.verma.model.Products;

public interface ProductsManager {
	
	public boolean saveOrUpdateProductsIntoProducts(Products productObj);
	public List<Products> fetchListOfProducts(int page, int size);
	

}
