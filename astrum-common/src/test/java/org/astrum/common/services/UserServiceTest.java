package org.astrum.common.services;

import java.util.Date;

import javax.inject.Inject;

import org.astrum.common.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/applicationContext-hibernate.xml"})	
public class UserServiceTest {
		
	@Inject 
	UserService userService;
	
	@Test
	public void tesLogin(){
		Person p = new Person();
		p.setDateOfBirth(new Date());
		p.setFirstName("Billy");
		p.setLastName("Thorton");
		
		userService.login(p);
		
	}

}
