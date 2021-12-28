package edu.uic.ids517.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MessageBean {
	private String responseMessage;
	private String errorMessage;
	private String flagcondition;
	
	public void init(){
		responseMessage = "";
		errorMessage = "";
		flagcondition = "";
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getFlagcondition() {
		return flagcondition;
	}
	public void setFlagcondition(String flagcondition) {
		this.flagcondition = flagcondition;
	}
}
