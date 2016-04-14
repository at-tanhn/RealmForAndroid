package com.asiantech.realmforandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.asiantech.realmforandroid.R;

/**
 * Created by honhattan on 4/13/16.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {
    ;
    protected TextView tvATerName, tvATerAge, tvMotoBikeColor, tvMotoBikeName;

    public CustomViewHolder(View view) {
        super(view);
        this.tvATerName = (TextView) view.findViewById(R.id.tvATerName);
        this.tvATerAge = (TextView) view.findViewById(R.id.tvATerAge);
        this.tvMotoBikeColor = (TextView) view.findViewById(R.id.tvMotoBikeColor);
        this.tvMotoBikeName = (TextView) view.findViewById(R.id.tvMotoBikeName);
    }
}