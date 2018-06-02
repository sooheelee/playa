package com.example.android.playa;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    /**
     * This displays saved values when display orientation is switched.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
//            quantity = savedInstanceState.getInt(QUANTITY_TEXT_VIEW);
//            displayQuantity(quantity);
//            calculateTotalPrice();
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    /**
     * This saves values.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
//        savedInstanceState.putInt(QUANTITY_TEXT_VIEW, quantity);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollNowPlaying();

        Button book = findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookIntent = new Intent(MainActivity.this, BookActivity.class);
                startActivity(bookIntent);
            }
        });

        Button music = findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicIntent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(musicIntent);
            }
        });

        Button radio = findViewById(R.id.radio);
        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent radioIntent = new Intent(MainActivity.this, RadioActivity.class);
                startActivity(radioIntent);
            }
        });

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
        // Yet to wire: indicate which device is connected.
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_SHORT).show();
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        List<String> listOfBluetoothDevices = new ArrayList<String>();
        for (BluetoothDevice isBluetoothDevice : pairedDevices) {
            String deviceName = isBluetoothDevice.getName();
            String deviceHardwareAddress = isBluetoothDevice.getAddress();
            String deviceNameAndAddress = deviceName + " " + deviceHardwareAddress;
            listOfBluetoothDevices.add(deviceNameAndAddress);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                listOfBluetoothDevices
        );
        bluetooth.setAdapter(arrayAdapter);
        onPausePlayButtonClick();

    }

    public void onPausePlayButtonClick() {
        final FloatingActionButton pauseOrPlayButton = findViewById(R.id.floatingActionButtonPause);
        pauseOrPlayButton.setTag(1);
        pauseOrPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((int) pauseOrPlayButton.getTag() == 1) {
                    pauseOrPlayButton.setImageResource(android.R.drawable.ic_media_play);
                    pauseOrPlayButton.setTag(2);
                } else {
                    pauseOrPlayButton.setImageResource(android.R.drawable.ic_media_pause);
                    pauseOrPlayButton.setTag(1);
                }
            }
        });
    }

    private void scrollNowPlaying() {
        TextView scrollingText = findViewById(R.id.nowPlaying);
        scrollingText.setSelected(true);
        scrollingText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        scrollingText.setSingleLine(true);
    }

    /**
     * Creates menu.
     *
     * @param menu has reset option.
     * @return true upon inflation
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Acts on menu item selection.
     *
     * @param item RESET clears quiz answers
     * @return boolean of item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}


