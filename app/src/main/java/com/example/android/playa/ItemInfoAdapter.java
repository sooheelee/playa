package com.example.android.playa;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

        ItemInfo currentItem = getItem(position);
        TextView itemTextView = listItemView.findViewById(R.id.item_title);
        itemTextView.setText(currentItem.getTitle());
        TextView defaultTextView = listItemView.findViewById(R.id.item_additional_info);
        defaultTextView.setText(currentItem.getByLine());
        TextView lengthTextView = listItemView.findViewById(R.id.item_length);
        lengthTextView.setText(currentItem.getLength());
        return listItemView;
    }
}
