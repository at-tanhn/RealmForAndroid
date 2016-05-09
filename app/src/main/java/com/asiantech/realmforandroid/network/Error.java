package com.asiantech.realmforandroid.network;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by TienLQ on 4/21/16
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Error {

    private int stateCode;
    @SerializedName("errors")
    private String message;
}
