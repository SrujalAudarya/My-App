package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class LoginDoneActivity extends AppCompatActivity {

    String str_username, str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_done);

        Intent i = getIntent();
        str_username = i.getStringExtra("Username");
        str_password = i.getStringExtra("Password");

        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LoginDoneActivity.this, HomeActivity.class);
                i.putExtra("Username",str_username);
                i.putExtra("Password",str_password);
                Toast.makeText(LoginDoneActivity.this, "Login Successfully Done...!", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        },3000);
    }
}