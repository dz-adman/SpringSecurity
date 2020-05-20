package com.ad.app.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Users implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long userId;
	
	@NotNull
	private String userName;
	
	@NotNull
	private String encryptedPassword;

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", username=" + userName + ", password=" + encryptedPassword + "]";
	}
	
}
