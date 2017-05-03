package com.havayi.weekdays.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.havayi.weekdays.R;
import com.havayi.weekdays.models.Day;


public class ItemDayPager extends Fragment {

    public static final String ARG_PAGE = "page";
    public static final String ARG_DAY = "day";
    private int mPageNumber;
    private Day mDay;

    public ItemDayPager() {
        // Required empty public constructor
    }


    public static ItemDayPager create(int pageNumber, Day day) {
        ItemDayPager fragment = new ItemDayPager();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putParcelable(ARG_DAY, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        mDay = getArguments().getParcelable(ARG_DAY);
        //setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.item_day_pager, container, false);
        ((TextView) rootView.findViewById(R.id.day_name_pager)).setText(mDay.getName());
        rootView.setBackgroundColor(mDay.getColor());
        return rootView;
    }
}
