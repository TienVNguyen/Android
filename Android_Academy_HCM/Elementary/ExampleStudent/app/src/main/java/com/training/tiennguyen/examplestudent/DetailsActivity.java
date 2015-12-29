/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.examplestudent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.training.tiennguyen.examplestudent.constants.VariableConstants;

/**
 * DetailsActivity
 *
 * @author TienNguyen
 */
public class DetailsActivity extends AppCompatActivity {
    private ImageView studentAvatar;
    private TextView studentName;
    private TextView studentEmail;
    private TextView studentGender;
    private TextView studentPhone;
    private TextView studentMajor;

    /**
     * onCreate function.
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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
        studentAvatar = (ImageView) findViewById(R.id.imgAvatar);
        studentName = (TextView) findViewById(R.id.txtName);
        studentEmail = (TextView) findViewById(R.id.txtEmail);
        studentGender = (TextView) findViewById(R.id.txtGender);
        studentPhone = (TextView) findViewById(R.id.txtPhone);
        studentMajor = (TextView) findViewById(R.id.txtMajor);
    }

    /**
     * Initial function(s) inside of main activity
     */
    private void initFunction() {
        // Get the intent passed from MainActivity
        Intent intent = getIntent();
        if (intent != null && intent.getAction().equalsIgnoreCase(VariableConstants.STUDENT_DETAIL)) {
            studentName.setText(intent.getStringExtra(VariableConstants.STUDENT_NAME));
            studentEmail.setText(intent.getStringExtra(VariableConstants.STUDENT_EMAIL));
            studentGender.setText(intent.getBooleanExtra(VariableConstants.STUDENT_GENDER, true) ? "Nam" : "Nu");
            studentPhone.setText(intent.getStringExtra(VariableConstants.STUDENT_PHONE));
            studentMajor.setText(intent.getStringExtra(VariableConstants.STUDENT_MAJOR));

            // Load image through Picasso API
            Picasso.with(this)
                    .load(intent.getStringExtra(VariableConstants.STUDENT_AVATAR))
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            // If the image is loaded successfully
                            studentAvatar.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {
                            // If the image is failed to load, the default image will appear
                            studentAvatar.setImageResource(R.drawable.android_default_avatar);
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                        }
                    });
        }
    }
}
