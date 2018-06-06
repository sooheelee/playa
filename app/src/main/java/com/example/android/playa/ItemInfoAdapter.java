package com.example.android.playa;

import android.app.Activity;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link ItemInfoAdapter} is an (@link ArrayAdapter} that provides the layout for each list item composed of
 * multiple data sources defined by {@link ItemInfo} objects.
 */
public class ItemInfoAdapter extends ArrayAdapter<ItemInfo> {
    public ItemInfoAdapter(Activity context, ArrayList<ItemInfo> word) {
        super(context, 0, word);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the (@link Word} object in the array
        ItemInfo currentWord = getItem(position);
 //       Log.i("ItemInfoAdapter", currentWord.toString());

        // Find and populate the TextView for the Miwok word
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.item_title);
        miwokTextView.setText(currentWord.getTitle());

        // Find and populate the TextView for the default language word that translates the Miwok word
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.item_additional_info);
        defaultTextView.setText(currentWord.getByLine());

        TextView lengthTextView = (TextView) listItemView.findViewById(R.id.item_length);
        lengthTextView.setText(currentWord.getLength());

        TextView nowPlayingTextView = (TextView) listItemView.findViewById(R.id.now_playing_detail1);

//        ImageView iconView = listItemView.findViewById(R.id.word_icon);
//        iconView.setImageResource(currentWord.getImageResourceID());

        // Return the whole list item layout of 2 TextViews
        return listItemView;
    }
}