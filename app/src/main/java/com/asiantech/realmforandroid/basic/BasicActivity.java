package com.asiantech.realmforandroid.basic;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.asiantech.realmforandroid.BaseRealmActivity;
import com.asiantech.realmforandroid.R;
import com.asiantech.realmforandroid.model.ATer;
import com.asiantech.realmforandroid.model.MotoBike;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by honhattan on 4/13/16.
 */
public class BasicActivity extends BaseRealmActivity implements View.OnClickListener {
    RecyclerView mRecyclerView;
    Button mBtnFindAllSorted, mBtnBetween, mBtnContains, mBtnEqualTo;
    EditText mEdtFrom, mEdtTo, mEdtContains, mEdtEqualTo;
    QueryRecyclerAdapter queryRecyclerAdapter;
    List<ATer> aTerList = new ArrayList<>();


    EditText mEdtATerName, mEdtATerAge;
    EditText mEdtMotoBikeName, mEdtMotoBikeColor;
    Button mBtnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ///////////////////////
        mEdtATerName = (EditText) findViewById(R.id.edtAterName);
        mEdtATerAge = (EditText) findViewById(R.id.edtAterTeam);
        mEdtMotoBikeColor = (EditText) findViewById(R.id.edtMotoBikeColor);
        mEdtMotoBikeName = (EditText) findViewById(R.id.edtMotoBikeName);
        mBtnAdd = (Button) findViewById(R.id.btnAdd);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                ATer aTer = realm.createObject(ATer.class);
                try {
                    aTer.setAge(Integer.valueOf(mEdtATerAge.getText().toString()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    mEdtATerAge.setError("NumberFormatException");
                }
                aTer.setName(mEdtATerName.getText().toString());
                MotoBike motoBike = realm.createObject(MotoBike.class);
                motoBike.setName(mEdtMotoBikeName.getText().toString());
                motoBike.setColor(mEdtMotoBikeColor.getText().toString());
                aTer.setMotobike(motoBike);
                realm.commitTransaction();
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerQuery);
        //finallSorted
        mBtnFindAllSorted = (Button) findViewById(R.id.btnFindAllSorted);
        //Between from to
        mBtnBetween = (Button) findViewById(R.id.btnBetween);
        mEdtFrom = (EditText) findViewById(R.id.edtFrom);
        mEdtTo = (EditText) findViewById(R.id.edtTo);
        //contains
        mBtnContains = (Button) findViewById(R.id.btnContains);
        mEdtContains = (EditText) findViewById(R.id.edtContainsName);
        //Equalto
        mBtnEqualTo = (Button) findViewById(R.id.btnEqualTo);
        mEdtEqualTo = (EditText) findViewById(R.id.edtEqualTo);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        queryRecyclerAdapter = new QueryRecyclerAdapter(this, aTerList);
        mRecyclerView.setAdapter(queryRecyclerAdapter);

        mBtnFindAllSorted.setOnClickListener(this);
        mBtnBetween.setOnClickListener(this);
        mBtnContains.setOnClickListener(this);
        mBtnEqualTo.setOnClickListener(this);

    }

    private void findAllSorted() {
        aTerList.addAll(realm.where(ATer.class).findAllSorted(ATer.FIELD_NAME));
        Log.d("findAllSorted", "" + aTerList.size());
        queryRecyclerAdapter.notifyDataSetChanged();
    }

    private void betweenFromTo() {
        int from, to;
        try {
            from = Integer.valueOf(mEdtFrom.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            mEdtFrom.setError("NumberFormatException");
            return;
        }
        try {
            to = Integer.valueOf(mEdtTo.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            mEdtTo.setError("NumberFormatException");
            return;
        }
        aTerList.clear();
        RealmResults<ATer> aTerRealmQuery = realm.where(ATer.class).between(ATer.FIELD_AGE, from, to).findAll();
        aTerList.addAll(aTerRealmQuery);
        Log.d("betweenFromTo", "" + aTerRealmQuery.size());
        queryRecyclerAdapter.notifyDataSetChanged();
    }

    private void contains() {
        aTerList.clear();
        RealmResults<ATer> aTerRealmQuery = realm.where(ATer.class).contains(ATer.FIELD_NAME, mEdtContains.getText().toString()).findAll();
        aTerList.addAll(aTerRealmQuery);
        queryRecyclerAdapter.notifyDataSetChanged();
    }

    private void equalTo() {
        aTerList.clear();
        RealmResults<ATer> aTerRealmQuery = realm.where(ATer.class).equalTo(ATer.FIELD_NAME, mEdtEqualTo.getText().toString()).findAll();
        aTerList.addAll(aTerRealmQuery);
        queryRecyclerAdapter.notifyDataSetChanged();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFindAllSorted:
                findAllSorted();
                break;
            case R.id.btnBetween:
                betweenFromTo();
                break;
            case R.id.btnContains:
                contains();
                break;
            case R.id.btnEqualTo:
                equalTo();
                break;
        }
    }
}
