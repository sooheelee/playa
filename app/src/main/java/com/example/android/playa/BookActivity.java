package com.example.android.playa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_list);

        final ArrayList<ItemInfo> wordsArrayList = new ArrayList<>();
        wordsArrayList.add(new ItemInfo(getString(R.string.book_01_title), getString(R.string.book_artist), getString(R.string.book_01_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_02_title), getString(R.string.book_artist), getString(R.string.book_02_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_03_title), getString(R.string.book_artist), getString(R.string.book_03_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_04_title), getString(R.string.book_artist), getString(R.string.book_04_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_05_title), getString(R.string.book_artist), getString(R.string.book_05_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_06_title), getString(R.string.book_artist), getString(R.string.book_06_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_07_title), getString(R.string.book_artist), getString(R.string.book_07_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_08_title), getString(R.string.book_artist), getString(R.string.book_08_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_09_title), getString(R.string.book_artist), getString(R.string.book_09_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_10_title), getString(R.string.book_artist), getString(R.string.book_10_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_11_title), getString(R.string.book_artist), getString(R.string.book_11_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_12_title), getString(R.string.book_artist), getString(R.string.book_12_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_13_title), getString(R.string.book_artist), getString(R.string.book_13_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_14_title), getString(R.string.book_artist), getString(R.string.book_14_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_15_title), getString(R.string.book_artist), getString(R.string.book_15_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_16_title), getString(R.string.book_artist), getString(R.string.book_16_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_17_title), getString(R.string.book_artist), getString(R.string.book_17_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_18_title), getString(R.string.book_artist), getString(R.string.book_18_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_19_title), getString(R.string.book_artist), getString(R.string.book_19_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_20_title), getString(R.string.book_artist), getString(R.string.book_20_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_21_title), getString(R.string.book_artist), getString(R.string.book_21_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_22_title), getString(R.string.book_artist), getString(R.string.book_22_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_23_title), getString(R.string.book_artist), getString(R.string.book_23_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.book_24_title), getString(R.string.book_artist), getString(R.string.book_24_length)));

        ItemInfoAdapter adapter = new ItemInfoAdapter(this, wordsArrayList);
        final ListView listView = (ListView) findViewById(R.id.list);

        TextView headerToListView = new TextView(this);

        headerToListView.setText(R.string.book_info);
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

                MainActivity.itemSelectedInfo= wordsArrayList.get(position-1).getByLine();
                MainActivity.itemSelectedDetail1 = wordsArrayList.get(position-1).getTitle();
                MainActivity.itemSelectedDetail2 = wordsArrayList.get(position-1).getLength();

                // Remember to remove
                Log.i("BookActivity onItemClick",   MainActivity.itemSelectedInfo + " " + MainActivity.itemSelectedDetail1  + " " + MainActivity.itemSelectedDetail2);

//                startActivity(new Intent(BookActivity.this, MainActivity.class));
            }
        });
    }
}
