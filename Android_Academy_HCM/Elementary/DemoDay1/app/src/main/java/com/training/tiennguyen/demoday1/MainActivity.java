package com.training.tiennguyen.demoday1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtEmail;
    private EditText txtPass;
    private Button btnSignIn;
    private Button btnExit;
    private DBDemo dbDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        dbDemo = new DBDemo(this);

        User user1 = new User();
        user1.setEmail("acb@gmail.com");
        user1.setPassword("123456");
        dbDemo.addUser(user1);

        User user2 = new User();
        user2.setEmail("def@gmail.com");
        user2.setPassword("780807987");
        dbDemo.addUser(user2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnExit = (Button) findViewById(R.id.btnExit);

        btnSignIn.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        // Case 2 onClick;
        /*btnSignIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "OnClick", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    // Case 3 onClick: Must set onClick attribute
    public void signInEvent(View view) {
        Toast.makeText(MainActivity.this, "OnClick", Toast.LENGTH_SHORT).show();
    }

    //Case 1 onClick: need to  implements View.OnClickListener
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSignIn:
                handleSignInClick();
                break;
            case R.id.btnExit:
                handleSignInExit();
                break;
            default:
                break;
        }
    }

    private void handleSignInExit() {
        Toast.makeText(this, "OnExit", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private void handleSignInClick() {
        String email = txtEmail.getText().toString();
        String pass = txtPass.getText().toString();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(pass);
            if (checkLogin(user)){
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("NAME", user.getEmail());
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show();
            }
        } else {
            txtEmail.setError("Email error!");
        }
    }

    private boolean checkLogin(User user) {
        return dbDemo.checkUserExists(user);
    }
}
