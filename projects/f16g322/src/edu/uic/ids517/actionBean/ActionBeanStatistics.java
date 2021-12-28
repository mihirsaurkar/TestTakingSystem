package edu.uic.ids517.actionBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.management.StandardEmitterMBean;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.inference.TestUtils;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import edu.uic.ids517.model.DbmsUserBean;
import edu.uic.ids517.model.MessageBean;

@ManagedBean
@SessionScoped
public class ActionBeanStatistics {
	
	private String exam ;
	private double minValue;
	private double scores[];
	private double maxValue ;
	private double  median ;
	private double mean;
	private double variance ;
	private double std;
	private double q1;
	private double q3;
	private double iqr;
	private double range;
	private DbmsUserBean dbmsUserBean;
	private MessageBean mBean;
	private ResultSet resultSet;
	private boolean renderstats;
	private String regXam1;
	private String regXam2;
	
	private double x[];
	private double y[];
	
	private double intercept ;
	private double slope;
	private double rsq; 
	private double significance;
	private double interceptStdErr;
	private double slopeStdErr;
	private double fstatistics;
	private double pValue;
	private String equation;
	
	
	
	
	public String getEquation() {
		return equation;
	}
	public void setEquation(String equation) {
		this.equation = equation;
	}
	public double getFstatistics() {
		return fstatistics;
	}
	public void setFstatistics(double fstatistics) {
		this.fstatistics = fstatistics;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	public double getpValue() {
		return pValue;
	}
	public void setpValue(double pValue) {
		this.pValue = pValue;
	}
	public double getRsq() {
		return rsq;
	}
	public void setRsq(double rsq) {
		this.rsq = rsq;
	}
	public double[] getX() {
		return x;
	}
	public void setX(double[] x) {
		this.x = x;
	}
	public double[] getY() {
		return y;
	}
	public void setY(double[] y) {
		this.y = y;
	}
	public String getRegXam1() {
		return regXam1;
	}
	public void setRegXam1(String regXam1) {
		this.regXam1 = regXam1;
	}
	public String getRegXam2() {
		return regXam2;
	}
	public void setRegXam2(String regXam2) {
		this.regXam2 = regXam2;
	}
	public double getIntercept() {
		return intercept;
	}
	public void setIntercept(double intercept) {
		intercept = intercept;
	}
	public double getSlope() {
		return slope;
	}
	public void setSlope(double slope) {
		slope = slope;
	}
	
	public double getSignificance() {
		return significance;
	}
	public void setSignificance(double significance) {
		this.significance = significance;
	}
	public double getInterceptStdErr() {
		return interceptStdErr;
	}
	public void setInterceptStdErr(double interceptStdErr) {
		this.interceptStdErr = interceptStdErr;
	}
	public double getSlopeStdErr() {
		return slopeStdErr;
	}
	public void setSlopeStdErr(double slopeStdErr) {
		this.slopeStdErr = slopeStdErr;
	}
	public boolean isRenderstats() {
		return renderstats;
	}
	public void setRenderstats(boolean renderstats) {
		this.renderstats = renderstats;
	}
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	public double getMinValue() {
		return minValue;
	}
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	public double[] getScores() {
		return scores;
	}
	public void setScores(double[] scores) {
		this.scores = scores;
	}
	public double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}
	public double getMedian() {
		return median;
	}
	public void setMedian(double median) {
		this.median = median;
	}
	public double getMean() {
		return mean;
	}
	public void setMean(double mean) {
		this.mean = mean;
	}
	public double getVariance() {
		return variance;
	}
	public void setVariance(double variance) {
		this.variance = variance;
	}
	public double getStd() {
		return std;
	}
	public void setStd(double std) {
		this.std = std;
	}
	public double getQ1() {
		return q1;
	}
	public void setQ1(double q1) {
		this.q1 = q1;
	}
	public double getQ3() {
		return q3;
	}
	public void setQ3(double q3) {
		this.q3 = q3;
	}
	public double getIqr() {
		return iqr;
	}
	public void setIqr(double iqr) {
		this.iqr = iqr;
	}
	
	public double getRange() {
		return range;
	}
	public void setRange(double range) {
		this.range = range;
	}
	
	
	@SuppressWarnings("unchecked")
	public void generateStats () throws SQLException{
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		boolean connectStatus=true;
		DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		//mBean = (MessageBean) m.get("messageBean");
		//mBean.setErrorMessage("");
		//mBean.setResponseMessage("");
		
		
		if(dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
		{	dbmsUserBean.closeDataBaseConnection();
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		else
		{
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		if(connectStatus)
		{
			String query = "SELECT " + exam + " from f16g322_temp_roster;" ;
			
			dbmsUserBean.executeQuery(query);
			
			resultSet = dbmsUserBean.getRs();
			 dbmsUserBean.getResultList();
		
		
			List <String> values = dbmsUserBean.getDataList();
			 
			 scores = new double[values.size()];
			 
			 for (int i=0; i<values.size();i++){
				 scores[i] = Double.parseDouble(values.get(i));
			 }
								
		}
		 minValue = StatUtils.min(scores);
		 maxValue = StatUtils.max(scores);
		 mean = StatUtils.mean(scores);
		 variance = StatUtils.variance(scores, mean);
		 std = Math.sqrt(variance);
		 median = StatUtils.percentile(scores, 50.0);
		 q1 = StatUtils.percentile(scores, 25.0);
		 q3 = StatUtils.percentile(scores, 75.0);
		
		iqr = q3-q1;
		range = maxValue-minValue;
		
		if (minValue > 0 && maxValue >0 && mean >0 && variance > 0 && std >0 && median >0 && q1 >0 && q3 >0 )
		{
			renderstats = true;
		}
		else
		{
			renderstats = false;
		}
		//dbmsUserBean.closeDataBaseConnection();
	}
	
	public void getScoresX(String xam ){
		
		double a[];
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		boolean connectStatus=true;
		DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		//mBean = (MessageBean) m.get("messageBean");
		//mBean.setErrorMessage("");
		//mBean.setResponseMessage("");
		
		
		if(dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
		{	dbmsUserBean.closeDataBaseConnection();
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		else
		{
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		if(connectStatus)
		{
			String query = "SELECT " + xam + " from f16g322_temp_roster;" ;
			
			dbmsUserBean.executeQuery(query);
			
			resultSet = dbmsUserBean.getRs();
			 dbmsUserBean.getResultList();
		
		
			List <String> values = dbmsUserBean.getDataList();
			 
			 a = new double[values.size()];
			 
			 for (int i=0; i<values.size();i++){
				 a[i] = Double.parseDouble(values.get(i));
				 
				 this.setX(a);
			 }
			
					
		}
	
	}
	
	
public void getScoresY(String xam ){
		
		double a[];
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		boolean connectStatus=true;
		DbmsUserBean dbmsUserBean = (DbmsUserBean) m.get("dbmsUserBean");
		//mBean = (MessageBean) m.get("messageBean");
		//mBean.setErrorMessage("");
		//mBean.setResponseMessage("");
		
		
		if(dbmsUserBean.getStatus().equalsIgnoreCase("SUCCESS"))
		{	dbmsUserBean.closeDataBaseConnection();
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		else
		{
			connectStatus=dbmsUserBean.getDataBaseConnection();
			System.out.println("The database is connected");
		}
		if(connectStatus)
		{
			String query = "SELECT " + xam + " from f16g322_temp_roster;" ;
			
			dbmsUserBean.executeQuery(query);
			
			resultSet = dbmsUserBean.getRs();
			 dbmsUserBean.getResultList();
		
		
			List <String> values = dbmsUserBean.getDataList();
			 
			 a = new double[values.size()];
			 
			 for (int i=0; i<values.size();i++){
				 a[i] = Double.parseDouble(values.get(i));
				 
				 this.setY(a);
			 }
			
					
		}
	
	}
	
	public void getRegression()
	{
		
		
		this.getScoresX(regXam1);
		this.getScoresY(regXam2);
		
		
		PearsonsCorrelation c = new PearsonsCorrelation();
		SimpleRegression sr = new SimpleRegression();
		
		sr.clear();
		if (x.length == y.length)
		{
		for(int k=0; k<x.length; k++) {
		sr.addData(x[k], y[k]);
		System.out.println("The value for x and y for index " + k + "  x: " +x[k]+" y :"+y[k] );
		}
		
		intercept= sr.getIntercept();
		slope= sr.getSlope();
		rsq= sr.getRSquare();
		significance= sr.getSignificance();
		interceptStdErr = getInterceptStdErr();
		slopeStdErr= sr.getSlopeStdErr();
		//y = intercept + slope * x
		
		List classes = new ArrayList();
		classes.add(x);
		classes.add(y);
		
		fstatistics = TestUtils.oneWayAnovaFValue(classes);
		
		pValue = TestUtils.oneWayAnovaPValue(classes);
		
		if(slope >0)
		equation = "y = "+ intercept + " + "+java.lang.Math.abs(slope)+" * x";
		else
			equation = "y = "+ intercept + " - "+java.lang.Math.abs(slope)+" * x";	
				}
		else
		{
			System.out.println("there is mismatch in lengths of arrays for regression");
			
		}
		
		
		
		
	}
	
	}
	

