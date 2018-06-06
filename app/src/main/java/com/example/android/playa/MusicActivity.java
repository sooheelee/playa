package com.example.android.playa;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    private View currentSelectedView;
    private View lastSelectedView;

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
        final ListView listView = (ListView) findViewById(R.id.list);

        TextView headerToListView = new TextView(this);

        headerToListView.setText(R.string.song_info);
        //these need to pull from a dimens file
        headerToListView.setTextSize(16);
        //these need conversion to dp
        headerToListView.setPadding(36,8,16,8);

        listView.setAdapter(adapter);
        listView.addHeaderView(headerToListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);

                lastSelectedView = view;
                currentSelectedView = view;

                Log.i("viewthatisselected", view.toString());

                MainActivity.itemSelectedInfo = getString(R.string.song_info);
                MainActivity.itemSelectedDetail1 = wordsArrayList.get(position-1).getTitleAndArtist();
                MainActivity.itemSelectedDetail2 = wordsArrayList.get(position-1).getLength();

                // Remember to remove
                Log.i("MusicActivityClick",   MainActivity.itemSelectedInfo + " " + MainActivity.itemSelectedDetail1  + " " + MainActivity.itemSelectedDetail2);

                selectionColoring(currentSelectedView);
//                startActivity(new Intent(MusicActivity.this, MainActivity.class));
           }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            setContentView(R.layout.activity_main);
            lastSelectedColoring(lastSelectedView);
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    private void lastSelectedColoring(View listViewItem) {
        listViewItem.setBackgroundColor(getResources().getColor(R.color.colorGray));
        TextView textView1 = listViewItem.findViewById(R.id.item_title);
        TextView textView2 = listViewItem.findViewById(R.id.item_additional_info);
        TextView textView3 = listViewItem.findViewById(R.id.item_length);
        textView1.setTextColor(getResources().getColor(R.color.colorAccent));
        textView2.setTextColor(getResources().getColor(R.color.colorAccent));
        textView3.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void selectionColoring(View listViewItem) {
        listViewItem.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        TextView textView1 = listViewItem.findViewById(R.id.item_title);
        TextView textView2 = listViewItem.findViewById(R.id.item_additional_info);
        TextView textView3 = listViewItem.findViewById(R.id.item_length);
        textView1.setTextColor(getResources().getColor(R.color.colorWhite));
        textView2.setTextColor(getResources().getColor(R.color.colorWhite));
        textView3.setTextColor(getResources().getColor(R.color.colorWhite));
    }
}
