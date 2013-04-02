package org.astrum.common.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public  class BaseDAO {

	public static EntityManagerFactory entityManagerFactory;
	
	public BaseDAO() {
		try{
		setUp();
		}
		catch(Exception e){
			System.out.println("Could not retrieve entityMangerFactory");
		}
	}
	
	protected void setUp() throws Exception {
	    entityManagerFactory = Persistence.createEntityManagerFactory( "manager" );
	}
	
	
	
}
