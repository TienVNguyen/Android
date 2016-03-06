/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.udacity.firebase.shoppinglistplusplus.model;

import com.firebase.client.ServerValue;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

/**
 * ShoppingList
 * <p/>
 * Created by TienVNguyen on 05/03/2016.
 */
public class ShoppingList {
    private String listName;
    private String owner;
    private HashMap<String, Object> timestampLastChanged;

    public ShoppingList() {
    }

    public ShoppingList(String listName, String owner) {
        this.listName = listName;
        this.owner = owner;
        HashMap<String, Object> timestampLastChangedObj = new HashMap<String, Object>();
        timestampLastChangedObj.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampLastChangedObj;
    }

    public ShoppingList(String listName, String owner, HashMap<String, Object> timestampLastChanged) {
        this.listName = listName;
        this.owner = owner;
        this.timestampLastChanged = timestampLastChanged;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public HashMap<String, Object> getTimestampLastChanged() {
        return timestampLastChanged;
    }

    public void setTimestampLastChanged(HashMap<String, Object> timestampLastChanged) {
        this.timestampLastChanged = timestampLastChanged;
    }
}
