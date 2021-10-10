package com.flightbooking.app.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {

	
	public static String convertingDate(String inputDate){
		SimpleDateFormat utcdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		utcdate.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date utc = null;
		try {
			utc = utcdate.parse(inputDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utc.toString();
	}
	
	public static Date convertingDate_2(Date inputDate) {
		SimpleDateFormat utcdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		utcdate.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date utc=null;
		try {
			utc = utcdate.parse(inputDate.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utc;
	}
	
}
