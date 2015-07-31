package com.neirx.neirdialogs;


import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.util.Log;

import com.neirx.neirdialogs.dialog.BaseDialogFragment;
import com.neirx.neirdialogs.dialog.ListDialogFragment;
import com.neirx.neirdialogs.dialog.MessageDialogFragment;
import com.neirx.neirdialogs.dialog.SelectDialogFragment;
import com.neirx.neirdialogs.dialog.TextStyle;


public abstract class DialogCreator {
    enum DialogStyle {Holo, Material}

    int titleColor, dividerTitleColor, dividerTitleHeight, buttonTextColor,
            buttonTextSize, dividerButtonsColor, backgroundDialogColor, messageColor;
    float titleSize, messageSize;
    Typeface titleTypeface, buttonTextTypeface;
    TextStyle titleStyle, buttonTextStyle;
    Context context;
    @DrawableRes
    int dividerTitleResId, buttonSelectorId, backgroundDialogResId;


    protected DialogCreator(Context context) {
        this.context = context;

        backgroundDialogColor = context.getResources().getColor(R.color.dialog_background);
        backgroundDialogResId = 0;

        titleColor = context.getResources().getColor(R.color.holo_blue);
        titleSize = 16;
        titleTypeface = Typeface.DEFAULT;
        titleStyle = TextStyle.NORMAL;

        dividerTitleColor = context.getResources().getColor(R.color.holo_blue);
        dividerTitleResId = 0;
        dividerTitleHeight = 2;

        buttonTextColor = context.getResources().getColor(R.color.holo_dark_text);
        buttonTextSize = 12;
        buttonTextTypeface = Typeface.DEFAULT;
        buttonTextStyle = TextStyle.NORMAL;
        buttonSelectorId = R.drawable.holo_btn_selector;
        dividerButtonsColor = context.getResources().getColor(R.color.button_border);
    }

    public ListDialogFragment getListDialog() {
        return new ListDialogFragment();
    }

    public SelectDialogFragment getSelectDialog(boolean isMultiChoice) {
        SelectDialogFragment dialog = new SelectDialogFragment();
        dialog.setChoiceMode(isMultiChoice);
        return dialog;
    }

    public void getDatePickerDialog() {

    }

    public void getTimePickerDialog() {

    }

    public MessageDialogFragment getMessageDialog() {
        MessageDialogFragment dialog = new MessageDialogFragment();
        setBaseProperties(dialog);
        return dialog;
    }


    private void setBaseProperties(BaseDialogFragment dialog) {
        dialog.setDialogBackgroundColor(backgroundDialogColor);
        dialog.setDialogBackground(backgroundDialogResId);

        dialog.setTitleColor(titleColor);
        dialog.setTitleSize(titleSize);
        dialog.setTitleTypeface(titleTypeface, titleStyle);

        dialog.setDividerTitleColor(dividerTitleColor);
        dialog.setDividerTitleBackground(dividerTitleResId);
        dialog.setDividerTitleHeight(dividerTitleHeight);

        dialog.setButtonsTextColor(buttonTextColor);
        dialog.setButtonsTextSize(buttonTextSize);
        dialog.setButtonsTypeface(buttonTextTypeface, buttonTextStyle);
        dialog.setButtonsBackground(buttonSelectorId);
        dialog.setDividerButtonsColor(dividerButtonsColor);
    }
}
