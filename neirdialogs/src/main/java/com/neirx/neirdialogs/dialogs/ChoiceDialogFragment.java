package com.neirx.neirdialogs.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.adapters.MultiChoiceAdapter;
import com.neirx.neirdialogs.adapters.SingleChoiceAdapter;
import com.neirx.neirdialogs.enums.TextStyle;
import com.neirx.neirdialogs.interfaces.NeirDialogInterface;
import com.neirx.neirdialogs.interfaces.SingleChoiceDialog;

import java.util.List;


public class ChoiceDialogFragment extends HoloBaseDialog  implements SingleChoiceDialog {
    protected ListView lvChoice;
    protected List<String> items;
    int[] checkedItemsPos;
    protected int flagSelector;
    protected int requestCode = -1;
    BaseAdapter adapter;
    protected int itemTextColor;
    protected float itemTextSize;
    protected TextStyle itemTextStyle;
    protected Typeface itemTextTypeface;
    protected int itemTextGravity;
    protected int itemBackgroundSelector;
    protected NeirDialogInterface.OnItemClickListener onItemClickListener;
    protected int itemPaddingStart = -1, itemPaddingTop = -1, itemPaddingEnd = -1, itemPaddingBottom = -1;


    /**
     * Установка списка для адаптера ListView.
     *
     * @param items коллекция пунктов списка
     * @param checkedItemsPos позиции выбранных элементов
     */
    public void setItems(List<String> items, int... checkedItemsPos) {
        this.items = items;
        this.checkedItemsPos = checkedItemsPos;
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

    /**
     *Установка ресурса флажка переключателя.
     *
     * @param flagSelector ресурс флажка
     */
    public void setFlagSelector(@DrawableRes int flagSelector){
        this.flagSelector = flagSelector;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.holo_list_dialog, null);
        lineBtnTopHor = view.findViewById(R.id.viewTop);
        lineBtnLeftVer = view.findViewById(R.id.viewLeft);
        lineBtnRightVer = view.findViewById(R.id.viewRight);
        btnNegative = (Button) view.findViewById(R.id.btnNegative);
        btnNeutral = (Button) view.findViewById(R.id.btnNeutral);
        btnPositive = (Button) view.findViewById(R.id.btnPositive);
        layTitle = (LinearLayout) view.findViewById(R.id.layTitle);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        dividerTitle = view.findViewById(R.id.dividerTitle);
        lvChoice = (ListView) view.findViewById(R.id.lvChoice);
        layButtons = view.findViewById(R.id.layButtons);

        checkRootView();
        checkTitle();
        checkButtons();
        checkChoice();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        dismiss();
        if (onClickListener == null) {
            Toast.makeText(getActivity(),  "onClickListener == null", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = view.getId();
        int buttonId = 0;
        if (id == R.id.btnPositive) {
            buttonId = NeirDialogInterface.BUTTON_POSITIVE;
        } else if (id == R.id.btnNegative) {
            buttonId = NeirDialogInterface.BUTTON_NEGATIVE;
        } else if (id == R.id.btnNeutral) {
            buttonId = NeirDialogInterface.BUTTON_NEUTRAL;
        }

        if(isMultiChoice){
            Integer[] arrCheckedPos = ((MultiChoiceAdapter)adapter).getCheckedItemsPos();
            onClickListener.onClick(tag, buttonId, arrCheckedPos);
        } else {
            Integer checkedPos = ((SingleChoiceAdapter)adapter).getCheckedItemPos();
            onClickListener.onClick(tag, buttonId, checkedPos);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    protected void checkList() {
        if(isMultiChoice) {
            adapter = new MultiChoiceAdapter(items, checkedItemsPos, getActivity(), itemTextColor, itemTextSize, itemTextStyle,
                    itemTextTypeface, flagSelector, itemBackgroundSelector);
        } else {
            adapter = new SingleChoiceAdapter(items, checkedItemsPos, getActivity(), itemTextColor, itemTextSize, itemTextStyle,
                    itemTextTypeface, flagSelector, itemBackgroundSelector);
        }
        lvChoice.setAdapter(adapter);
    }
}
