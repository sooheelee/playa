package com.example.android.playa;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageButton book = findViewById(R.id.book);
//        book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent bookIntent = new Intent(MainActivity.this, BookActivity.class);
//                startActivity(bookIntent);
//            }
//        });

        Button music = findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicIntent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(musicIntent);
            }
        });

//        ImageButton radio = findViewById(R.id.radio);
//        radio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent radioIntent = new Intent(MainActivity.this, RadioActivity.class);
//                startActivity(radioIntent);
//            }
//        });

        ListView bluetooth = findViewById(R.id.bluetooth);

        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    Log.v("BOOTOOTH", "Device found: " + device.getName() + device.getAddress());
                }
            }
        };

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_SHORT).show();
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            //        if(pairedDevices.size()>0)
//
//        {
            // from https://stackoverflow.com/questions/10795424/how-to-get-the-bluetooth-devices-as-a-list
            List<String> listOfBluetoothDevices = new ArrayList<String>();
            for (BluetoothDevice isbluetoothDevice : pairedDevices) {
                String deviceName = isbluetoothDevice.getName().toString();
                String deviceHardwareAddress = isbluetoothDevice.getAddress();
                String deviceNameAndAddress = deviceName + deviceHardwareAddress;
                listOfBluetoothDevices.add(deviceNameAndAddress);
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1,
                    listOfBluetoothDevices
            );
            bluetooth.setAdapter(arrayAdapter);
//        }
//
//            listView.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
//
//                @Override
//                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//
//                }
//            });

        } else{
            Toast.makeText(this, "Bluetooth is off", Toast.LENGTH_SHORT).show();
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);


//        // from https://stackoverflow.com/questions/4458529/mediacontroller-always-show-on-android
//        final VideoView videoView = findViewById(R.id.player);
//        final MediaController mediaController = new MediaController(videoView.getContext());
//        mediaController.setMediaPlayer(videoView);
//        videoView.setMediaController(mediaController);
//
//        {
//            // from https://stackoverflow.com/questions/10795424/how-to-get-the-bluetooth-devices-as-a-list
//            List<String> listOfBluetoothDevices = new ArrayList<String>();
//            for (BluetoothDevice isblueToothDevice : pairedDevices) {
//                String deviceName = isblueToothDevice.getName();
//                String deviceHardwareAddress = isblueToothDevice.getAddress();
//                listOfBluetoothDevices.add(deviceName, deviceHardwareAddress);
//            }
//
//            ListViewAdapter bluetoothListViewAdapter = new ListViewAdapter(this, listOfBluetoothDevices);
//            ListView listView = findViewById(R.id.bluetooth);
//            listView.setAdapter(bluetoothListViewAdapter);
//
//            listView.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
//
//                @Override
//                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//
//                }
//            });
//            arg0;
//            arg1;
//            arg2;
//            arg3;
//            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        }

//
//        MediaController player = findViewById(R.id.player);
//        player.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent playerIntent = new Intent(MainActivity.this, PlayerActivity.class);
//                startActivity(playerIntent);
//            }
//        });
    }
