package com.neirx.neirdialogs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.interfaces.ChoiceItem;

import java.util.List;

/**
 * Created by Waide Shery on 25.07.2015.
 */
public class MultiChoiceAdapter extends BaseAdapter {
    LayoutInflater lInflater;
    TextView textView;
    CheckBox checkBox;
    List<ChoiceItem> listItems;

    public MultiChoiceAdapter(List<ChoiceItem> listItems, Context context){
        this.listItems = listItems;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
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

        for (ChoiceItem item : listItems){
            textView.setText(item.getTitle());
            checkBox.setChecked(item.isChecked());
        }
        return view;
    }
}
