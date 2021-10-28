package com.shalini.verma.Controller;

import java.io.File;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.shalini.verma.manager.ProductsManager;
import com.shalini.verma.manager.impl.ProductsManagerImpl;
import com.shalini.verma.model.Products;

public class ProcessLoadingOfDataService {
	
	
	public void processLoadingOfDataIntoDB(ProductsManager manager) throws Exception {
		ProcessLoadingOfDataService objx= new ProcessLoadingOfDataService();
		
		//ReadFileFromClasspath ob = new ReadFileFromClasspath();
		
		File obx = objx.getFile("products.json");
		System.out.print(obx.getPath());
		Reader reader = Files.newBufferedReader(obx.toPath());
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(reader);
		JSONArray dataArray = (JSONArray) json.get("data");
		for(Object dataIndex : dataArray) {
			Products persistentObj = new Products();
			JSONObject object =(JSONObject)(dataIndex);
			
			
			persistentObj.setUniqueId(String.valueOf(object.get("unique_id")));
			persistentObj.setRetailPrice(object.get("retail_price")!=null?Integer.parseInt(String.valueOf(object.get("retail_price"))):null);
			persistentObj.setDiscountedPrice(object.get("discounted_price")!=null?Integer.parseInt(String.valueOf(object.get("discounted_price"))) : null);
			persistentObj.setImagesList(String.valueOf(object.get("images")));
			persistentObj.setDescription(String.valueOf(object.get("description")));
			persistentObj.setProductRating(object.get("product_rating")!=null && !(object.get("product_rating")).equals("No rating available")? Float.parseFloat(String.valueOf(object.get("product_rating"))):null);
			persistentObj.setOverallRating(object.get("overall_rating")!=null && !(object.get("overall_rating")).equals("No rating available")? Float.parseFloat(String.valueOf(object.get("overall_rating"))):null);
			persistentObj.setBrand(String.valueOf(object.get("brand")));
			persistentObj.setProductName(String.valueOf(object.get("product_name")));
			
			
			manager.saveOrUpdateProductsIntoProducts(persistentObj);
			
			
		}
		

	}
	
	private File getFile(String fileName)throws Exception {
		ClassLoader clsLoader = getClass().getClassLoader();
		URL resource = clsLoader.getResource(fileName);
		if(null!=resource) {
			return new File(resource.getFile());
		}
		else {
			throw new Exception("File Not Found!");
		}
		
	}

}
