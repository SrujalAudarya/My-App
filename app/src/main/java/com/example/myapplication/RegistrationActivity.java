package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    TextView tv_registration_page,tv_new_registration;
    EditText et_name,et_mobile_no,et_email,et_username,et_password,et_con_password;
    CheckBox cb_show_hide;
    Button register_btn;
    Animation fadeInAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Registration Page");

        tv_registration_page = findViewById(R.id.tv_registration_page);
        tv_new_registration = findViewById(R.id.new_registration);
        et_name = findViewById(R.id.et_name);
        et_mobile_no = findViewById(R.id.et_mobile_no);
        et_email = findViewById(R.id.et_email_id);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_con_password = findViewById(R.id.et_conform_password);
        cb_show_hide = findViewById(R.id.cb_login_show_hide);
        register_btn = findViewById(R.id.register_button);

        fadeInAnim = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.fadein);

        tv_registration_page.setAnimation(fadeInAnim);
        tv_new_registration.setAnimation(fadeInAnim);

        cb_show_hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_con_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_con_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et_name.getText().toString().isEmpty())
                 {
                     et_name.setError("Please Enter Name");
                 }
                 else if (et_mobile_no.getText().toString().isEmpty())
                 {
                     et_mobile_no.setError("Please Enter Mobile Number");
                 }
                 else if (et_mobile_no.getText().toString().length() !=10)
                 {
                     et_mobile_no.setError("Mobile Number must be 10 digits");
                 }
                 else if (et_email.getText().toString().isEmpty())
                 {
                     et_email.setError("Please Enter Email ID");
                 }
                 else if (!et_email.getText().toString().contains("@") ||
                          !et_email.getText().toString().contains(".com"))
                 {
                     et_email.setError("Email ID must be End with// @gmail.com");
                 }
                 else if(et_username.getText().toString().isEmpty())
                 {
                    et_username.setError("Please Enter Username");
                 }
                 else if (et_username.getText().toString().length()<8)
                 {
                    et_username.setError("Username must be greater then 8");
                 }
                 else if (!et_username.getText().toString().matches(".*[A-Z].*"))
                 {
                      et_username.setError("Please Enter At Least one Uppercase Letter");
                 }
                 else if (!et_username.getText().toString().matches(".*[a-z].*"))
                 {
                     et_username.setError("Please Enter At Least one Lowercase Letter");
                 }
                 else if (!et_username.getText().toString().matches(".*[0-9].*"))
                 {
                     et_username.setError("Please Enter At Least one Number Digit");
                 }
                 else if (!et_username.getText().toString().matches(".*[!,@,#,$,%,^,&,*,-].*"))
                 {
                     et_username.setError("Please Enter At Least one Special Symbol Like '@','#' ");
                 }
                 else if (et_password.getText().toString().isEmpty())
                 {
                    et_password.setError("Please Enter your Password");
                 }
                 else if (et_password.getText().toString().length()<8)
                 {
                    et_password.setError("Password must be Greater then 8 or Equal");
                 }
                 else if (et_con_password.getText().toString().isEmpty())
                 {
                     et_con_password.setError("Please Enter Confirm Password");
                 }
                 else if (!et_con_password.getText().toString().equals(et_password.getText().toString()))
                 {
                     et_con_password.setError("Confirm Password doesn't match with Password");
                 }
                 else
                {
                    Intent i = new Intent(RegistrationActivity.this, RegistrationDoneActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}