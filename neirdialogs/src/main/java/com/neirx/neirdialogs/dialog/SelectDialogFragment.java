package com.neirx.neirdialogs.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
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
import com.neirx.neirdialogs.helper.ChoiceItem;


public class SelectDialogFragment extends ListDialogFragment {
    protected ListView lvChoice;
    protected ChoiceItem[] items;
    boolean isMultiChoice;

    public void setChoiceMode(boolean isMultiChoice){
        this.isMultiChoice = isMultiChoice;
    }

    public void setItems(ChoiceItem[] items) {
        this.items = items;
    }


    protected void checkList() {
        if(items == null){
            items = new ChoiceItem[]{new ChoiceItem("", false)};
        }
        BaseAdapter adapter;
        if(isMultiChoice) {
            adapter = new MultiChoiceAdapter(items);
        } else {
            adapter = new SingleChoiceAdapter(items);
        }
        lvChoice.setAdapter(adapter);
    }
}
