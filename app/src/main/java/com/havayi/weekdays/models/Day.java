package com.havayi.weekdays.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Havayi on 03-May-17.
 */

public class Day implements Parcelable {

    private int color;
    private String name;

    public Day(int color, String name) {
        this.color = color;
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(color);
        out.writeString(name);
    }

    public static final Parcelable.Creator<Day> CREATOR
            = new Parcelable.Creator<Day>() {
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    private Day(Parcel in) {
        color = in.readInt();
        name = in.readString();
    }
}
