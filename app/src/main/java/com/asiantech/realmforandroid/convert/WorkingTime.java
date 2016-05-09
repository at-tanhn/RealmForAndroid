package com.asiantech.realmforandroid.convert;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.WorkingTimeRealmProxy;
import lombok.Data;

/**
 * Created by TienLQ on 4/18/16
 */
@Data
@org.parceler.Parcel(implementations = {WorkingTimeRealmProxy.class},
        value = org.parceler.Parcel.Serialization.FIELD,
        analyze = {WorkingTime.class})
public class WorkingTime extends RealmObject implements Parcelable {
    public WorkingTime() {
    }
    @SerializedName("start")
    private String start;
    @SerializedName("end")
    private String end;
    @SerializedName("mon")
    private String mon;
    @SerializedName("tue")
    private String tue;
    @SerializedName("wed")
    private String wed;
    @SerializedName("thu")
    private String thu;
    @SerializedName("fri")
    private String fri;
    @SerializedName("sat")
    private String sat;
    @SerializedName("sun")
    private String sun;
    @SerializedName("holiday")
    private String holiday;

    protected WorkingTime(Parcel in) {
        start = in.readString();
        end = in.readString();
        mon = in.readString();
        tue = in.readString();
        wed = in.readString();
        thu = in.readString();
        fri = in.readString();
        sat = in.readString();
        sun = in.readString();
        holiday = in.readString();
    }

    public static final Creator<WorkingTime> CREATOR = new Creator<WorkingTime>() {
        @Override
        public WorkingTime createFromParcel(Parcel in) {
            return new WorkingTime(in);
        }

        @Override
        public WorkingTime[] newArray(int size) {
            return new WorkingTime[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(start);
        dest.writeString(end);
        dest.writeString(mon);
        dest.writeString(tue);
        dest.writeString(wed);
        dest.writeString(thu);
        dest.writeString(fri);
        dest.writeString(sat);
        dest.writeString(sun);
        dest.writeString(holiday);
    }
}
