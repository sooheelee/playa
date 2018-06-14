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

public class RadioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_list);

        final ArrayList<ItemInfo> wordsArrayList = new ArrayList<>();
        wordsArrayList.add(new ItemInfo(getString(R.string.radio_01_title), getString(R.string.radio_01_artist), getString(R.string.radio_01_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.radio_02_title), getString(R.string.radio_02_artist), getString(R.string.radio_02_length)));

        final ItemInfoAdapter adapter = new ItemInfoAdapter(this, wordsArrayList);
        final ListView listView = (ListView) findViewById(R.id.list);

        TextView headerToListView = new TextView(this);

        headerToListView.setText(R.string.radio_info);
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

                MainActivity.mediaTag = 3;
                MainActivity.RadioSelection= wordsArrayList.get(position-1).getTitle();
                MainActivity.RadioSelectionDetail1 = wordsArrayList.get(position-1).getByLine();
                MainActivity.RadioSelectionDetail2 = wordsArrayList.get(position-1).getLength();

                // Remember to remove
                Log.i("RadioActivity",   MainActivity.RadioSelection + " " + MainActivity.RadioSelectionDetail1  + " " + MainActivity.RadioSelectionDetail2);
//                startActivity(new Intent(RadioActivity.this, MainActivity.class));
            }
        });
    }
}
