package com.neirx.neirdialogs.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.adapters.ListAdapter;
import com.neirx.neirdialogs.enums.TextStyle;
import com.neirx.neirdialogs.interfaces.ListDialog;
import com.neirx.neirdialogs.interfaces.NeirDialogInterface;


public class HoloListDialog extends HoloRootDialog implements ListDialog, AdapterView.OnItemClickListener {
    protected ListView lvChoice;
    protected String[] items;
    protected int itemTextColor;
    protected float itemTextSize;
    protected TextStyle itemTextStyle;
    protected Typeface itemTextTypeface;
    protected int itemTextGravity;
    protected int itemBackgroundSelector;
    protected NeirDialogInterface.OnItemClickListener onItemClickListener;
    protected int itemPaddingStart = -1, itemPaddingTop = -1, itemPaddingEnd = -1, itemPaddingBottom = -1;

    /**
     * Установка пунктов списка.
     * @param items массив строк с пунктами
     */
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
     * Установка выравнивания текста пунктов списка.
     *
     * @param gravity выравнивание {@link android.view.Gravity}
     */
    public void setItemTextGravity(int gravity) {
        itemTextGravity = gravity;
    }

    /**
     * Установка отступов пунктов списка в dp.
     *
     * @param start  начало
     * @param top    верх
     * @param end    конец
     * @param bottom низ
     */
    public void setItemTextPaddingDP(int start, int top, int end, int bottom) {
        itemPaddingStart = start;
        itemPaddingTop = top;
        itemPaddingEnd = end;
        itemPaddingBottom = bottom;
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

        lvChoice = (ListView) view.findViewById(R.id.lvChoice);

        checkRootView();
        checkTitle();
        checkList();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }


    /**
     * Метод обработки нажатий на пункты ListView.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        dismiss();
        if(onItemClickListener == null){
            return;
        }
        onItemClickListener.onItemClick(tag, i);
    }

    /**
     * Метод для установки Listener'а на ListView.
     */
    public  void setOnItemClickListener(NeirDialogInterface.OnItemClickListener listener, String tag){
        onItemClickListener = listener;
        this.tag = tag;
    }

    /**
     * Установка параметров пусктов списка.
     */
    protected void checkList() {
        boolean noItems = false;
        if(items == null){
            items = new String[]{""};
            noItems = true;
        }
        ListAdapter adapter = new ListAdapter(items, getActivity());
        adapter.setItemParam(itemTextColor, itemTextSize, itemTextStyle, itemTextTypeface, itemTextGravity, itemBackgroundSelector);
        adapter.setItemTextPadding(itemPaddingStart, itemPaddingTop, itemPaddingEnd, itemPaddingBottom);
        lvChoice.setAdapter(adapter);
        if(noItems){
            lvChoice.setClickable(false);
        } else {
            lvChoice.setOnItemClickListener(this);
        }
    }

}
