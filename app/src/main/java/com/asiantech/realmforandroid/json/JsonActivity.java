package com.asiantech.realmforandroid.json;

import android.os.Bundle;
import android.widget.GridView;

import com.asiantech.realmforandroid.BaseRealmActivity;
import com.asiantech.realmforandroid.R;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by honhattan on 4/13/16.
 */
public class JsonActivity extends BaseRealmActivity {

    private GridView mGridView;
    private CityAdapter mAdapter;
    //   private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_example);

//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
//        Realm.deleteRealm(realmConfiguration);
//        realm = Realm.getInstance(realmConfiguration);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Load from file "cities.json" first time
        if (mAdapter == null) {
            List<City> cities = null;
            try {
                cities = loadCities();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //This is the GridView adapter
            mAdapter = new CityAdapter(this);
            mAdapter.setData(cities);

            //This is the GridView which will display the list of cities
            mGridView = (GridView) findViewById(R.id.cities_list);
            mGridView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            mGridView.invalidate();
        }
    }


    public List<City> loadCities() throws IOException {

        loadJsonFromStream();
        loadJsonFromJsonObject();
        loadJsonFromString();

        return realm.allObjects(City.class);
    }

    private void loadJsonFromStream() throws IOException {
        // Use streams if you are worried about the size of the JSON whether it was persisted on disk
        // or received from the network.
        InputStream stream = getAssets().open("cities.json");

        // Open a transaction to store items into the realm
        realm.beginTransaction();
        try {
            realm.createAllFromJson(City.class, stream);
            realm.commitTransaction();
        } catch (IOException e) {
            // Remember to cancel the transaction if anything goes wrong.
            realm.cancelTransaction();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private void loadJsonFromJsonObject() {
        Map<String, String> city = new HashMap<String, String>();
        city.put("name", "København");
        city.put("votes", "9");
        JSONObject json = new JSONObject(city);

        realm.beginTransaction();
        realm.createObjectFromJson(City.class, json);
        realm.commitTransaction();
    }

    private void loadJsonFromString() {
        String json = "{ name: \"Aarhus\", votes: 99 }";

        realm.beginTransaction();
        realm.createObjectFromJson(City.class, json);
        realm.commitTransaction();
    }
}