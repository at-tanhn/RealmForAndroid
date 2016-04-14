package com.asiantech.realmforandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asiantech.realmforandroid.model.ATer;
import com.asiantech.realmforandroid.model.MotoBike;

import io.realm.RealmChangeListener;

/**
 * Created by honhattan on 4/13/16.
 */
public class AddActivity extends BaseRealmActivity {
    EditText mEdtATerName, mEdtATerAge;
    EditText mEdtMotoBikeName, mEdtMotoBikeColor;
    Button mBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
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
        realm.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                Toast.makeText(AddActivity.this, "Added", Toast.LENGTH_LONG).show();
            }
        });

    }
}
