package com.asiantech.realmforandroid.network.core;

import android.util.Log;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TienLQ on 4/21/16
 *
 * @param <T>
 */
public abstract class Callback<T> implements retrofit.Callback<T> {

    private static final String TAG = Callback.class.getSimpleName();

    public Callback() {
    }

    public abstract void success(T t);

    public abstract void failure(RetrofitError error, Error myError);

    @Override
    public void success(T t, Response response) {
        success(t);
    }

    public void failure(RetrofitError error) {
        if (error.getResponse() != null) {
            try {
                Error myError = (Error) error.getBodyAs(Error.class);
                failure(error, myError);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                Error serverError = new Error(503, "Service Unavailable");
                failure(error, serverError);
            }
        } else {
            failure(error, new Error(0, null));
        }
    }

}
