package org.astrum.common.dao;

import junit.framework.TestCase;

import org.astrum.common.domain.Person;

public class PersonDAOTest extends TestCase {

	public void testPersonDAO(){
		PersonDAO dao = new PersonDAO();
		dao.savePerson(new Person());
		dao.savePerson(new Person());
		dao.savePerson(new Person());
		dao.savePerson(new Person());
		dao.readPerson();
		
	}
}
