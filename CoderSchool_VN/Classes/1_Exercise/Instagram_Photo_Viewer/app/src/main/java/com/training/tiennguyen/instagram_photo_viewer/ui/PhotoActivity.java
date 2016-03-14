/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.training.tiennguyen.instagram_photo_viewer.R;
import com.training.tiennguyen.instagram_photo_viewer.adapter.PhotoAdapter;
import com.training.tiennguyen.instagram_photo_viewer.model.CommentObject;
import com.training.tiennguyen.instagram_photo_viewer.model.PhotoObject;
import com.training.tiennguyen.instagram_photo_viewer.utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class PhotoActivity extends AppCompatActivity {
    @Bind(R.id.swipe_container)
    protected SwipeRefreshLayout swipeContainer;
    @Bind(R.id.lv_photos)
    protected ListView lvPhotos;
    private ArrayList<PhotoObject> photoObjects;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        photoObjects = new ArrayList<>();
        photoAdapter = new PhotoAdapter(this, photoObjects);
        lvPhotos.setAdapter(photoAdapter);

        if (isNetworkConnected()) {
            // Steam data
            fetchPopularPhotos();
        }


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTimelineAsync();
            }
        });


        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    /**
     * isNetworkConnected
     */
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isAvailable() && netInfo.isConnectedOrConnecting() && !netInfo.isFailover()) {
            return true;
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Network Failures")
                    .setMessage("Your connection failed to load! Please close the app and connect again!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do noting
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return false;
        }
    }

    /**
     * Send out requirement
     */
    private void fetchPopularPhotos() {
        String url = "https://api.instagram.com/v1/media/popular?client_id=" + Constants.clientId;

        AsyncHttpClient httpClient = new AsyncHttpClient();

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
                JSONArray photoJsonArray;
                try {
                    photoJsonArray = response.getJSONArray("data");

                    for (int i = 0; i < photoJsonArray.length(); i++) {
                        JSONObject jsonObject = photoJsonArray.getJSONObject(i);

                        // Decode attributes into data model
                        PhotoObject photoObject = new PhotoObject();
                        photoObject.setName(jsonObject.getJSONObject("user").getString("username"));
                        JSONObject caption = jsonObject.optJSONObject("caption");
                        if (caption != null) {
                            photoObject.setCaption(caption.optString("text"));
                        }
                        JSONObject comments = jsonObject.optJSONObject("comments");
                        if (comments != null) {
                            JSONArray jsonArray = comments.getJSONArray("data");
                            if (jsonArray != null) {
                                photoObject.setCommentsCount(jsonArray.length());
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject jsonCommentObject;
                                    CommentObject commentObject = new CommentObject();
                                    try {
                                        jsonCommentObject = jsonArray.getJSONObject(j);
                                        if (j == 0)
                                            photoObject.setComment1(jsonCommentObject.getJSONObject("from")
                                                    .optString("username") + " " + jsonCommentObject.optString("text"));

                                        if (j == 1)
                                            photoObject.setComment2(jsonCommentObject.getJSONObject("from")
                                                    .optString("username") + " " + jsonCommentObject.optString("text"));

                                        commentObject.setUser(jsonCommentObject.getJSONObject("from").optString("username"));
                                        commentObject.setAvatar(jsonCommentObject.getJSONObject("from").optString("profile_picture"));
                                        commentObject.setText(jsonCommentObject.optString("text"));
                                        photoObject.getComments().add(commentObject);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        break;
                                    }
                                }
                            }
                        }
                        String type = jsonObject.optString("type");
                        if (type != null && !type.isEmpty()) {
                            photoObject.setType(type);
                            photoObject.setImageUrl(jsonObject.getJSONObject("images")
                                    .optJSONObject("standard_resolution").optString("url"));
                            photoObject.setImageHeight(jsonObject.getJSONObject("images")
                                    .optJSONObject("standard_resolution").optInt("height"));

                            if (type.equalsIgnoreCase("video")) {
                                photoObject.setVideoUrl(jsonObject.getJSONObject("videos")
                                        .optJSONObject("standard_resolution").optString("url"));
                                photoObject.setVideoHeight(jsonObject.getJSONObject("videos")
                                        .optJSONObject("standard_resolution").optInt("height"));
                            }
                        }
                        JSONObject likes = jsonObject.optJSONObject("likes");
                        if (likes != null) {
                            photoObject.setLikeCount(likes.optInt("count"));
                        }
                        JSONObject user = jsonObject.optJSONObject("user");
                        if (user != null) {
                            photoObject.setAvatar(user.optString("profile_picture"));
                        }
                        photoObject.setId(jsonObject.optString("id"));

                        // Add new object
                        photoObjects.add(photoObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // callBack
                photoAdapter.notifyDataSetChanged();
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
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Network Failures")
                        .setMessage("Your connection failed to load!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    /**
     * Refresh items
     */
    public void fetchTimelineAsync() {
        photoAdapter.clear();

        if (isNetworkConnected()) {
            // ...the data has come back, add new items to your adapter...
            fetchPopularPhotos();
        }

        swipeContainer.setRefreshing(false);
    }
}
