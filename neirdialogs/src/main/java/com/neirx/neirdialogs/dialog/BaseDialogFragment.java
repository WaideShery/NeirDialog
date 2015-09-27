package com.neirx.neirdialogs.dialog;


import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neirx.neirdialogs.Statical;
import com.neirx.neirdialogs.interfaces.NeirDialogInterface;

/**
 * Абстрактный класс диалогового окна в стиле Holo. Включает в себя настройку Заглавия и Кнопок окна,
 * а также цвет фона, размеры, цвета и стили шрифтов.
 */
public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {
    protected View view, lineBtnFirst, lineBtnSecond, lineBtnHorizontal;
    protected ViewGroup layTitle;
    View layButtons;
    protected View dividerTitle;
    protected Button btnNegative, btnNeutral, btnPositive;
    protected TextView tvTitle;
    private String title, negativeButton, positiveButton, neutralButton;
    protected int buttonCount;
    protected int titleColor;
    protected float titleSize;
    protected Typeface titleTypeface;
    protected TextStyle titleStyle;
    protected int backgroundDialogResId;
    protected int backgroundDialogColor;
    protected int dividerTitleResId;
    protected int dividerTitleColor;
    protected int dividerTitleHeight;
    protected int buttonTextColor;
    protected float buttonTextSize;
    protected Typeface buttonTextTypeface;
    protected TextStyle buttonTextStyle;
    protected int buttonSelectorId;
    protected int dividerButtonsColor;
    protected NeirDialogInterface.OnClickListener onClickListener;
    protected String tag;

    /**
     * Абстрактный метод, в котором нужно установить layout с соответствующим диалоговым окном.
     * В методе должны быть определены такие view элементы:
     * layTitle - ViewGroup, который включает заглавие и разделительную линию.
     * tvTitle - TextView с тесктом заглавия.
     * dividerTitle - View, разделительная титульная полоса.
     * lineBtnHorizontal - горизонтальная отделяющая кнопки полоса.
     * lineBtnFirst, lineBtnSecond - вертикальные разделительные полосы между кнопками.
     * layButtons - ViewGroup, который включает кнопки и разделительные линии.
     * btnNegative, btnNeutral, btnPositive - Button, соответствующие view кнопок.
     *
     * @param savedInstanceState Bundle с сохраненным состоянием
     * @return Объект диалогового окна
     */
    @Override
    public abstract Dialog onCreateDialog(Bundle savedInstanceState);

    /**
     * Установка фона диалогового окна.
     *
     * @param resId ресурс с фоном
     */
    public void setDialogBackground(@DrawableRes int resId) {
        backgroundDialogResId = resId;
    }

    /**
     * Установка фона диалогового окна.
     *
     * @param color ресурс цвета фона
     */
    public void setDialogBackgroundColor(int color) {
        backgroundDialogColor = color;
    }

    /**
     * Установка фона титульной разделяющей линии.
     *
     * @param resId ресурс с фоном
     */
    public void setDividerTitleBackground(@DrawableRes int resId) {
        dividerTitleResId = resId;
    }

    /**
     * Установка цвета титульной разделяющей линии.
     *
     * @param color цвет
     */
    public void setDividerTitleColor(int color) {
        dividerTitleColor = color;
    }

    /**
     * Установка высоты титульной разделяющей линии.
     *
     * @param dpHeight высота в dp
     */
    public void setDividerTitleHeight(int dpHeight) {
        dividerTitleHeight = dpHeight;
    }

    /**
     * Установка текста заглавия.
     * Если не вызывать этот метод, то диалоговое окно не будет включать заглавие.
     *
     * @param title текст заглавия
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Установка цвета заглавия.
     *
     * @param color ресурс цвета
     */
    public void setTitleColor(int color) {
        titleColor = color;
    }

    /**
     * Установка размера шрифта заглавия.
     *
     * @param sizeSp размер шрифта
     */
    public void setTitleSize(float sizeSp) {
        titleSize = sizeSp;
    }

    /**
     * Установка шрифта и стиля отображения заглавия.
     *
     * @param tf    шрифт
     * @param style стиль текста
     */
    public void setTitleTypeface(Typeface tf, TextStyle style) {
        titleTypeface = tf;
        titleStyle = style;
    }

    /**
     * Установка цвета текста кнопок.
     *
     * @param color ресурс цвета
     */
    public void setButtonsTextColor(int color) {
        buttonTextColor = color;
    }

    /**
     * Установка размера текста кнопок.
     *
     * @param size размер текста
     */
    public void setButtonsTextSize(float size) {
        buttonTextSize = size;
    }

    /**
     * Установка шрифта и стиля отображения текста кнопок.
     *
     * @param tf    шрифт
     * @param style стиль текста
     */
    public void setButtonsTypeface(Typeface tf, TextStyle style) {
        buttonTextTypeface = tf;
        buttonTextStyle = style;

    }

    public void setDividerButtonsColor(int color) {
        dividerButtonsColor = color;

    }

    /**
     * Установка фона кнопок.
     *
     * @param resIdSelector ресурс фона
     */
    public void setButtonsBackground(@DrawableRes int resIdSelector) {
        buttonSelectorId = resIdSelector;

    }

    /**
     * Метод для определения какие кнопки установлены. Установливает buttonCount.
     * Соответствует цифрам в двоичном формате. Ноль - кнопка не установлена, единица - установлена.
     * Первая цыфра - negativeButton, вторая - neutralButton, третья - positiveButton.
     * Примеры: 000 - ниодна кнопка не установлена, buttonCount = 0;
     *          001 - установлена только positiveButton, buttonCount = 1;
     *          110 - установлены negativeButton и neutralButton buttonCount = 6.
     */
    private void defineCount(){
        if(negativeButton == null && neutralButton == null && positiveButton == null){
            buttonCount = 0;
        }
        if(negativeButton == null && neutralButton == null && positiveButton != null){
            buttonCount = 1;
        }
        if(negativeButton == null && neutralButton != null && positiveButton == null){
            buttonCount = 2;
        }
        if(negativeButton == null && neutralButton != null && positiveButton != null){
            buttonCount = 3;
        }
        if(negativeButton != null && neutralButton == null && positiveButton == null){
            buttonCount = 4;
        }
        if(negativeButton != null && neutralButton == null && positiveButton != null){
            buttonCount = 5;
        }
        if(negativeButton != null && neutralButton != null && positiveButton == null){
            buttonCount = 6;
        }
        if(negativeButton != null && neutralButton != null && positiveButton != null){
            buttonCount = 7;
        }
    }

    /**
     * Установка текста и слушателя Positive кнопки.
     * Если не вызывать этот метод, то диалоговое окно не будет включать Positive кнопку.
     *
     * @param titleButton текст кнопки
     */
    public void setPositiveButton(String titleButton) {
        this.positiveButton = titleButton;
        defineCount();
    }

    /**
     * Установка текста и слушателя Neutral кнопки.
     * Если не вызывать этот метод, то диалоговое окно не будет включать Neutral кнопку.
     *
     * @param titleButton текст кнопки
     */
    public void setNeutralButton(String titleButton) {
        this.neutralButton = titleButton;
        defineCount();
    }

    /**
     * Установка текста и слушателя Negative кнопки.
     * Если не вызывать этот метод, то диалоговое окно не будет включать Negative кнопку.
     *
     * @param titleButton текст кнопки
     */
    public void setNegativeButton(String titleButton) {
        this.negativeButton = titleButton;
        defineCount();
    }

    /**
     * Метод проверки, установлено ли заглавие у окна.
     * Если не установлено, то соответствующее view скрывается.
     */
    protected void checkTitle() {
        if (title == null) {
            layTitle.setVisibility(View.GONE);
        } else {
            Log.d(Statical.TAG, "title = " + title);
            Log.d(Statical.TAG, "titleColor = " + titleColor);
            Log.d(Statical.TAG, "titleSize = " + titleSize);
            tvTitle.setText(title);
            tvTitle.setTextColor(titleColor);
            tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize);
            tvTitle.setTypeface(titleTypeface, titleStyle.getValue());
            if (dividerTitleResId == 0) {
                dividerTitle.setBackgroundColor(dividerTitleColor);
            } else {
                dividerTitle.setBackgroundResource(dividerTitleResId);
            }
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerTitleHeight,
                    getResources().getDisplayMetrics());
            dividerTitle.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));

        }
    }

    /**
     * Метод для установки слушателя нажатия кнопок в диалоговом окне
     *
     * @param listener слушатель
     * @param tag      идентификатор диалогового окна
     */
    public void setOnClickListener(NeirDialogInterface.OnClickListener listener, String tag) {
        onClickListener = listener;
        this.tag = tag;
    }

    /**
     * Метод проверки, какой фон устанавливать для диалогового окна.
     */
    protected void checkDialogBackground() {
        if (backgroundDialogResId == 0) {
            view.setBackgroundColor(backgroundDialogColor);
        } else {
            view.setBackgroundResource(backgroundDialogResId);
        }
    }

    /**
     * Метод проверки, установлены ли кнопки у окна.
     * Если не установлены, то соответствующие view скрываются.
     */
    protected void checkButtons() {
        lineBtnHorizontal.setBackgroundColor(dividerButtonsColor);
        lineBtnFirst.setBackgroundColor(dividerButtonsColor);
        lineBtnSecond.setBackgroundColor(dividerButtonsColor);
        if(buttonCount == 0){
            layButtons.setVisibility(View.GONE);
            return;
        } else if(buttonCount == 1 || buttonCount == 2 || buttonCount == 4){
            lineBtnFirst.setVisibility(View.GONE);
            lineBtnSecond.setVisibility(View.GONE);
        } else if(buttonCount == 3 || buttonCount == 5){
            lineBtnFirst.setVisibility(View.GONE);
        } else if(buttonCount == 6){
            lineBtnSecond.setVisibility(View.GONE);
        }
        seButtonParameters(positiveButton, btnPositive);
        seButtonParameters(negativeButton, btnNegative);
        seButtonParameters(neutralButton, btnNeutral);
    }

    /**
     * Установка параметров кнопок если они не равны null или скрытие view кнопок в противном случае.
     */
    private void seButtonParameters(String title, Button button){
        if(title == null){
            button.setVisibility(View.GONE);
        } else {
            button.setTextColor(buttonTextColor);
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, buttonTextSize);
            button.setTypeface(buttonTextTypeface, buttonTextStyle.getValue());
            button.setBackgroundResource(buttonSelectorId);
            button.setText(title);
            button.setOnClickListener(this);
        }
    }

    @Override
    public abstract void onClick(View view);
}
