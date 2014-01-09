package com.fhswf.silentvoice.adapter;

import java.util.ArrayList;
import java.util.Locale;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.fhswf.silentvoice.R;

public class TabAdapter extends FragmentPagerAdapter implements
		ActionBar.TabListener, ViewPager.OnPageChangeListener {

	private final ActionBar mActionBar;
	private final Context mContext;
	private final ViewPager mViewPager;
	private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

	final class TabInfo {
		private final Class<?> clss;
		private final Bundle args;

		TabInfo(Class<?> _class, Bundle _args) {
			clss = _class;
			args = _args;
		}
	}

	public TabAdapter(FragmentActivity activity, ViewPager pager) 
	{
		super(activity.getSupportFragmentManager());

		
		mContext = activity;

		mActionBar = activity.getActionBar();

		mViewPager = pager;
		mViewPager.setAdapter(this);
		mViewPager.setOnPageChangeListener(this);
		

	}

	public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
		TabInfo info = new TabInfo(clss, args);
		tab.setTag(info);
		tab.setTabListener(this);
		mTabs.add(info);
		mActionBar.addTab(tab);
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int pos) {
		TabInfo info = mTabs.get(pos);
		return Fragment.instantiate(mContext, info.clss.getName(), info.args);

	}

	@Override
	public int getCount() {
		// �ndern wenns net geht
		return mTabs.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return mContext.getString(R.string.hear_section1).toUpperCase(l);
		case 1:
			return mContext.getString(R.string.hear_section2).toUpperCase(l);

		}
		return null;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Object tag = tab.getTag();
		for (int i = 0; i < mTabs.size(); i++) {
			if (mTabs.get(i) == tag) {
				mViewPager.setCurrentItem(tab.getPosition());
			}
		}

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrollStateChanged(int state) {
	
	}

	@Override
	public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) {
		
	}

	@Override
	public void onPageSelected(int pos) {
		
		System.out.println("Page Selected: " + pos);
		mActionBar.setSelectedNavigationItem(pos);
		
	}

}
