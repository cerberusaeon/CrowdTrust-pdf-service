package org.astrum.services.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserLogin {

	private String userName;
	private String password;

	@XmlElement
	public String getUserName() {
		return userName;
	}

	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
