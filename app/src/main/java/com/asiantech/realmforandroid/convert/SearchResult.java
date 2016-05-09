package com.asiantech.realmforandroid.convert;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by Binc on 27/04/2016.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class SearchResult {
    @SerializedName("status")
    String status;
    @SerializedName("num_found")
    String numFound;
    @SerializedName("rows")
    String rows;
    @SerializedName("items")
    List<Item> items;
}
