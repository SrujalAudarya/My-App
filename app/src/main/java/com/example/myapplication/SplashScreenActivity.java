package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashScreenActivity extends AppCompatActivity {

    TextView tv_title, tv_subtitle;
    ImageView img_logo;
    Animation fadeInAnim,topdown, downtop;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        preferences = PreferenceManager.getDefaultSharedPreferences(SplashScreenActivity.this);
        editor = preferences.edit();

FirebaseMessaging.getInstance().getToken()
    .addOnCompleteListener(new OnCompleteListener<String>() {
        @Override
        public void onComplete(@NonNull Task<String> task) {
          if (!task.isSuccessful()) {
              Toast.makeText(SplashScreenActivity.this, "FCM Token is not received", Toast.LENGTH_SHORT).show();
            return;
          }

          // Get new FCM registration token
          String t1 = task.getResult();
          editor.putString("token",t1).commit();

          // Log and toast
          Toast.makeText(SplashScreenActivity.this, t1, Toast.LENGTH_SHORT).show();
        }
    });

        img_logo = findViewById(R.id.img_logo);
        tv_title = findViewById(R.id.TV_main_title);
        tv_subtitle = findViewById(R.id.TV_main_subtitle);

        fadeInAnim = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.fadein);
        topdown = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.top_down);
        downtop = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.down_top);

        img_logo.setAnimation(fadeInAnim);
        img_logo.setAnimation(topdown);
        tv_title.setAnimation(fadeInAnim);
        tv_title.setAnimation(downtop);
        tv_subtitle.setAnimation(fadeInAnim);
        tv_subtitle.setAnimation(downtop);
        Handler h = new Handler();

        h.postDelayed(() -> {
            Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }, 3000);
    }
}