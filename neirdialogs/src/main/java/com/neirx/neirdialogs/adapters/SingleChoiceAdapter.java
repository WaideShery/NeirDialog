package com.neirx.neirdialogs.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.enums.TextStyle;

import java.util.List;

/**
 * Created by Waide Shery on 25.07.2015.
 *
 */
public class SingleChoiceAdapter extends BaseAdapter {
    private LayoutInflater lInflater;
    private RadioButton radioButton;
    private Context context;
    private List<String> listItems;
    private int checkedItemPos;
    private int textColor;
    private float textSize;
    private TextStyle textStyle;
    private Typeface textTypeface;
    @DrawableRes
    private int flagSelector, bgSelector;

    public SingleChoiceAdapter(List<String> listItems, int[] checkedItemsPos, Context context, int textColor, float textSize,
                               TextStyle textStyle, Typeface textTypeface, int flagSelector, int bgSelector){
        this.listItems = listItems;
        this.checkedItemPos = checkedItemsPos[0];
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

    public int getCheckedItemPos() {
        return checkedItemPos;
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
            view = lInflater.inflate(R.layout.adapter_singlechoice, parent, false);
        }
        radioButton = (RadioButton) view.findViewById(R.id.radioButton);
        radioButton.setText(listItems.get(position));
        radioButton.setChecked(checkedItemPos == position);
        radioButton.setTextColor(textColor);
        radioButton.setTextSize(textSize);
        radioButton.setTypeface(textTypeface, textStyle.getValue());
        radioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(flagSelector), null);
        radioButton.setBackgroundResource(bgSelector);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[] arrayItemsChecked = new boolean[listItems.size()];
                for (int i = 0; i < listItems.size(); i++) {
                    if (i != position) {
                        arrayItemsChecked[i] = false;
                    } else {
                        arrayItemsChecked[i] = true;
                        checkedItemPos = i;
                    }

                }
                SingleChoiceAdapter.this.notifyDataSetChanged();
            }
        });
        return view;
    }
}
