package edu.uic.ids517.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.MonthConstants;

@ManagedBean
@SessionScoped
public class ChartBean {
	
	
	public static JFreeChart createTimeSeriesChart(double[] value,String[] date) 
	{
		// here we just populate a series with random data...
		TimeSeries series = new TimeSeries("Scores");
		SimpleDateFormat standardDateFormat = new SimpleDateFormat("mm/dd/yyyy");
		//Date myDate;
		Day current = new Day(1, MonthConstants.JANUARY, 2001);
		for (int i = 0; i < value.length; i++) {
			//try {
			//	myDate = (Date) standardDateFormat.parse(date[i].substring(0, 10));
			//	Day current = new Day(myDate);
				series.add(current,value[i]);
				current = (Day) current.next();
		//	} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
			
		}
		XYDataset data = new TimeSeriesCollection(series);
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
		"Time Series Chart", "Date", "Score",
		data, true, true, false
		);
		return chart;
		}
	
	public static JFreeChart createBarChart(String[] name, double[] exam1, double[] exam2, double[] exam3) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//dataset.add(exam1[0],name[0],"EXAM 1");
		/*
		 * dataset.add(score1,name1,exam01)
		 * dataset.add(score2,name1,exam02)
		 * dataset.add(score3,name1,exam03)
		 */
		
		for (int i =0 ; i<name.length; i++)
		{
			String n = name[i];
			dataset.addValue(exam1[i],n,"EXAM 1");
			dataset.addValue(exam2[i],n,"EXAM 2");
			dataset.addValue(exam3[i],n,"EXAM 3");
			//dataset.addValue(value, rowKey, columnKey);
		}
		
		
		
		JFreeChart chart = ChartFactory.createBarChart3D(
		"Bar Chart",
		"Exam",
		"Scores",
		dataset,
		PlotOrientation.VERTICAL,
		true,
		true,
		false
		);
		return chart;
		}
	
	public static JFreeChart createPieChart(String exam, double propA, double propB, double propC, double propD,double propE) {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("A", propA);
		data.setValue("B",propB);
		data.setValue("C", propC);
		data.setValue("D", propD);
		data.setValue("E",propE);
		JFreeChart chart = ChartFactory.createPieChart(
		"Pie Chart", data, true, true, false
		);
		return chart;
		}

}
