/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
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

        // Initialize
        photoObjects = new ArrayList<>();

        // Create adapter linking to the source.
        photoAdapter = new PhotoAdapter(this, photoObjects);

        // Find the listView from input
        //lvPhotos = (ListView) findViewById(R.id.lv_photos); // TODO: This was replace by Butterknife
        lvPhotos.setAdapter(photoAdapter);

        // Steam data
        fetchPopularPhotos();

        // Lookup the swipe container view
        //swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_container); // TODO: This was replace by Butterknife

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync();
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    /**
     * Send out requirement
     */
    private void fetchPopularPhotos() {
        String url = "https://api.instagram.com/v1/media/popular?client_id=" + Constants.clientId;

        // Create the network client
        AsyncHttpClient httpClient = new AsyncHttpClient();

        // Trigger the GET request
        httpClient.get(url, null, new JsonHttpResponseHandler() {

            // Work

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
                                for (int j = 0; j < 2; j++) {
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
                            JSONObject standardResolution = jsonObject.getJSONObject(type + "s")
                                    .optJSONObject("standard_resolution");
                            if (standardResolution != null) {
                                if (type.equalsIgnoreCase("image")) {
                                    photoObject.setImageUrl(standardResolution.optString("url"));
                                    photoObject.setImageHeight(standardResolution.optInt("height"));
                                } else if (type.equalsIgnoreCase("video")) {
                                    photoObject.setVideoUrl(standardResolution.optString("url"));
                                    photoObject.setVideoHeight(standardResolution.optInt("height"));
                                }
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
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    /**
     * Refresh items
     */
    public void fetchTimelineAsync() {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        /* TODO: At the moment, client is not support
        client.getHomeTimeline(0, new JsonHttpResponseHandler() {
            public void onSuccess(JSONArray json) {
                // Remember to CLEAR OUT old items before appending in the new ones
                photoAdapter.clear();

                // ...the data has come back, add new items to your adapter...
                //photoAdapter.addAll(...);
                fetchPopularPhotos();

                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
            }
            public void onFailure(Throwable e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }
        });*/

        // Remember to CLEAR OUT old items before appending in the new ones
        photoAdapter.clear();

        // ...the data has come back, add new items to your adapter...
        fetchPopularPhotos();

        // Now we call setRefreshing(false) to signal refresh has finished
        swipeContainer.setRefreshing(false);
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     * <p/>
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     * <p/>
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     * <p/>
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     * <p/>
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }*/

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p/>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }*/

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
        super.onResume();
    }
}
