package com.neirx.neirdialogs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.helper.ChoiceItem;

/**
 * Created by Waide Shery on 25.07.2015.
 */
public class MultiChoiceAdapter extends BaseAdapter {
    LayoutInflater lInflater;
    TextView textView;
    CheckBox checkBox;
    ChoiceItem[] choiceItems;

    public MultiChoiceAdapter(ChoiceItem[] choiceItems){
        this.choiceItems = choiceItems;
    }

    @Override
    public int getCount() {
        return choiceItems.length;
    }

    @Override
    public Object getItem(int position) {
        return choiceItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.adapter_multichoice, parent, false);
        }
        textView = (TextView) view.findViewById(R.id.textView);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        for (ChoiceItem item : choiceItems){
            textView.setText(item.getTitle());
            checkBox.setChecked(item.isChecked());
        }
        return view;
    }
}
