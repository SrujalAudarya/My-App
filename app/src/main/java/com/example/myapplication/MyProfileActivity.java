package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyProfileActivity extends AppCompatActivity {

    String str_username, str_password;
    TextView  tv_username, tv_password, tv_myToken;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        setTitle("My Profile");

        preferences = PreferenceManager.getDefaultSharedPreferences(MyProfileActivity.this);
        editor = preferences.edit();

        tv_username = findViewById(R.id.tv_myprofile_username);
        tv_password = findViewById(R.id.tv_myprofile_password);
        tv_myToken = findViewById(R.id.tv_myprofile_token);

        Intent i = getIntent();
        str_username = i.getStringExtra("Username");
        str_password = i.getStringExtra("Password");

        tv_username.setText(str_username);
        tv_password.setText(str_password);

        tv_myToken.setText(preferences.getString("token",""));

        Toast.makeText(MyProfileActivity.this, str_username+" "+str_password, Toast.LENGTH_SHORT).show();

    }
}