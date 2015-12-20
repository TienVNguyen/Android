package com.training.tiennguyen.demoday1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageView = (ImageView) super.findViewById(R.id.ImageViewShow);

        Picasso.with(ImageActivity.this).load("http://www.bhphotovideo.com/images/images500x500/Won_Background_MM11021010_Muslin_Modern_Background_622089.jpg").into(imageView);
    }
}
