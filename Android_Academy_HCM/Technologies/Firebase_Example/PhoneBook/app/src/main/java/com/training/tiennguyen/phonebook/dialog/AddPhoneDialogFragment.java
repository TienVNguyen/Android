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
import com.training.tiennguyen.phonebook.R;
import com.training.tiennguyen.phonebook.model.PhoneBook;
import com.training.tiennguyen.phonebook.utils.Constants;

/**
 * @author Created by TienVNguyen on 09/03/2016.
 */
public class AddPhoneDialogFragment extends DialogFragment {
    private EditText mEditTextName;

    public static DialogFragment newInstance() {
        /* Arguments*/
        Bundle args = new Bundle();

        /* Fragment */
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.setArguments(args);
        return dialogFragment;
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

        /**
         * Call addPhoneList() when user taps "Done" keyboard action
         */
        mEditTextName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN) {
                    addPhoneList();
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

        return super.onCreateDialog(savedInstanceState);
    }

    /**
     * Add new phone list
     */
    public void addPhoneList() {
        // Get the reference to the root node in Firebase
        Firebase firebase = new Firebase(Constants.FIREBASE_URL);

        // Get the string that the user entered into the EditText and make an object with it
        // We'll use "Anonymous Owner" for the owner because we don't have user accounts yet
        String name = mEditTextName.getText().toString();
        String phone = "";
        PhoneBook phoneBook = new PhoneBook(name, phone);


        // Go to the LISTNAME child node of the root node. Then set value for it
        // This will create the node for you if it doesn't already exist.
        // Then using the setValue menu it will serialize the ShoppingList POJO
        firebase.child(Constants.FIREBASE_PROPERTY_PHONE_BOOK).setValue(phoneBook);
    }
}
