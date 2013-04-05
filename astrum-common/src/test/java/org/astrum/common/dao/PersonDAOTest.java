package org.astrum.common.dao;

import java.util.List;

import javax.inject.Inject;

import org.astrum.common.domain.Person;
import org.astrum.common.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml",
		"classpath:applicationContext-hibernate.xml" })
public class PersonDAOTest {

	@Inject
	private PersonRepository personRepository;
	
	@Test
	public void testPersonDAO(){
		
		personRepository.save(new PersonDAO().savePerson(new Person()));
		personRepository.save(new PersonDAO().savePerson(new Person()));
		personRepository.save(new PersonDAO().savePerson(new Person()));
		personRepository.save(new PersonDAO().savePerson(new Person()));
		List<Person>  people = personRepository.findAll();
		for(Person p: people){
			System.out.println("Person: "+p);
		}
		
	}
}
