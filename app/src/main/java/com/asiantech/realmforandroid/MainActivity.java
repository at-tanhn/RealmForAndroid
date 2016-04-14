package com.asiantech.realmforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootLayout = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        Button btnInsert = (Button) findViewById(R.id.btnInsert);
        Button btnQuery = (Button) findViewById(R.id.btnQuery);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnAdd.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                Intent intent1 = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnDelete:
                Intent intent2 = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnInsert:
                Intent intent3 = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent3);
                break;
            case R.id.btnQuery:
                Intent intent4 = new Intent(MainActivity.this, QueryActivity.class);
                startActivity(intent4);
                break;

        }
    }
}