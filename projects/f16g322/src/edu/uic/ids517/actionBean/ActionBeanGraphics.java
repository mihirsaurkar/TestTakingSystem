package edu.uic.ids517.actionBean;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.math3.stat.StatUtils;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import edu.uic.ids517.model.User;
import edu.uic.ids517.model.ChartBean;
import edu.uic.ids517.model.DbmsUserBean;

@ManagedBean
@SessionScoped
public class ActionBeanGraphics {
	private String exam ;
	private Object[][] sData;
	private String XYchartFile;
	private User user;
	private boolean renderChart;
	private boolean renderBarChart;
	private String barchartFile;
	private boolean renderPieChart;
	private String piechartFile;
	
	
	
	public boolean isRenderPieChart() {
		return renderPieChart;
	}


	public void setRenderPieChart(boolean renderPieChart) {
		this.renderPieChart = renderPieChart;
	}


	public String getPiechartFile() {
		return piechartFile;
	}


	public void setPiechartFile(String piechartFile) {
		this.piechartFile = piechartFile;
	}


	public String getBarchartFile() {
		return barchartFile;
	}


	public void setBarchartFile(String barchartFile) {
		this.barchartFile = barchartFile;
	}


	public boolean isRenderBarChart() {
		return renderBarChart;
	}


	public void setRenderBarChart(boolean renderBarChart) {
		this.renderBarChart = renderBarChart;
	}


	public String getExam() {
		return exam;
	}


	public void setExam(String exam) {
		this.exam = exam;
	}


	public Object[][] getsData() {
		return sData;
	}


	public void setsData(Object[][] sData) {
		this.sData = sData;
	}


	public String getXYchartFile() {
		return XYchartFile;
	}


	public void setXYchartFile(String xYchartFile) {
		XYchartFile = xYchartFile;
	}


	public boolean isRenderChart() {
		return renderChart;
	}


	public void setRenderChart(boolean renderChart) {
		this.renderChart = renderChart;
	}


	public void generateGraph()
	{
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		boolean connectStatus=true;
		DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		user = (User) m.get("user");
		String path = context.getExternalContext().getRealPath("/temp");
		System.out.println("PATH "+ path);
		boolean status = dbmsUserBean.getDataBaseConnection();
		renderChart = false;
		if(status)
		{
		
		String sql;
			
		dbmsUserBean.getDataBaseConnection();
		
		sql= "select Last_access, "+exam+" from f16g322_temp_roster;";
		
		System.out.println("SQL : " + sql);
		
		//dbms.executeQuery(sql);
		
		String[] date;
		double[] values;
		
		
		sData = dbmsUserBean.getObjectexecuteQuery(sql);

		values= new double[sData.length];
		date = new String[sData.length];
		
		System.out.println(" the length is before the for loop " + sData.length);
		
		for (int i = 0; i< sData.length;i++)
		{
			System.out.println("The value is " + sData[i][0]+"  " + sData[i][1]);
				
			date[i] = (String) sData[i][0];
			values[i] = Double.parseDouble((String) sData[i][1]);
			
							
			//System.out.println("The value is " + sData[i][0]+"  " + sData[i][1]);
							
			}

	
			JFreeChart chart = ChartBean.createTimeSeriesChart(values,date);
			File out = null;
			ByteArrayOutputStream s = new ByteArrayOutputStream();
			String pathWebContent = "/temp/";
			XYchartFile = pathWebContent+user.getUserName()+"_"+"XYCHART"+".png" ;
			System.out.println("XYchartFile :"+ XYchartFile);
			out = new File(path+"/"+user.getUserName()+"_"+"XYCHART"+".png");
			try {
				ChartUtilities.saveChartAsPNG(out, chart, 600, 450);
				renderChart=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				renderChart=false;
			}
		
		System.out.println("render chart " + renderChart);
		dbmsUserBean.closeDataBaseConnection();
		}
		}
		 
		
		public void generateBarGraph()
		{
			
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String,Object> m = context.getExternalContext().getSessionMap();
			boolean connectStatus=true;
			DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
			user = (User) m.get("user");
			String path = context.getExternalContext().getRealPath("/temp");
			System.out.println("PATH "+ path);
			boolean status = dbmsUserBean.getDataBaseConnection();
			renderBarChart = false;
			if(status)
			{
			
			String sql;
				
			dbmsUserBean.getDataBaseConnection();
			
			sql= "select First_name,Exam01,Exam02,Exam03  from f16g322_temp_roster";
			
			System.out.println("SQL : " + sql);
			
			//dbms.executeQuery(sql);
			
			String[] name;
			double[] exam01;
			double[] exam02;
			double[] exam03;
			
			
			sData = dbmsUserBean.getObjectexecuteQuery(sql);

			
			
			System.out.println(" the length is before the for loop " + sData.length);
			
			name = new String[sData.length];
			exam01 = new double[sData.length];
			exam02= new double[sData.length];
			exam03= new double[sData.length];
			
			for (int i = 0; i< sData.length;i++)
			{
				//System.out.println("The value is " + sData[i][0]+"  " + sData[i][1]);
					
				name[i] = (String) sData[i][0];
				exam01[i] = Double.parseDouble((String) sData[i][1]);
				exam02[i] = Double.parseDouble((String) sData[i][2]);
				exam03[i] = Double.parseDouble((String) sData[i][3]);
								
				//System.out.println("The value is " + sData[i][0]+"  " + sData[i][1]);
					
					
				}

		
				JFreeChart chart = ChartBean.createBarChart(name,  exam01, exam02, exam03) ;
				File out = null;
				ByteArrayOutputStream s = new ByteArrayOutputStream();
				String pathWebContent = "/temp/";
				barchartFile = pathWebContent+user.getUserName()+"_"+"BarCHART"+".png" ;
				System.out.println("barchartFile :"+ barchartFile);
				out = new File(path+"/"+user.getUserName()+"_"+"BarCHART"+".png");
				try {
					ChartUtilities.saveChartAsPNG(out, chart, 600, 450);
					renderBarChart=true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					renderBarChart=false;
				}
			
			System.out.println("render chart " + renderChart);
			dbmsUserBean.closeDataBaseConnection();
			}	
		
	}
		
		public void generatePieChart()
		{
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String,Object> m = context.getExternalContext().getSessionMap();
			boolean connectStatus=true;
			DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
			user = (User) m.get("user");
			String path = context.getExternalContext().getRealPath("/temp");
			System.out.println("PATH "+ path);
			boolean status = dbmsUserBean.getDataBaseConnection();
			renderPieChart = false;
			
			
			double[] values;
			String [] grades;
			
			if(status)
			{
				
				 String sql= "select " + exam +"  from f16g322_temp_roster;";
				
				//dbms.executeQuery(sql);
				
				 sData = dbmsUserBean.getObjectexecuteQuery(sql);
			
				 
			
			
			System.out.println(" the length is before the for loop " + sData.length);
			//name = new String[sData.length];
			//exam01 = new double[sData.length];
			//exam02= new double[sData.length];
			//exam03= new double[sData.length];
			values = new double [sData.length];
			grades = new String[sData.length];
			@SuppressWarnings("unused")
			double cntA =0, cntB =0, cntC=0,cntD=0,cntE =0;
			
			double propA=0, propB=0,propC=0,propD=0,propE=0;
			
			for (int i = 0; i< sData.length;i++)	
			{			
				//name[i] = (String) sData[i][0];
				//exam01[i] = Double.parseDouble((String) sData[i][1]);
				//exam02[i] = Double.parseDouble((String) sData[i][2]);
				//exam03[i] = Double.parseDouble((String) sData[i][3]);
				values[i] = Double.parseDouble((String) sData[i][0]);
				//System.out.println("The value is " + i +" "+ sData[i][j]);
			}

			double max = StatUtils.max(values);
			double total;
			System.out.println(" MAX "+ max);
			
			if (max > 250)
			{
				total = 1100;
			}
			else
			{
				total = 250;
			}
			for (int i = 0; i< sData.length;i++)	
			{
					double percent =0;
				//name[i] = (String) sData[i][0];
				//exam01[i] = Double.parseDouble((String) sData[i][1]);
				//exam02[i] = Double.parseDouble((String) sData[i][2]);
				//exam03[i] = Double.parseDouble((String) sData[i][3]);
				//System.out.println("The value is " + i +" "+ sData[i][j]);
				
				percent = (values[i]/total) * 100;
				// int pct = (int) percent;
				 if ( percent >= 90)
				 {
					 grades[i] = "A";
					 cntA++;
				 }
				 else if(percent >= 80)
				 {
					 grades[i]="B";
					 cntB++;
				 }
				 else if ( percent >=70)
				 {
					 grades[i]="C";
					 cntC++;
				 }
				 else if ( percent >=60)
				 {
					 grades[i]="D";
					 cntD++;
				 }
				 else
				 {
					 grades[i]="E";
					 cntE++;
				 }
				
				}
			
			
			
			if ( (cntA+cntB+cntC+cntD+cntE) == values.length)
			{
				System.out.println("A :" + cntA+ " B "+ cntB +" C " + cntC + " D "+cntD+ " E " + cntE);
				propA = Math.round(((cntA/(cntA+cntB+cntC+cntD+cntE)) * 100.00)*100.00)/100.00;
				//System.out.println(" The prop for A " + cntA + " divided by " + (cntA+cntB+cntC+cntD+cntE) + " equals " + (cntA/(cntA+cntB+cntC+cntD+cntE)));
				propB= Math.round(((cntB/(cntA+cntB+cntC+cntD+cntE)) * 100.00)*100.00)/100.00;
				propC= Math.round(((cntC/(cntA+cntB+cntC+cntD+cntE)) * 100.00)*100.00)/100.00;
				propD=  Math.round(((cntD/(cntA+cntB+cntC+cntD+cntE)) * 100.00)*100.00)/100.00;
				propE=  Math.round(((cntE/(cntA+cntB+cntC+cntD+cntE)) * 100.00)*100.00)/100.00;
				System.out.println("propA :" + Math.round(propA*100)/100+ " propB "+ Math.round(propB*100)/100 +" propC " + Math.round(propC*100)/100 + " propD "+ Math.round(propD*100)/100+ " propE " + Math.round(propE*100)/100);
				System.out.println( "total prop = " + (propA+propB+propC+propD+propE ));
			}
			
			//System.out.println(sData.toString());
			
			JFreeChart chart = ChartBean.createPieChart(exam,propA,propB,propC,propD,propE) ;
			File out = null;
			ByteArrayOutputStream s = new ByteArrayOutputStream();
			String pathWebContent = "/temp/";
			piechartFile = pathWebContent+user.getUserName()+"_"+"pieCHART"+".png" ;
			System.out.println("barchartFile :"+ piechartFile);
			out = new File(path+"/"+user.getUserName()+"_"+"pieCHART"+".png");
			try {
				ChartUtilities.saveChartAsPNG(out, chart, 600, 450);
				renderPieChart=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				renderPieChart=false;
			}
			
		System.out.println("render chart " + renderChart);
		dbmsUserBean.closeDataBaseConnection();
		}	
			
		}	
}
