package com.shalini.verma.manager.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.shalini.verma.dao.ExchangeOrderDao;
import com.shalini.verma.manager.ExchangeOrderManager;
import com.shalini.verma.model.ExchangeOrderPojo;

@Service
public class ExchangeOrderManagerImpl implements ExchangeOrderManager {
	
	@Autowired
	private ExchangeOrderDao exchangeOrderDaoObject;
	
	
	
	@Autowired SessionFactory factory;

	@Override
	public boolean applyExchangeOrder(List<ExchangeOrderPojo> dataObjects) {
		// TODO Auto-generated method stub
		return exchangeOrderDaoObject.saveAll(dataObjects) !=null;
	}

	@Override
	public List<ExchangeOrderPojo> fetchExchangeOrderByDate(java.sql.Date sqldate) {
		Session session=null;
		try {
			session=factory.openSession();
			Query<ExchangeOrderPojo> query = session.createQuery("From ExchangeOrderPojo where createdOn like ?1");
			query.setParameter(1, "%"+(java.sql.Date)sqldate+"%");
			List<ExchangeOrderPojo> list = query.list();
			if(list!=null)
				return list;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
