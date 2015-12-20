package com.training.tiennguyen.demoday1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private TextView txtEmail;
    private int RESULT_COLOR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initData();
    }

    private void initData() {

        final Intent intent = getIntent();
        ((TextView) findViewById(R.id.email)).setText(intent.getStringExtra("NAME"));

        findViewById(R.id.emailColor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomeActivity.this, ColorActivity.class);
                startActivityForResult(intent1, RESULT_COLOR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_COLOR) {
            if (resultCode == RESULT_OK) {
                int color = data.getIntExtra("COLOR", 0xff000000);
                ((TextView) findViewById(R.id.email)).setTextColor(color);
            }
        }
    }
}
