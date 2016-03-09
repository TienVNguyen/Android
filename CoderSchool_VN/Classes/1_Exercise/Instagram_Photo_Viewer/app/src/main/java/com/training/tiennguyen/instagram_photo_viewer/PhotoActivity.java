/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.training.tiennguyen.instagram_photo_viewer.adapter.PhotoAdapter;
import com.training.tiennguyen.instagram_photo_viewer.model.PhotoObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotoActivity extends AppCompatActivity {
    private static final String clientId = "e05c462ebd86446ea48a5af73769b602";
    private ArrayList<PhotoObject> photoObjects;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        // Initialize
        photoObjects = new ArrayList<>();

        // Create adapter linking to the source.
        photoAdapter = new PhotoAdapter(this, photoObjects);

        // Find the listView from input
        ListView lvPhotos = (ListView) findViewById(R.id.lv_photos);
        lvPhotos.setAdapter(photoAdapter);

        // Steam data
        fetchPopularPhotos();
    }

    /**
     * Send out requirement
     */
    private void fetchPopularPhotos() {
        String url = "https://api.instagram.com/v1/media/popular?client_id=" + clientId;

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
                JSONArray photoJsonArray = null;
                try {
                    photoJsonArray = response.getJSONArray("data");

                    // Iterate array
                    for (int i = 0; i < photoJsonArray.length(); i++) {
                        // Get JSON object at each positions
                        JSONObject jsonObject = photoJsonArray.getJSONObject(i);

                        // Decode attributes into data model
                        PhotoObject photoObject = new PhotoObject();
                        photoObject.setName(jsonObject.getJSONObject("user").getString("username"));
                        photoObject.setCaption(jsonObject.getJSONObject("caption").getString("text"));
                        photoObject.setImageUrl(jsonObject.getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
                        photoObject.setImageHeight(jsonObject.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"));
                        photoObject.setLikeCount(jsonObject.getJSONObject("likes").getInt("count"));

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
}
