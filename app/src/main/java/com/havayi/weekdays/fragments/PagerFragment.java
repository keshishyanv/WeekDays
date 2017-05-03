package com.havayi.weekdays.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.havayi.weekdays.R;
import com.havayi.weekdays.models.Day;
import com.havayi.weekdays.models.DayPagerTransformer;

public class PagerFragment extends Fragment {

    private static final String DAYS_TAG = "days";
    private static final String CURRENT_POSITION_TAG = "current";

    private Day[] mDays;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private int currentPosition;

    public PagerFragment() {
        // Required empty public constructor
    }

    public void setDays(Day[] days) {
        mDays = days;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pager, container, false);
        if (savedInstanceState != null){
            mDays = (Day[]) savedInstanceState.getParcelableArray(DAYS_TAG);
            currentPosition = savedInstanceState.getInt(CURRENT_POSITION_TAG);
        }

        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        mPagerAdapter = new DayPagerAdapter(getChildFragmentManager());
        mPager.setPageTransformer(true, new DayPagerTransformer());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(currentPosition, true);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private class DayPagerAdapter extends FragmentStatePagerAdapter {

        DayPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            currentPosition = position;
            return ItemDayPager.create(position, mDays[position]);
        }

        @Override
        public int getCount() {
            return mDays.length;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray(DAYS_TAG, mDays);
        outState.putInt(CURRENT_POSITION_TAG, currentPosition);
    }
}
