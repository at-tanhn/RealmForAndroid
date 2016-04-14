package com.asiantech.realmforandroid;

import android.app.Activity;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by honhattan on 4/13/16.
 */
public class BaseRealmActivity extends Activity {
    protected Realm realm;
    protected RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the Realm configuration
        realmConfig = new RealmConfiguration.Builder(this).build();
        // Open the Realm for the UI thread.
        try {
            realm = Realm.getInstance(realmConfig);
        } catch (RealmMigrationNeededException exception) {
            try {
                Realm.deleteRealm(realmConfig);
                //Realm file has been deleted.
                realm = Realm.getInstance(realmConfig);
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }
}
