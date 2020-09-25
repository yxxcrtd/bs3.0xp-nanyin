package com.jixu.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/** 
 * @ClassName 		Client
 * @author  		Pan Wei 
 * @Date 			2018年3月6日 上午10:37:30 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="Client") 
public class Client implements Serializable{
	
	private static final long serialVersionUID = -806829177900138273L;

	private int clientID;
	
	private String clientName;
	
	private String clientShortName;
	
	private int city;
	
	private String officeAddress;
	
	private String contactNumber;
	
	private String emailAddress;

	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.util.GenerateMyID", parameters={
			@Parameter(name = "idInitValue", value = "2"), // 步长，每次id增加多少
			@Parameter(name = "idLength", value = "10")}) // 初始值	会计系统,初始值1
	@Column(name="clientID")
	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	@Column(name="clientName")
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Column(name="clientShortName")
	public String getClientShortName() {
		return clientShortName;
	}

	public void setClientShortName(String clientShortName) {
		this.clientShortName = clientShortName;
	}

	@Column(name="city")
	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	@Column(name="officeAddress")
	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	@Column(name="contactNumber")
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Column(name="emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
