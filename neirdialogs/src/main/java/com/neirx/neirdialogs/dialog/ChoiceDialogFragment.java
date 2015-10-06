package com.neirx.neirdialogs.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
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
import com.neirx.neirdialogs.adapter.MultiChoiceAdapter;
import com.neirx.neirdialogs.adapter.SingleChoiceAdapter;
import com.neirx.neirdialogs.interfaces.NeirDialogInterface;

import java.util.List;


public class ChoiceDialogFragment extends HoloListDialog {
    protected ListView lvChoice;
    protected List<String> items;
    protected boolean isMultiChoice;
    protected int flagSelector, itemBackgroundSelector;
    protected SelectItemListener checkedItemListener = null;
    protected int requestCode = -1;
    int[] checkedItemsPos;
    BaseAdapter adapter;

    public interface SelectItemListener {
        void onFinishSelectDialog(int requestCode, boolean[] checkedItems);
    }



    /**
     * Установка вида списка ListView: одиночный выбор или множественный.
     *
     * @param isMultiChoice true - множественный выбор.
     */
    public void setMultiChoiceMode(boolean isMultiChoice){
        this.isMultiChoice = isMultiChoice;
    }

    /**
     * Установка списка для адаптера ListView.
     *
     * @param items коллекция объектов имплементирующих интерфейс.
     */
    public void setItems(List<String> items, int... checkedItemsPos) {
        this.items = items;
        this.checkedItemsPos = checkedItemsPos;
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
