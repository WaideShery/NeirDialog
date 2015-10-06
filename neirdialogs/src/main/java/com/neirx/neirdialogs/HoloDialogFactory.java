package com.neirx.neirdialogs;


import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.util.Log;

import com.neirx.neirdialogs.dialog.HoloRootDialog;
import com.neirx.neirdialogs.dialog.EditTextDialogFragment;
import com.neirx.neirdialogs.dialog.HoloListDialog;
import com.neirx.neirdialogs.dialog.HoloMessageDialog;
import com.neirx.neirdialogs.dialog.ChoiceDialogFragment;
import com.neirx.neirdialogs.enums.TextStyle;
import com.neirx.neirdialogs.interfaces.DialogFactory;
import com.neirx.neirdialogs.interfaces.ListDialog;
import com.neirx.neirdialogs.interfaces.MessageDialog;
import com.neirx.neirdialogs.interfaces.MultiChoiceDialog;
import com.neirx.neirdialogs.interfaces.SingleChoiceDialog;


public class HoloDialogFactory implements DialogFactory {
    protected enum DialogStyle {Holo, Material}

    protected int titleColor, dividerTitleColor, dividerTitleHeight, buttonTextColor, buttonTextSize,
            dividerButtonsColor, backgroundDialogColor, messageTextColor, listItemTextColor, editTextColor, hintTextColor;

    protected float titleSize, messageTextSize, listItemTextSize, editTextSize;

    protected Typeface titleTypeface, buttonTextTypeface, messageTextTypeface, listItemTextTypeface,
            editTextTypeface;

    protected TextStyle titleStyle, buttonTextStyle, messageTextStyle, listItemTextStyle, editTextStyle;

    protected Context context;

    @DrawableRes
    protected int dividerTitleResId, buttonSelectorId, backgroundDialogResId, listItemBackgroundSelector,
            singleFlagSelector, multiFlagSelector;


    protected HoloDialogFactory(Context context) {
        this.context = context;

        backgroundDialogColor = context.getResources().getColor(R.color.dialog_background);
        backgroundDialogResId = 0;

        titleColor = context.getResources().getColor(R.color.holo_blue);
        titleSize = 18;
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

        messageTextColor = context.getResources().getColor(R.color.holo_dark_text);
        messageTextSize = 16;
        messageTextTypeface = Typeface.DEFAULT;
        messageTextStyle = TextStyle.NORMAL;


        editTextColor = context.getResources().getColor(R.color.holo_dark_edit_text);
        editTextSize = 16;
        editTextTypeface = Typeface.DEFAULT;
        editTextStyle = TextStyle.NORMAL;
        hintTextColor = context.getResources().getColor(R.color.holo_dark_edit_hint);

        listItemTextColor = context.getResources().getColor(R.color.holo_dark_text);
        listItemTextSize = 16;
        listItemTextTypeface = Typeface.DEFAULT;
        listItemTextStyle = TextStyle.NORMAL;
        listItemBackgroundSelector = R.drawable.holo_list_item_selector;

        singleFlagSelector = R.drawable.holo_radiobtn_selector;
        multiFlagSelector = R.drawable.holo_checkbox_selector;
    }

    public ListDialog createListDialog() {
        HoloListDialog dialog = new HoloListDialog();
        setBaseProperties(dialog);
        setListItemProperties(dialog);
        return dialog;
    }


    public ChoiceDialogFragment getChoiceDialog(boolean isMultiChoice) {
        ChoiceDialogFragment dialog = new ChoiceDialogFragment();
        setBaseProperties(dialog);
        setListItemProperties(dialog);
        dialog.setMultiChoiceMode(isMultiChoice);
        if(isMultiChoice){
            dialog.setFlagSelector(multiFlagSelector);
        } else {
            dialog.setFlagSelector(singleFlagSelector);

        }
        return dialog;
    }

    public SingleChoiceDialog createSingleChoiceDialog() {
        return null;
    }

    public MultiChoiceDialog createMultiChoiceDialog() {
        return  null;
    }

    public MessageDialog createMessageDialog() {
        HoloMessageDialog dialog = new HoloMessageDialog();
        setBaseProperties(dialog);
        dialog.setMessageColor(messageTextColor);
        dialog.setMessageSize(messageTextSize);
        dialog.setMessageTypeface(messageTextTypeface, messageTextStyle);
        return dialog;
    }

    public EditTextDialogFragment getEditTextDialog(){
        EditTextDialogFragment dialog = new EditTextDialogFragment();
        setBaseProperties(dialog);
        dialog.setEditTextSize(editTextSize);
        dialog.setEditTextColor(editTextColor);
        dialog.setEditTextTypeface(editTextTypeface, editTextStyle);
        dialog.setHintTextColor(hintTextColor);
        return dialog;
    }

    private void setListItemProperties(HoloListDialog dialog) {
        dialog.setItemBackgroundSelector(listItemBackgroundSelector);
        dialog.setItemTextColor(listItemTextColor);
        dialog.setItemTextSize(listItemTextSize);
        dialog.setItemTextTypeface(listItemTextTypeface, listItemTextStyle);
    }

    private void setBaseProperties(HoloRootDialog dialog) {
        Log.d(Statical.TAG, "setBaseProperties");
        dialog.setDialogBackgroundColor(backgroundDialogColor);
        dialog.setDialogBackground(backgroundDialogResId);

        dialog.setTitleColor(titleColor);
        dialog.setTitleSize(titleSize);
        dialog.setTitleTypeface(titleTypeface, titleStyle);

        dialog.setDividerTitleColor(dividerTitleColor);
        dialog.setDividerTitleBackground(dividerTitleResId);
        dialog.setDividerTitleWidth(dividerTitleHeight);

        dialog.setButtonsTextColor(buttonTextColor);
        dialog.setButtonsTextSize(buttonTextSize);
        dialog.setButtonsTypeface(buttonTextTypeface, buttonTextStyle);
        dialog.setButtonsBackground(buttonSelectorId);
        dialog.setDividerButtonsColor(dividerButtonsColor);
    }
}
