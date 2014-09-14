package com.karonl.tambo;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalTime {

	@SuppressLint("SimpleDateFormat")
	public static String returnDate(int count) {  
	    Calendar strDate = Calendar.getInstance();  
	    strDate.add(Calendar.DATE, count);  
	    SimpleDateFormat sdf = new SimpleDateFormat("dd");  
	    SimpleDateFormat sdfm = new SimpleDateFormat("M"); 
	    if(sdf.format(strDate.getTime()).toString().equals("01")){
	    return sdfm.format(strDate.getTime())+"月";  
	    }else{
	    return sdf.format(strDate.getTime());  
	    }
	}  
	
	
	public static int getWNumber(String tempdate){
		SimpleDateFormat   dateFormat = new   SimpleDateFormat("yyyy-MM-dd");   
        Date date = null;   
        if(tempdate=="now"){
        	 date = new java.util.Date();
        }else{
        try {
			date = dateFormat.parse(tempdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        }//if
        Calendar cd = Calendar.getInstance();   
        cd.setTime(date);   
        int mydate = cd.get(Calendar.DAY_OF_WEEK);   //获取指定日期转换成星期几 
        switch(mydate){   
        case   1:mydate = 6;break;   
        case   2:mydate = 0;break;   
        case   3:mydate = 1;break;   
        case   4:mydate = 2;break;   
        case   5:mydate = 3;break;   
        case   6:mydate = 4;break;   
        default:mydate = 5;break;   
         }  
        return mydate;
	}


}
