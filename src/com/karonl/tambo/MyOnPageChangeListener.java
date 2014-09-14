package com.karonl.tambo;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.karonl.tambo.Calendar;

public class MyOnPageChangeListener implements OnPageChangeListener {

	private TextView vlistview;
	private Nweek wenk;
	private int prepage = 1000;
	private int nprepagenum = 0;
	private LinearLayout inner;
	public ArrayList<HashMap<String, String>> myArrayList= new ArrayList<HashMap<String, String>>();
	private String xin[] = {"一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五","十六","十七","十八","十九","二十","二十一","二十二","二十三","二十四","二十五"};
	private Handler handler=new Handler();  
	
	public MyOnPageChangeListener(Context context,TextView vlistview,ListView dummyList,LinearLayout inner){
		this.vlistview = vlistview;
		this.inner=inner;
		wenk = Calendar.wenkstatic;

	}
	
	@Override
	public void onPageSelected(int arg0) {
		
		wenk.setweek(arg0);
		vlistview.setText("第"+xin[arg0]+"周");
		
		Thread t2 = new freshUI(); 
        t2.start(); 
        
        Log.i("",wenk.getweek()+"/"+prepage+"/"+nprepagenum);//nprepagenum初始第一次界面
        
		if((prepage+1)==wenk.getweek())MainActivity.titlemove(-10,0,-4,30);
		else if((prepage+1)!=wenk.getweek() && nprepagenum==prepage)MainActivity.titlemove(0,-10,30,-4);
        
		nprepagenum = arg0;
		if(prepage>999)prepage = nprepagenum;
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}
	
	public class freshUI extends Thread{ 
	    public void run() { 
	    	try {
                Thread.sleep(400);
               } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
               }
	    	handler.post(runnableUi);
	    }
	}
	Runnable runnableUi=new  Runnable(){  
        @Override  
        public void run() {  
            //更新界面  
        	  
        	Calendar.update("",inner);
        }  
          
    }; 
	

}