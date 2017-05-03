package com.havayi.weekdays.models;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.havayi.weekdays.R;

/**
 * Created by Havayi on 03-May-17.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {

    private Day[] mDays;

    public void setDays(Day[] days) {
        mDays = days;
    }

    @Override
    public DayAdapter.DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View nView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_rec_view, parent, false);
        return new DayViewHolder(nView);
    }

    @Override
    public void onBindViewHolder(DayAdapter.DayViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        holder.dayName.setText(mDays[pos].getName());
        holder.root.setBackgroundColor(mDays[pos].getColor());
    }

    @Override
    public int getItemCount() {
        return mDays.length;
    }

    class DayViewHolder extends RecyclerView.ViewHolder{

        TextView dayName;
        CardView root;

        DayViewHolder(View itemView) {
            super(itemView);
            dayName = (TextView) itemView.findViewById(R.id.day_name);
            root = (CardView) itemView.findViewById(R.id.item_background_view);
        }
    }
}
