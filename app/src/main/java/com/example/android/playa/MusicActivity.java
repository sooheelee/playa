package com.example.android.playa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    /**
     * Creates music activity and displays details on the playlist, here an album.
     * Saves last selection to shared preferences.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_list);

        final ArrayList<ItemInfo> wordsArrayList = new ArrayList<>();
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

        final ItemInfoAdapter adapter = new ItemInfoAdapter(this, wordsArrayList);
        final ListView listView = findViewById(R.id.list);

        TextView headerToListView = new TextView(this);
        headerToListView.setText(R.string.song_info);

        final int spToPixels = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.media_header_text_size);
        headerToListView.setTextSize(spToPixels);
        headerToListView.setTextColor(getResources().getColor(R.color.colorWhite));
        headerToListView.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));

        final int dpToPixelsLeft = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.media_header_padding_left);
        final int dpToPixelsRight = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.media_header_padding_right);
        final int dpToPixelsTop = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.media_header_padding_top);
        final int dpToPixelsBottom = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.media_header_padding_bottom);
        headerToListView.setPadding(dpToPixelsLeft,dpToPixelsTop,dpToPixelsRight,dpToPixelsBottom);

        listView.setAdapter(adapter);
        listView.addHeaderView(headerToListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                MainActivity.mediaTag = 2;
                MainActivity.MusicSelection = getString(R.string.song_info);
                MainActivity.MusicSelectionDetail1 = wordsArrayList.get(position - 1).getTitleAndArtist();
                MainActivity.MusicSelectionDetail2 = wordsArrayList.get(position - 1).getLength();
            }
        });
    }
}