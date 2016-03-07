package com.udacity.firebase.shoppinglistplusplus.utils;

/**
 * Constants class store most important strings and paths of the app
 */
public final class Constants {

    /**
     * Constants related to locations in Firebase, such as the name of the node
     * where active lists are stored (ie "activeLists")
     */


    /**
     * Constants for Firebase object properties
     */
    public static final String FIREBASE_PROPERTY_LISTNAME = "listName";
    public static final String FIREBASE_PROPERTY_ACTIVELIST = "activeList";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_LOCATION_ACTIVE_LIST = "locationActiveList";

    /**
     * Constants for Firebase URL
     */
    //public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final String FIREBASE_URL = "https://shoplist-sample.firebaseio.com/";
    public static final String FIREBASE_URL_ACTIVE_LIST = FIREBASE_URL + "/" + FIREBASE_PROPERTY_ACTIVELIST;

    /**
     * Constants for bundles, extras and shared preferences keys
     */
    public static final String KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";

}
