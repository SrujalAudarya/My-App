package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    Button btn_turn_on, btn_turn_off, btn_get_visible, btn_list_devices;
    ListView listView;
    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        setTitle("Bluetooth");
        ActivityCompat.requestPermissions(BluetoothActivity.this,
                new String[]{Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_ADMIN,Manifest.permission.BLUETOOTH_CONNECT,Manifest.permission.BLUETOOTH_ADVERTISE},
                999);

        btn_turn_on = findViewById(R.id.btn_bluetooth_turn_on);
        btn_get_visible = findViewById(R.id.btn_bluetooth_getvisible);
        btn_list_devices = findViewById(R.id.btn_bluetooth_list_device);
        btn_turn_off = findViewById(R.id.btn_bluetooth_turn_off);
        listView = findViewById(R.id.tv_bluetooth_showpairdevices);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btn_turn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothAdapter.disable();
                Toast.makeText(BluetoothActivity.this, "Bluetooth is Turn Off", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void bluetoothTurnOn(View view) {
        if (!bluetoothAdapter.isEnabled())
        {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i, 10);
            Toast.makeText(BluetoothActivity.this, " Bluetooth is Turn On", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(BluetoothActivity.this, "Your Bluetooth is Already on", Toast.LENGTH_SHORT).show();
        }
    }

    public void getVisible(View view) {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(i, 1000);
        Toast.makeText(BluetoothActivity.this, "Your Device is not visible to all", Toast.LENGTH_SHORT).show();
    }

    public void listDevices(View view) {
        pairedDevices = bluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();
        for (BluetoothDevice bluetoothDevice : pairedDevices)
        {
            list.add(bluetoothDevice.getName());
            ArrayAdapter adapter = new ArrayAdapter(BluetoothActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);

            listView.setAdapter(adapter);
        }
    }

}