package com.training.tiennguyen.demoday1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Win 8.1 VS8 X64 on 19/12/2015.
 */
public class ColorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent data = new Intent();
        data .putExtra("COLOR", 0xff66900);
        setResult(RESULT_OK, data);
        finish();
    }
}
