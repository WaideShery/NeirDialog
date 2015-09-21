package com.neirx.neirdialogs.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.dialog.TextStyle;

/**
 * Created by Waide Shery on 25.07.2015.
 *
 */
public class ListChoiceAdapter extends BaseAdapter {
    LayoutInflater lInflater;
    TextView textView;
    final private String[] items;
    final private int itemTextColor;
    final private float itemTextSize;
    final private TextStyle itemTextStyle;
    final private Typeface itemTextTypeface;
    final private int itemBackgroundSelector;

    public ListChoiceAdapter(String[] items, Context context, int itemTextColor, float itemTextSize,
            TextStyle itemTextStyle, Typeface itemTextTypeface, int itemBackgroundSelector){
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
        this.itemTextColor = itemTextColor;
        this.itemTextSize = itemTextSize;
        this.itemTextStyle = itemTextStyle;
        this.itemTextTypeface = itemTextTypeface;
        this.itemBackgroundSelector = itemBackgroundSelector;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.adapter_listchoice, parent, false);
        }
        textView = (TextView) view.findViewById(R.id.textView);

        textView.setText(items[position]);
        textView.setTextColor(itemTextColor);
        textView.setTextSize(itemTextSize);
        textView.setTypeface(itemTextTypeface, itemTextStyle.getValue());

        view.setBackgroundResource(itemBackgroundSelector);
        return view;
    }
}
