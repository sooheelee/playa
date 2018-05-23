package com.example.android.playa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton book = findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookIntent = new Intent(MainActivity.this, BookActivity.class);
                startActivity(bookIntent);
            }
        });

        ImageButton music = findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicIntent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(musicIntent);
            }
        });

        ImageButton radio = findViewById(R.id.radio);
        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent radioIntent = new Intent(MainActivity.this, RadioActivity.class);
                startActivity(radioIntent);
            }
        });

//        ListView bluetooth = findViewById(R.id.bluetooth);
//        bluetooth.setOnItemClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent bluetoothIntent = new Intent(MainActivity.this, BluetoothActivity.class);
//                startActivity(bluetoothIntent);
//            }
//        });
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
}
