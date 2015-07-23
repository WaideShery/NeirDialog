package com.neirx.neirdialog.base;


import android.content.Context;
import android.graphics.Color;

import com.neirx.neirdialog.R;

public class DialogCreator {
    enum DialogStyle{Holo, Material}

    DialogStyle style;
    int backgroundWindow, titleColor, textColor, dividerColor, editFocusColor, editNonFocusColor;
    Context context;


    private DialogCreator(Context context) {
        this.context = context;
    }

    protected void setStyle(){
        style = DialogStyle.Holo;
        backgroundWindow = context.getResources().getColor(R.color.holo_green_light);
        titleColor = context.getResources().getColor(R.color.holo_green_light);
        textColor = context.getResources().getColor(R.color.dark_grey);
        dividerColor = titleColor;
        editFocusColor = titleColor;
        editNonFocusColor = context.getResources().getColor(R.color.holo_green_dark);
    }

    public void getListDialog(Context context){

    }

    public void getDatePickerDialog(){

    }

    public void getTimePickerDialog(){

    }

    public void getSimpleDialog(){

    }
}
