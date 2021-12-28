package edu.uic.ids517.actionBean;


import java.awt.List;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle; 

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

//import edu.uic.ids517.model.DBaseBean;
import edu.uic.ids517.model.User;
import edu.uic.ids517.model.DbmsUserBean;
import edu.uic.ids517.model.MessageBean;
import edu.uic.ids517.model.SQLConstants;
@ManagedBean
@SessionScoped
public class ActionBeanLoginLogout {

	private MessageBean message;
	private DbmsUserBean userbean;
	private User user;
	//private SQLConstants sqlConstants;
	DbmsUserBean dBaseBean = new DbmsUserBean();
	//DBaseBean dbase = new DBaseBean();
	
	
	public String applicationLogin(){
		return "/dblogin.jsp";
	}
	
	public String processDBLogin() {
		System.out.println("Entering processlogin()");

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		
		//ResourceBundle rb = ResourceBundle.getBundle("edu.uic.ids517.nl.common");

		message = (MessageBean) m.get("messageBean");
		userbean= (DbmsUserBean) m.get("dbmsUserBean");
		//dbase = (DBaseBean)m.get("dBaseBean");
		System.out.println("userbean::"+userbean);
		String sqlquery=null;
		message.setResponseMessage("");
		message.setErrorMessage("");
		System.out.println("message:" +message.getResponseMessage() );
		boolean status=false;
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
		String sessionId = httpServletRequest.getSession().getId();
		String userName = userbean.getUserName();
		//userName = rb.getString("USERNAME");
		//System.out.println("UserName from resource bundle:::"+ userName); 
		userName = userbean.getUserName();
		System.out.println("UserName::"+ userName); 
		System.out.println("Session ID::"+ sessionId);

		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		System.out.println(date);
		/*if(dbase!= null)*/
		if (userbean.getUserName().isEmpty()){
			message.setErrorMessage("User Name cannot be Empty");
			return "FAIL";
		}
		else if (userbean.getPassword().isEmpty()){
			message.setErrorMessage("Password cannot be Empty");
			return "FAIL";			
		}
		else if (userbean.getDbmsHost().isEmpty()){
			message.setErrorMessage("DBms Host cannot be Empty");
			return "FAIL";

		}
		else if (userbean.getDbmsHost().isEmpty()){
			message.setErrorMessage("DBms Host cannot be Empty");
			return "FAIL";
		}
		else if (userbean.getDbms().isEmpty()){
			message.setErrorMessage("dbms  cannot be Empty");
			return "FAIL";
		}
		else if (userbean.getDbSchema().isEmpty()){
			message.setErrorMessage("databaseSchema  cannot be Empty");
			return "FAIL";
		}
		else{
			
				try{
					status=userbean.getDataBaseConnection();
					if(status){
						String  in =  Inet4Address.getLocalHost().getHostAddress();
						System.out.println("in::"+in);
						//userbean.setQueryType("SELECT");
						userbean.executeQuery(SQLConstants.CREATE_TRANSACTION_TABLE);
						//userbean.executeQuery(SQLConstants.CREATE_USER_TABLE);
						userbean.executeQuery(SQLConstants.CREATE_APP_USER_TABLE);
						userbean.executeQuery(SQLConstants.INSERT_DEFAULT_ADMIN);
						userbean.executeQuery(SQLConstants.INSERT_DEFAULT_INSTRUCTOR);
						userbean.executeQuery(SQLConstants.INSERT_DEFAULT_STUDENT);
						userbean.executeQuery("insert into f16g322_app_users  select distinct first_name,last_name,username,student_id,'Student' from f16g322_roster;");
						//userbean.executeQuery(SQLConstants.DROP_SP_UPDATE_ROSTER);
						//userbean.executeQuery(SQLConstants.DROP_SP_UPDATE_TEST);
						//userbean.executeQuery(SQLConstants.CREATE_SP_UPDATE_ROSTER);
						//System.out.println("Roster update SP created");
						//userbean.executeQuery(SQLConstants.CREATE_SP_UPDATE_TEST);
						System.out.println("TEST update SP created");
						
						String sqlquery1 = "INSERT INTO " + userbean.getDbSchema() + ".f16g322_audit_log_table(`UserName`, `IpAddress`, `session_id`, `session_start_date`, `session_end_date`)" + " VALUES ('" + userName + "','" + in + "','" 
								+ sessionId + "','" + date + "','')";
						System.out.println(sqlquery1);
						int executestatus=userbean.executeQuery(sqlquery1)	;
						System.out.println("data inserted " + executestatus);
					}
					else{
						message.setErrorMessage("Unable to connect to the database. Please try again after some time.");
						return "Fail";
					}
				}
				catch(Exception e){
					message.setErrorMessage(e.getMessage());
					e.printStackTrace();
					return "Fail";
				}
			}
		return "SUCCESS";
			 
		}
		
	
	@SuppressWarnings("unused")
	public String processUserLogin(){
		System.out.println("Entering processlogin()");

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		String page = null;
		message = (MessageBean) m.get("messageBean");
		userbean= (DbmsUserBean) m.get("dbmsUserBean");
		//dbase = (DBaseBean)m.get("dBaseBean");
		 user = (User) m.get("user");
		 //user bean to validate user
		 boolean connectStatus =false;
		 String out = "FAIL";
		 if(userbean.getStatus().equals("SUCCESS"))
			{	 	userbean.closeDataBaseConnection();
					connectStatus=userbean.getDataBaseConnection();
					System.out.println("The database is connected");
			}
			else
			{
				connectStatus=userbean.getDataBaseConnection();
				System.out.println("The database is connected");
			}
		
		 if(connectStatus)
		 { 
		String sqlquery=null;
		message.setResponseMessage("");
		message.setErrorMessage("");
		System.out.println("message:" +message.getResponseMessage() );
		boolean status=false;
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
		String sessionId = httpServletRequest.getSession().getId();
		String userName = user.getUserName();
		System.out.println("user name " + userName);
		String userType = user.getUserType();
		System.out.println("user type " + userType);
		String userPassword  = user.getPassword();
		System.out.println("user Password" + userPassword);
		String db_password ;
		user.setUserType(userType);
		
		
		//userbean.setQueryType("SELECT");
		String query= "SELECT userpwd FROM "+ userbean.getDbSchema() +".f16g322_APP_USERS WHERE LOWER(USERNAME) = LOWER('"+userName+"')"+ "AND LOWER(USERTYPE) = LOWER('"+userType+"');";
		userbean.executeQuery(query);
		
		 userbean.getResultList();
		 ArrayList result =  (ArrayList)  userbean.getDataList();
		
		if(result.isEmpty() )
			{message.setErrorMessage("result is empty");
			 return "FAIL";
			}
		else if(result.size() > 1)
		{message.setErrorMessage("Multiple Rows returned");
		 return "FAIL";
		}
		else 
		{
			db_password=(String) result.get(0);
			if (userPassword.equals(db_password))
				return "SUCCESS";
		
				message.setErrorMessage("The password does not match try again");
				return "FAIL";
			
		}
		
		}
		 else
		 {
			 message.setErrorMessage("unable to connect to database");
			 return "FAIL";
		 }
	}
	public String processLogout() {

		

		FacesContext context = FacesContext.getCurrentInstance();
		Map <String, Object> m = context.getExternalContext().getSessionMap();
		userbean = (DbmsUserBean) m.get("dbmsUserBean");
		message = (MessageBean) m.get("messageBean");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
		String sessionId = httpServletRequest.getSession().getId();
		System.out.println("Session ID::"+ sessionId);
		Calendar cal = Calendar.getInstance();
		Date end_date = cal.getTime();
		
		boolean connectStatus= true;
		
			
			if(userbean.getStatus().equals("SUCCESS"))
			{	 	userbean.closeDataBaseConnection();
					connectStatus=userbean.getDataBaseConnection();
					System.out.println("The database is connected");
			}
			else
			{
				connectStatus=userbean.getDataBaseConnection();
				System.out.println("The database is connected");
			}
		
		
		if (connectStatus){
			
			//dbase action
			String sqlquery = "Update " + userbean.getDbSchema() + ".f16g322_audit_log_table set session_end_date = '"+ end_date + "' where session_id = '" + sessionId +"';";
			int executestatus= userbean.executeQuery(sqlquery)	;
			System.out.println(executestatus);
			context.getExternalContext().invalidateSession();
			message.setResponseMessage("You have terminated the database session");
			userbean.closeDataBaseConnection();
			return "SUCCESS";
		}
		else
		{
			message.setErrorMessage("Failed to connect to database.");
			return "FAIL";
		}

		
		
		/*try {
				httpServletRequest.logout();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
	
	}
}

