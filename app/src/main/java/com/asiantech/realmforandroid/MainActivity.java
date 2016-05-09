package com.asiantech.realmforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.asiantech.realmforandroid.basic.BasicActivity;
import com.asiantech.realmforandroid.convert.SearchResult;
import com.asiantech.realmforandroid.json.JsonActivity;
import com.asiantech.realmforandroid.migration.MigrationActivity;
import com.asiantech.realmforandroid.network.core.ApiClient;
import com.asiantech.realmforandroid.network.core.Callback;
import com.asiantech.realmforandroid.thread.ThreadActivity;

import retrofit.RetrofitError;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootLayout = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnBasic = (Button) findViewById(R.id.btnBasic);
        Button btnJson = (Button) findViewById(R.id.btnJson);
        Button btnThread = (Button) findViewById(R.id.btnThread);
        Button btnMigration = (Button) findViewById(R.id.btnMigration);
        btnBasic.setOnClickListener(this);
        btnJson.setOnClickListener(this);
        btnThread.setOnClickListener(this);
        btnMigration.setOnClickListener(this);
        ApiClient.call().getSearchDental("id59533256eparkdental", "tan", "", "", "", "", "", "", "", "", "", "", new Callback<SearchResult>() {
            @Override
            public void success(SearchResult searchResult) {
                Log.d("tesr","success");
            }

            @Override
            public void failure(RetrofitError error, Error myError) {

            }
        });
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBasic:
                Intent intent1 = new Intent(MainActivity.this, BasicActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnThread:
                Intent intent2 = new Intent(MainActivity.this, ThreadActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnJson:
                Intent intent3 = new Intent(MainActivity.this, JsonActivity.class);
                startActivity(intent3);
                break;
            case R.id.btnMigration:
                Intent intent4 = new Intent(MainActivity.this, MigrationActivity.class);
                startActivity(intent4);
                break;

        }
    }
}