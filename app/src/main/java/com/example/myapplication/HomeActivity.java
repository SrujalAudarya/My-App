package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    boolean doubletab = false;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    String str_username, str_password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home Page");

        tabLayout = findViewById(R.id.tablelayout);
        viewPager = findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ChatFragment(), "Chats");
        viewPagerAdapter.addFragment(new StatusFragment(), "Status");
        viewPagerAdapter.addFragment(new CallsFragment(), "Calls");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Intent i = getIntent();
        str_username = i.getStringExtra("Username");
        str_password = i.getStringExtra("Password");

        Toast.makeText(HomeActivity.this, str_username+" "+str_password, Toast.LENGTH_SHORT).show();

        preferences = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        editor = preferences.edit();

        preferences = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("firsttime",true);

        if (isFirstTime)
        {
            welcome();
        }
    }

    private void welcome(){
        AlertDialog.Builder ab = new AlertDialog.Builder(HomeActivity.this);
        ab.setTitle("Audarya.Pvt.Ltd");
        ab.setMessage("Welcome to your App....");
        ab.setPositiveButton("Thank You...", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();

        editor = preferences.edit();
        editor.putBoolean("firsttime",false).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.home_menu_location)
        {
            Toast.makeText(HomeActivity.this, "My Location", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, MyLocationActivity.class);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.home_menu_my_profile)
        {
            Toast.makeText(HomeActivity.this, "My Profile", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, MyProfileActivity.class);
            i.putExtra("Username", str_username);
            i.putExtra("Password", str_password);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.home_menu_text_to_speech)
        {
            Toast.makeText(HomeActivity.this, "Text To Speech", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, Text_To_SpeechActivity.class);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.home_menu_camera)
        {
            Toast.makeText(HomeActivity.this, "Camera", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, CameraActivity.class);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.home_menu_bluetooth)
        {
            Toast.makeText(HomeActivity.this, "Bluetooth", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, BluetoothActivity.class);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.home_menu_setting)
        {
            Toast.makeText(HomeActivity.this, "Setting", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, SettingActivity.class);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.home_menu_contact_us)
        {
            Toast.makeText(HomeActivity.this, "Contact us", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, Contact_usActivity.class);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.home_menu_about_us)
        {
            Toast.makeText(HomeActivity.this, "About Us", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, About_usActivity.class);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.home_menu_logout)
        {
            AlertDialog.Builder ad = new AlertDialog.Builder(HomeActivity.this);
            ad.setTitle("Logout");
            ad.setMessage("Are you Sure you want To Logout");
            ad.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            ad.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                    editor.putBoolean("Login",false).commit();
                    startActivity(i);
                    finish();
                }
            }).create().show();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubletab)
        {
            super.onBackPressed();
            Toast.makeText(HomeActivity.this, "Close App....", Toast.LENGTH_SHORT).show();
        }
       else
        {
            Toast.makeText(HomeActivity.this, "Press again to exit app", Toast.LENGTH_SHORT).show();
            doubletab = true;
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubletab = false;
                }
            },2000);
        }
    }
}
