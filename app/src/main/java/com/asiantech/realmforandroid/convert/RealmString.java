package com.asiantech.realmforandroid.convert;

import io.realm.RealmObject;
import io.realm.RealmStringRealmProxy;

/**
 * Created by honhattan on 5/5/16.
 */
@org.parceler.Parcel(implementations = {RealmStringRealmProxy.class},
        value = org.parceler.Parcel.Serialization.FIELD,
        analyze = {RealmString.class})
public class RealmString extends RealmObject {

    private String stringValue;

    public RealmString() {
    }

    public RealmString(String stringValue) {
        this.stringValue = stringValue;
    }



    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }


}
