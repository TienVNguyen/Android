/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.phonebook.utils;

/**
 * Represents the home screen of the app
 *
 * @author Created by TienVNguyen on 09/03/2016.
 */
public class Constants {
    /**
     * Constants related to locations in Firebase, such as the name of the node
     * where active lists are stored (ie "activeLists")
     */
    public static final String FIREBASE_LOCATION_AUTHOR_DETAILS = "authorDetails";
    public static final String FIREBASE_LOCATION_ACTIVE_LISTS = "phoneBooks";
    public static final String FIREBASE_LOCATION_PHONE_LIST_ITEMS = "phoneListItems";

    /**
     * Constants for Firebase object properties
     */
    public static final String FIREBASE_PROPERTY_PHONE_BOOK = "phoneBook";
    public static final String FIREBASE_PROPERTY_NAME = "name";
    public static final String FIREBASE_PROPERTY_PHONE = "phone";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_ACTIVELIST = "activeList";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String FIREBASE_PROPERTY_AVATAR = "urlAvatar";
    public static final String FIREBASE_PROPERTY_GIT = "urlGit";
    public static final String FIREBASE_PROPERTY_GIT_EXERCISE = "urlGitExercise";

    /**
     * Constants for Firebase URL
     */
    //public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final String FIREBASE_URL = "https://phone-book-db.firebaseio.com/";
    public static final String FIREBASE_URL_ACTIVE_LISTS = FIREBASE_URL + FIREBASE_LOCATION_ACTIVE_LISTS;
    public static final String FIREBASE_URL_AUTHOR_DETAILS = FIREBASE_URL + FIREBASE_LOCATION_AUTHOR_DETAILS;

    /**
     * Constants for bundles, extras and shared preferences keys
     */
    public static final String KEY_LIST_NAME = "LIST_NAME";
    public static final String KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";
    public static final String KEY_LIST_ID = "LIST_ID";
    public static final String KEY_LIST_ITEM_NAME = "ITEM_NAME";
    public static final String KEY_LIST_ITEM_ID = "LIST_ITEM_ID";
}
