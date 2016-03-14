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
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.training.tiennguyen.instagram_photo_viewer.R;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        if (isNetworkConnected()) {
            /* Get data */
            Intent intent = getIntent();
            String url = intent.getStringExtra("URL");
            int height = intent.getIntExtra("HEIGHT", 200);

            /* Populate to run the video */
            final VideoView mVideoView = (VideoView) findViewById(R.id.user_video_full);
            mVideoView.setVideoPath(url);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(mVideoView);
            mVideoView.setMediaController(mediaController);
            mVideoView.setVideoURI(Uri.parse(url));
            mVideoView.requestFocus();
            mVideoView.setMinimumHeight(height);
            mVideoView.start();
        }
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

}
