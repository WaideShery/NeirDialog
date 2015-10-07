package com.neirx.neirdialogs.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.interfaces.SingleChoiceDialog;

import java.util.List;

/**
 * Created by Waide Shery on 07.10.2015.
 *
 */
public class HoloSingleChoiceDialog extends HoloBaseDialog implements SingleChoiceDialog {
    protected ListView lvChoice;
    protected List<String> items;
    protected boolean isMultiChoice;
    protected int flagSelector, itemBackgroundSelector;
    protected int requestCode = -1;
    int[] checkedItemsPos;
    BaseAdapter adapter;

    /**
     * Установка списка для адаптера ListView.
     *
     * @param items коллекция объектов имплементирующих интерфейс.
     */
    public void setItems(List<String> items, int... checkedItemsPos) {
        this.items = items;
        this.checkedItemsPos = checkedItemsPos;
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
        layButtons = (ViewGroup) view.findViewById(R.id.layButtons);

        checkRootView();
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
}
