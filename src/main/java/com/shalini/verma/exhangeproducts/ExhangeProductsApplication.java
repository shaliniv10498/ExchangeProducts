package com.shalini.verma.exhangeproducts;

import java.io.File;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.shalini.verma.manager.ProductsManager;
import com.shalini.verma.manager.impl.ProductsManagerImpl;
import com.shalini.verma.model.Products;

@SpringBootApplication
@EnableJpaRepositories("com.shalini.verma.dao")
@ComponentScan(basePackages={"com.shalini.verma.*"})
@EntityScan("com.shalini.verma.model")
public class ExhangeProductsApplication {
	

	public static void main(String[] args) throws Exception {
		
			
		
		SpringApplication.run(ExhangeProductsApplication.class, args);
	 }
	
		
	

}
