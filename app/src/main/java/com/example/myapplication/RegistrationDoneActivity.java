package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class RegistrationDoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_done);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i = new Intent(RegistrationDoneActivity.this, LoginActivity.class);
                Toast.makeText(RegistrationDoneActivity.this, "Registration Successfully Done...!", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        },3000);
    }
}