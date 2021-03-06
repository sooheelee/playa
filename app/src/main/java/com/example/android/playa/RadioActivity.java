package com.example.android.playa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RadioActivity extends AppCompatActivity {

    /**
     * Creates radio activity and displays details for the preloaded radio stations, here two Boston public radio stations.
     * Saves last selection to shared preferences.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_list);

        final ArrayList<ItemInfo> wordsArrayList = new ArrayList<>();
        wordsArrayList.add(new ItemInfo(getString(R.string.radio_01_title), getString(R.string.radio_01_artist), getString(R.string.radio_01_length)));
        wordsArrayList.add(new ItemInfo(getString(R.string.radio_02_title), getString(R.string.radio_02_artist), getString(R.string.radio_02_length)));

        final ItemInfoAdapter adapter = new ItemInfoAdapter(this, wordsArrayList);
        final ListView listView = findViewById(R.id.list);

        TextView headerToListView = new TextView(this);
        headerToListView.setText(R.string.radio_info);

        final int spToPixels = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.media_header_text_size);
        headerToListView.setTextSize(spToPixels);
        headerToListView.setTextColor(getResources().getColor(R.color.colorWhite));
        headerToListView.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));

        final int dpToPixelsLeft = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.media_header_padding_left);
        final int dpToPixelsRight = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.media_header_padding_right);
        final int dpToPixelsTop = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.media_header_padding_top);
        final int dpToPixelsBottom = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.media_header_padding_bottom);
        headerToListView.setPadding(dpToPixelsLeft, dpToPixelsTop, dpToPixelsRight, dpToPixelsBottom);

        listView.setAdapter(adapter);
        listView.addHeaderView(headerToListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                MainActivity.mediaTag = 3;
                MainActivity.RadioSelection = wordsArrayList.get(position - 1).getTitle();
                MainActivity.RadioSelectionDetail1 = wordsArrayList.get(position - 1).getByLine();
                MainActivity.RadioSelectionDetail2 = wordsArrayList.get(position - 1).getLength();
            }
        });
    }
}
