package com.karonl.tambo;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Getweek {
	@SuppressLint("SimpleDateFormat")
	public static String getweek(){
	Calendar calendar = new GregorianCalendar(); 
	 SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    calendar.setTime(new Date()); 
    calendar.add(Calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动 
      Date date=calendar.getTime(); 

   dateFormatter.applyPattern("w");
   return dateFormatter.format(date);
	}

}
