package com.neirx.neirdialogs;


import android.content.Context;

import com.neirx.neirdialogs.dialog.ListDialogFragment;
import com.neirx.neirdialogs.dialog.MessageDialogFragment;
import com.neirx.neirdialogs.dialog.SelectDialogFragment;


public abstract class DialogCreator {
    enum DialogStyle{Holo, Material}

    DialogStyle style;
    int backgroundWindow, titleColor, textColor, dividerColor, editFocusColor, editNonFocusColor;
    Context context;


    protected DialogCreator(Context context) {
        this.context = context;
        style = DialogStyle.Holo;
        //backgroundWindow = context.getResources().getColor(R.color.holo_green_light);
        //titleColor = context.getResources().getColor(R.color.holo_green_light);
        //textColor = context.getResources().getColor(R.color.dark_grey);
        dividerColor = titleColor;
        editFocusColor = titleColor;
        editNonFocusColor = context.getResources().getColor(R.color.holo_green_dark);
    }

    public ListDialogFragment getListDialog(){
        return new ListDialogFragment();
    }

    public SelectDialogFragment getSelectDialog(boolean isMultiChoice){
        SelectDialogFragment dialog = new SelectDialogFragment();
        dialog.setChoiceMode(isMultiChoice);
        return dialog;
    }

    public void getDatePickerDialog(){

    }

    public void getTimePickerDialog(){

    }

    public MessageDialogFragment getMessageDialog(){
        return new MessageDialogFragment();
    }
}
