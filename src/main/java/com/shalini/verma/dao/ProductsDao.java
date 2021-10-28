package com.shalini.verma.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shalini.verma.model.Products;

@Repository
public interface ProductsDao extends JpaRepository<Products,String>{
	

}
