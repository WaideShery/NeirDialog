package com.neirx.neirdialogs.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.dialog.TextStyle;
import com.neirx.neirdialogs.interfaces.ChoiceItem;

import java.util.List;

/**
 * Created by Waide Shery on 25.07.2015.
 */
public class SingleChoiceAdapter extends BaseAdapter {
    private LayoutInflater lInflater;
    private RadioButton radioButton;
    private Context context;
    private List<ChoiceItem> listItems;
    private int textColor;
    private float textSize;
    private TextStyle textStyle;
    private Typeface textTypeface;
    @DrawableRes
    private int flagSelector, bgSelector;

    public SingleChoiceAdapter(List<ChoiceItem> listItems, Context context, int textColor, float textSize,
                               TextStyle textStyle, Typeface textTypeface, int flagSelector, int bgSelector){
        this.listItems = listItems;
        this.context = context;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.textColor = textColor;
        this.textSize = textSize;
        this.textStyle = textStyle;
        this.textTypeface = textTypeface;
        this.bgSelector = bgSelector;
        this.flagSelector = flagSelector;
        this.bgSelector = bgSelector;
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
            view = lInflater.inflate(R.layout.adapter_singlechoice, parent, false);
        }
        radioButton = (RadioButton) view.findViewById(R.id.radioButton);
        ChoiceItem item = listItems.get(position);
        radioButton.setText(item.getTitle());
        radioButton.setChecked(item.isChecked());
        radioButton.setTextColor(textColor);
        radioButton.setTextSize(textSize);
        radioButton.setTypeface(textTypeface, textStyle.getValue());
        radioButton.setCompoundDrawables(context.getResources().getDrawable(flagSelector), null, null, null);
        radioButton.setBackgroundResource(bgSelector);
        return view;
    }
}
