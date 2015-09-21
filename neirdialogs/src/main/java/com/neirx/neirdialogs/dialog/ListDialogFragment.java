package com.neirx.neirdialogs.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.adapter.ListChoiceAdapter;
import com.neirx.neirdialogs.interfaces.NeirDialogInterface;


public class ListDialogFragment extends BaseDialogFragment implements AdapterView.OnItemClickListener {
    protected ListView lvChoice;
    protected String[] items;
    protected int itemTextColor;
    protected float itemTextSize;
    protected TextStyle itemTextStyle;
    protected Typeface itemTextTypeface;
    protected int itemBackgroundSelector;
    protected NeirDialogInterface.OnItemClickListener onItemClickListener;

    public void setItems(String[] items) {
        this.items = items;
    }

    /**
     * Установка цвета текста пунктов списка.
     *
     * @param color ресурс цвета
     */
    public void setItemTextColor(int color) {
        itemTextColor = color;
    }

    /**
     * Установка размера шрифта текста пунктов списка.
     *
     * @param sizeSp размер шрифта
     */
    public void setItemTextSize(float sizeSp) {
        itemTextSize = sizeSp;
    }

    /**
     * Установка шрифта и стиля отображения текста пунктов списка.
     *
     * @param tf    шрифт
     * @param style стиль текста
     */
    public void setItemTextTypeface(Typeface tf, TextStyle style) {
        itemTextTypeface = tf;
        itemTextStyle = style;
    }

    /**
     * Установка селектора фона пунктов списка.
     *
     * @param itemBackgroundSelector селектор фона пунктов списка.
     */
    public void setItemBackgroundSelector(@DrawableRes int itemBackgroundSelector){
        this.itemBackgroundSelector = itemBackgroundSelector;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.holo_list_dialog, null);

        layTitle = (LinearLayout) view.findViewById(R.id.layTitle);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        dividerTitle = view.findViewById(R.id.dividerTitle);
        lineBtnFirst = view.findViewById(R.id.viewHorFirst);
        lineBtnSecond = view.findViewById(R.id.viewHorSecond);
        layButtons = view.findViewById(R.id.layButtons);
        layButtons.setVisibility(View.GONE);
        lvChoice = (ListView) view.findViewById(R.id.lvChoice);

        checkTitle();
        checkList();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {}

    @Override
    public void setOnClickListener(NeirDialogInterface.OnClickListener listener, String tag) {}

    public  void setOnItemClickListener(NeirDialogInterface.OnItemClickListener listener, String tag){
        onItemClickListener = listener;
        this.tag = tag;
    }

    protected void checkList() {
        boolean noItems = false;
        if(items == null){
            items = new String[]{""};
            noItems = true;
        }
        BaseAdapter adapter = new ListChoiceAdapter(items, getActivity(), itemTextColor, itemTextSize, itemTextStyle,
                itemTextTypeface, itemBackgroundSelector);
        lvChoice.setAdapter(adapter);
        if(noItems){
            lvChoice.setClickable(false);
        } else {
            lvChoice.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        dismiss();
        if(onItemClickListener == null){
            return;
        }
        onItemClickListener.onItemClick(tag, i);
    }
}
