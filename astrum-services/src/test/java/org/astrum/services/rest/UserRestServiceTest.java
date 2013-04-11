package org.astrum.services.rest;

import java.util.Date;

import javax.inject.Inject;

import org.astrum.common.domain.Person;
import org.astrum.common.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/applicationContext-hibernate.xml","/applicationContext-services.xml"})	
public class UserRestServiceTest {
	
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
