package com.asiantech.realmforandroid.basic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiantech.realmforandroid.R;
import com.asiantech.realmforandroid.model.ATer;

import java.util.List;

/**
 * Created by honhattan on 4/13/16.
 */
public class QueryRecyclerAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private List<ATer> feedItemList;
    private Context mContext;

    public QueryRecyclerAdapter(Context context, List<ATer> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_ater, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        ATer feedItem = feedItemList.get(i);
        customViewHolder.tvATerName.setText("Name:" + feedItem.getName());
        customViewHolder.tvATerAge.setText("Age:" + feedItem.getAge());
        if (feedItem.getMotobike() != null) {
            customViewHolder.tvMotoBikeColor.setText("Color:" + feedItem.getMotobike().getColor());
            customViewHolder.tvMotoBikeName.setText("Name:" + feedItem.getMotobike().getName());
        }


        //Setting text view title

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }
}
