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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.training.tiennguyen.adapter.ToDoListAdapter;
import com.training.tiennguyen.constants.VariableConstants;
import com.training.tiennguyen.database.SQLiteConnection;
import com.training.tiennguyen.model.ToDoElement;

import java.util.ArrayList;
import java.util.List;

/**
 * This contain the main activity. <br>
 * Such as: Showing list, Add and Edit buttons, etc.
 *
 * @author TienNguyen
 */
public class MainActivity extends AppCompatActivity {
    private List<ToDoElement> toDoElementList = new ArrayList<>();
    private ListView toDoListObject;
    private TextView noItemsObject;
    private TextView appLogoObject;
    private ImageView removeIconObject;
    private ImageView cancelIconObject;
    private ImageView saveIconObject;
    private ImageView closeIconObject;
    private LinearLayout authorDetailsObject;

    /**
     * Starting point of main activity.
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

        // Init Internal Object for action
        toDoListObject = (ListView) findViewById(R.id.toDoList);
        noItemsObject = (TextView) findViewById(R.id.noItems);
        appLogoObject = (TextView) findViewById(R.id.appLogo);
        removeIconObject = (ImageView) findViewById(R.id.removeIcon);
        cancelIconObject = (ImageView) findViewById(R.id.cancelIcon);
        saveIconObject = (ImageView) findViewById(R.id.saveIcon);
        closeIconObject = (ImageView) findViewById(R.id.closeIcon);
        authorDetailsObject = (LinearLayout) findViewById(R.id.authorDetails);

        // Set logo text
        TextView appLogoObject = (TextView) findViewById(R.id.appLogo);
        appLogoObject.setText(R.string.to_do_list);
    }

    /**
     * Initial function(s) inside of main activity
     */
    private void initFunction() {
        authorDetailsInit();

        if (checkListExisted() > 0) {
            // If it's true, list contains some elements
            // Only toDoList & removeIcon are visible.
            toDoListObject.setVisibility(View.VISIBLE);
            removeIconObject.setVisibility(View.VISIBLE);

            noItemsObject.setVisibility(View.INVISIBLE);
            cancelIconObject.setVisibility(View.INVISIBLE);
            saveIconObject.setVisibility(View.INVISIBLE);

            // Init list
            toDoElementList = new ArrayList<>();
        } else {
            // If it's false, it's empty list.
            // Only noItems is visible.
            noItemsObject.setVisibility(View.VISIBLE);

            toDoListObject.setVisibility(View.INVISIBLE);
            removeIconObject.setVisibility(View.INVISIBLE);
            cancelIconObject.setVisibility(View.INVISIBLE);
            saveIconObject.setVisibility(View.INVISIBLE);

            // Get list's records from database
            SQLiteConnection sqLiteConnection = new SQLiteConnection(MainActivity.this);
            toDoElementList = sqLiteConnection.selectAllToDoObjects();
            sqLiteConnection.close();

            // Create adapter
            ToDoListAdapter toDoListAdapter = new ToDoListAdapter(this, toDoElementList);

            // Set adapter to list
            toDoListObject.setAdapter(toDoListAdapter);

            // If user chooses any elements, move to edit page
            toDoListObject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
                    intent.putExtra(VariableConstants.TITLE, toDoElementList.get(position).getTitle());
                    intent.putExtra(VariableConstants.DETAILS, toDoElementList.get(position).getDetails());
                    intent.putExtra(VariableConstants.PRIORITY, toDoElementList.get(position).getPriority());
                }
            });
        }
    }

    /**
     * Set animation for author details
     */
    private void authorDetailsInit() {

        final Animation slideToRight = AnimationUtils.loadAnimation(this, R.anim.slide_left_to_right);
        slideToRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                authorDetailsObject.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation slideToLeft = AnimationUtils.loadAnimation(this, R.anim.slide_right_to_left);
        slideToLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                authorDetailsObject.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        appLogoObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authorDetailsObject.getVisibility() == View.GONE) {
                    authorDetailsObject.setVisibility(View.VISIBLE);
                    authorDetailsObject.startAnimation(slideToRight);
                }
            }
        });

        closeIconObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authorDetailsObject.getVisibility() == View.VISIBLE) {
                    //linearLayout.setVisibility(View.GONE);
                    authorDetailsObject.startAnimation(slideToLeft);
                }
            }
        });
    }

    /**
     * Function to check there is any record inside of list.
     *
     * @return resultCount. If there is no record, it will return -1
     */
    private int checkListExisted() {
        // Check function
        SQLiteConnection sqLiteConnection = new SQLiteConnection(MainActivity.this);
        int resultCount = sqLiteConnection.selectCountToDoObjects();
        sqLiteConnection.close();

        // Return count
        return resultCount;
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        // Hide author details layout
        authorDetailsObject.setVisibility(View.GONE);

        super.onResume();
    }
}
