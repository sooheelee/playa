package com.example.android.playa;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

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
        TextView deviceList = findViewById(R.id.device_list);


        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            int REQUEST_ENABLE_BT = 1;
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            deviceList.setText("For bluetooth functions, turn bluetooth on and restart app.");
        }

        // Lists only known, i.e. previously paired bluetooth devices, that are available.
        // Which is connected is a fuction yet to be wired.
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_SHORT).show();
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        List<String> listOfBluetoothDevices = new ArrayList<String>();
        for (BluetoothDevice isbluetoothDevice : pairedDevices) {
            String deviceName = isbluetoothDevice.getName();
            String deviceHardwareAddress = isbluetoothDevice.getAddress();
            int isBondBonded = isbluetoothDevice.getType();
            String deviceNameAndAddress = deviceName + "\n" + deviceHardwareAddress + "\n" + String.valueOf(isBondBonded);
            listOfBluetoothDevices.add(deviceNameAndAddress);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                listOfBluetoothDevices
        );
        bluetooth.setAdapter(arrayAdapter);
    }
}


//        // from https://stackoverflow.com/questions/4458529/mediacontroller-always-show-on-android
//        final VideoView videoView = findViewById(R.id.player);
//        final MediaController mediaController = new MediaController(videoView.getContext());
//        mediaController.setMediaPlayer(videoView);
//        videoView.setMediaController(mediaController);
//
//        {
//
//            listView.setOnItemSelectedListener

//
//        MediaController player = findViewById(R.id.player);
//        player.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent playerIntent = new Intent(MainActivity.this, PlayerActivity.class);
//                startActivity(playerIntent);
//            }
//        });

