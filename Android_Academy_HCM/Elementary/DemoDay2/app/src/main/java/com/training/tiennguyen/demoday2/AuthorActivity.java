/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.demoday2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.training.tiennguyen.helper.ImageHelper;

public class AuthorActivity extends AppCompatActivity {
    private ImageView avatar;
    private TextView name;
    private WebView webView;
    public String AUTHOR_NAME = "NAME";
    public String AUTHOR_AVATAR = "AVATAR";
    public String AUTHOR_WEBSITE = "WEBSITE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        getSupportActionBar().hide();
        avatar = (ImageView) findViewById(R.id.avartarAuthor);
        name = (TextView) findViewById(R.id.nameAuthor);
        webView = (WebView) findViewById(R.id.webView);

        final Intent intent = getIntent();

        new AsyncTask<Void, Void, Void>() {
            Bitmap bm;

            @Override
            protected Void doInBackground(Void... params) {
                /*if(bm co trong cache) {
                    bm = getCached();
                } else {
                    bm = ImageHelper.getBitmapFromURL(mStudentList.get(position).getAvatar());
                }*/
                bm = ImageHelper.getBitmapFromURL(intent.getStringExtra(AUTHOR_AVATAR));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if(bm != null) {
                    avatar.setImageBitmap(bm);
                } else {
                    avatar.setImageResource(R.drawable.androidimage);
                }
            }
        }.execute();


        name.setText(intent.getStringExtra(AUTHOR_NAME));
        webView.loadUrl(intent.getStringExtra(AUTHOR_WEBSITE));
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
