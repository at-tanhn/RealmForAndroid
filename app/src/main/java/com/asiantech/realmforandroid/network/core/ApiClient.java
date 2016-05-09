package com.asiantech.realmforandroid.network.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.asiantech.realmforandroid.convert.RealmString;
import com.asiantech.realmforandroid.convert.RealmStringDeserializer;
import com.asiantech.realmforandroid.network.Api;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import io.realm.RealmList;
import io.realm.RealmObject;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;


/**
 * Created by TienLQ on 4/20/16
 */
public class ApiClient {

    private static final String HEADER_UA = "User-Agent";
    private static final String TAG = ApiClient.class.getSimpleName();

    private static final int TIMEOUT_CONNECTION = 10000;

    private static ApiClient sApiClient;
    /**
     * Api service
     */
    private Api api;
    /**
     * android application context
     */
    private Context mContext;
    /**
     * Headers that need to be added to every request can be specified using a RequestInterceptor
     */
    private final RequestInterceptor mRequestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            request.addHeader(HEADER_UA, createUserAgent());
        }
    };

    public static Api call() {
        return getInstance().api;
    }

    /**
     * get singleton instance
     *
     * @return current apiclient
     */
    public static synchronized ApiClient getInstance() {
        if (sApiClient == null) {
            sApiClient = new ApiClient();
        }
        return sApiClient;
    }

    public void init(ApiConfig apiConfig) {
        Log.d(TAG, "initialize start");
        mContext = apiConfig.mContext;
        // Gson rules
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {
                }.getType(), new RealmStringDeserializer())
                .create();

        // initialize OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(TIMEOUT_CONNECTION, TimeUnit.MILLISECONDS);

        // initialize RestAdapter
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(apiConfig.mBaseUrl)
                .setClient(new OkClient(okHttpClient))
                .setRequestInterceptor(mRequestInterceptor)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        // implementation of the TryApi interface.
        api = restAdapter.create(Api.class);
        Log.d(TAG, "initialize end");
    }

    private String createUserAgent() {
        PackageManager pm = mContext.getPackageManager();
        String versionName = "";
        try {
            PackageInfo packageInfo = pm.getPackageInfo(mContext.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return System.getProperty("http.agent") + " " + mContext.getPackageName() + "/" + versionName;
    }


}
