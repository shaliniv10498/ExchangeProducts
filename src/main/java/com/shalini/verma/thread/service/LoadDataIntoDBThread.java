package com.shalini.verma.thread.service;

import org.apache.logging.log4j.ThreadContext;

import com.shalini.verma.Controller.ProcessLoadingOfDataService;
import com.shalini.verma.manager.ProductsManager;
import com.shalini.verma.model.Products;

public class LoadDataIntoDBThread extends Thread{
	private ProductsManager manager;
	private Products product;
	public LoadDataIntoDBThread( ProductsManager manager) {
		this.manager=manager;
		this.product=product;
		
	}
	
	public void run() {
		ThreadContext.put("thread", "one");
		ProcessLoadingOfDataService dataProcessingObject = new ProcessLoadingOfDataService();
		try {
			dataProcessingObject.processLoadingOfDataIntoDB(manager);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
