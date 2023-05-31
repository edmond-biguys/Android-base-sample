// IMyAidlRemote.aidl
package com.cym.sample.binder;
import com.cym.sample.binder.User;

// Declare any non-default types here with import statements

interface IMyAidlRemote {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    int getUid();
    User getUser(int uid);
}