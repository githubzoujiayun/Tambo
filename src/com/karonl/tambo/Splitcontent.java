package com.karonl.tambo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


public class Splitcontent {
	private String content = "";
	private String[] aArr ;
	
	public void SplitC(Context context){
	
     
    SharedPreferences sp = context.getSharedPreferences("SP",0);
    content = sp.getString("STRING_data", "none");
    Log.e("--",content);
    
    //Thread t1 = new TestThread(); 
    //t1.start();
    
	if(content.equals("none")){

	}else{
		 aArr = (content+"0").split("\\#"); 
	}
	
	}
}
