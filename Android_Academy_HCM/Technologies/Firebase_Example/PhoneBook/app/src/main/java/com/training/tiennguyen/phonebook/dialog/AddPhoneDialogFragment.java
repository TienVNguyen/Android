/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.phonebook.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.training.tiennguyen.phonebook.R;
import com.training.tiennguyen.phonebook.model.PhoneBook;
import com.training.tiennguyen.phonebook.utils.Constants;

import java.util.HashMap;

/**
 * @author Created by TienVNguyen on 09/03/2016.
 */
public class AddPhoneDialogFragment extends DialogFragment {
    private EditText mEditTextName;
    private EditText mEditTextPhone;

    public static DialogFragment newInstance() {
        /* Arguments*/
        Bundle args = new Bundle();

        /* Fragment */
        AddPhoneDialogFragment addPhoneDialogFragment = new AddPhoneDialogFragment();
        addPhoneDialogFragment.setArguments(args);
        return addPhoneDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /**
         *  Use the Builder class for convenient dialog construction
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), 1);

        /**
         *  Get the layout inflater
         */
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View rootView = layoutInflater.inflate(R.layout.dialog_add_phone_list, null);
        mEditTextName = (EditText) rootView.findViewById(R.id.edit_text_name);
        mEditTextPhone = (EditText) rootView.findViewById(R.id.edit_text_phone);

        /**
         * Call addPhoneList() when user taps "Done" keyboard action
         */
        mEditTextName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (!mEditTextName.getText().toString().isEmpty() && !mEditTextPhone.getText().toString().isEmpty()) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN) {
                        addPhoneList();
                    }
                }
                return true;
            }
        });

        mEditTextPhone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (!mEditTextName.getText().toString().isEmpty() && !mEditTextPhone.getText().toString().isEmpty()) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN) {
                        addPhoneList();
                    }
                }
                return true;
            }
        });

        /**
         * Inflate and set the layout for the dialog
         * Pass null as the parent view because its going in the dialog layout
         */
        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton(R.string.positive_button_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addPhoneList();
                    }
                });

        return builder.create();
    }

    /**
     * Add new phone list
     */
    public void addPhoneList() {
        // Get the string that the user entered into the EditText and make an object with it
        String name = mEditTextName.getText().toString();
        String phone = mEditTextPhone.getText().toString();

        // If EditText input is not empty
        if (!name.isEmpty() && !phone.isEmpty()) {

            // Get the reference to the root node in Firebase
            Firebase firebase = new Firebase(Constants.FIREBASE_URL_ACTIVE_LISTS);
            Firebase newListRef = firebase.push();

            /* Save listsRef.push() to maintain same random Id */
            final String listId = newListRef.getKey();

            /**
             * Set raw version of date to the ServerValue.TIMESTAMP value and save into timestampCreatedMap
             */
            HashMap<String, Object> timestampCreated = new HashMap<>();
            timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

            /* Build the phone list */
            PhoneBook newShoppingList = new PhoneBook(name, phone, timestampCreated);

            /* Add the phone list */
            newListRef.setValue(newShoppingList);

            /* Close the dialog fragment */
            AddPhoneDialogFragment.this.getDialog().cancel();
        }
    }
}
