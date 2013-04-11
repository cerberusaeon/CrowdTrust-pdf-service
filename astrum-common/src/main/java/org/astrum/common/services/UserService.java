package org.astrum.common.services;

import javax.inject.Inject;

import org.astrum.common.domain.Person;
import org.astrum.common.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Inject
	PersonRepository personRepository;
	
	public String login(Person p){
		personRepository.save(p);
		return "Success";
	}
	public String register (Person p){
		
		personRepository.save(p);
		return "Success";
	}
	
}
