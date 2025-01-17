package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class LoginActivity extends AppCompatActivity {

    //step 1 Object creation
    EditText et_username,et_password;
    CheckBox cb_show_hide;
    Button btn_login;
    TextView tv_new_user,tv_loginpage, tv_term_condition;
    Animation fadeInAnim;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login Activity");

        preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        editor = preferences.edit();

        if (preferences.getBoolean("Login",false))
        {
            Intent in = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(in);
        }
        // step 2 Find the id and Assign to objects

        et_username = findViewById(R.id.et_login_username);
        et_password = findViewById(R.id.et_login_password);
        cb_show_hide = findViewById(R.id.cb_login_show_hide);
        btn_login = findViewById(R.id.button);
        tv_new_user = findViewById(R.id.tv_login_new_user);
        tv_loginpage = findViewById(R.id.tv_login_page);
        tv_term_condition = findViewById(R.id.tv_term_condition);

         fadeInAnim = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.fadein );

         tv_loginpage.setAnimation(fadeInAnim);

        cb_show_hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_username.getText().toString().isEmpty())
                {
                    et_username.setError("Please Enter Username");
                }
                else if (et_username.getText().toString().length()<8)
                {
                    et_username.setError("Username must be greater then 8");
                }
                else if (et_password.getText().toString().isEmpty())
                {
                    et_password.setError("Please Enter your Password");
                }
                else if (et_password.getText().toString().length()<8)
                {
                    et_password.setError("Password must be Greater then 8 or Equal");
                }
                else
                {
                    Intent i = new Intent(LoginActivity.this, LoadingActivity.class);
                    i.putExtra("Username",et_username.getText().toString());
                    i.putExtra("Password",et_password.getText().toString());
                    editor.putBoolean("Login",true).commit();
                    startActivity(i);
                    finish();
                }
            }
        });

        tv_new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });

        tv_term_condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://google.com/"));
                startActivity(i);
            }
        });
    }
}
