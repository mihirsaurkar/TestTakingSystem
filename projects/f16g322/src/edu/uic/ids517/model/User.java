package edu.uic.ids517.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class User {
	private String userId;
	private String userName;
	private String password;
	private String fName;
	private String emailID;
	private String loginStartTime;
	private String loginEndTime;
	private String userType;
	private int user;
	
	
	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		
		if(userType.equals("Administrator")){
			user=1;
		}
		else if(userType.equals("Student")){
			user=2;
		}
		else if(userType.equals("Instructor"))
			user=3;
		
		this.userType = userType;
	}

	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginStartTime() {
		return loginStartTime;
	}
	public void setLoginStartTime(String loginStartTime) {
		this.loginStartTime = loginStartTime;
	}
	public String getLoginEndTime() {
		return loginEndTime;
	}
	public void setLoginEndTime(String loginEndTime) {
		this.loginEndTime = loginEndTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
}
