package com.example.android.playa;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    }

    /**
     * This saves values across the application's lifetime (e.g. if it hasn't been closed)
     *
     * @param savedInstanceState
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        saveStateOrPrefs();
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * This saves values persistently (fires when app is closed)
     */
    @Override
    // Try this then onPause
    protected void onPause() {
        super.onPause();
        saveStateOrPrefs();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveStateOrPrefs();
    }

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

//    private void loadSavedPrefs() {
//
//        SharedPreferences sharedPref = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
//
//        mediaTag = sharedPref.getInt(getString(R.string.saved_media_tag), 0);
//        itemSelectedInfo = sharedPref.getString(getString(R.string.saved_item_selected_info), "");
//        itemSelectedDetail1 = sharedPref.getString(getString(R.string.saved_item_selected_detail_1), "");
//        itemSelectedDetail2 = sharedPref.getString(getString(R.string.saved_item_selected_detail_2), "");
//        BookSelection = sharedPref.getString(getString(R.string.saved_book_selection), "");
//        MusicSelection = sharedPref.getString(getString(R.string.saved_music_selection), "");
//        RadioSelection = sharedPref.getString(getString(R.string.saved_radio_selection), "");
//        BookSelectionDetail1 = sharedPref.getString(getString(R.string.saved_book_selection_detail_1), "");
//        MusicSelectionDetail1 = sharedPref.getString(getString(R.string.saved_music_selection_detail_1), "");
//        RadioSelectionDetail1 = sharedPref.getString(getString(R.string.saved_radio_selection_detail_1), "");
//        BookSelectionDetail2 = sharedPref.getString(getString(R.string.saved_book_selection_detail_2), "");
//        MusicSelectionDetail2 = sharedPref.getString(getString(R.string.saved_music_selection_detail_2), "");
//        RadioSelectionDetail2 = sharedPref.getString(getString(R.string.saved_radio_selection_detail_2), "");

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
        List<String> listOfBluetoothDevices = new ArrayList<>();
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
//        bluetooth.setSelector(R.color.colorFuchsia);

        bluetooth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
//                Toast.makeText(getApplicationContext(), R.string.headphones, Toast.LENGTH_SHORT).show();
                toggleSelection(view);
            }
        });

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

//    public boolean doPreferencesExist() {
//        SharedPreferences sharedPref = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
//        if (sharedPref.contains("ITEM_SELECTED_INFO")) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    private View lastSelectedItem;
    private void toggleSelection(View view) {
        if (lastSelectedItem != null) {
            lastSelectedItem.setBackgroundColor(Color.TRANSPARENT);
        }
        view.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        lastSelectedItem = view;
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