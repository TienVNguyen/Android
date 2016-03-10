/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.phonebook.model;

/**
 * Defines the data structure for element inside PhoneBook list.
 *
 * @author Created by TienVNguyen on 11/03/2016.
 */
public class PhoneBookItem {
    private String itemName;
    private String owner;

    public PhoneBookItem() {
    }

    public PhoneBookItem(String itemName) {
        this.itemName = itemName;
        this.owner = "Anonymous Owner";
    }

    public String getItemName() {
        return itemName;
    }

    public String getOwner() {
        return owner;
    }
}
