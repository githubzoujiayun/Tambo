package com.karonl.tambo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.karonl.tambo.CalTime;

public class Calendar extends PagerAdapter {
	public List<View> mListViews;
	private LinearLayout  t1, t2, t3, t4, t5, t6, t7;
	private static Context context;
	private static TextView vlistview;
	static Nweek wenkstatic;
	private static ListView mdummyList;
	public static ArrayList<HashMap<String, String>> myArrayList= new ArrayList<HashMap<String, String>>();
	private static String xin[] = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
	private int o  = MainActivity.nowzhou;
	private int t =0;
	public static int oldcur = 0;
	public static int nowcur = 0;
	private LinearLayout linner;


	@SuppressWarnings("static-access")
	public Calendar(List<View> mListViews,Context context,TextView vlistview,ListView mdummyList,LinearLayout linner) {
		this.mListViews = mListViews;
		this.vlistview= vlistview;
		this.context = context;
		this.mdummyList = mdummyList;
		this.linner=linner;
		wenkstatic = new Nweek();
		
	}


	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		//((ViewPager) arg0).removeView(mListViews.get(arg1));
		//Log.e("--remove", ""+arg1);
		/*if (arg1 > 0) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
			Log.e("sss",arg1+"");
		}*/
	}

	@Override
	public void finishUpdate(View arg0) {
	}

	@Override
	public int getCount() {
		return 25;
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		//((ViewPager) arg0).addView(mListViews.get(arg1), 0);
		
		
		
		try {
			((ViewPager) arg0).addView(mListViews.get(arg1 % mListViews.size()), 0);
			
			t1 = (LinearLayout)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.an1);
			t2 = (LinearLayout)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.an2);
			t3 = (LinearLayout)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.an3);
			t4 = (LinearLayout)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.an4);
			t5 = (LinearLayout)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.an5);
			t6 = (LinearLayout)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.an6);
			t7 = (LinearLayout)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.an7);

			t1.setOnClickListener(new MyOnClickListener(0,linner));
			t2.setOnClickListener(new MyOnClickListener(1,linner));
			t3.setOnClickListener(new MyOnClickListener(2,linner));
			t4.setOnClickListener(new MyOnClickListener(3,linner));
			t5.setOnClickListener(new MyOnClickListener(4,linner));
			t6.setOnClickListener(new MyOnClickListener(5,linner));
			t7.setOnClickListener(new MyOnClickListener(6,linner));
			
		} catch (Exception e) {
		}
		
		
		
		t = (arg1-o)*7;
		t = t - CalTime.getWNumber("now");
		
		TextView bat1 = (TextView)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.am1);
	    bat1.setText(CalTime.returnDate(t));

		TextView bat2 = (TextView)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.am2);
		bat2.setText(CalTime.returnDate(t+1));

		TextView bat3 = (TextView)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.am3);
		bat3.setText(CalTime.returnDate(t+2));

		TextView bat4 = (TextView)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.am4);
		bat4.setText(CalTime.returnDate(t+3));

		TextView bat5 = (TextView)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.am5);
		bat5.setText(CalTime.returnDate(t+4));

		TextView bat6 = (TextView)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.am6);
		bat6.setText(CalTime.returnDate(t+5));

		TextView bat7 = (TextView)mListViews.get(arg1 % mListViews.size()).findViewById(R.id.am7);
		bat7.setText(CalTime.returnDate(t+6));

		return mListViews.get(arg1 % mListViews.size());

	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
	}
	
	public int getItemPosition(Object object) {
		    return POSITION_NONE;
		}

	
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;
		private LinearLayout inner;

		public MyOnClickListener(int i,LinearLayout a) {
			index = i;
			inner = a;
		}

		@Override
		public void onClick(View v) {
			
			nowcur = index;
			update("",inner);

			
		}
	};

	public static int update(String n,LinearLayout a){
		if(n.equals("set"))nowcur=CalTime.getWNumber("now");
		wenkstatic.setxing(nowcur);
		vlistview.setText(xin[nowcur]);
		//Log.i(""+wenkstatic.getweek(),""+wenkstatic.getxing());
		int one = MainActivity.offset * 2 + MainActivity.bmpW;
		Animation animation = null;
		animation = new TranslateAnimation(oldcur * one, nowcur * one, 0, 0);
		animation.setFillAfter(true);// True:鍥剧墖鍋滃湪鍔ㄧ敾缁撴潫浣嶇疆
		animation.setDuration(200);
		MainActivity.cursor.startAnimation(animation);
		oldcur = nowcur;
		//Log.e("","更新数据");
		myArrayList.clear();
		myArrayList = wenkstatic.getcontents(myArrayList);

		 mdummyList.setAdapter( new SimpleAdapter(context, myArrayList,R.layout.fragment_table, new String[] { "time","time2","time3"}, new int[] {R.id.textView1,R.id.textView2,R.id.textView3 }));
		 setListViewHeightBasedOnChildren(mdummyList);
		 if(myArrayList.size()<1){
		 a.setVisibility(View.VISIBLE);
		// Log.e("","View.VISIBLE"+wenkstatic.getweek());
		 }else{
		 a.setVisibility(View.GONE);
		// Log.e("","View.GONE"+wenkstatic.getweek());
		 }
		 return myArrayList.size();
	}
	
	
	public static void setListViewHeightBasedOnChildren(ListView listView) {  
        ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {  
            // pre-condition  
            return;  
        }  
  
        int totalHeight = 0;  
        for (int i = 0; i < listAdapter.getCount(); i++) {  
            View listItem = listAdapter.getView(i, null, listView);  
            listItem.measure(0, 0);  
            totalHeight += listItem.getMeasuredHeight();  
        }  
  
        ViewGroup.LayoutParams params = listView.getLayoutParams();  
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));  
        listView.setLayoutParams(params);  
    } 
	

}

