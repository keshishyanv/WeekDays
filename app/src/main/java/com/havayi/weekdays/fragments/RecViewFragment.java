package com.havayi.weekdays.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.havayi.weekdays.R;
import com.havayi.weekdays.models.Day;
import com.havayi.weekdays.models.DayAdapter;

public class RecViewFragment extends Fragment {

    private DayAdapter dayAdapter;
    private Day[] mDays;

    public RecViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rec_view, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.days_list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        dayAdapter = new DayAdapter();
        dayAdapter.setDays(mDays);
        rv.setAdapter(dayAdapter);
        return rootView;
    }

    public void setDays(Day[] days){
        mDays = days;
    }

}
