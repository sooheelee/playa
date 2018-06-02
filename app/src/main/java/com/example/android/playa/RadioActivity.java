package com.example.android.playa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class RadioActivity extends AppCompatActivity {

    public String itemSelectedID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_list);

        ArrayList<ItemInfo> wordsArrayList = new ArrayList<>();
        wordsArrayList.add(new ItemInfo(getString(R.string.song_01_title), getString(R.string.song_01_artist), getString(R.string.song_01_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_02_title), getString(R.string.song_02_artist), getString(R.string.song_02_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_03_title), getString(R.string.song_03_artist), getString(R.string.song_03_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_04_title), getString(R.string.song_04_artist), getString(R.string.song_04_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_05_title), getString(R.string.song_05_artist), getString(R.string.song_05_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_06_title), getString(R.string.song_06_artist), getString(R.string.song_06_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_07_title), getString(R.string.song_07_artist), getString(R.string.song_07_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_08_title), getString(R.string.song_08_artist), getString(R.string.song_08_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_09_title), getString(R.string.song_09_artist), getString(R.string.song_09_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_10_title), getString(R.string.song_10_artist), getString(R.string.song_10_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_11_title), getString(R.string.song_11_artist), getString(R.string.song_11_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_12_title), getString(R.string.song_12_artist), getString(R.string.song_12_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_13_title), getString(R.string.song_13_artist), getString(R.string.song_13_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.song_14_title), getString(R.string.song_14_artist), getString(R.string.song_14_length)));

        ItemInfoAdapter adapter = new ItemInfoAdapter(this, wordsArrayList);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelectedID = this.toString();

                // Prints " I/onItemClick: com.example.android.playa.MusicActivity$1@dfa477c"
                Log.i("onItemClick", itemSelectedID);
            }
        });

    }
}
