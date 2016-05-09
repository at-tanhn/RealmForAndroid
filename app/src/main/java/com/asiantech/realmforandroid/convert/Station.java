package com.asiantech.realmforandroid.convert;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.StationRealmProxy;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by TienLQ on 4/18/16
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
@org.parceler.Parcel(implementations = {StationRealmProxy.class},
        value = org.parceler.Parcel.Serialization.FIELD,
        analyze = {Station.class})
public class Station extends RealmObject implements Parcelable {
    public Station() {

    }

    @SerializedName("station_name")
    private String stationName;
    @SerializedName("station_exit")
    private String stationExit;
    @SerializedName("means")
    private String means;
    @SerializedName("time_required")
    private String timeRequired;


    protected Station(Parcel in) {
        stationName = in.readString();
        stationExit = in.readString();
        means = in.readString();
        timeRequired = in.readString();
    }

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stationName);
        dest.writeString(stationExit);
        dest.writeString(means);
        dest.writeString(timeRequired);
    }
}
