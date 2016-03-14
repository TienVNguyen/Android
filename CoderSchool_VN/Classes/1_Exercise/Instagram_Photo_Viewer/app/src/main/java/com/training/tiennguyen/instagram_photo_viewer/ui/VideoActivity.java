/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.ui;

import android.content.Intent;
import android.media.MediaPlayer;
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

        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        int height = intent.getIntExtra("HEIGHT", 200);

        final VideoView mVideoView = (VideoView) findViewById(R.id.user_video_full);
        mVideoView.setVideoPath(url);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoURI(Uri.parse(url));
        mVideoView.requestFocus();
        mVideoView.setMinimumHeight(height);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // TODO: At the moment still not run
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
            }
        });
    }
}
