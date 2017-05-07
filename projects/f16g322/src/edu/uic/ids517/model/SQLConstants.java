package edu.uic.ids517.model;

public class SQLConstants {
	//public static final String CREATE_USER_TABLE ="create table IF not exists user (userName varchar(16), password varchar(16), fName varchar(16),emailId varchar(20));";
	public static final String CREATE_TRANSACTION_TABLE=" create table IF not exists  f16g322_audit_log_table (UserName Varchar(45), IPAddress Varchar(45), session_Id varchar(45), session_start_date varchar(45), session_end_date varchar(45));";
	public static final String CREATE_APP_USER_TABLE="CREATE TABLE IF NOT EXISTS f16g322_APP_USERS ("+
													   " fname VARCHAR(50),"+
													    "lname VARCHAR(50),"+
													    "userName VARCHAR(50) PRIMARY KEY,"+
													    "userpwd VARCHAR(100),"+
													    "userType VARCHAR(50));";
	/*public static final String CREATE_TEST_TABLE= "create table IF NOT EXISTS TEST_TABLE "
													+ "(CRN varchar(10),professor varchar(50),"
													+ "test_id int auto_increment primary key);";*/
	
	public static final String CREATE_METADATA_TABLE="create table IF not exists f16g322_TableMetaData (table_name varchar(255),column_name varchar(255),column_type varchar(255));";
	public static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS ";
	public static final String SELECT_USER_TABLE = "SELECT * FROM user";
	public static final String SELECT_TABLE="SELECT * FROM ";
	public static final String INSERT_USER="INSERT INTO user (userName,password,fName,emailId) values ";
	//public static final String INSERT_transaction="INSERT INTO transaction(userName,Starttime) values ";
	//public static final String DELETE_METADATA="delete from TableMetaData where table_name='";
	public static final String LIST_TABLE_USER="SELECT TABLE_NAME FROM information_schema.tables where TABLE_NAME NOT IN (\"user\",\"f16g322_audit_log_table\",\"TableMetaData\") AND TABLE_SCHEMA=";
	public static final String INSERT_DEFAULT_ADMIN = "insert ignore into f16g322_app_users SET fname = 'Sys', lname ='Root', username='root', userpwd='123', usertype='Administrator';";
    public static final String INSERT_DEFAULT_INSTRUCTOR="insert ignore into f16g322_app_users SET fname = 'Prof', lname ='Instructor', username='Professor', userpwd='456', usertype='Instructor';";
    public static final String INSERT_DEFAULT_STUDENT ="insert ignore into f16g322_app_users SET fname = 'Student', lname ='Student', username='student', userpwd='789', usertype='Student';";
    public static final String SELECT_USER_QUERY = "SELECT * FROM <tableName> ";

            
    public static final String CREATE_TABLE_COURSE_ROSTER = "CREATE TABLE If NOT EXISTS f16g322_course ("+
    		  												"C_ID int(11) NOT NULL AUTO_INCREMENT,"+
    		  												"CRN varchar(10) NOT NULL ,"+
    		  												"course varchar(10),"+
    		  												"PRIMARY KEY (C_ID,CRN));"; 
    			  
    public static final String CREATE_TABLE_ROSTER = "Create table If not exists f16g322_Roster( C_ID  int ,"+
    													"Last_Name varchar(50) ,"+
    													"First_Name varchar(50),"+
    													"Username varchar(50),"+	
    													"Student_ID varchar(12),"+	
    													"Last_Access varchar(50),"+
    													"Availability varchar(10),"+
    													"Total double,"+
    													"Exam01 double,	"+
    													"Exam02 double,"+	
    													"Exam03 double,"+
    													"Project double);";
        
    public static final String CREATE_TABLE_TEST = "Create table If not exists f16g322_Test ( c_id int , CRN varchar(10), "+
    												"course varchar(10), "+
    												"test_ID int auto_increment primary key, "+
    												"availability varchar(10),"+
    												"start_date date,"+
    												"end_date date,"+
    												"duration int);";

    public static final String CREATE_TABLE_QUESTION = "CREATE TABLE f16g322_question ( test_id int(11),"+
    													"Q_id int(11) NOT NULL AUTO_INCREMENT, "
    													+ "Question_type varchar(100) , "
    													+ "question_text varchar(500) , "
    													+ "correct_answer varchar(500), "
    													+ "tolerance varchar(10), "
    													+ "PRIMARY KEY (Q_id) );";
  
    public static final String CREATE_TABLE_STUDENTS_ANSWERS = "Create table If not exists f16g322_students_answers (username varchar(50),"+
    															"Q_id int,"+
    															"students_answer varchar(50));";
    
    public static final String CREATE_TABLE_STUDETNTS_SCORES = "Create table If not exists f16g322_students_scores (username varchar(50), c_id int , "+
																"test_ID int, "+
																"score int);";
     
    									    		
     
}
