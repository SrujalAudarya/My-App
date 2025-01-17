package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contact_usActivity extends AppCompatActivity {

    EditText et_mobile_no, et_mobile_no_2, et_message;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        setTitle("Contact Us");
        ActivityCompat.requestPermissions(Contact_usActivity.this,
                new String[]{Manifest.permission.SEND_SMS}, 555);

        et_mobile_no = findViewById(R.id.et_contactus_mobile_no);
        et_mobile_no_2 = findViewById(R.id.et_contactus_mobile_no2);
        et_message = findViewById(R.id.et_contactus_message);
        btn_send = findViewById(R.id.btn_contactus_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_mobile_no.getText().toString().isEmpty())
                {
                    et_mobile_no.setError("Please Enter Mobile");
                }
                else if (et_mobile_no.getText().toString().length() != 10)
                {
                    et_mobile_no.setError("Mobile Number Must 10 Digit");
                }
                else if (et_mobile_no_2.getText().toString().isEmpty())
                {
                    et_mobile_no_2.setError("Please Enter Mobile");
                }
                else if (et_mobile_no_2.getText().toString().length() != 10)
                {
                    et_mobile_no_2.setError("Mobile Number Must 10 Digit");
                }
                else if (et_message.getText().toString().isEmpty())
                {
                    et_message.setError("Please Enter Message First");
                }
                else
                {
                    String mobile_no = et_mobile_no.getText().toString();
                    String mobile_no_2 = et_mobile_no_2.getText().toString();
                    String message = et_message.getText().toString();

                    Intent i = new Intent(Contact_usActivity.this, HomeActivity.class);
                    PendingIntent pi = PendingIntent.getActivity(Contact_usActivity.this, 0, i,PendingIntent.FLAG_IMMUTABLE);

                    //send sms

                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(mobile_no, null, message, pi, null);
                    sms.sendTextMessage(mobile_no_2, null, message, pi, null);
                    Toast.makeText(Contact_usActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}