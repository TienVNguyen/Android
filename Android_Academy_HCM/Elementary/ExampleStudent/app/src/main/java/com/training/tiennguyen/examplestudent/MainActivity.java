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
    private EditText studentName;
    private EditText studentEmail;
    private EditText studentPhone;
    private EditText studentMajor;
    private EditText studentAvatar;
    private RadioButton studentMale;
    private RadioButton studentFemale;
    private RadioGroup studentGender;
    private Button buttonSubmit;
    private Button buttonCancel;

    /**
     * This action will be executed when activity is called
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initial view(s) inside of main activity
        initView();

        // Initial function(s) inside of main activity
        initFunction();
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

        // Init for action
        studentName = (EditText) findViewById(R.id.edtName);
        studentEmail = (EditText) findViewById(R.id.edtEmail);
        studentPhone = (EditText) findViewById(R.id.edtPhone);
        studentMajor = (EditText) findViewById(R.id.edtMajor);
        studentAvatar = (EditText) findViewById(R.id.edtAvatar);
        studentMale = (RadioButton) findViewById(R.id.rdbMale);
        studentFemale = (RadioButton) findViewById(R.id.rdbFemale);
        studentGender = (RadioGroup) findViewById(R.id.rdgGender);
        buttonSubmit = (Button) findViewById(R.id.btnSubmit);
        buttonCancel = (Button) findViewById(R.id.btnCancel);
    }

    /**
     * Initial function(s) inside of main activity
     */
    private void initFunction() {
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to ListActivity
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.setAction(VariableConstants.SEARCH_ALL);
                startActivity(intent);
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check views' value
                if (checkInput()) {
                    // Check existed record
                    if (!checkExisted()) {
                        // Insert a new record to database
                        if (addStudent() != -1) {
                            // If it inserts successfully, show message and move to DetailsActivity
                            successAdd();
                        } else {
                            // If there is error, the id will be -1, show error message
                            failedAdd(VariableConstants.REGISTER_MESSAGE_FAIL_TITLE, VariableConstants.REGISTER_MESSAGE_FAIL);
                        }
                    } else {
                        failedAdd(VariableConstants.EXISTED_MESSAGE_TITLE, VariableConstants.EXISTED_MESSAGE);
                    }
                }
            }
        });
    }

    /**
     * There is error issue with insert record
     *
     * @param messageTitle   title of message
     * @param messageContain contain of message
     */
    private void failedAdd(String messageTitle, String messageContain) {
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
        dialogMessage.append(VariableConstants.SPACE);
        dialogMessage.append(messageContain);

        // Build dialog and show it
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(MainActivity.this);
        builderDialog.setIcon(android.R.drawable.ic_dialog_alert);
        builderDialog.setTitle(messageTitle);
        builderDialog.setPositiveButton(VariableConstants.REGISTER_MESSAGE_AGAIN, onClickListener);
        builderDialog.setMessage(dialogMessage.toString());
        builderDialog.show();

        // Set focus to Name
        studentName.requestFocus();
    }

    /**
     * There is no error issue with insert record
     */
    private void successAdd() {
        // Prepare intent
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra(VariableConstants.STUDENT_NAME, studentName.getText().toString());
        intent.putExtra(VariableConstants.STUDENT_EMAIL, studentEmail.getText().toString());
        intent.setAction(VariableConstants.SEARCH_FILTER);

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
        Editable object = studentName.getText();
        if (object == null || object.toString().isEmpty()) {
            studentName.setError(VariableConstants.NAME_ERROR_EMPTY);
            studentName.requestFocus();
            return false;
        } else if (object.length() > 30) {
            studentName.setError(VariableConstants.NAME_ERROR_OVER_30_CHARACTERS);
            studentName.requestFocus();
            return false;
        }

        // Email must not be empty or wrong format
        object = studentEmail.getText();
        if (object == null || object.toString().isEmpty()) {
            studentEmail.setError(VariableConstants.EMAIL_ERROR_EMPTY);
            studentEmail.requestFocus();
            return false;
        } else if (object.length() > 30) {
            studentEmail.setError(VariableConstants.EMAIL_ERROR_OVER_30_CHARACTERS);
            studentEmail.requestFocus();
            return false;
        } else if (!EmailAPI.isEmailValid(object.toString())) {
            studentEmail.setError(VariableConstants.EMAIL_ERROR_WRONG_FORMAT);
            studentEmail.requestFocus();
            return false;
        }

        // Gender must not empty
        if (studentGender.getCheckedRadioButtonId() == -1
                || (!studentMale.isChecked() && !studentFemale.isChecked())) {
            studentMale.setError(VariableConstants.GENDER_ERROR_EMPTY);
            studentMale.setChecked(true);
            studentMale.requestFocus();
            return false;
        }

        // Phone must not empty or wrong format
        object = studentPhone.getText();
        if (object == null || object.toString().isEmpty()) {
            studentPhone.setError(VariableConstants.PHONE_ERROR_EMPTY);
            studentPhone.requestFocus();
            return false;
        } else if (object.toString().length() < 6 || object.length() > 13) {
            studentPhone.setError(VariableConstants.PHONE_ERROR_UNDER_6_OVER_30_CHARACTERS);
            studentPhone.requestFocus();
            return false;
        } else if (!PhoneNumberUtils.isGlobalPhoneNumber(object.toString())) {
            studentPhone.setError(VariableConstants.PHONE_ERROR_WRONG_FORMAT);
            studentPhone.requestFocus();
            return false;
        }

        // Major must not empty
        object = studentMajor.getText();
        if (object == null || object.toString().isEmpty()) {
            studentMajor.setError(VariableConstants.MAJOR_ERROR_EMPTY);
            studentMajor.requestFocus();
            return false;
        } else if (object.length() > 20) {
            studentMajor.setError(VariableConstants.MAJOR_ERROR_OVER_20_CHARACTERS);
            studentMajor.requestFocus();
            return false;
        }

        // Avatar must not empty or wrong format
        object = studentAvatar.getText();
        if (object == null || object.toString().isEmpty()) {
            studentAvatar.setError(VariableConstants.AVATAR_ERROR_EMPTY);
            studentAvatar.requestFocus();
            return false;
        } else if (object.length() > 200) {
            studentAvatar.setError(VariableConstants.AVATAR_ERROR_OVER_200_CHARACTERS);
            studentAvatar.requestFocus();
            return false;
        } else if (!URLUtil.isValidUrl(object.toString())) {
            studentAvatar.setError(VariableConstants.AVATAR_ERROR_WRONG_FORMAT);
            studentAvatar.requestFocus();
            return false;
        } else if (!ImageAPI.isImageURLValid(object.toString())) {
            studentAvatar.setError(VariableConstants.AVATAR_ERROR_WRONG_FORMAT);
            studentAvatar.requestFocus();
            return false;
        }

        // There are no invalid
        return true;
    }


    /**
     * This function will check if that student's existed or already registered
     *
     * @return existedFlag. If there is a record, it will return false.
     */
    private boolean checkExisted() {
        // Prepare object to check
        Student student = new Student();
        student.setName(studentName.getText().toString());
        student.setEmail(studentEmail.getText().toString());

        // Check function
        SQLiteConnection sqLiteConnection = new SQLiteConnection(MainActivity.this);
        boolean existedFlag = sqLiteConnection.checkStudentExists(student);
        sqLiteConnection.close();

        // Return flag
        return existedFlag;
    }

    /**
     * This function will insert a new record into database
     *
     * @return long result id of new student. If there is error, it will return -1
     */
    private long addStudent() {
        // Prepare object to insert
        Student student = new Student();
        student.setName(studentName.getText().toString());
        student.setEmail(studentEmail.getText().toString());
        student.setGender(studentMale.isChecked());
        student.setPhone(studentPhone.getText().toString());
        student.setMajor(studentMajor.getText().toString());
        student.setAvatar(studentAvatar.getText().toString());

        // Insert function
        SQLiteConnection sqLiteConnection = new SQLiteConnection(MainActivity.this);
        long id = sqLiteConnection.addStudent(student);
        sqLiteConnection.close();

        // Return id
        return id;
    }
}
