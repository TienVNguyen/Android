/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.examplestudent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.training.tiennguyen.examplestudent.constants.VariableConstants;
import com.training.tiennguyen.examplestudent.database.SQLiteConnection;
import com.training.tiennguyen.examplestudent.model.Student;
import com.training.tiennguyen.examplestudent.supportAPI.EmailAPI;
import com.training.tiennguyen.examplestudent.supportAPI.ImageAPI;

/**
 * MainActivity
 *
 * @author TienNguyen
 */
public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPhone;
    private EditText edtMajor;
    private EditText edtAvatar;
    private RadioButton rdbMale;
    private RadioButton rdbFemale;
    private RadioGroup rdgGender;
    private Button btnSubmit;
    private Button btnCancel;

    /**
     * This action will be executed when activity is called
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide Support Action Bar if it is possible
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Initial view(s) inside of main activity
        initView();

        // Initial function(s) inside of main activity
        initFunction();
    }

    /**
     * Initial view(s) inside of main activity
     */
    private void initView() {
        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtMajor = (EditText) findViewById(R.id.edtMajor);
        edtAvatar = (EditText) findViewById(R.id.edtAvatar);
        rdbMale = (RadioButton) findViewById(R.id.rdbMale);
        rdbFemale = (RadioButton) findViewById(R.id.rdbFemale);
        rdgGender = (RadioGroup) findViewById(R.id.rdgGender);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnCancel = (Button) findViewById(R.id.btnCancel);
    }

    /**
     * Initial function(s) inside of main activity
     */
    private void initFunction() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to DetailsActivity
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check views' value
                if (checkInput()) {
                    // Insert a new record to database
                    if (addStudent() == -1) {
                        // If it inserts successfully, show message and move to DetailsActivity
                        successAdd();
                    } else {
                        // If there is error, the id will be -1, show error message
                        failedAdd();
                    }
                }
            }
        });
    }

    /**
     * There is error issue with insert record
     */
    private void failedAdd() {
        // Create onClickListener
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            /**
             * This method will be invoked when a button in the dialog is clicked.
             *
             * @param dialog The dialog that received the click.
             * @param which  The button that was clicked (e.g.
             *               {@link DialogInterface#BUTTON1}) or the position
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        break;
                }
            }
        };

        // Build message content
        StringBuilder dialogMessage = new StringBuilder();
        dialogMessage.append(VariableConstants.REGISTER_MESSAGE);
        dialogMessage.append(edtName.getText().toString());
        dialogMessage.append(VariableConstants.REGISTER_MESSAGE_SUCCESS);

        // Build dialog and show it
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(MainActivity.this);
        builderDialog.setIcon(android.R.drawable.ic_dialog_alert);
        builderDialog.setTitle(VariableConstants.REGISTER_MESSAGE_FAIL_TITLE);
        builderDialog.setPositiveButton(VariableConstants.REGISTER_MESSAGE_AGAIN, onClickListener);
        builderDialog.setMessage(dialogMessage.toString());
        builderDialog.setInverseBackgroundForced(false);
        builderDialog.show();

        // Set focus to Name
        edtName.requestFocus();
    }

    /**
     * There is no error issue with insert record
     */
    private void successAdd() {
        // Prepare intent
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(VariableConstants.STUDENT_NAME, edtName.getText().toString());
        intent.putExtra(VariableConstants.STUDENT_EMAIL, edtEmail.getText().toString());

        // Start activity
        startActivity(intent);
    }

    /**
     * This function will check the value to see it suitable or not
     *
     * @return boolean result condition check
     */
    private boolean checkInput() {
        // Name must not empty
        Editable object = edtName.getText();
        if (object == null || object.toString().isEmpty()) {
            edtName.setError(VariableConstants.NAME_ERROR_EMPTY);
            edtName.requestFocus();
            return false;
        } else if (object.length() > 30) {
            edtName.setError(VariableConstants.NAME_ERROR_OVER_30_CHARACTERS);
            edtName.requestFocus();
            return false;
        }

        // Email must not be empty or wrong format
        object = edtEmail.getText();
        if (object == null || object.toString().isEmpty()) {
            edtEmail.setError(VariableConstants.EMAIL_ERROR_EMPTY);
            edtEmail.requestFocus();
            return false;
        } else if (object.length() > 30) {
            edtName.setError(VariableConstants.EMAIL_ERROR_OVER_30_CHARACTERS);
            edtName.requestFocus();
            return false;
        } else if (!EmailAPI.isEmailValid(object.toString())) {
            edtEmail.setError(VariableConstants.EMAIL_ERROR_WRONG_FORMAT);
            edtEmail.requestFocus();
            return false;
        }

        // Gender must not empty
        if (rdgGender.getCheckedRadioButtonId() == -1
                || (!rdbMale.isChecked() && !rdbFemale.isChecked())) {
            rdbMale.setError(VariableConstants.GENDER_ERROR_EMPTY);
            rdbMale.setChecked(true);
            rdbMale.requestFocus();
            return false;
        }

        // Phone must not empty or wrong format
        object = edtPhone.getText();
        if (object == null || object.toString().isEmpty()) {
            edtPhone.setError(VariableConstants.PHONE_ERROR_EMPTY);
            edtPhone.requestFocus();
            return false;
        } else if (object.toString().length() < 6 || object.length() > 13) {
            edtName.setError(VariableConstants.PHONE_ERROR_UNDER_6_OVER_30_CHARACTERS);
            edtName.requestFocus();
            return false;
        } else if (!PhoneNumberUtils.isGlobalPhoneNumber(object.toString())) {
            edtPhone.setError(VariableConstants.PHONE_ERROR_WRONG_FORMAT);
            edtPhone.requestFocus();
            return false;
        }

        // Major must not empty
        object = edtMajor.getText();
        if (object == null || object.toString().isEmpty()) {
            edtMajor.setError(VariableConstants.MAJOR_ERROR_EMPTY);
            edtMajor.requestFocus();
            return false;
        } else if (object.length() > 20) {
            edtName.setError(VariableConstants.MAJOR_ERROR_OVER_20_CHARACTERS);
            edtName.requestFocus();
            return false;
        }

        // Avatar must not empty or wrong format
        object = edtAvatar.getText();
        if (object == null || object.toString().isEmpty()) {
            edtAvatar.setError(VariableConstants.AVATAR_ERROR_EMPTY);
            edtAvatar.requestFocus();
            return false;
        } else if (object.length() > 200) {
            edtName.setError(VariableConstants.AVATAR_ERROR_OVER_200_CHARACTERS);
            edtName.requestFocus();
            return false;
        } else if (!URLUtil.isValidUrl(object.toString())) {
            edtAvatar.setError(VariableConstants.AVATAR_ERROR_WRONG_FORMAT);
            edtAvatar.requestFocus();
            return false;
        } else if (!ImageAPI.isImageURLValid(object.toString())) {
            edtAvatar.setError(VariableConstants.AVATAR_ERROR_WRONG_FORMAT);
            edtAvatar.requestFocus();
            return false;
        }

        // There are no invalid
        return true;
    }


    /**
     * This function will insert a new record into database
     *
     * @return long result id of new student. If there is error, it will return -1
     */
    private long addStudent() {
        // Prepare object to insert
        Student student = new Student();
        student.setName(edtName.getText().toString());
        student.setEmail(edtEmail.getText().toString());
        student.setPhone(edtPhone.getText().toString());
        student.setMajor(edtMajor.getText().toString());
        student.setAvatar(edtAvatar.getText().toString());

        // Insert function
        SQLiteConnection sqLiteConnection = new SQLiteConnection(MainActivity.this);
        return sqLiteConnection.addStudent(student);
    }
}
