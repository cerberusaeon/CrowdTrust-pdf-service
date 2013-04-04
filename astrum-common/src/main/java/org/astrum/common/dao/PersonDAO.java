package org.astrum.common.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import org.astrum.common.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
@Repository
public class PersonDAO extends BaseDAO {

	protected static Logger logger = LoggerFactory.getLogger(PersonDAO.class);
	
	public PersonDAO()  {
		super();
		
	}
	
	public Person savePerson(Person p){
		logger.info("entered savePerson");
		p.setDateOfBirth(new Date());
		p.setFirstName("Bob"+new Random().nextInt());
		p.setLastName("Dylan");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(p);
		entityManager.getTransaction().commit();
		entityManager.close();
		logger.debug("~~~~~~~~~~~~~~~~~~~ check~~~~~~~~~~~~~~~~~~~~~~");
		logger.info("leaving savePerson");
		return p;
	}
	
	public Person readPerson(){
		Person person = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Person> result = entityManager.createQuery( "from Person", Person.class ).getResultList();
		for ( Person p : result ) {
		    System.out.println(p);
		    person = p;
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return person;
	}
}
