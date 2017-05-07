package edu.uic.ids517.actionBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.jsp.jstl.sql.Result;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

//import edu.uic.ids517.model.DBaseBean;
import edu.uic.ids517.model.DbmsUserBean;
import edu.uic.ids517.model.MessageBean;
import edu.uic.ids517.model.SQLConstants;
import edu.uic.ids517.model.User;

import java.sql.ResultSet;
@ManagedBean
@SessionScoped
public class ActionBeanDatabaseAccess {

	private String tableName;
	private List <String> columnNames;
	private List <String> tableViewList;
	private List <String> columnNamesSelected;
	private DbmsUserBean dbmsUserBean;
	private Result result;
	private MessageBean mBean;
	private boolean tableListRendered;
	private boolean renderSet;
	private boolean renderCols;
	private boolean renderQuery;
	private boolean renderUserQuery;
	private boolean columnListRendered;
	private String userQuery;
	int numberColumns;
	int numberRows;
	private List<String> createTables;
	//private boolean renderCreateTables;
	
	
	
	public DbmsUserBean getDbmsUserBean() {
		return dbmsUserBean;
	}
	public void setDbmsUserBean(DbmsUserBean dbmsUserBean) {
		this.dbmsUserBean = dbmsUserBean;
	}
	public List<String> getCreateTables() {
		return createTables;
	}
	public void setCreateTables(List<String> createTables) {
		this.createTables = createTables;
	}
	public boolean isRenderUserQuery() {
		return renderUserQuery;
	}
	public void setRenderUserQuery(boolean renderUserQuery) {
		this.renderUserQuery = renderUserQuery;
	}
	public String getUserQuery() {
		return userQuery;
	}
	public void setUserQuery(String userQuery) {
		this.userQuery = userQuery;
	}

	public boolean isRenderQuery() {
		return renderQuery;
	}
	public void setRenderQuery(boolean renderQuery) {
		this.renderQuery = renderQuery;
	}
	public boolean isRenderCols() {
		return renderCols;
	}
	public void setRenderCols(boolean renderCols) {
		this.renderCols = renderCols;
	}
	public int getNumberColumns() {
		return numberColumns;
	}
	public void setNumberColumns(int numberColumns) {
		this.numberColumns = numberColumns;
	}
	public int getNumberRows() {
		return numberRows;
	}
	public void setNumberRows(int numberRows) {
		this.numberRows = numberRows;
	}
	public boolean isRenderSet() {
		return renderSet;
	}
	public void setRenderSet(boolean renderSet) {
		this.renderSet = renderSet;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public boolean isTableListRendered() {
		return tableListRendered;
	}
	public void setTableListRendered(boolean tableListRendered) {
		this.tableListRendered = tableListRendered;
	}
	public boolean isColumnListRendered() {
		return columnListRendered;
	}
	public void setColumnListRendered(boolean columnListRendered) {
		this.columnListRendered = columnListRendered;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<String> getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}
	public List<String> getTableViewList() {
		return tableViewList;
	}
	public void setTableViewList(List<String> tableViewList) {
		this.tableViewList = tableViewList;
	}
	public List<String> getColumnNamesSelected() {
		return columnNamesSelected;
	}
	public void setColumnNamesSelected(List<String> columnNamesSelected) {
		this.columnNamesSelected = columnNamesSelected;
	}
	
	public void displayTableName(){
		System.out.println("Inside display table Names");
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		boolean connectStatus=true;
		DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		mBean = (MessageBean) m.get("messageBean");
		mBean.setErrorMessage("");
		mBean.setResponseMessage("");
		if(dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
		{
			dbmsUserBean.closeDataBaseConnection();
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		else
		{
			connectStatus=dbmsUserBean.getDataBaseConnection();	
		}
		if(connectStatus)
		{
			mBean.setResponseMessage("List of Tables");
			mBean.setErrorMessage("");
			
			
			tableViewList = dbmsUserBean.getTables();
			
			if(tableViewList.isEmpty() || tableViewList == null){
				mBean.setErrorMessage("No table exists");
				mBean.setResponseMessage("");
				tableListRendered = false;
			}
			System.out.println("datalist::"+tableViewList.size());
			dbmsUserBean.closeDataBaseConnection();
			tableListRendered = true;
		}
		else{
			mBean.setResponseMessage("");
			mBean.setErrorMessage("Unable to connect to the database. Please try again later.");
			tableListRendered = false;
		}
		renderQuery = false;
		mBean.setResponseMessage("Tables are displayed in the select box");
	}
	
		
	public void displayColumns(){
		System.out.println("Inside display tables");
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		
		DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		mBean = (MessageBean) m.get("messageBean");
		mBean.setErrorMessage("");
		mBean.setResponseMessage("");
		boolean connectStatus=true;
		
		if(!dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
		{
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		
		if (connectStatus)
		{
			mBean.setResponseMessage("List of columns");
			mBean.setErrorMessage("");
			System.out.println("Cols of table "+ tableName);
			columnNames = dbmsUserBean.getColumns(tableName);
			
			if(columnNames.isEmpty()){
				mBean.setErrorMessage("No column exists");
				mBean.setResponseMessage("");
				columnListRendered = false;
			}
			System.out.println("Column List::"+columnNames.size());
			dbmsUserBean.closeDataBaseConnection();
			columnListRendered = true;
			
			
		}
		else{
			mBean.setResponseMessage("");
			mBean.setErrorMessage("Unable to connect to the database. Please try again later.");
			columnListRendered = false;
		}
		System.out.println("columnListRendered "+ columnListRendered );
		mBean.setResponseMessage("Columns are displayed in the select many box");
		renderQuery=false;
	}
	
	public void displayTables(){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		
		DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		mBean = (MessageBean) m.get("messageBean");
		mBean.setErrorMessage("");
		mBean.setResponseMessage("");
	
		boolean connectStatus=true;
		
		if(!dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
		{
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		
		if (connectStatus){
			String query = SQLConstants.SELECT_TABLE+tableName;
			System.out.println("The query is: "+query);
			dbmsUserBean.executeQuery(query);
			
			//dbmsUserBean.convertToResult();
			result = dbmsUserBean.getResult();
			columnNames = dbmsUserBean.getColumns(tableName);
			String coloumnNames[]=result.getColumnNames();
			numberColumns = coloumnNames.length;
			numberRows = result.getRowCount();
			renderSet=true;
			if(result==null){
				mBean.setErrorMessage("Resut is null");
				mBean.setResponseMessage("");
				renderSet=false;
				
			}
			
		System.out.println("ResultSet obtained " +renderSet);
			//dbmsUserBean.closeConnection();
		}
		mBean.setResponseMessage("Table is displayed below");
		if(renderSet)
		{renderCols = false;
		renderQuery=true;
		renderUserQuery=false;
		}
	}
	
	public void displaySelectedColumns(){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		
		DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		mBean = (MessageBean) m.get("messageBean");
		mBean.setErrorMessage("");
		mBean.setResponseMessage("");
	
		boolean connectStatus=true;
		
		if(!dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
		{
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		
		if (connectStatus){
			String cols = "";
			int size = columnNamesSelected.size();
			for (int i=0;i <size;i++)
			{ 	if(i<(size-1))
					cols = cols +columnNamesSelected.get(i)+ ",";
				else
					cols = cols +columnNamesSelected.get(i)+ " ";
				
			}
			
			String query = "SELECT " + cols + " FROM " +tableName;
			System.out.println("List of cols " + cols );
			System.out.println("The query is: "+query);
			dbmsUserBean.executeQuery(query);
			
			//dbmsUserBean.convertToResult();
			result = dbmsUserBean.getResult();
			//columnNames = dbmsUserBean.getColumns(tableName);
			
			/*String coloumnNames[]=result.getColumnNames();
			columnNames=null;
			for (int i=0; i < coloumnNames.length; i++)
			{
				columnNames.add(coloumnNames[i]); 
			}*/
			
			numberColumns = columnNamesSelected.size();
			numberRows = result.getRowCount();
			renderCols=true;
			if(result==null){
				mBean.setErrorMessage("Resut is null");
				mBean.setResponseMessage("");
				renderCols=false;
			}
			
		System.out.println("ResultSet obtained " +renderSet);
			//dbmsUserBean.closeConnection();
		}
		mBean.setResponseMessage("The columns selected are displayed below");
		if(renderCols)
		{renderSet = false;
		renderQuery=true;
		renderUserQuery=false;
		}
		
		
	}
	
	
	public void  dropSelectedTable(){
	FacesContext context = FacesContext.getCurrentInstance();
	Map<String,Object> m = context.getExternalContext().getSessionMap();
	
	DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
	mBean = (MessageBean) m.get("messageBean");
	mBean.setErrorMessage("");
	mBean.setResponseMessage("");

	boolean connectStatus=true;
	
	if(!dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
	{
		connectStatus=dbmsUserBean.getDataBaseConnection();
		System.out.println("The database is connected");
	}
	
	if (connectStatus){
		
	//	String cols = "";
	//	int size = columnNamesSelected.size();
	//	for (int i=0;i <size;i++)
	//	{ 	if(i<(size-1))
	//			cols = cols +columnNamesSelected.get(i)+ ",";
	//		else
	//			cols = cols +columnNamesSelected.get(i)+ " ";
	//		
	//	}
		
		String query = SQLConstants.DROP_USER_TABLE + tableName;
		//System.out.println("List of cols " + cols );
		System.out.println("The query is: "+query);
		dbmsUserBean.executeQuery(query);
		mBean.setResponseMessage("The table is droped");
		//dbmsUserBean.convertToResult();
		result = dbmsUserBean.getResult();
		//columnNames = dbmsUserBean.getColumns(tableName);
		
		/*String coloumnNames[]=result.getColumnNames();
		columnNames=null;
		for (int i=0; i < coloumnNames.length; i++)
		{
			columnNames.add(coloumnNames[i]); 
		}*/
		
		//numberColumns = columnNamesSelected.size();
		// numberRows = result.getRowCount();
		//renderCols=true;
		//if(result==null){
			mBean.setErrorMessage("Resut is null");
			mBean.setResponseMessage("");
			renderCols=false;
	//	}
		
	//System.out.println("ResultSet obtained " +renderSet);
		dbmsUserBean.closeDataBaseConnection();
	}
	
	renderSet=false;
	renderQuery=true;
}
	
	public void executeUserQuery(){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		
		DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		mBean = (MessageBean) m.get("messageBean");
		mBean.setErrorMessage("");
		mBean.setResponseMessage("");

		boolean connectStatus=true;
		
		if(dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
		{ 	
			dbmsUserBean.closeDataBaseConnection();
			System.out.println("Open connection closed");
		}
		
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
			if (connectStatus){
				
				String query = userQuery;
				
				System.out.println("The query is: "+query);
				dbmsUserBean.executeQuery(query);
				
				//dbmsUserBean.convertToResult();
				result = dbmsUserBean.getResult();
				try{
				if(!columnNames.isEmpty())
				columnNames.clear();
				
				String coloumnNames[]=result.getColumnNames();
				for (int i=0; i < coloumnNames.length; i++)
				{
					columnNames.add(coloumnNames[i]); 
				}
				numberColumns = coloumnNames.length;
				numberRows = result.getRowCount();
				
				if(result==null){
					mBean.setErrorMessage("Resut is null");
					mBean.setResponseMessage("");
					renderUserQuery=false;
				}
				renderUserQuery =true;
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				dbmsUserBean.closeDataBaseConnection();
			
		}
		
		
		
		System.out.println("ResultSet obtained " + renderUserQuery);
		mBean.setResponseMessage("The user Query is run Successfully");
		if(renderUserQuery)
		{renderCols = false;
		renderQuery=true;
		renderSet=false;
		}
	}
	
	public void createTables(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		
		DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		mBean = (MessageBean) m.get("messageBean");
		mBean.setErrorMessage("");
		mBean.setResponseMessage("");
		
		createTables = new ArrayList();
		
		createTables.add(SQLConstants.CREATE_APP_USER_TABLE);
		//createTables.add(SQLConstants.CREATE_QUESTION_TABLE);
		//createTables.add(SQLConstants.CREATE_TEST_TABLE);
		//createTables.add(SQLConstants.CREATE_USER_TABLE);
		createTables.add(SQLConstants.CREATE_TABLE_COURSE_ROSTER);
		createTables.add(SQLConstants.CREATE_TABLE_ROSTER);
		createTables.add(SQLConstants.CREATE_TABLE_TEST);
		createTables.add(SQLConstants.CREATE_TABLE_QUESTION);
		createTables.add(SQLConstants.CREATE_TABLE_STUDENTS_ANSWERS);
		createTables.add(SQLConstants.CREATE_TABLE_STUDETNTS_SCORES);
	
		
		 
		String tables_created = "";
		
		boolean connectStatus=true;
		
		if(dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
		{ 	
			dbmsUserBean.closeDataBaseConnection();
			System.out.println("Open connection closed");
		}
		
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
			if (connectStatus){
				
				for(int i = 0 ; i < createTables.size() ;i++)
				{
				String query = createTables.get(i);
				dbmsUserBean.executeQuery(query);
				System.out.println("The query is: "+query);
				tables_created = tables_created + query +"\n";
				}
				
				dbmsUserBean.closeDataBaseConnection();			
		}
			mBean.setResponseMessage(tables_created);
		 	
	}
	
	public String reset(){
		System.out.println("Entering reset");
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		mBean = (MessageBean) m.get("messageBean");
		//tableData.clear();
		//if(!tableViewList.isEmpty())
	//	tableViewList.clear();
		//if(!columnNames.isEmpty())
		//columnNames.clear();
		
		mBean.setErrorMessage("");
		mBean.setResponseMessage("");
		tableName="";
		//buttonName=0;
		//buttonNameDisplayData=0;*/
		return "databaseAccess.jsp";
	}

	

}


