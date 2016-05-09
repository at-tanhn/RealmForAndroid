package com.asiantech.realmforandroid.network.core;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by TienLQ on 4/21/16
 */
public final class ApiConfig {
    final Context mContext;
    final String mBaseUrl;

    private ApiConfig(Builder builder) {
        this.mContext = builder.mContext;
        this.mBaseUrl = builder.mBaseUrl;
    }

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    /**
     * Builder
     */
    public static class Builder {
        private final Context mContext;
        private String mBaseUrl;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setBaseUrl(@NonNull String baseUrl) {
            this.mBaseUrl = baseUrl;
            return this;
        }

        public ApiConfig build() {
            return new ApiConfig(this);
        }
    }

}
