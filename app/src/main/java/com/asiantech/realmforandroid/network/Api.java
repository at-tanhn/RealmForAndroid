package com.asiantech.realmforandroid.network;

import com.asiantech.realmforandroid.convert.SearchResult;
import com.asiantech.realmforandroid.network.core.Callback;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;


/**
 * Created by TienLQ on 4/21/16
 */
public interface Api {
    //search
    @FormUrlEncoded
    @POST("/clinic/search")
    void getSearchDental(@Query(Parameter.APP_ID) String appID,
                         @Field(Parameter.KEYWORD1) String keyword1,
                         @Field(Parameter.KEYWORD2) String keyword2,
                         @Field(Parameter.SORT) String sort,
                         @Field(Parameter.START) String start,
                         @Field(Parameter.ROW) String rows,
                         @Field(Parameter.LAT) String lat,
                         @Field(Parameter.LON) String lng,
                         @Field(Parameter.DISTANCE) String distance,
                         @Field(Parameter.MEDICAL_CONTENT) String medicalContents,
                         @Field(Parameter.MEDICAL_FEATURE) String medicalFeature,
                         @Field(Parameter.DOCTOR_FEATURE) String doctorFeature,
                         Callback<SearchResult> cb);



}
