/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.phonebook.activeLists;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.training.tiennguyen.phonebook.R;
import com.training.tiennguyen.phonebook.model.PhoneBook;

/**
 * Populates the list_view_active_lists inside PhoneListFragment
 *
 * @author Created by TienVNguyen on 11/03/2016.
 */
public class PhoneListAdapter extends FirebaseListAdapter<PhoneBook> {
    /**
     * Public constructor that initializes private instance variables when adapter is created
     */
    public PhoneListAdapter(Activity activity, Class<PhoneBook> modelClass, int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }


    /**
     * Protected method that populates the view attached to the adapter (list_view_active_lists)
     * with items inflated from fragment_phone_list_adaptert_adapter.xml
     * populateView also handles data changes and updates the listView accordingly
     */
    @Override
    protected void populateView(View view, PhoneBook phoneBook, int i) {
        /**
         * Grab the needed Textview and strings
         */
        TextView textViewListName = (TextView) view.findViewById(R.id.text_view_list_name);
        TextView textViewListPhone = (TextView) view.findViewById(R.id.text_view_list_phone);

        /* Set */
        textViewListName.setText(phoneBook.getName());
        textViewListPhone.setText(phoneBook.getPhone());
    }
}
