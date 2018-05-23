package com.example.android.playa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

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

        ListView bluetooth = findViewById(R.id.bluetooth);

        // from https://stackoverflow.com/questions/4458529/mediacontroller-always-show-on-android
        final VideoView videoView = findViewById(R.id.player);
        final MediaController mediaController = new MediaController(videoView.getContext());
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);

//        {
//            @Override
//            public void setMediaPlayer(MediaPlayerControl player) {
//                super.setMediaPlayer(player);
//                this.show(4000);
//            }
//
//            @Override
//            public void show(int timeout) {
//                super.show(timeout);
//                this.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void hide() {
//                super.hide();
//                this.setVisibility(View.GONE);
//            }
//
//            @Override
//            public boolean dispatchKeyEvent(KeyEvent event) {
//                if(event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//                    Activity activity = (Activity)getContext();
//                    activity.finish();
//                }
//                return true;
//            }
//        };

//        mediaController.setAnchorView(videoView);
//        mediaController.setMediaPlayer(videoView);
//        videoView.setMediaController(mediaController);
//        mediaController.requestFocus();
//
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                videoView.start();
//                mediaController.show(10000);
//            }
//        });
//
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                finish();
//            }
//        });


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

    // from https://stackoverflow.com/questions/4458529/mediacontroller-always-show-on-android
    public class MyMediaController extends MediaController {
        public MyMediaController(Context context, AttributeSet attrs) {
            super (context, attrs);
        }

        public MyMediaController(Context context, boolean useFastForward) {
            super(context, useFastForward);
        }

        public MyMediaController(Context context) {
            super(context);
        }

        @Override
        public void show(int timeout) {
            super.show(0);
        }
    }
}
