package edu.uic.ids517.actionBean;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.apache.myfaces.util.FilenameUtils;

//import edu.uic.ids517.model.DBaseBean;
import edu.uic.ids517.model.DataBean;
import edu.uic.ids517.model.User;
import edu.uic.ids517.model.DbmsUserBean;
import edu.uic.ids517.model.MessageBean;
import edu.uic.ids517.model.SQLConstants;

@ManagedBean
@SessionScoped
public class ActionBeanImport {

	String uploadedFileContents;

	@ManagedProperty(value="#{messageBean}")
	MessageBean message;
	boolean fileImport;
	boolean fileImportError;
	String fileName;
	long fileSize;
	private String table;
	private UploadedFile uploadedFile; 
	private String fileLabel; 
	String fileContentType;
	String tableName;
	private FacesContext context;
	private User user;
	private String crn;
	private String course;
	private String startDate;
	private String endDate;
	private String duration;
	private String rosterOrTest;
	private String fileType;
	private List<String> tableNameExport=new ArrayList<String>();
	private boolean checkRosterOrTest;
	private DbmsUserBean userbean ;
	private String downloadTable;
	private String downloadfileName;
	
	
	public String getDownloadfileName() {
		return downloadfileName;
	}

	public void setDownloadfileName(String downloadfileName) {
		this.downloadfileName = downloadfileName;
	}

	public String getDownloadTable() {
		return downloadTable;
	}

	public void setDownloadTable(String downloadTable) {
		this.downloadTable = downloadTable;
	}

	public boolean isCheckRosterOrTest() {
		return checkRosterOrTest;
	}

	public void setCheckRosterOrTest(boolean checkRosterOrTest) {
		this.checkRosterOrTest = checkRosterOrTest;
	}

	public String getRosterOrTest() {
		
		return rosterOrTest;
		
	}

	public void setRosterOrTest(String rosterOrTest) {
		
		this.rosterOrTest = rosterOrTest;
		//this.methodRosterOrTest();
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	String contextPath;
	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}
	
	public DbmsUserBean getDbmsUserBean() {
		return dbmsUserBean;
	}

	public void setDbmsUserBean(DbmsUserBean dbmsUserBean) {
		this.dbmsUserBean = dbmsUserBean;
	}

	public MessageBean getmBean() {
		return mBean;
	}

	public void setmBean(MessageBean mBean) {
		this.mBean = mBean;
	}

	public List<String> getTableNameExport() {
		return tableNameExport;
	}

	public void setTableNameExport(List<String> tableNameExport) {
		this.tableNameExport = tableNameExport;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getUploadedFileContents() {
		return uploadedFileContents;
	}

	public void setUploadedFileContents(String uploadedFileContents) {
		this.uploadedFileContents = uploadedFileContents;
	}

	public MessageBean getMessage() {
		return message;
	}

	public void setMessage(MessageBean message) {
		this.message = message;
	}

	public boolean isFileImport() {
		return fileImport;
	}

	public void setFileImport(boolean fileImport) {
		this.fileImport = fileImport;
	}

	public boolean isFileImportError() {
		return fileImportError;
	}

	public void setFileImportError(boolean fileImportError) {
		this.fileImportError = fileImportError;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getFileLabel() {
		return fileLabel;
	}

	public void setFileLabel(String fileLabel) {
		this.fileLabel = fileLabel;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public ArrayList<DataBean> getDataBean() {
		return dataBean;
	}

	public void setDataBean(ArrayList<DataBean> dataBean) {
		this.dataBean = dataBean;
	}

	public int getNumberRows() {
		return numberRows;
	}

	public void setNumberRows(int numberRows) {
		this.numberRows = numberRows;
	}

	public boolean isRenderParseTable() {
		return renderParseTable;
	}

	public void setRenderParseTable(boolean renderParseTable) {
		this.renderParseTable = renderParseTable;
	}

	/* public DBaseBean getDbase() {
		return dbase;
	}

	public void setDbase(DBaseBean dbase) {
		this.dbase = dbase;
	}
	*/

	public DbmsUserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(DbmsUserBean userBean) {
		this.userBean = userBean;
	}

	ArrayList<DataBean> dataBean;
	int numberRows;
	boolean renderParseTable;
	//DBaseBean dbase = new DBaseBean();
	@ManagedProperty(value="#{dbmsUserBean}")
	DbmsUserBean userBean;
	DbmsUserBean dbmsUserBean = new DbmsUserBean();
	MessageBean mBean = new MessageBean();

	@PostConstruct  
	public void init() {
		 context = FacesContext.getCurrentInstance(); 
		Map <String, Object> m = context.getExternalContext().getSessionMap();  
		message = (MessageBean)m.get("MessageBean");
		//dbase = (DBaseBean)m.get("dBaseBean");
		userBean = (DbmsUserBean)m.get("DbmsUserBean");
		contextPath = context.getExternalContext().getRealPath("/temp");
		
		/*message
		= (MessageBean) FacesContext.getCurrentInstance().getApplication()
		.getELResolver().getValue(context, null, "messageBean");

		userBean
		= (DbmsUserBean) FacesContext.getCurrentInstance().getApplication()
		.getELResolver().getValue(context, null, "dbmsUserBean");
		System.out.println("after");
		*/
	} 
	
	public String processFileUpload() {
		String status = "SUCCESS";
		uploadedFileContents = null;
		mBean.setErrorMessage("");
		
		// test println only
		System.out.println("context path: " + contextPath);
		String path = contextPath + "temp";
		System.out.println("path: " + path);
		File tempFile = null;
		FileOutputStream fos = null;
		int n = 0;
		mBean.setErrorMessage("");
		fileImport = false;
		fileImportError = true;
		try {
		fileName = uploadedFile.getName();
		System.out.println("File name :" + fileName);
		File f = new File(fileName);
		fileSize = uploadedFile.getSize();
		fileContentType = uploadedFile.getContentType();
		// next line if want upload in String for memory processing
		// uploadedFileContents = new String(uploadedFile.getBytes());
		tempFile = new File(contextPath + "/" + f.getName());
		//tempFile.createNewFile();
		System.out.println("Temp File :" + tempFile);
		//System.out.println("tempfile: "+ tempFile);
		System.out.println("Temp file exists " + tempFile.exists() + " Abs path "+tempFile.getAbsolutePath());
		//tempFile.mkdirs();
		tempFile.createNewFile();
		System.out.println("After creating file");
		System.out.println("Temp file exists " + tempFile.exists() + " Abs path "+tempFile.getAbsolutePath());
		fos = new FileOutputStream(tempFile);
		// next line if want file uploaded to disk
		fos.write(uploadedFile.getBytes());
		fos.close();
		Scanner s = new Scanner(tempFile);
		String input;
		//input = s.nextLine();
		while(s.hasNext()) {
		input = s.nextLine();
		n++;
		// test only println
		// Process data//
		}
		
		s.close();
		numberRows = n;
		fileImport = true;
		} // end try
		catch (IOException e) {
	    mBean.setErrorMessage("Error Reading from file" + tempFile );
	    e.printStackTrace();
		fileImportError = true;
		} // end catch
		catch (Exception e) {
		    mBean.setErrorMessage("error during upload" + tempFile );
		    e.printStackTrace();
			fileImportError = true;
			} // end catch
		if(status.equalsIgnoreCase("SUCCESS"))
			this.uploadFile();
		return status;
		}
		
	@SuppressWarnings("unchecked")
	public void uploadFile() {
		
		System.out.println("Entering processlogin()");
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		
		message = (MessageBean) m.get("messageBean");
		dbmsUserBean= (DbmsUserBean) m.get("dbmsUserBean");
		//Connection con = dbmsUserBean.getCon();
		//CallableStatement cstmt = null;
		InputStream is;
		String message = "";
		String query;
		// String tableSelected = "user_table";
		 int check=0;
		// ArrayList<String> perItem1=new ArrayList<String>();
		 String[] perItem = null;
		boolean connectStatus=true;
		
		if(!dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
		{
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		
		if (connectStatus){	
				
				// insert values into course (C_ID,CRN,course)
				
			query=" INSERT INTO f16g322_course (crn, course) select * from  ( select '"+crn+"','"+course+"') as temp WHERE NOT EXISTS (SELECT crn FROM f16g322_course WHERE crn ='"+crn+"' and course ='"+course+"') LIMIT 1;";
				
			dbmsUserBean.executeQuery(query);
				
				if (checkRosterOrTest)
				{
					// test
					
					try {
						
						dbmsUserBean.executeQuery("DROP TABLE IF EXISTS f16g322_TEMP_TEST");
						System.out.println("The f16g322_TEMP_TEST is dropped");
						 query = "CREATE TABLE f16g322_TEMP_TEST (QType varchar(10),	Question varchar(100),answer varchar (100), tolerance varchar(100)); ";
						System.out.println("The query is "+ query);
												
						dbmsUserBean.executeQuery(query);
						System.out.println("the temp_test table is created.");
						is = uploadedFile.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(is));
						String line ;;
						
						
								
						int checkMsg = 0;
						while ((line= br.readLine()) != null) {

							//line = br.readLine();
							if (line == null) {
								break;
							}
							if(fileType.equalsIgnoreCase("csv"))
								perItem = line.split(",");
								else if (fileType.equalsIgnoreCase("txt"))
									perItem = line.split("\t");
							String insQuery = "Insert into f16g322_TEMP_TEST ( QType ,	Question ,answer , tolerance) values(";
							for (int i = 0; i < perItem.length; i++) {
								// userId=perItem[0];

								insQuery = insQuery + "'" + perItem[i] + "'";
								if (i < perItem.length - 1) {
									insQuery = insQuery + ",";
								}

							}
							insQuery = insQuery + ");";
							
							
							System.out.println("insQuery " + insQuery);
							check = dbmsUserBean.executeQuery(insQuery);
							if (check != 1) {
								checkMsg = 1;
							}
						}
						if (checkMsg == 0) {
							message = "File Uploaded Successfully";
						} else {
							message = "Upload failed";
						}
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(message));

					} catch (IOException e) {
						// TODO Auto-generated catch block
						message = e.getMessage();
						e.printStackTrace();
						//return message;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					query= "select c_id from f16g322_course where course ='" +course + "'and crn='"+crn+"';";
					
					dbmsUserBean.executeQuery(query);
					dbmsUserBean.getResultList();
					
					List <String> rs = new ArrayList<String>();
					rs = dbmsUserBean.getDataList();
					
					int c_id = Integer.parseInt(rs.get(0));
					
					query="insert into f16g322_Test (c_id,CRN, course, start_date, end_date, duration)"+
						"values ("+c_id+",'"+crn+"','"+course+"',STR_TO_DATE('"+startDate+"','%m/%d/%Y'),STR_TO_DATE('"+endDate+"','%m/%d/%Y'),'"+duration+"');";
					System.out.println("Query "+query);
					dbmsUserBean.executeQuery(query);
					
					query = "update  f16g322_Test set availability = case when  curdate() between start_date and end_date then 'Y' else 'N' end ;";
					dbmsUserBean.executeQuery(query);
					
					//may need create a new connection as result will be closed.
					query = "select test_id from f16g322_test where CRN ='"+crn+"' and course = '"+course+"' and start_date=STR_TO_DATE('"+startDate+"', '%m/%d/%Y') and end_date = STR_TO_DATE('"+endDate+"','%m/%d/%Y') ;";
					System.out.println("the Query " + query);
					int test_id = 0;
					Object[][] ob = dbmsUserBean.getObjectexecuteQuery(query);
					System.out.println("lenght of result object " + ob.length);
					for (int i = 0; i< ob.length;i++)	
					{			
						
						test_id = (Integer) ob[i][0];
					
					}
					
					if (test_id >0)
					{query = "insert into f16g322_Question (test_id,Question_type,question_text,correct_answer,tolerance)"+
							"select "+test_id+",Qtype,question,answer,tolerance from f16g322_temp_test";
					
					dbmsUserBean.executeQuery(query);
					
					}
					else
						System.out.println("TEST_id not found data not inserted");
					
					
				}
				else{
					//ROSTER
					
					try {

						is = uploadedFile.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(is));
						String line = br.readLine();
						if(fileType.equalsIgnoreCase("csv"))
							perItem = line.split(",");
							else if (fileType.equalsIgnoreCase("txt"))
								perItem = line.split("\t");
						String insertCol = perItem[0];
						// int j=0;
						for (int i = 1; i < perItem.length; i++) {
							insertCol = insertCol + "," + perItem[i];
						}
						 insertCol=insertCol+")";
						
					
									 query = "CREATE TABLE f16g322_TEMP_ROSTER (";
										query = query+ perItem[0] +" varchar(200),";
										for (int j = 1; j < perItem.length; j++)
										{
											
											if (j==(perItem.length -1) )
												query = query+ perItem[j] + " varchar(200))";
											else 
												query = query+ perItem[j] + " varchar(200) ,";
										}
								
									
									System.out.println("The query is "+ query);
									
									dbmsUserBean.executeQuery("DROP TABLE IF EXISTS f16g322_TEMP_ROSTER");
									System.out.println("The temp_roster is dropped");
									dbmsUserBean.executeQuery(query);
									System.out.println("The f16g322_TEMP_ROSTER is created.");
									
						
						int checkMsg = 0;
						while (line != null) {

							line = br.readLine();
							if (line == null) {
								break;
							}
							if(fileType.equalsIgnoreCase("csv"))
								perItem = line.split(",");
								else if (fileType.equalsIgnoreCase("txt"))
									perItem = line.split("\t");
							String insQuery = "Insert into f16g322_TEMP_ROSTER ( "
									+ insertCol + " values(";
							for (int i = 0; i < perItem.length; i++) {
								// userId=perItem[0];

								insQuery = insQuery + "'" + perItem[i] + "'";
								if (i < perItem.length - 1) {
									insQuery = insQuery + ",";
								}

							}
							insQuery = insQuery + ");";
							// String message1=new String();
							//PreparedStatement statement = con.prepareStatement(insQuery);
							//int check = statement.executeUpdate();
							// message=con.execQuery();
							System.out.println("insQuery " + insQuery);
							check = dbmsUserBean.executeQuery(insQuery);
							if (check != 1) {
								checkMsg = 1;
							}
						}
						if (checkMsg == 0) {
							message = "File Uploaded Successfully";
						} else {
							message = "Upload failed";
						}
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(message));
					
						query= "select c_id from f16g322_course where course ='" +course + "'and crn='"+crn+"';";
						
						dbmsUserBean.executeQuery(query);
						dbmsUserBean.getResultList();
						
						List <String> rs = new ArrayList<String>();
						rs = dbmsUserBean.getDataList();
						
						int c_id = Integer.parseInt(rs.get(0));
						
						query = "insert into f16g322_roster(Last_Name, First_Name, Username, Student_ID, Last_Access, Availability, Total, Exam01, Exam02, Exam03, Project)"+
								"select * from f16g322_temp_roster";
						dbmsUserBean.executeQuery(query);
						
						query= "update f16g322_roster set c_id = "+c_id+" where c_id is null";
						
						dbmsUserBean.executeQuery(query);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						message = e.getMessage();
						e.printStackTrace();
						//return message;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			
		}
		else{
			System.out.println("Could not connect to DB");
			
		}			
		dbmsUserBean.closeDataBaseConnection();
		/*try {
			cstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void methodRosterOrTest()
	{
		if (rosterOrTest.equalsIgnoreCase("roster"))
			checkRosterOrTest = false;
		else if (rosterOrTest.equalsIgnoreCase("test"))
			checkRosterOrTest=  true;
		else 
			checkRosterOrTest = false;
		
	}
		
	


	

	
	@SuppressWarnings("null")
	public void downloadFile()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Map <String, Object> m = context.getExternalContext().getSessionMap();
		ExternalContext ec = context.getExternalContext();
		FileOutputStream fos = null;
		HttpServletResponse response = (HttpServletResponse) ec.getResponse();  
		dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		user = (User) m.get("user");
		
		boolean connectStatus=true;
		
		if(dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
		{
			dbmsUserBean.closeDataBaseConnection();
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		else
		{
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
			
			
		}
		
		if (connectStatus){	
			
			String path = ec.getRealPath("/temp");
			String fileNameBase = downloadTable+".csv";
			downloadfileName = path + "/" +user.getUserName()+ "_" + fileNameBase;
			System.out.println("Filname : "+downloadfileName);
			String query = "SELECT * FROM f16g322_"+downloadTable +";";
			
			System.out.println("The query is "+ query);
			File f = new File(downloadfileName);
			 List <String> columnNames = dbmsUserBean.getColumns("f16g322_"+downloadTable);
			 
			 Object[][] sData = dbmsUserBean.getObjectexecuteQuery(query);
			 StringBuffer sb = new StringBuffer();
			 try{
				 fos = new FileOutputStream(f);
				 for(int i = 0; i<columnNames.size();i++){
					 sb.append( columnNames.get(i)+"," );
						 				 
				 }
				 sb.append("\n");
				 fos.write(sb.toString().getBytes());
				 for(int i = 0; i < sData.length; i++) {
					 sb = new StringBuffer();
					 for(int j=0; j<sData[0].length; j++) {
					 sb.append(sData[i][j].toString() + ",");
					 }
					 // System.out.println();
					 sb.append("\n");
					 fos.write(sb.toString().getBytes());
					 }
					 fos.flush();
					 fos.close();
					 
					 response.setContentType("application/x-download");
					 response.setHeader("Content-disposition", "attachment; filename=" +user.getUserName()+ "_" + fileNameBase);  
					 //response.setContentLength((int) f.length()); 
					 FileReader  fileReader = new FileReader(downloadfileName);
					 BufferedReader bufferedReader = new BufferedReader(fileReader);  	
					 sb = new StringBuffer();
					    ServletOutputStream out = null;
					    String line;
					    out = response.getOutputStream();
						while ((line = bufferedReader.readLine()) != null) {
							sb.append(line);
							sb.append("\n");
							  
				            //out.flush();  
						}
						fileReader.close();
						out.write(sb.toString().getBytes());
						context.responseComplete();
					    
					 //context.responseComplete();
			 } catch (FileNotFoundException e) {
					e.printStackTrace();
					
				} catch (IOException e) {
					e.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
				}
			
		}
		else{
			System.out.println("Could not connect to DB");
			
		}			
		dbmsUserBean.closeDataBaseConnection();
		
		
	}
}
