package com.asiantech.realmforandroid.convert;

import com.google.gson.annotations.SerializedName;

import org.parceler.ParcelPropertyConverter;

import io.realm.ItemRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by Binc on 27/04/2016.
 */
@Data
@org.parceler.Parcel(implementations = {ItemRealmProxy.class},
        value = org.parceler.Parcel.Serialization.FIELD,
        analyze = {Item.class})
public class Item extends RealmObject {
    public Item() {
    }
    @PrimaryKey
    @SerializedName("id")
    String id;
    @SerializedName("clinic_name")
    String clinicName;
    @SerializedName("clinic_name_kana")
    String clinicNameKana;
    @SerializedName("post_code")
    String postCode;
    @SerializedName("city")
    String city;
    @SerializedName("district")
    String district;
    @SerializedName("building")
    String building;
    @SerializedName("lat")
    String lat;
    @SerializedName("lon")
    String lon;
    @SerializedName("tel")
    String tel;
    @SerializedName("medical_specialist")
    String medicalSpecialist;
    @SerializedName("thumbnail_image")
    String thumbNailImage;
    @SerializedName("prov_name")
    String provName;
    @SerializedName("stations")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    RealmList<Station> stations;
    @SerializedName("comment_count")
    String commentCount;
    @SerializedName("answer_count")
    String answerCount;
    @SerializedName("distance")
    String distance;
    @SerializedName("reserve_today")
    String reserveToday;
    @SerializedName("reserve_tomorrow")
    String reserveTomorrow;
    @SerializedName("reserve_after_tomorrow")
    String reserveAfterTomorrow;
    @SerializedName("reserve_url")
    String reserveUrl;
    @SerializedName("medical_contents")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    RealmList<RealmString> medicalContents;
    @SerializedName("own_expense")
    String ownExpense;
    @SerializedName("consultation_feature")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    RealmList<RealmString> consultationFeature;
    @SerializedName("working_times")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    RealmList<WorkingTime> workingTimes;
    @SerializedName("images")
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    RealmList<RealmString> images;
    Integer scroll = 0;
    Integer pos = 0;
    Boolean isSee = false;
    Boolean isCall = false;
    Boolean isFavourite = false;
    String timeCall;



}
