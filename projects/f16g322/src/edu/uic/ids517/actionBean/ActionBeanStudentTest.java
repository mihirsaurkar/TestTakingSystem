package edu.uic.ids517.actionBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.jsp.jstl.sql.Result;

import edu.uic.ids517.model.DbmsUserBean;
import edu.uic.ids517.model.MessageBean;
import edu.uic.ids517.model.QuestionsBean;
import edu.uic.ids517.model.User;

@ManagedBean
@SessionScoped
public class ActionBeanStudentTest {

	private DbmsUserBean userbean;
	private MessageBean message;
	private User user;
	private Result result;
	private String[] columnNames;
	private boolean renderTests;
	private int testId;
	private boolean renderFeedBack;
	private Result feedbackresult;
	private String[] feedBackcolumnNames;
	private double student_score;
	private boolean feedBackerror;
	private boolean startTesterror;
	private boolean submitTesterror;
	
	int cid[]; 
	int testid[]; 
	String CRN[];
	String course[]; 
	
	List <Integer> cid_list;
	List <Integer> testid_list;
	List <String> crn_list;
	List <String> course_list;
	List <QuestionsBean> questions;
	
	
	
	
	

	public boolean isSubmitTesterror() {
		return submitTesterror;
	}

	public void setSubmitTesterror(boolean submitTesterror) {
		this.submitTesterror = submitTesterror;
	}

	public boolean isFeedBackerror() {
		return feedBackerror;
	}

	public void setFeedBackerror(boolean feedBackerror) {
		this.feedBackerror = feedBackerror;
	}

	public boolean isStartTesterror() {
		return startTesterror;
	}

	public void setStartTesterror(boolean startTesterror) {
		this.startTesterror = startTesterror;
	}

	public double getStudent_score() {
		return student_score;
	}

	public void setStudent_score(double student_score) {
		this.student_score = student_score;
	}

	public List<QuestionsBean> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionsBean> questions) {
		this.questions = questions;
	}

	

	public String[] getFeedBackcolumnNames() {
		return feedBackcolumnNames;
	}

	public void setFeedBackcolumnNames(String[] feedBackcolumnNames) {
		this.feedBackcolumnNames = feedBackcolumnNames;
	}

	public Result getFeedbackresult() {
		return feedbackresult;
	}

	public void setFeedbackresult(Result feedbackresult) {
		this.feedbackresult = feedbackresult;
	}

	public boolean isRenderFeedBack() {
		return renderFeedBack;
	}

	public void setRenderFeedBack(boolean renderFeedBack) {
		this.renderFeedBack = renderFeedBack;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public List<Integer> getCid_list() {
		return cid_list;
	}

	public void setCid_list(List<Integer> cid_list) {
		this.cid_list = cid_list;
	}

	public List<Integer> getTestid_list() {
		return testid_list;
	}

	public void setTestid_list(List<Integer> testid_list) {
		this.testid_list = testid_list;
	}

	public List<String> getCrn_list() {
		return crn_list;
	}

	public void setCrn_list(List<String> crn_list) {
		this.crn_list = crn_list;
	}

	public List<String> getCourse_list() {
		return course_list;
	}

	public void setCourse_list(List<String> course_list) {
		this.course_list = course_list;
	}

	public int[] getCid() {
		return cid;
	}

	public void setCid(int[] cid) {
		this.cid = cid;
	}

	public int[] getTestid() {
		return testid;
	}

	public void setTestid(int[] testid) {
		this.testid = testid;
	}

	public String[] getCRN() {
		return CRN;
	}

	public void setCRN(String[] cRN) {
		CRN = cRN;
	}

	public String[] getCourse() {
		return course;
	}

	public void setCourse(String[] course) {
		this.course = course;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public boolean isRenderTests() {
		return renderTests;
	}

	public void setRenderTests(boolean renderTests) {
		this.renderTests = renderTests;
	}

	@SuppressWarnings("rawtypes")
	public void showTests()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Map <String, Object> m = context.getExternalContext().getSessionMap();
		userbean = (DbmsUserBean) m.get("dbmsUserBean");
		message = (MessageBean) m.get("messageBean");
		user = (User) m.get("user");
		boolean connectStatus = false;
		renderTests=false;
		
		String query;
		
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
			query = "select c_id, Test_id,CRN,course,start_date,end_date from f16g322_Test t "+
					"where   t.c_id in (select c_id from f16g322_Roster where " +
					" username ='"+ user.getUserName()+"' );" ;
			
			System.out.println("Query "+query);
			userbean.executeQuery(query);
			result = userbean.getResult();
			columnNames = result.getColumnNames();
			renderTests = true;
			
			Object[][] sData= userbean.getObjectexecuteQuery(query);
			
			 cid = new int[sData.length];
			 testid = new int[sData.length];
			 CRN = new String[sData.length];
			 course = new String[sData.length];
			
			 cid_list = new ArrayList(sData.length);
			 testid_list= new ArrayList(sData.length);
			 crn_list = new ArrayList(sData.length);
			 course_list= new ArrayList(sData.length);
			 
			for( int i =0; i<sData.length;i++)
			{
				cid[i] = (Integer) sData[i][0];
				testid[i] =(Integer)sData[i][1];
				CRN[i] = (String)sData[i][2];
				course[i] = (String)sData[i][3];
				
				cid_list.add(cid[i]);
				testid_list.add(testid[i]);
				crn_list.add(CRN[i]);
				course_list.add(course[i]);
				
				
			}
			renderTests=true;
			userbean.closeDataBaseConnection();
			//renderTests=true;
		}
		else{
			message.setErrorMessage("Failed to connect to database.");
			renderTests = false;
			}
		
	}
	
	public void showFeedBack()
	{   startTesterror=false;
		submitTesterror=false;
		FacesContext context = FacesContext.getCurrentInstance();
		Map <String, Object> m = context.getExternalContext().getSessionMap();
		userbean = (DbmsUserBean) m.get("dbmsUserBean");
		message = (MessageBean) m.get("messageBean");
		boolean connectStatus = false;
		
		String query;
		
		
		
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
			
			query = "select q.Test_id,s.Q_id,s.students_answer,q.correct_answer "
					+ "from f16g322_students_answers s, f16g322_Question q "+
						"where s.q_id = q.q_id and Test_id = "+ testId+" and s.username = '"+user.getUserName()+"' and q.test_id in"
								+ " (select test_id from f16g322_test where curdate() > end_date);";
			
				
			System.out.println("Query "+query);
			userbean.executeQuery(query);
			
			feedbackresult = userbean.getResult();
			
			feedBackcolumnNames = feedbackresult.getColumnNames();
			
			if(feedbackresult.getRowCount() == 0)
			 {
				message.setErrorMessage("No Feed Back available");
				//feedBackcolumnNames = null;
				System.out.println("Row count is 0");
				feedBackerror=true;
				startTesterror=false;
				renderFeedBack = false;
			 }
			else{	
				feedBackerror=true;
				startTesterror=false;
			renderFeedBack = true;
			}
		}
		else{
			message.setErrorMessage("Failed to connect to database.");
			feedBackerror=true;
			startTesterror=false;
			renderFeedBack = false;
		}
	}
	
public String takeTest(){
	submitTesterror=false;
	feedBackerror=false;
	
	FacesContext context = FacesContext.getCurrentInstance();
	Map <String, Object> m = context.getExternalContext().getSessionMap();
	userbean = (DbmsUserBean) m.get("dbmsUserBean");
	message = (MessageBean) m.get("messageBean");
	User user = (User) m.get("user");
	boolean connectStatus = false;
	String userName = user.getUserName();
	String query;
	
	
	
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
	
	//System.out.println(" TEST is "+ testId);
	//select q_id,Question_type,question_text from question where test_id = tesstId ;
	
	if(connectStatus)
	{
		
		query = "select q_id,Question_type,question_text,test_id from f16g322_question where test_id ="+testId+" and  "+
				" ('"+userName+"',"+testId+") not in"+ 
				"(select distinct sa.username,q.test_id	from  f16g322_students_answers sa, f16g322_question q"+
				" where q.Q_id = sa.Q_id);";
		
			
		System.out.println("Query "+query);
		userbean.executeQuery(query);
		
		Object[][] sData = userbean.getObjectexecuteQuery(query);
		
		questions =  new ArrayList<QuestionsBean>(sData.length);
		if(sData.length ==0)
		{
			message.setErrorMessage("There are no tests avaialable currently please check later");
			feedBackerror=false;
			startTesterror=true;
			return "FAIL";
		}
		else{
			
			for (int i = 0 ; i<sData.length; i++)
			{
				QuestionsBean qb= new QuestionsBean();
				
				qb.setQid((Integer)sData[i][0]);
				qb.setQuestiontext((String)sData[i][1]);
				qb.setQuestiontype((String)sData[i][2]);
				qb.setTestid((Integer)sData[i][3]);
				
				questions.add(qb);
			}
			
		}
		
		userbean.closeDataBaseConnection();
		
		return "SUCCESS";
	}
	else{
		message.setErrorMessage("Failed to connect to database.");
		feedBackerror=false;
		startTesterror=true;
		return "FAIL";
	}
	
}


public String submitTest()
{   submitTesterror = true;
	feedBackerror = false;
	startTesterror = false;
	FacesContext context = FacesContext.getCurrentInstance();
	Map <String, Object> m = context.getExternalContext().getSessionMap();
	userbean = (DbmsUserBean) m.get("dbmsUserBean");
	message = (MessageBean) m.get("messageBean");
	boolean connectStatus = false;
	
	String query;
	
	
	
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
	
	//System.out.println(" TEST is "+ testId);
	//select q_id,Question_type,question_text from question where test_id = tesstId ;
	
	if(connectStatus)
	{	 int testid = 0;
			for( int i =0 ; i< questions.size();i++)
			{	
				QuestionsBean qb ;
				
				qb = questions.get(i);
				
				query = " insert into f16g322_students_answers (username,Q_id,students_answer) "+
						"values ('"+user.getUserName()+"',"+qb.getQid()+",'"+qb.getAnswers()+"');";
				System.out.println("Query " + query);
				testid=qb.getTestid();
				userbean.executeQuery(query);
			}
			
			
			query = "select s.q_id,s.students_answer,q.correct_answer,q.tolerance,t.test_id "
					+ " from f16g322_students_answers s, f16g322_Question q, f16g322_test t " +
						"where s.q_id = q.q_id and q.test_id = t.test_id and t.test_id = "+testId+
					" and s.username ='"+user.getUserName()+"';";
			System.out.println("Query "+ query);
			Object[][] sData = userbean.getObjectexecuteQuery(query);
			
			int score =0;
			int cid =0;
			for ( int i=0; i<sData.length;i++)
				{	
					
					try{
					double ans= Double.parseDouble((String)sData[i][1]);
					
					double correct_ans = Double.parseDouble((String)sData[i][2]);
					double tolerance = Double.parseDouble((String)sData[i][3]);
					 cid = (Integer) sData[i][4];
					 System.out.println("The correct_ans " + correct_ans+ " tolerance "+ tolerance+" answer :"+ans);
				if ( ans <= (correct_ans + tolerance) && ans >= (correct_ans - tolerance)){
					 score=score + 1;
					
				}	
					}
					catch(Exception e)
					{
						e.printStackTrace();
						message.setErrorMessage("Number format error in data fetched from database. Notify instructor");
						return "FAIL";
					}
			}
			
			student_score = score;
			score =0;
			if(testid !=0 && cid !=0){
				query = "insert into f16g322_students_scores (username,c_id,test_id,score) values ('"+user.getUserName()+"',"+cid+","+testid+","+student_score+");";	
				System.out.println("QUERy " + query);
				userbean.executeQuery(query);
				userbean.closeDataBaseConnection();
				message.setResponseMessage("The score is " + student_score);;
				message.setErrorMessage("");
				return "SUCCESS";
			}else{	
				message.setErrorMessage("The data is not consistent please check");
				return "FAIL";
					}
			
	}
	else{
		System.out.println("Database not connected");
		return "FAIL";
	}
	
}

}
