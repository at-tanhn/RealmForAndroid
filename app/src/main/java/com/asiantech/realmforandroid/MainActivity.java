package com.asiantech.realmforandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asiantech.realmforandroid.model.Cat;
import com.asiantech.realmforandroid.model.Dog;
import com.asiantech.realmforandroid.model.Person;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootLayout = null;

    private Realm realm;
    private RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = ((LinearLayout) findViewById(R.id.container));
        rootLayout.removeAllViews();

        // These operations are small enough that
        // we can generally safely run them on the UI thread.

        // Create the Realm configuration
        realmConfig = new RealmConfiguration.Builder(this).build();
        // Open the Realm for the UI thread.
        realm = Realm.getInstance(realmConfig);

        basicCRUD(realm);
//        basicQuery(realm);
//        basicLinkQuery(realm);
//
//        // More complex operations can be executed on another thread.
//        new AsyncTask<Void, Void, String>() {
//            @Override
//            protected String doInBackground(Void... voids) {
//                String info;
//                info = complexReadWrite();
//                info += complexQuery();
//                return info;
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                showStatus(result);
//            }
//        }.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }

    private void showStatus(String txt) {
        Log.i(TAG, txt);
        TextView tv = new TextView(this);
        tv.setText(txt);
        rootLayout.addView(tv);
    }

    private void basicCRUD(Realm realm) {
        showStatus("Perform basic Create/Read/Update/Delete (CRUD) operations...");

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.beginTransaction();

        // Add a person
        for (int i = 0; i < 100; i++) {
            Person person = realm.createObject(Person.class);
            person.setId(i);
            person.setName("Young Person");
            person.setAge(14);

        }

        // When the transaction is committed, all changes a synced to disk.
        realm.commitTransaction();
        int size = realm.where(Person.class).findAll().size();
        for (int i = 0; i < size; i++) {
            Person person = realm.where(Person.class).findAll().get(i);
            showStatus(person.getId()+":"+person.getName() + ":" + person.getAge());

        }
        // Find the first person (no query conditions) and read a field
//        person = realm.where(Person.class).findFirst();
//        showStatus(person.getName() + ":" + person.getAge());
//
//        // Update person in a transaction
//        realm.beginTransaction();
//        person.setName("Senior Person");
//        person.setAge(99);
//        showStatus(person.getName() + " got older: " + person.getAge());
//        realm.commitTransaction();

        // Delete all persons
//        realm.beginTransaction();
//        realm.allObjects(Person.class).
//        realm.commitTransaction();

    }

    private void basicQuery(Realm realm) {
        showStatus("\nPerforming basic Query operation...");
        showStatus("Number of persons: " + realm.allObjects(Person.class).size());

        RealmResults<Person> results = realm.where(Person.class).equalTo("age", 99).findAll();

        showStatus("Size of result set: " + results.size());
    }

    private void basicLinkQuery(Realm realm) {
        showStatus("\nPerforming basic Link Query operation...");
        showStatus("Number of persons: " + realm.allObjects(Person.class).size());

        RealmResults<Person> results = realm.where(Person.class).equalTo("cats.name", "Tiger").findAll();

        showStatus("Size of result set: " + results.size());
    }

    private String complexReadWrite() {
        String status = "\nPerforming complex Read/Write operation...";

        // Open the default realm. All threads must use it's own reference to the realm.
        // Those can not be transferred across threads.
        Realm realm = Realm.getInstance(realmConfig);

        // Add ten persons in one transaction
        realm.beginTransaction();
        Dog fido = realm.createObject(Dog.class);
        fido.name = "fido";
        for (int i = 0; i < 10; i++) {
            Person person = realm.createObject(Person.class);
            person.setId(i);
            person.setName("Person no. " + i);
            person.setAge(i);
            person.setDog(fido);

            // The field tempReference is annotated with @Ignore.
            // This means setTempReference sets the Person tempReference
            // field directly. The tempReference is NOT saved as part of
            // the RealmObject:
            person.setTempReference(42);

            for (int j = 0; j < i; j++) {
                Cat cat = realm.createObject(Cat.class);
                cat.name = "Cat_" + j;
                person.getCats().add(cat);
            }
        }
        realm.commitTransaction();

        // Implicit read transactions allow you to access your objects
        status += "\nNumber of persons: " + realm.allObjects(Person.class).size();

        // Iterate over all objects
        for (Person pers : realm.allObjects(Person.class)) {
            String dogName;
            if (pers.getDog() == null) {
                dogName = "None";
            } else {
                dogName = pers.getDog().name;
            }
            status += "\n" + pers.getName() + ":" + pers.getAge() + " : " + dogName + " : " + pers.getCats().size();
        }

        // Sorting
        RealmResults<Person> sortedPersons = realm.allObjects(Person.class);
        sortedPersons.sort("age", Sort.DESCENDING);
        status += "\nSorting " + sortedPersons.last().getName() + " == " + realm.allObjects(Person.class).first()
                .getName();

        realm.close();
        return status;
    }

    private String complexQuery() {
        String status = "\n\nPerforming complex Query operation...";

        Realm realm = Realm.getInstance(realmConfig);
        status += "\nNumber of persons: " + realm.allObjects(Person.class).size();

        // Find all persons where age between 7 and 9 and name begins with "Person".
        RealmResults<Person> results = realm.where(Person.class)
                .between("age", 7, 9)       // Notice implicit "and" operation
                .beginsWith("name", "Person").findAll();
        status += "\nSize of result set: " + results.size();

        realm.close();
        return status;
    }
}