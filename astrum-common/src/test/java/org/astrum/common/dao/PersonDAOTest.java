package org.astrum.common.dao;

import org.astrum.common.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml",
		"classpath:applicationContext-hibernate.xml" })
public class PersonDAOTest {

	@Inject
	private PersonDAO personDAO;
	
	@Test
	public void testPersonDAO(){
		PersonDAO dao = new PersonDAO();
		dao.savePerson(new Person());
		dao.savePerson(new Person());
		dao.savePerson(new Person());
		dao.savePerson(new Person());
		dao.readPerson();
		
	}
}
