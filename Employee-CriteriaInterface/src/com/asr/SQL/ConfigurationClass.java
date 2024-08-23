package com.asr.SQL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.asr.Model.Employee;



public class ConfigurationClass {
	
	public static Session createConfig()
	{
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session openSession = sessionFactory.openSession();
		
		return openSession;
		
	}
	
	
	
	
	
	

}
