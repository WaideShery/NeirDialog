package com.neirx.neirdialogs.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.adapter.MultiChoiceAdapter;
import com.neirx.neirdialogs.adapter.SingleChoiceAdapter;
import com.neirx.neirdialogs.interfaces.ChoiceItem;

import java.util.List;


public class SelectDialogFragment extends ListDialogFragment {
    protected ListView lvChoice;
    protected List<ChoiceItem> items;
    protected boolean isMultiChoice;
    protected int flagSelector, itemBackgroundSelector;
    protected SelectItemListener checkedItemListener = null;
    protected int requestCode = -1;

    public interface SelectItemListener {
        void onFinishSelectDialog(int requestCode, boolean[] checkedItems);
    }



    /**
     * Установка вида списка ListView: одиночный выбор или множественный.
     *
     * @param isMultiChoice true - множественный выбор.
     */
    public void setChoiceMode(boolean isMultiChoice){
        this.isMultiChoice = isMultiChoice;
    }

    /**
     * Установка списка для адаптера ListView.
     *
     * @param items коллекция объектов имплементирующих интерфейс ChoiceItem.
     */
    public void setItems(List<ChoiceItem> items) {
        this.items = items;
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
        lineBtnFirst = view.findViewById(R.id.viewHorFirst);
        lineBtnSecond = view.findViewById(R.id.viewHorSecond);
        btnNegative = (Button) view.findViewById(R.id.btnNegative);
        btnNeutral = (Button) view.findViewById(R.id.btnNeutral);
        btnPositive = (Button) view.findViewById(R.id.btnPositive);
        layTitle = (LinearLayout) view.findViewById(R.id.layTitle);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        dividerTitle = view.findViewById(R.id.dividerTitle);
        lvChoice = (ListView) view.findViewById(R.id.lvChoice);
        layButtons = view.findViewById(R.id.layButtons);

        checkDialogBackground();
        checkTitle();
        checkButtons();
        checkList();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {

    }

    protected void checkList() {
        BaseAdapter adapter;
        if(isMultiChoice) {
            adapter = new MultiChoiceAdapter(items, getActivity(), itemTextColor, itemTextSize, itemTextStyle,
                    itemTextTypeface, flagSelector, itemBackgroundSelector, checkedItemListener, requestCode);
        } else {
            adapter = new SingleChoiceAdapter(items, getActivity(), itemTextColor, itemTextSize, itemTextStyle,
                    itemTextTypeface, flagSelector, itemBackgroundSelector, checkedItemListener, requestCode);
        }
        lvChoice.setAdapter(adapter);
    }
}
