package com.neirx.neirdialogs.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.Statical;
import com.neirx.neirdialogs.enums.TextStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Waide Shery on 25.07.2015.
 */
public class MultiChoiceAdapter extends BaseAdapter {
    private LayoutInflater lInflater;
    private Context context;
    //private CheckBox checkBox;
    private List<String> listItems;
    List<Integer> listCheckedItemsPos;
    private int textColor;
    private float textSize;
    private TextStyle textStyle;
    private Typeface textTypeface;
    @DrawableRes
    private int flagSelector, bgSelector;

    public Integer[] getCheckedItemsPos(){
        if(listCheckedItemsPos == null){
            return null;
        }
        return listCheckedItemsPos.toArray(new Integer[listCheckedItemsPos.size()]);
    }

    public MultiChoiceAdapter(List<String> listItems, int[] checkedItemsPos, Context context, int textColor, float textSize,
                              TextStyle textStyle, Typeface textTypeface, int flagSelector, int bgSelector) {
        this.listItems = listItems;
        this.listCheckedItemsPos = new ArrayList<>();
        if (checkedItemsPos != null) {
            addToCheckedList(checkedItemsPos);
        }
        this.context = context;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.textColor = textColor;
        this.textSize = textSize;
        this.textStyle = textStyle;
        this.textTypeface = textTypeface;
        this.flagSelector = flagSelector;
        this.bgSelector = bgSelector;
    }

    private void addToCheckedList(int[] checkedItemsPos) {
        for (int item : checkedItemsPos) {
            if (!listCheckedItemsPos.contains(item)) {
                listCheckedItemsPos.add(item);
            }
        }
    }

    private void addToCheckedList(int itemPos) {
        if (!listCheckedItemsPos.contains(itemPos)) {
            listCheckedItemsPos.add(itemPos);
        }
    }

    private void removeFromCheckedList(Integer itemPos) {
        if (listCheckedItemsPos.contains(itemPos)) {
            listCheckedItemsPos.remove(itemPos);
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.adapter_multichoice, parent, false);
        }
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        checkBox.setText(listItems.get(position));
        checkBox.setTextColor(textColor);
        checkBox.setTextSize(textSize);
        checkBox.setTypeface(textTypeface, textStyle.getValue());
        checkBox.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(flagSelector), null);
        checkBox.setBackgroundResource(bgSelector);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Statical.TAG, "" + position);
                if (checkBox.isChecked()) {
                    addToCheckedList(position);
                } else {
                    removeFromCheckedList(position);
                }

            }
        });
        checkBox.setChecked(listCheckedItemsPos.contains(position));
        return view;
    }
}
