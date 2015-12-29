/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.examplestudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.training.tiennguyen.examplestudent.adapter.StudentAdapter;
import com.training.tiennguyen.examplestudent.constants.VariableConstants;
import com.training.tiennguyen.examplestudent.database.SQLiteConnection;
import com.training.tiennguyen.examplestudent.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * DetailsActivity
 *
 * @author TienNguyen
 */
public class ListActivity extends AppCompatActivity {
    private ListView studentsListView;
    private List<Student> studentList = new ArrayList<>();

    /**
     * onCreate function.
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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
        studentsListView = (ListView) findViewById(R.id.lvStudentsList);
    }

    /**
     * Initial function(s) inside of main activity
     */
    private void initFunction() {
        // Get the intent passed from MainActivity
        Intent intent = getIntent();
        if (intent != null && intent.getAction().equalsIgnoreCase(VariableConstants.SEARCH_FILTER)) {
            // This will show the list with filter record
            // TODO: This will be supported later
            viewListWithFilter(intent);
        } else {
            // This will show the full list
            // TODO: After viewListWithFilter function is implemented, this code will be reopened
            //viewFullList();
        }

        // TODO: After viewListWithFilter function is implemented, this code will be deleted.
        viewFullList();
    }

    private void viewFullList() {
        // Prepare connection and get all student
        SQLiteConnection sqLiteConnection = new SQLiteConnection(ListActivity.this);
        studentList = sqLiteConnection.selectAllStudent();
        if (studentList != null && !studentList.isEmpty()) {
            // If there are some record(s), it will be set into the listView
            StudentAdapter studentAdapter = new StudentAdapter(this, studentList);
            studentsListView.setAdapter(studentAdapter);

            // Set onclick function for each element of listView
            studentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Move to ListActivity
                    Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                    intent.putExtra(VariableConstants.STUDENT_NAME, studentList.get(position).getName());
                    intent.putExtra(VariableConstants.STUDENT_EMAIL, studentList.get(position).getEmail());
                    intent.putExtra(VariableConstants.STUDENT_GENDER, studentList.get(position).isGender());
                    intent.putExtra(VariableConstants.STUDENT_PHONE, studentList.get(position).getPhone());
                    intent.putExtra(VariableConstants.STUDENT_MAJOR, studentList.get(position).getMajor());
                    intent.putExtra(VariableConstants.STUDENT_AVATAR, studentList.get(position).getAvatar());
                    intent.setAction(VariableConstants.STUDENT_DETAIL);
                    startActivity(intent);
                }
            });
        } else {
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
                            // Back to MainActivity for register
                            Intent intent = new Intent(ListActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                    }
                }
            };

            // If there are no records. A suggestion message will appears
            AlertDialog.Builder builderDialog = new AlertDialog.Builder(ListActivity.this);
            builderDialog.setIcon(android.R.drawable.ic_dialog_alert);
            builderDialog.setTitle(VariableConstants.SEARCH_MESSAGE_EMPTY_TITLE);
            builderDialog.setPositiveButton(VariableConstants.REGISTER_MESSAGE_AGAIN, onClickListener);
            builderDialog.setMessage(VariableConstants.SEARCH_MESSAGE_EMPTY_REGISTER_REQUEST);
            builderDialog.setCancelable(false);
            builderDialog.show();
        }
    }

    /**
     * viewListWithFilter
     *
     * @param intent intent
     */
    private void viewListWithFilter(Intent intent) {
        // Set the registered record information
        Student student = new Student();
        student.setName(intent.getStringExtra(VariableConstants.STUDENT_NAME));
        student.setEmail(intent.getStringExtra(VariableConstants.STUDENT_EMAIL));

        // Show successful message
        Toast.makeText(ListActivity.this, VariableConstants.REGISTER_MESSAGE_SUCCESS, Toast.LENGTH_SHORT).show();
    }
}
