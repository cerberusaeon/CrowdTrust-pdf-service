package org.astrum.common.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "PERSONS" )
public class Person {

	
	private Long personId;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PersonSequence")
	@SequenceGenerator(name="PersonSequence", sequenceName="personSequence")
	public Long getPersonId() {
		return personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return "Person [personId=" + personId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ "]";
	}
	
	
	
	
}
