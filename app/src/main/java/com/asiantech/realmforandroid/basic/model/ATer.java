package com.asiantech.realmforandroid.basic.model;

/**
 * Created by honhattan on 4/13/16.
 */

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

// Your model just have to extend RealmObject.
// This will inherit an annotation which produces proxy getters and setters for all fields.
public class ATer extends RealmObject {
    // All fields are by default persisted.
    public static final String FIELD_NAME = "name";
    public static final String FIELD_AGE = "age";
    public static final String FIELD_MOTOBIKE = "motobike";
    public static final String FIELD_LAPTOP = "laptop";
    private String name;
    private int age;

    // Other objects in a one-to-one relation must also subclass RealmObject
    private MotoBike motobike;

    // One-to-many relations is simply a RealmList of the objects which also subclass RealmObject
    private RealmList<Laptop> laptop;

    // You can instruct Realm to ignore a field and not persist it.
    @Ignore
    private int tempReference;


    // Let your IDE generate getters and setters for you!
    // Or if you like you can even have public fields and no accessors! See MotoBike.java and Laptop.java
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MotoBike getMotobike() {
        return motobike;
    }

    public void setMotobike(MotoBike motobike) {
        this.motobike = motobike;
    }

    public RealmList<Laptop> getLaptop() {
        return laptop;
    }

    public void setLaptop(RealmList<Laptop> laptop) {
        this.laptop = laptop;
    }

    public int getTempReference() {
        return tempReference;
    }

    public void setTempReference(int tempReference) {
        this.tempReference = tempReference;
    }


}