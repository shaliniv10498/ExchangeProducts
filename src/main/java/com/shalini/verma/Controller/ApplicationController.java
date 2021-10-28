package com.shalini.verma.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shalini.verma.APIStatusEnums.StatusEnums;
import com.shalini.verma.manager.ExchangeOrderManager;
import com.shalini.verma.manager.ProductsManager;
import com.shalini.verma.model.CustomerUser;
import com.shalini.verma.model.ExchangeOrderPojo;
import com.shalini.verma.model.MessageServiceRequest;
import com.shalini.verma.model.MessageServiceResponse;
import com.shalini.verma.model.Products;
import com.shalini.verma.thread.service.LoadDataIntoDBThread;

@RestController
public class ApplicationController  {
	private static ObjectMapper mapper = new ObjectMapper();

	
	@Autowired
	private ProductsManager manager;
	
	@Autowired
	private ExchangeOrderManager exchangeOrderManager;
	
	@RequestMapping(path="/load")
	public String loadJSONDataIntoDB(){
		JSONParser parser = new JSONParser();
		try {
			
			
				
			    LoadDataIntoDBThread loadingDataThread = new LoadDataIntoDBThread(manager);
			    loadingDataThread.start();
			    System.out.println("Thread Started!!!");
				return StatusEnums.SUCCESS.getStatus();
			
			

		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return StatusEnums.FAILURE.getStatus();
	}
	
	@RequestMapping(path="/fetchProducts")
	public List<Products> fetchListOfProducts(@RequestParam(value="pageNo") Integer pageNo, @RequestParam(value="limit") Integer limit){
		List<Products> listofProducts=null;
		try {
			listofProducts = manager.fetchListOfProducts(pageNo, limit);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return listofProducts;
		
	}
	
	
	@RequestMapping(path="/applyExchangeOrder" ,method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public String applyExchangeOrder(@RequestBody List<ExchangeOrderPojo> data) {
		List<ExchangeOrderPojo> listOfObjects = new ArrayList<>();
		Random random = new Random();
		int orderId = random.nextInt();
		try {
			if(data!=null) {
			for(ExchangeOrderPojo objectMap : data) {
				
				java.util.Date utilDate = new java.util.Date();
			    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				objectMap.setCreatedOn(sqlDate);
				objectMap.setOrderId(orderId);
			
				
				if(objectMap.getAskedPrice()>objectMap.getRetailPrice()) {
					objectMap.setPaymentStatus(StatusEnums.SUCCESS.getStatus());
				}
				else {
					objectMap.setPaymentStatus(StatusEnums.FAILURE.getStatus());
				}
				CustomerUser user = new CustomerUser();
				user.setPkUserId(1);
				objectMap.setUser(user);
				listOfObjects.add(objectMap);
			}
			boolean val= exchangeOrderManager.applyExchangeOrder(listOfObjects);
			if(val==true) {
				return StatusEnums.SUCCESS.getStatus();
			}
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return StatusEnums.FAILURE.getStatus();
		
	}
	
	@RequestMapping(path="/fetchAllOrdersByDate")
	public List<ExchangeOrderPojo> fetchAllOrdersByDate(@RequestParam(value="date") String date){
		List<ExchangeOrderPojo> pojoObject = null;
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
			java.util.Date dateObject = formatter.parse(date);
		    java.sql.Date sqlDate = new java.sql.Date(dateObject.getTime());
		        
	        System.out.println(sqlDate);
			pojoObject = exchangeOrderManager.fetchExchangeOrderByDate(sqlDate);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return pojoObject;
	}

	@GetMapping(path="/makeServiceCall")
	public MessageServiceResponse servicecall() {
		MessageServiceResponse responseData = null;
		Invocation.Builder invocationBuilder = null;
		WebTarget webTarget = null;
		Client client = null;
		HashMap<String,Object> responseObject = null;
		MessageServiceRequest reqObj = new MessageServiceRequest();
		HashMap<String,Object> map = new HashMap<>();
		HashMap<String,Object> map1 = new HashMap<>();
		map1.put("search", "Shal");
		map.put("fetchLimitedStudents",map1);
		reqObj.setRequest(map);
		System.out.println(reqObj);
		try {
			  client = ClientBuilder.newClient();
			  try {
				webTarget = client.target("http://localhost:8000").path("/api/v1/student/fetch/limit");	
				invocationBuilder = webTarget.request();
				Response response = invocationBuilder
							.post(Entity.entity(mapper.writeValueAsString(reqObj), javax.ws.rs.core.MediaType.APPLICATION_JSON));
					
				// response = invocationBuilder.get(Response.class);
				if(response.getStatus()== Response.Status.OK.getStatusCode()) {
					 responseData = mapper.readValue(response.readEntity(String.class),
							MessageServiceResponse.class);
					if (responseData != null) {
						responseObject = responseData.getResponse();
					}
					System.out.println(responseObject.get("result"));
				
				}
			  }
			catch(Exception ex) {
				ex.printStackTrace();
			}
	  }
		catch(Exception e) {e.printStackTrace();}
		
		finally {
			closeConnection(client);
		}
		return responseData;
  }
	private static void closeConnection(Client client) {
		try {
			if (client != null) {
				client.close();
			}
		} catch (Exception e) {
			//LOGGER.error("Error while closing  Message Service httpClient [messageTypes] : ", e);
		}
	}
}
