/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.training.tiennguyen.constants.VariableConstants;
import com.training.tiennguyen.database.SQLiteConnection;
import com.training.tiennguyen.model.ToDoElement;

/**
 * This contain the modify activity. <br>
 * Such as: Add and Edit functions.
 *
 * @author TienNguyen
 */
public class ModifyActivity extends AppCompatActivity {
    private int flags;
    private TextView appLogoObject;
    private ImageView removeIconObject;
    private ImageView cancelIconObject;
    private ImageView saveIconObject;
    private ImageView addIconObject;
    private EditText edtTitleObject;
    private EditText edtDetailsObject;
    private RadioButton rdbHighObject;
    private RadioButton rdbMediumObject;
    private RadioButton rdbLowObject;

    /**
     * Starting point of main activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        // Initial view(s) inside of main activity
        initView();

        // Initial function(s) inside of main activity
        initFunction();
    }

    /**
     * Initial function(s) inside of main activity
     */
    private void initFunction() {

        // Set action when clicking Cancel
        cancelIconObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Set action when clicking Save
        saveIconObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check input
                if (edtTitleObject.length() == 0) {
                    return;
                } else if (edtDetailsObject.length() == 0) {
                    return;
                }

                // Set object toDoElement for modifying database
                ToDoElement toDoElement = new ToDoElement();
                toDoElement.setTitle(edtTitleObject.getText().toString());
                toDoElement.setDetails(edtDetailsObject.getText().toString());
                if (rdbHighObject.isChecked()) {
                    toDoElement.setPriority(0);
                } else if (rdbMediumObject.isChecked()) {
                    toDoElement.setPriority(1);
                } else {
                    toDoElement.setPriority(2);
                }

                SQLiteConnection sqLiteConnection = new SQLiteConnection(ModifyActivity.this);
                if (flags == 1) {
                    // Add case
                    long resultCount = sqLiteConnection.insertElement(toDoElement);
                    sqLiteConnection.close();
                } else {
                    // Edit case
                    int resultCount = sqLiteConnection.updateElement(toDoElement);
                    sqLiteConnection.close();
                }

                finish();
            }
        });
    }

    /**
     * Initial view(s) inside of main activity
     */
    private void initView() {
        // Hide Support Action Bar if it is possible
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Init Internal Object for action
        appLogoObject = (TextView) findViewById(R.id.appLogo);
        removeIconObject = (ImageView) findViewById(R.id.removeIcon);
        cancelIconObject = (ImageView) findViewById(R.id.cancelIcon);
        saveIconObject = (ImageView) findViewById(R.id.saveIcon);
        addIconObject = (ImageView) findViewById(R.id.addIcon);
        edtTitleObject = (EditText) findViewById(R.id.edtTitle);
        edtDetailsObject = (EditText) findViewById(R.id.edtDetails);
        rdbHighObject = (RadioButton) findViewById(R.id.rdbHigh);
        rdbMediumObject = (RadioButton) findViewById(R.id.rdbMedium);
        rdbLowObject = (RadioButton) findViewById(R.id.rdbLow);

        // Get intent to verify the type of actions
        Intent intent = getIntent();
        flags = intent.getFlags();
        if (flags == 1) {
            // Add case
            edtTitleObject.setText("");
            edtDetailsObject.setText("");
            rdbHighObject.setChecked(true);
            rdbMediumObject.setChecked(false);
            rdbLowObject.setChecked(false);
            appLogoObject.setText(R.string.add_element);
        } else {
            // Edit case
            edtTitleObject.setEnabled(false);
            edtTitleObject.setText(intent.getStringExtra(VariableConstants.TITLE));
            edtDetailsObject.setText(intent.getStringExtra(VariableConstants.DETAILS));
            switch (intent.getIntExtra(VariableConstants.PRIORITY, 0)) {
                case 1:
                    // Medium
                    rdbHighObject.setChecked(false);
                    rdbMediumObject.setChecked(true);
                    rdbLowObject.setChecked(false);
                    break;
                case 2:
                    // Low
                    rdbHighObject.setChecked(false);
                    rdbMediumObject.setChecked(false);
                    rdbLowObject.setChecked(true);
                    break;
                default:
                    // High
                    rdbHighObject.setChecked(true);
                    rdbMediumObject.setChecked(false);
                    rdbLowObject.setChecked(false);
                    break;
            }
            appLogoObject.setText(R.string.edit_element);
        }

        removeIconObject.setVisibility(View.INVISIBLE);
        cancelIconObject.setVisibility(View.VISIBLE);
        saveIconObject.setVisibility(View.VISIBLE);
        addIconObject.setVisibility(View.INVISIBLE);
    }
}
