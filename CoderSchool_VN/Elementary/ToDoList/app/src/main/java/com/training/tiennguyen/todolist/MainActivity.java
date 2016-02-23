/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * This contain the main activity. <br>
 * Such as: Showing list, Add and Edit buttons, etc.
 *
 * @author TienNguyen
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Starting point of main activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Remove the Action Bar
        getSupportActionBar().hide();
    }
}
