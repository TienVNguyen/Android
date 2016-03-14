/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.training.tiennguyen.instagram_photo_viewer.R;
import com.training.tiennguyen.instagram_photo_viewer.adapter.CommentAdapter;
import com.training.tiennguyen.instagram_photo_viewer.model.CommentObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * CommentsActivity
 *
 * @author Created by TienVNguyen on 14/03/2016.
 */
public class CommentsActivity extends AppCompatActivity {
    @Bind(R.id.lv_comments)
    protected ListView listCommentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);

        /* Get data */
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        ArrayList<String> username = intent.getStringArrayListExtra("COMMENTS_USERNAME");
        ArrayList<String> avatar = intent.getStringArrayListExtra("COMMENTS_AVATAR");
        ArrayList<String> text = intent.getStringArrayListExtra("COMMENTS_TEXT");

        /* Populate data to ListView */
        ArrayList<CommentObject> commentObjects = new ArrayList<>();
        for (int i = 0; i < username.size(); i++) {
            CommentObject commentObject = new CommentObject();
            commentObject.setUser(username.get(i));
            commentObject.setAvatar(avatar.get(i));
            commentObject.setText(text.get(i));
            commentObjects.add(commentObject);
        }
        CommentAdapter commentAdapter = new CommentAdapter(this, commentObjects);
        listCommentView.setAdapter(commentAdapter);
    }
}
