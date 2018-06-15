package com.example.android.playa;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Creates PlaYa app's main activity.
 * PlaYa is a media player that caters to how I ingest media.
 *
 * At top are media selection buttons.
 * - The app represents three media types--audio books, music, and live radio--with buttons.
 * - The UI preloads an audio book, a music album and two local radio stations.
 *
 * The middle displays bluetooth functionality.
 * - If bluetooth is off, a dialog appears to deny or turn on bluetooth connectivity.
 * - If bluetooth is on, the device populates the bluetooth list with previously connected and available devices.
 *
 * At bottom is the media controller.
 * - Displays last selected media item that is currently playing.
 * - Slider-bar to fine-tune location in item playing.
 * - Backwards, Play/Pause, and Forwards buttons.
 */
public class MainActivity extends AppCompatActivity {

    public static int mediaTag;
    public static String itemSelectedInfo = "";
    public static String itemSelectedDetail1 = "";
    public static String itemSelectedDetail2 = "";
    public static String BookSelection = "";
    public static String MusicSelection = "";
    public static String RadioSelection = "";
    public static String BookSelectionDetail1 = "";
    public static String MusicSelectionDetail1 = "";
    public static String RadioSelectionDetail1 = "";
    public static String BookSelectionDetail2 = "";
    public static String MusicSelectionDetail2 = "";
    public static String RadioSelectionDetail2 = "";

    /**
     * Creates instance with listeners for button clicks, button long presses and bluetooth selection
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                itemSelectedInfo = BookSelection;
                itemSelectedDetail1 = BookSelectionDetail1;
                itemSelectedDetail2 = BookSelectionDetail2;
                displaySelected();
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaTag = 2;
                colorSelectedMedia();
                itemSelectedInfo = MusicSelection;
                itemSelectedDetail1 = MusicSelectionDetail1;
                itemSelectedDetail2 = MusicSelectionDetail2;
                displaySelected();
            }
        });

        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaTag = 3;
                colorSelectedMedia();
                itemSelectedInfo = RadioSelection;
                itemSelectedDetail1 = RadioSelectionDetail1;
                itemSelectedDetail2 = RadioSelectionDetail2;
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

        final ListView bluetooth = findViewById(R.id.bluetooth);
        TextView deviceList = findViewById(R.id.device_list);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            deviceList.setText(R.string.bluetooth_restart);
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            int REQUEST_ENABLE_BT = 1;
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        // Lists only known, i.e. previously paired bluetooth devices that are available.
        // Yet to wire indication of actually connected device.
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            deviceList.setText(R.string.bluetooth_on);
        }

        assert mBluetoothAdapter != null;
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        List<String> listOfBluetoothDevices = new ArrayList<>();
        for (BluetoothDevice isBluetoothDevice : pairedDevices) {
            String deviceName = isBluetoothDevice.getName();
            String deviceHardwareAddress = isBluetoothDevice.getAddress();
            String deviceNameAndAddress = deviceName + " " + deviceHardwareAddress;
            listOfBluetoothDevices.add(deviceNameAndAddress);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                listOfBluetoothDevices
        );
        bluetooth.setAdapter(arrayAdapter);

        bluetooth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                toggleSelection(view);
            }
        });
        onPausePlayButtonClick();
    }

    /**
     * Highlights media button corresponding to media that is currently playing
     */
    public void colorSelectedMedia() {
        final Button book = findViewById(R.id.book);
        final Button music = findViewById(R.id.music);
        final Button radio = findViewById(R.id.radio);

        if (mediaTag == 0) {
            music.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            radio.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            book.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            TextView selectMediaInstruction = findViewById(R.id.visible_if_no_media_selection);
            selectMediaInstruction.setText(R.string.long_press);
        }

        if (mediaTag == 1) {
            book.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
            music.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            radio.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            itemSelectedInfo = BookSelection;
            itemSelectedDetail1 = BookSelectionDetail1;
            itemSelectedDetail2 = BookSelectionDetail2;
            displaySelected();
        }

        if (mediaTag == 2) {
            music.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
            radio.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            book.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            itemSelectedInfo = MusicSelection;
            itemSelectedDetail1 = MusicSelectionDetail1;
            itemSelectedDetail2 = MusicSelectionDetail2;
            displaySelected();
        }

        if (mediaTag == 3) {
            radio.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
            music.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            book.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            itemSelectedInfo = RadioSelection;
            itemSelectedDetail1 = RadioSelectionDetail1;
            itemSelectedDetail2 = RadioSelectionDetail2;
            displaySelected();
        }
    }

    /**
     * Displays last selected media item, scrolling if item description is wider than the view
     */
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

    /**
     * Define variable for last selected bluetooth device
     */
    private View lastSelectedItem;

    /**
     * Highlights bluetooth device selection, which is limited to one in UI design
     */
    private void toggleSelection(View view) {
        if (lastSelectedItem != null) {
            lastSelectedItem.setBackgroundColor(Color.TRANSPARENT);
        }
        view.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        lastSelectedItem = view;
    }

    /**
     * Switches between PLAY (default) and PAUSE buttons on media controller
     */
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

    /**
     * Displays saved values when display orientation is switched.
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
    }

    /**
     * Saves values across the application's lifetime (e.g. if it hasn't been closed)
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        saveStateOrPrefs();
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Saves values persistently on pause
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveStateOrPrefs();
    }

    /**
     * Saves values persistently on stop
     */
    @Override
    protected void onStop() {
        super.onStop();
        saveStateOrPrefs();
    }

    /**
     * Saves values to shared preferences across activities
     */
    private void saveStateOrPrefs() {
        SharedPreferences sharedPref = this.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_media_tag), mediaTag);
        editor.putString(getString(R.string.saved_item_selected_info), itemSelectedInfo);
        editor.putString(getString(R.string.saved_item_selected_detail_1), itemSelectedDetail1);
        editor.putString(getString(R.string.saved_item_selected_detail_2), itemSelectedDetail2);
        editor.putString(getString(R.string.saved_book_selection), BookSelection);
        editor.putString(getString(R.string.saved_music_selection), MusicSelection);
        editor.putString(getString(R.string.saved_radio_selection), RadioSelection);
        editor.putString(getString(R.string.saved_book_selection_detail_1), BookSelectionDetail1);
        editor.putString(getString(R.string.saved_music_selection_detail_1), MusicSelectionDetail1);
        editor.putString(getString(R.string.saved_radio_selection_detail_1), RadioSelectionDetail1);
        editor.putString(getString(R.string.saved_book_selection_detail_2), BookSelectionDetail2);
        editor.putString(getString(R.string.saved_music_selection_detail_2), MusicSelectionDetail2);
        editor.putString(getString(R.string.saved_radio_selection_detail_2), RadioSelectionDetail2);
        editor.apply();
        editor.commit();
    }

    /**
     * Creates menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Acts on menu item selection
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
