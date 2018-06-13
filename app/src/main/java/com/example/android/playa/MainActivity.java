package com.example.android.playa;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    public static final String MyPREFERENCES = "MyPrefs";

    protected int mediaTag;

    public static final String MEDIA_TAG = "0";
    public static final String ITEM_SELECTED_INFO = "";
    public static final String ITEM_SELECTED_DETAIL_1 = "";
    public static final String ITEM_SELECTED_DETAIL_2 = "";

    public static String itemSelectedInfo = ITEM_SELECTED_INFO;
    public static String itemSelectedDetail1 = ITEM_SELECTED_DETAIL_1;
    public static String itemSelectedDetail2 = ITEM_SELECTED_DETAIL_2;

    public static final String REMEMBER_MUSIC_SELECTION = "";
    public static final String REMEMBER_BOOK_SELECTION = "";
    public static final String REMEMBER_RADIO_SELECTION = "";

    public static String rememberMusicSelection = REMEMBER_MUSIC_SELECTION;
    public static String rememberBookSelection = REMEMBER_BOOK_SELECTION;
    public static String rememberRadioSelection = REMEMBER_RADIO_SELECTION;

    /**
     * This displays saved values when display orientation is switched.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            displaySelected();
            colorSelectedMedia();
        } else {
            setContentView(R.layout.activity_main);
        }

        // Remember to remove
        Log.i("onRestoreMain", itemSelectedInfo + " " + itemSelectedDetail1 + " " + itemSelectedDetail2);
    }

    /**
     * This saves values.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(ITEM_SELECTED_INFO, itemSelectedInfo);
        savedInstanceState.putString(ITEM_SELECTED_DETAIL_1, itemSelectedDetail1);
        savedInstanceState.putString(ITEM_SELECTED_DETAIL_2, itemSelectedDetail2);
//        savedInstanceState.putInt(MEDIA_TAG, mediaTag);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        colorSelectedMedia();
        displaySelected();

        final Button book = findViewById(R.id.book);
        final Button music = findViewById(R.id.music);
        final Button radio = findViewById(R.id.radio);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaTag = 1;
                colorSelectedMedia();
                itemSelectedInfo = rememberBookSelection;
                itemSelectedDetail1 = rememberBookSelection;
                itemSelectedDetail2 = rememberBookSelection;
                displaySelected();
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaTag = 2;
                colorSelectedMedia();
                itemSelectedInfo = rememberMusicSelection;
                itemSelectedDetail1 = rememberMusicSelection;
                itemSelectedDetail2 = rememberMusicSelection;
                displaySelected();
            }
        });

        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaTag = 3;
                colorSelectedMedia();
                itemSelectedInfo = rememberRadioSelection;
                itemSelectedDetail1 = rememberRadioSelection;
                itemSelectedDetail2 = rememberRadioSelection;
                displaySelected();
            }
        });

        book.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent bookIntent = new Intent(MainActivity.this, BookActivity.class);
                startActivity(bookIntent);
                return true;
            }
        });

        music.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent musicIntent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(musicIntent);
                return true;
            }
        });

        radio.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent radioIntent = new Intent(MainActivity.this, RadioActivity.class);
                startActivity(radioIntent);
                return true;
            }
        });

        ListView bluetooth = findViewById(R.id.bluetooth);
        TextView deviceList = findViewById(R.id.device_list);


        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            deviceList.setText("For bluetooth functions, turn bluetooth on and restart app.");
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            int REQUEST_ENABLE_BT = 1;
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        // Lists only known, i.e. previously paired bluetooth devices, that are available.
        // Yet to wire: indicate which device is connected.
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            deviceList.setText("Bluetooth is on. Known devices:");
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

    public void displaySelected() {
        TextView scrollingInfo = findViewById(R.id.now_playing_info);
        scrollingInfo.setText(itemSelectedInfo);
        scrollingInfo.setSelected(true);
        scrollingInfo.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        scrollingInfo.setSingleLine(true);

        TextView scrollingDetail = findViewById(R.id.now_playing_detail1);
        scrollingDetail.setText(itemSelectedDetail1);
        scrollingDetail.setSelected(true);
        scrollingDetail.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        scrollingDetail.setSingleLine(true);

        TextView selectedItemLength = findViewById(R.id.now_playing_detail2);
        selectedItemLength.setText(itemSelectedDetail2);
    }

    public void colorSelectedMedia() {
        final Button book = findViewById(R.id.book);
        final Button music = findViewById(R.id.music);
        final Button radio = findViewById(R.id.radio);

        if (mediaTag == 0 || mediaTag == 2) {
            music.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
            radio.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            book.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        }

        if (mediaTag == 1) {
            book.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
            music.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            radio.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        }

        if (mediaTag == 3) {
            radio.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
            music.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            book.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        }
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


