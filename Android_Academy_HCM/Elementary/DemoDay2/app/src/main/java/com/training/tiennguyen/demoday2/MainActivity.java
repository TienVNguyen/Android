/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.demoday2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.training.tiennguyen.adapter.StudentAdapter;
import com.training.tiennguyen.mockup.Test;
import com.training.tiennguyen.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Student> studentList = new ArrayList<>();
    private LinearLayout linearLayout;
    private ImageView ivMenu;
    private ImageView ivGone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Xoa support bar
        getSupportActionBar().hide();
        linearLayout = (LinearLayout) findViewById(R.id.MyNevigation);
        ivMenu = (ImageView) findViewById(R.id.menu);
        ivGone = (ImageView) findViewById(R.id.closeNevigate);
        ListView listView = (ListView) findViewById(R.id.listView);

        // Init student data
        studentList = Test.getStudentList();

        // Create student adapter
        StudentAdapter studentAdapter = new StudentAdapter(this, studentList);

        // set data into characters.
        listView.setAdapter(studentAdapter);

        final Animation slideToRight = AnimationUtils.loadAnimation(this, R.anim.slide_left_to_right);
        final Animation slideToLeft = AnimationUtils.loadAnimation(this, R.anim.slide_right_to_left);

        slideToRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linearLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        slideToLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linearLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout.getVisibility() == View.GONE) {
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout.startAnimation(slideToRight);
                }
            }
        });

        ivGone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout.getVisibility() == View.VISIBLE) {
                    //linearLayout.setVisibility(View.GONE);
                    linearLayout.startAnimation(slideToLeft);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AuthorActivity.class);
                intent.putExtra(AuthorActivity.AUTHOR_NAME, studentList.get(position).getName());
                intent.putExtra(AuthorActivity.AUTHOR_AVATAR, studentList.get(position).getAvatar());
                intent.putExtra(AuthorActivity.AUTHOR_WEBSITE, studentList.get(position).getWebsite());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        linearLayout.setVisibility(View.GONE);
        super.onResume();
    }
}
