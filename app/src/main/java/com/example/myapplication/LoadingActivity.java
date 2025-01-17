package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class LoadingActivity extends AppCompatActivity {

    String str_username, str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Intent i = getIntent();
        str_username = i.getStringExtra("Username");
        str_password = i.getStringExtra("Password");

        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i = new Intent(LoadingActivity.this, LoginDoneActivity.class);
                i.putExtra("Username",str_username);
                i.putExtra("Password",str_password);
                startActivity(i);
            }
        },3000);
    }
}