package com.shalini.verma.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shalini.verma.dao.ProductsDao;
import com.shalini.verma.manager.ProductsManager;
import com.shalini.verma.model.ExchangeOrderPojo;
import com.shalini.verma.model.Products;

@Service
public class ProductsManagerImpl implements ProductsManager{
	
	@Autowired
	private ProductsDao daoObject;

	@Override
	public boolean saveOrUpdateProductsIntoProducts(Products productObj) {
		
		return daoObject.save(productObj) !=null;// TODO Auto-generated method stub
		
	}

	

	@Override
	public List<Products> fetchListOfProducts(int pageNo, int size) {
		List<Products> productList = new ArrayList<>();
		PageRequest pageable = PageRequest.of(pageNo, size);
		Page<Products> paginatedProducts= daoObject.findAll(pageable);
		// TODO Auto-generated method stub
		if(paginatedProducts.hasContent()) {
			return paginatedProducts.getContent();
		}
		else {
			return productList;
		}
	}



	



	
}
