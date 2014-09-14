package com.karonl.tambo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
	
	private ViewPager mPager;
	public ArrayList<HashMap<String, String>> myArrayList= new ArrayList<HashMap<String, String>>();
	//private MyScrollView myscrollView;
	private List<View> listViews;
	private ListView dummyList;
	private TextView zhouv,zhous;
	private LinearLayout backtitle,pagerLayout;
	private static TextView titletext;
	public static int nowzhou = 4;//现在周数
	private static ImageView titlepic;
	private ImageView imageselect;
	private PagerAdapter adapters;
	public static ImageView cursor;//
	public static int bmpW;
	public static int offset = 0;
	private TitlePopup titlePopup;
	private LinearLayout innersmall;
	private View linnerview;
	private String context;
	private String[] aArr;
	
	public class StartThread extends Thread{ 
	    public StartThread() { } 
	    public void run() { 
			InitImageView();
			selectinit();
	    }
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		    
        SharedPreferences sp = this.getSharedPreferences("SP", MODE_PRIVATE);
        context = sp.getString("Tambo_data_zhou", "none");
        if(context.equals("none")){
        	nowzhou = 7;//
        	SharedPreferences.Editor editor = sp.edit();  
            editor.putString("Tambo_data_zhou", Getweek.getweek()+"*"+nowzhou);  
            editor.commit(); 
            //Log.e("",Getweek.getweek()+"*"+nowzhou);
        }else{
        	aArr = context.split("\\*"); 
        	nowzhou =  Integer.parseInt(aArr[1])  + (Integer.parseInt(Getweek.getweek()) - Integer.parseInt(aArr[0]));
        	//Log.e("",Getweek.getweek()+"*"+nowzhou);
        }
		
		Thread t1 = new StartThread(); 
        t1.start();
        InitCalendar();
		Calendar.update("set",innersmall);

		linnerview=LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_warnning, null); 
		innersmall.addView(linnerview);	
	}
	

	private void InitCalendar() {
		backtitle = (LinearLayout)findViewById(R.id.back);
		innersmall = (LinearLayout)findViewById(R.id.innersmall);
		dummyList = (ListView)findViewById(R.id.listViewset);
		titletext = (TextView) findViewById(R.id.textViewtitle);
		zhous = (TextView) findViewById(R.id.textViewnumber);
		zhouv =  (TextView) findViewById(R.id.textViewnzhou);
		mPager = (ViewPager) findViewById(R.id.vPager2);
		titlepic = (ImageView) findViewById(R.id.imageViewname);
		imageselect = (ImageView) findViewById(R.id.imageselect);
		
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		listViews.add(mInflater.inflate(R.layout.calendar, null));
		listViews.add(mInflater.inflate(R.layout.calendar, null));
		listViews.add(mInflater.inflate(R.layout.calendar, null));
		adapters = new Calendar(listViews,this,zhous,dummyList,innersmall);
		
		mPager.setAdapter(adapters);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener(this,zhouv,dummyList,innersmall));
		mPager.setCurrentItem(nowzhou);
		
		backtitle.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View v) {
	        	 mPager.setCurrentItem(nowzhou);
	        	 adapters.notifyDataSetChanged();
	        	 Calendar.update("set",innersmall);
		      }});
		imageselect.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View v) {

	        	 titlePopup.show(v);
		      }});
		
		titlemove(0,0,60,30);
	}
	
	private void InitImageView() {
		
		cursor = new ImageView(this); 
		pagerLayout = (LinearLayout) findViewById(R.id.tj);
		cursor.setImageResource(R.drawable.a);
		@SuppressWarnings("deprecation")
		RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);  
		pagerLayout.addView(cursor,lp1);
		//cursor = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;	
		
		LayoutParams layoutParams=(LayoutParams) cursor.getLayoutParams();
		layoutParams.width=screenW / 7;
		cursor.setLayoutParams(layoutParams);

		offset = (screenW / 7 - bmpW) / 2;	
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);
	}
	
	private void selectinit(){
		titlePopup = new TitlePopup(this, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		titlePopup.addAction(new ActionItem(this, "导入课程", R.drawable.mm_title_btn_receiver_normal));
		titlePopup.addAction(new ActionItem(this, "设置周数", R.drawable.mm_title_btn_set_normal));
		titlePopup.addAction(new ActionItem(this, "切换课表", R.drawable.mm_title_btn_share_normal));
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//titlePopup.show(imageselect);
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static void titlemove(int a,int b,int c,int d){
		Animation animation = null;
		animation = new TranslateAnimation(0, 0, a, b);
		animation.setFillAfter(true);// 
		animation.setDuration(300);
		titlepic.startAnimation(animation);
		
		Animation animationname = null;
		animationname = new TranslateAnimation(0, 0, c, d);
		animationname.setFillAfter(true);// 
		animationname.setDuration(300);
		titletext.startAnimation(animationname);
	}
	

}
