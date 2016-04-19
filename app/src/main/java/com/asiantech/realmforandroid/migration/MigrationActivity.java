package com.asiantech.realmforandroid.migration;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asiantech.realmforandroid.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by honhattan on 4/13/16.
 */
public class MigrationActivity extends Activity {

    public static final String TAG = MigrationActivity.class.getName();

    private LinearLayout rootLayout = null;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_migration_example);

        rootLayout = ((LinearLayout) findViewById(R.id.container));
        rootLayout.removeAllViews();

        // 3 versions of the databases for testing. Normally you would only have one.
        copyBundledRealmFile(this.getResources().openRawResource(R.raw.default0), "default0");
        copyBundledRealmFile(this.getResources().openRawResource(R.raw.default1), "default1");
        copyBundledRealmFile(this.getResources().openRawResource(R.raw.default2), "default2");

        // When you create a RealmConfiguration you can specify the version of the schema.
        // If the schema does not have that version a RealmMigrationNeededException will be thrown.
        RealmConfiguration config0 = new RealmConfiguration.Builder(this)
                .name("default0")
                .schemaVersion(3)
                .build();

        // You can then manually call Realm.migrateRealm().
        try {
            Realm.migrateRealm(config0, new Migration());
        } catch (FileNotFoundException ignored) {
            // If the Realm file doesn't exist, just ignore.
        }
        try {
            realm = Realm.getInstance(config0);
        } catch (RealmMigrationNeededException exception) {
            exception.printStackTrace();
            try {
                Realm.deleteRealm(config0);
                //Realm file has been deleted.
                realm = Realm.getInstance(config0);
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }
        showStatus("Default0");
        showStatus(realm);
        realm.close();

        // Or you can add the migration code to the configuration. This will run the migration code without throwing
        // a RealmMigrationNeededException.
        RealmConfiguration config1 = new RealmConfiguration.Builder(this)
                .name("default1")
                .schemaVersion(3)
                .migration(new Migration())
                .build();

//        realm = Realm.getInstance(config1); // Automatically run migration if needed
        try {
            realm = Realm.getInstance(config1);
        } catch (RealmMigrationNeededException exception) {
            exception.printStackTrace();
            try {
                Realm.deleteRealm(config1);
                //Realm file has been deleted.
                realm = Realm.getInstance(config1);
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }
        showStatus("Default1");
        showStatus(realm);
        realm.close();

        // or you can set .deleteRealmIfMigrationNeeded() if you don't want to bother with migrations.
        // WARNING: This will delete all data in the Realm though.
        RealmConfiguration config2 = new RealmConfiguration.Builder(this)
                .name("default2")
                .schemaVersion(3)
                .deleteRealmIfMigrationNeeded()
                .build();

//        realm = Realm.getInstance(config2);
        try {
            realm = Realm.getInstance(config2);
        } catch (RealmMigrationNeededException exception) {
            exception.printStackTrace();
            try {
                Realm.deleteRealm(config2);
                //Realm file has been deleted.
                realm = Realm.getInstance(config2);
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }
        showStatus("default2");
        showStatus(realm);
        realm.close();
    }

    private String copyBundledRealmFile(InputStream inputStream, String outFileName) {
        try {
            File file = new File(this.getFilesDir(), outFileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, bytesRead);
            }
            outputStream.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String realmString(Realm realm) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Person person : realm.allObjects(Person.class)) {
            stringBuilder.append(person.toString()).append("\n");
        }

        return (stringBuilder.length() == 0) ? "<data was deleted>" : stringBuilder.toString();
    }

    private void showStatus(Realm realm) {
        showStatus(realmString(realm));
    }

    private void showStatus(String txt) {
        Log.i(TAG, txt);
        TextView tv = new TextView(this);
        tv.setText(txt);
        rootLayout.addView(tv);
    }
}