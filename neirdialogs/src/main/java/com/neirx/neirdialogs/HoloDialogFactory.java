package com.neirx.neirdialogs;


import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.Gravity;
import com.neirx.neirdialogs.dialogs.HoloBaseDialog;
import com.neirx.neirdialogs.dialogs.HoloMultiChoiceDialog;
import com.neirx.neirdialogs.dialogs.HoloRootDialog;
import com.neirx.neirdialogs.dialogs.EditTextDialogFragment;
import com.neirx.neirdialogs.dialogs.HoloListDialog;
import com.neirx.neirdialogs.dialogs.HoloMessageDialog;
import com.neirx.neirdialogs.dialogs.ChoiceDialogFragment;
import com.neirx.neirdialogs.dialogs.HoloSingleChoiceDialog;
import com.neirx.neirdialogs.enums.TextStyle;
import com.neirx.neirdialogs.interfaces.DialogFactory;
import com.neirx.neirdialogs.interfaces.ListDialog;
import com.neirx.neirdialogs.interfaces.MessageDialog;
import com.neirx.neirdialogs.interfaces.MultiChoiceDialog;
import com.neirx.neirdialogs.interfaces.SingleChoiceDialog;


public class HoloDialogFactory implements DialogFactory {

    /**
     * Фон диалогового окна. Первым проверяется ресурс backgroundDialogResId, если он не равен -1, то устанавливается фоном.
     * В противном случае, фоном ставиться цвет backgroundDialogColor.
     */
    protected @DrawableRes int backgroundDialogResId;
    protected int backgroundDialogColor;

    /**
     * Фон заголовка диалогового окна. Первым проверяется ресурс layTitleBackgroundResId, если он не равен -1, то устанавливается фоном.
     * В противном случае, фоном ставиться цвет layTitleBackgroundColor (если больше -1).
     */
    protected @DrawableRes int layTitleBackgroundResId;
    protected int layTitleBackgroundColor;

    /**
     * Настройка шрифта заголовка. Размер шрифта в sp.
     */
    protected int titleColor;
    protected int titleSize;
    protected Typeface titleTypeface;
    protected TextStyle titleStyle;

    /**
     * Выравнивание заголовка.
     */
    protected int titleGravity;

    /**
     * Отступы заголовка в dp.
     */
    protected int tvTitlePaddingStart;
    protected int tvTitlePaddingTop;
    protected int tvTitlePaddingEnd;
    protected int tvTitlePaddingBottom;

    /**
     * Фон разделяющей линии заголовка. Первым проверяется ресурс dividerTitleResId, если он не равен -1, то устанавливается фоном.
     * В противном случае, фоном ставиться цвет dividerTitleColor.
     */
    protected @DrawableRes int dividerTitleResId;
    protected int dividerTitleColor;

    /**
     * Ширина разделяющей линии заголовка в dp.
     */
    protected int dividerTitleWidth;

    /**
     * Ресурс селектора кнопок.
     */
    protected @DrawableRes int buttonSelectorId;

    /**
     * Настройка шрифта кнопок. Размер шрифта указывается в sp.
     */
    protected int buttonTextColor;
    protected int buttonTextSize;
    protected Typeface buttonTextTypeface;
    protected TextStyle buttonTextStyle;

    /**
     * Выравнивание текста кнопок.
     */
    protected int buttonTextGravity;

    /**
     * Фон горизонтальной и вертикальных линии layout'а кнопок.
     * Первыми проверяются ресурсы dividerBtnHorizontalResId и dividerBtnVerticalResId,
     * если они не равны -1, то устанавливаются фоном.
     * В противном случае, фоном ставятся цвета dividerBtnHorizontalColor и dividerBtnVerticalColor.
     */
    protected @DrawableRes int dividerBtnHorizontalResId;
    protected @DrawableRes int dividerBtnVerticalResId;
    protected int dividerBtnHorizontalColor;
    protected int dividerBtnVerticalColor;

    /**
     * Ширина горизонтальной и вертикальных линий layout'а кнопок в dp.
     */
    protected int dividerBtnHorizontalWidth;
    protected int dividerBtnVerticalWidth;

    /**
     * Настройка шрифта сообщения в MessageDialog. Размер шрифта указывается в sp.
     */
    protected int messageTextColor;
    protected int messageTextSize;
    protected Typeface messageTextTypeface;
    protected TextStyle messageTextStyle;

    /**
     * Выравнивание текста сообщения в MessageDialog.
     */
    protected int messageTextGravity;

    /**
     * Отступы сообщения в MessageDialog. Указываются в dp.
     */
    protected int tvMessagePaddingStart;
    protected int tvMessagePaddingTop;
    protected int tvMessagePaddingEnd;
    protected int tvMessagePaddingBottom;

    /**
     * Ресурс селектора пунктов в ListDialog.
     */
    protected  @DrawableRes int listItemBackgroundSelector;

    /**
     * Настройка шрифта пунктов в ListDialog. Размер шрифта указывается в sp.
     */
    protected int listItemTextColor;
    protected int listItemTextSize;
    protected Typeface listItemTextTypeface;
    protected TextStyle listItemTextStyle;

    /**
     * Выравнивание текста сообщения в MessageDialog.
     */
    protected int listItemTextGravity;

    /**
     * Отступы сообщения в MessageDialog. Указываются в dp.
     */
    protected int tvListItemPaddingStart;
    protected int tvListItemPaddingTop;
    protected int tvListItemPaddingEnd;
    protected int tvListItemPaddingBottom;


    protected int editTextColor, hintTextColor;

    protected int editTextSize;

    protected Typeface editTextTypeface;

    protected TextStyle editTextStyle;

    private final Context context;

    @DrawableRes
    protected int singleFlagSelector, multiFlagSelector;


    protected HoloDialogFactory(Context context) {
        this.context = context;

        backgroundDialogColor = context.getResources().getColor(R.color.holo_dialog_background);
        backgroundDialogResId = -1;

        layTitleBackgroundResId = -1;
        layTitleBackgroundColor = -1;

        titleColor = context.getResources().getColor(R.color.holo_title_text);
        titleSize = 18;
        titleTypeface = Typeface.DEFAULT;
        titleStyle = TextStyle.NORMAL;
        titleGravity = Gravity.START;

        tvTitlePaddingStart = 16;
        tvTitlePaddingTop = 16;
        tvTitlePaddingEnd = 16;
        tvTitlePaddingBottom = 16;

        dividerTitleResId = -1;
        dividerTitleColor = context.getResources().getColor(R.color.holo_title_divider);
        dividerTitleWidth = 2;

        buttonSelectorId = R.drawable.holo_btn_selector;

        buttonTextColor = context.getResources().getColor(R.color.holo_button_text);
        buttonTextSize = 12;
        buttonTextTypeface = Typeface.DEFAULT;
        buttonTextStyle = TextStyle.NORMAL;
        buttonTextGravity = -1;

        dividerBtnHorizontalColor = context.getResources().getColor(R.color.holo_button_divider);
        dividerBtnVerticalColor = context.getResources().getColor(R.color.holo_button_divider);
        dividerBtnHorizontalResId = -1;
        dividerBtnVerticalResId = -1;

        dividerBtnHorizontalWidth = 2;
        dividerBtnVerticalWidth = 2;

        messageTextColor = context.getResources().getColor(R.color.holo_message_text);
        messageTextSize = 16;
        messageTextTypeface = Typeface.DEFAULT;
        messageTextStyle = TextStyle.NORMAL;

        messageTextGravity = -1;

        tvMessagePaddingStart = 12;
        tvMessagePaddingTop = 12;
        tvMessagePaddingEnd = 12;
        tvMessagePaddingBottom = 12;

        listItemBackgroundSelector = R.drawable.holo_list_item_selector;

        listItemTextColor = context.getResources().getColor(R.color.holo_list_item_text);
        listItemTextSize = 16;
        listItemTextTypeface = Typeface.DEFAULT;
        listItemTextStyle = TextStyle.NORMAL;

        listItemTextGravity = -1;

        tvListItemPaddingStart = 12;
        tvListItemPaddingTop = 12;
        tvListItemPaddingEnd = 12;
        tvListItemPaddingBottom = 12;






        editTextColor = context.getResources().getColor(R.color.holo_dark_edit_text);
        editTextSize = 16;
        editTextTypeface = Typeface.DEFAULT;
        editTextStyle = TextStyle.NORMAL;
        hintTextColor = context.getResources().getColor(R.color.holo_dark_edit_hint);

        singleFlagSelector = R.drawable.holo_radiobtn_selector;
        multiFlagSelector = R.drawable.holo_checkbox_selector;
    }

    public ListDialog createListDialog() {
        HoloListDialog dialog = new HoloListDialog();
        setViewProperties(dialog);
        setTitleProperties(dialog);
        dialog.setItemBackgroundSelector(listItemBackgroundSelector);
        dialog.setItemTextColor(listItemTextColor);
        dialog.setItemTextSize(listItemTextSize);
        dialog.setItemTextTypeface(listItemTextTypeface, listItemTextStyle);
        dialog.setItemTextGravity(listItemTextGravity);
        dialog.setItemTextPaddingDP(tvListItemPaddingStart, tvListItemPaddingTop, tvListItemPaddingEnd, tvListItemPaddingBottom);
        return dialog;
    }


    public ChoiceDialogFragment getChoiceDialog(boolean isMultiChoice) {
        ChoiceDialogFragment dialog = new ChoiceDialogFragment();
        setViewProperties(dialog);
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
        HoloSingleChoiceDialog dialog = new HoloSingleChoiceDialog();
        setViewProperties(dialog);
        setButtonsProperties(dialog);
        return dialog;
    }

    public MultiChoiceDialog createMultiChoiceDialog() {
        HoloMultiChoiceDialog dialog = new HoloMultiChoiceDialog();
        setViewProperties(dialog);
        setButtonsProperties(dialog);
        return  dialog;
    }

    public MessageDialog createMessageDialog() {
        HoloMessageDialog dialog = new HoloMessageDialog();
        setViewProperties(dialog);
        setTitleProperties(dialog);
        setButtonsProperties(dialog);
        dialog.setMessageColor(messageTextColor);
        dialog.setMessageSize(messageTextSize);
        dialog.setMessageFont(messageTextTypeface, messageTextStyle);
        if(messageTextGravity > -1){
            dialog.setMessageGravity(messageTextGravity);
        }
        dialog.setMessagePaddingDP(tvMessagePaddingStart, tvMessagePaddingTop, tvMessagePaddingEnd, tvMessagePaddingBottom);
        return dialog;
    }

    public EditTextDialogFragment getEditTextDialog(){
        EditTextDialogFragment dialog = new EditTextDialogFragment();
        setViewProperties(dialog);
        setTitleProperties(dialog);
        setButtonsProperties(dialog);
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

    /**
     * Установка настроек главного view диалогового окна.
     *
     * @param dialog Объкт диалогового окна.
     */
    private void setViewProperties(HoloRootDialog dialog) {
        Log.d(Statical.TAG, "setBaseProperties");
        if(backgroundDialogResId > -1){
            dialog.setDialogBackground(backgroundDialogResId);
        } else if(backgroundDialogColor > -1) {
            dialog.setDialogBackgroundColor(backgroundDialogColor);
        }
    }

    /**
     * Установка настроек заголовка диалогового окна.
     *
     * @param dialog Объкт диалогового окна.
     */
    private void setTitleProperties(HoloRootDialog dialog) {
        if(layTitleBackgroundResId > -1){
            dialog.setDialogBackground(layTitleBackgroundResId);
        } else if(layTitleBackgroundColor > -1){
            dialog.setDialogBackgroundColor(layTitleBackgroundColor);
        }
        dialog.setTitleTextColor(titleColor);
        dialog.setTitleTextSize(titleSize);
        dialog.setTitleTextFont(titleTypeface, titleStyle);
        if(titleGravity > -1) {
            dialog.setTitleGravity(titleGravity);
        }
        dialog.setTvTitlePaddingDP(tvTitlePaddingStart, tvTitlePaddingTop, tvTitlePaddingEnd, tvTitlePaddingBottom);
        if(dividerTitleResId > -1){
            dialog.setDividerTitleBackground(dividerTitleResId);
        } else{
            dialog.setDividerTitleColor(dividerTitleColor);
        }
        dialog.setDividerTitleWidth(dividerTitleWidth);
    }

    /**
     * Установка настроек layout'а кнопок диалогового окна.
     *
     * @param dialog Объкт диалогового окна.
     */
    private void setButtonsProperties(HoloBaseDialog dialog) {
        dialog.setButtonsSelectorId(buttonSelectorId);
        dialog.setTextButtonsColor(buttonTextColor);
        dialog.setTextButtonsSize(buttonTextSize);
        dialog.setTextButtonsFont(buttonTextTypeface, buttonTextStyle);
        if(buttonTextGravity > -1){
            dialog.setTextButtonsGravity(buttonTextGravity);
        }
        if(dividerBtnHorizontalResId > -1){
            dialog.setTopDividerBtnResId(dividerBtnHorizontalResId);
        } else{
            dialog.setTopDividerBtnColor(dividerBtnHorizontalColor);
        }
        if(dividerBtnVerticalResId > -1){
            dialog.setLeftDividerBtnResId(dividerBtnVerticalResId);
            dialog.setRightDividerBtnResId(dividerBtnVerticalResId);
        } else{
            dialog.setLeftDividerBtnColor(dividerBtnVerticalColor);
            dialog.setRightDividerBtnColor(dividerBtnVerticalColor);
        }
        dialog.setTopDividerBtnWidth(dividerBtnHorizontalWidth);
        dialog.setLeftDividerBtnWidth(dividerBtnVerticalWidth);
        dialog.setRightDividerBtnWidth(dividerBtnVerticalWidth);
    }
}

