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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.training.tiennguyen.instagram_photo_viewer.R;
import com.training.tiennguyen.instagram_photo_viewer.adapter.CommentAdapter;
import com.training.tiennguyen.instagram_photo_viewer.model.CommentObject;
import com.training.tiennguyen.instagram_photo_viewer.utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * CommentsActivity
 *
 * @author Created by TienVNguyen on 14/03/2016.
 */
public class CommentsActivity extends AppCompatActivity {
    @Bind(R.id.lv_comments)
    protected ListView listCommentView;
    private ArrayList<CommentObject> commentObjects;
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");

        ButterKnife.bind(this);

        // Initialize
        commentObjects = new ArrayList<>();

        // Create adapter linking to the source.
        commentAdapter = new CommentAdapter(this, commentObjects);

        // Find the listView from input
        //listView = (ListView) findViewById(R.id.lv_comments); // TODO: This was replace by Butterknife
        listCommentView.setAdapter(commentAdapter);

        // Steam data
        fetchPopularComments(id);
    }

    private void fetchPopularComments(String id) {
        String url = "https://api.instagram.com/v1/media/" + id + "?client_id=" + Constants.clientId;

        // Create the network client
        AsyncHttpClient httpClient = new AsyncHttpClient();

        // Trigger the GET request
        httpClient.get(url, null, new JsonHttpResponseHandler() {
            /**
             * Returns when request succeeds
             *
             * @param statusCode http response status line
             * @param headers    response headers if any
             * @param response   parsed response if any
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Iterate each photos into item
                JSONArray photoJsonArray;
                try {
                    photoJsonArray = response.getJSONArray("data");

                    // Iterate array
                    for (int i = 0; i < photoJsonArray.length(); i++) {
                        // Get JSON object at each positions
                        JSONObject jsonObject = photoJsonArray.getJSONObject(i);

                        // Decode attributes into data model
                        CommentObject commentObject = new CommentObject();


                        // Add new object
                        commentObjects.add(commentObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // callBack
                commentAdapter.notifyDataSetChanged();
            }

            /**
             * Returns when request failed
             *
             * @param statusCode http response status line
             * @param headers    response headers if any
             * @param throwable  exception if any
             */
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
