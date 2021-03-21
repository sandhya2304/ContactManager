package com.smart.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="contact11")
public class Contact
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cID;
	private String cName;
	private String secondName;
	private String work;
	private String email;
	private String Phone;
	
	private String cImageURL;
	
	
	@Column(length=500)
	private String cDescription;
	
	@ManyToOne
	private User user;
	
	
	
	public Contact() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getcImageURL() {
		return cImageURL;
	}

	public void setcImageURL(String cImageURL) {
		this.cImageURL = cImageURL;
	}

	public String getcDescription() {
		return cDescription;
	}

	public void setcDescription(String cDescription) {
		this.cDescription = cDescription;
	}




	@Override
	public boolean equals(Object obj) {
		
		return this.cID == ((Contact)obj).getcID();
	}





	
	

}
