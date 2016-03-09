/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.phonebook.model;

import com.firebase.client.ServerValue;
import com.training.tiennguyen.phonebook.utils.Constants;

import java.util.HashMap;

/**
 * Defines the data structure for PhoneBook objects.
 *
 * @author Created by TienVNguyen on 09/03/2016.
 */
public class PhoneBook {
    private String name;
    private String phone;
    private HashMap<String, Object> timestampLastChanged;

    public PhoneBook() {
    }

    public PhoneBook(String name, String phone) {
        this.name = name;
        this.phone = phone;
        HashMap<String, Object> timestampLastChangedObj = new HashMap<>();
        timestampLastChangedObj.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampLastChangedObj;
    }

    public PhoneBook(String name, String phone, HashMap<String, Object> timestampLastChanged) {
        this.name = name;
        this.phone = phone;
        this.timestampLastChanged = timestampLastChanged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public HashMap<String, Object> getTimestampLastChanged() {
        return timestampLastChanged;
    }

    public void setTimestampLastChanged(HashMap<String, Object> timestampLastChanged) {
        this.timestampLastChanged = timestampLastChanged;
    }
}
