/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.examplestudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.training.tiennguyen.examplestudent.supportAPI.EmailAPI;

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
                    if (addStudent()) {
                        // Move to DetailsActivity
                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
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
            edtName.setError("Tên không được trống!");
            edtName.setText("");
            edtName.requestFocus();
            return false;
        }

        // Email must not be empty or wrong format
        object = edtEmail.getText();
        if (object == null || object.toString().isEmpty()) {
            edtEmail.setError("Email không được trống!");
            edtEmail.setText("");
            edtEmail.requestFocus();
            return false;
        } else if (!EmailAPI.isEmailValid(object.toString())) {
            edtEmail.setError("Email sai định dạng!");
            edtEmail.setText("");
            edtEmail.requestFocus();
            return false;
        }

        // Gender must not empty
        if (rdgGender.getCheckedRadioButtonId() == -1 || (!rdbMale.isChecked() && !rdbFemale.isChecked())) {
            rdbMale.setError("Chưa lựa chọn giới tính!");
            rdbMale.setChecked(true);
            rdbMale.requestFocus();
            return false;
        }

        // Phone must not empty or wrong format
        object = edtPhone.getText();
        if (object == null || object.toString().isEmpty()) {
            edtPhone.setError("Điện thoại không được trống!");
            edtPhone.setText("");
            edtPhone.requestFocus();
            return false;
        } else if (!PhoneNumberUtils.isGlobalPhoneNumber(object.toString())) {
            edtPhone.setError("Điện thoại sai định dạng");
            edtPhone.setText("");
            edtPhone.requestFocus();
            return false;
        }

        // Major must not empty
        object = edtMajor.getText();
        if (object == null || object.toString().isEmpty()) {
            edtMajor.setError("Ngành học không được trống!");
            edtMajor.setText("");
            edtMajor.requestFocus();
            return false;
        }

        // Avatar must not empty or wrong format
        object = edtAvatar.getText();
        if (object == null || object.toString().isEmpty()) {
            edtAvatar.setError("Hình ảnh không được trống!");
            edtAvatar.setText("");
            edtAvatar.requestFocus();
            return false;
        } else if (!URLUtil.isValidUrl(object.toString())) {
            edtAvatar.setError("URI sai định dạng hay không hợp lệ!");
            edtAvatar.setText("");
            edtAvatar.requestFocus();
            return false;
        }

        // There are no invalid
        return true;
    }


    /**
     * This function will insert a new record into database
     *
     * @return boolean result condition check
     */
    private boolean addStudent() {
        return true;
    }
}
