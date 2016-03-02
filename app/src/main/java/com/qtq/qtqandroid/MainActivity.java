package com.qtq.qtqandroid;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.viewbadger.BadgeView;

public class MainActivity extends FragmentActivity implements OnClickListener
{
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments;

	private LinearLayout mTabMsg;
	private LinearLayout mTabFun;
	private LinearLayout mTabReport;
	private LinearLayout mTabMe;

	private ImageButton mImgMsg;
	private ImageButton mImgFun;
	private ImageButton mImgReport;
	private ImageButton mImgMe;

	BadgeView badgeView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();
		initEvent();

		//ImageButton bb= (ImageButton) findViewById(R.id.id_tab_weixin_img);

		


		setSelect(1);
	}

	private void initEvent()
	{
		mTabMsg.setOnClickListener(this);
		mTabFun.setOnClickListener(this);
		mTabReport.setOnClickListener(this);
		mTabMe.setOnClickListener(this);
	}

	private void initView()
	{
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		mTabMsg = (LinearLayout) findViewById(R.id.id_tab_msg);
		mTabFun = (LinearLayout) findViewById(R.id.id_tab_fun);
		mTabReport = (LinearLayout) findViewById(R.id.id_tab_report);
		mTabMe = (LinearLayout) findViewById(R.id.id_tab_me);

		mImgMsg = (ImageButton) findViewById(R.id.id_tab_msg_img);
		mImgFun = (ImageButton) findViewById(R.id.id_tab_fun_img);
		mImgReport = (ImageButton) findViewById(R.id.id_tab_report_img);
		mImgMe = (ImageButton) findViewById(R.id.id_tab_me_img);

		badgeView=new BadgeView(this,mImgMsg);
		badgeView.setText("2");
		badgeView.show();

		mFragments = new ArrayList<Fragment>();
		Fragment mTab01 = new MsgFragment();
		Fragment mTab02 = new FunFragment();
		Fragment mTab03 = new ReportFragment();
		Fragment mTab04 = new MeFragment();
		mFragments.add(mTab01);
		mFragments.add(mTab02);
		mFragments.add(mTab03);
		mFragments.add(mTab04);

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{

			@Override
			public int getCount()
			{
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0)
			{
				return mFragments.get(arg0);
			}
		};
		mViewPager.setAdapter(mAdapter);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			
			@Override
			public void onPageSelected(int arg0)
			{
				int currentItem = mViewPager.getCurrentItem();
				setTab(currentItem);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.id_tab_msg:
			setSelect(0);
			break;
		case R.id.id_tab_fun:
			setSelect(1);
			break;
		case R.id.id_tab_report:
			setSelect(2);
			break;
		case R.id.id_tab_me:
			setSelect(3);
			break;

		default:
			break;
		}
	}

	private void setSelect(int i)
	{
		setTab(i);
		mViewPager.setCurrentItem(i);
	}

	private void setTab(int i)
	{
		resetImgs();

		switch (i)
		{
		case 0:
			mImgMsg.setImageResource(R.drawable.tab_msg_pressed);
			break;
		case 1:
			mImgFun.setImageResource(R.drawable.tab_fun_pressed);
			break;
		case 2:
			mImgReport.setImageResource(R.drawable.tab_report_pressed);
			break;
		case 3:
			mImgMe.setImageResource(R.drawable.tab_me_pressed);
			break;
		}
	}

	/**
	 * �л�ͼƬ����ɫ
	 */
	private void resetImgs()
	{
		mImgMsg.setImageResource(R.drawable.tab_msg_normal);
		mImgFun.setImageResource(R.drawable.tab_fun_normal);
		mImgReport.setImageResource(R.drawable.tab_report_normal);
		mImgMe.setImageResource(R.drawable.tab_me_normal);
	}

}
