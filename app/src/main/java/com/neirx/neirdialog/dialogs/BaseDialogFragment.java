package com.neirx.neirdialog.dialogs;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Абстрактный класс диалогового окна в стиле Holo. Включает в себя настройку Заглавия и Кнопок окна,
 * а также цвет фона, размеры, цвета и стили шрифтов.
 */
public abstract class BaseDialogFragment extends DialogFragment {
    protected View view;
    protected ViewGroup layTitle;
    protected View dividerTitle;
    protected Button btnNegative, btnNeutral, btnPositive;
    protected TextView tvTitle;
    private String title, negativeButton, positiveButton, neutralButton;
    private View.OnClickListener negativeButtonListener, positiveButtonListener, neutralButtonListener;
    protected int buttonCount;

    /**
     * Абстрактный метод, в котором нужно установить layout с соответствующим диалоговым окном.
     * В методе должны быть определены такие view элементы:
     * layTitle - ViewGroup, который включает заглавие и разделительную линию.
     * tvTitle - TextView с тесктом заглавия.
     * dividerTitle - View, разделительная полоса.
     * btnNegative, btnNeutral, btnPositive - Button, соответствующие view кнопок.
     *
     * @param savedInstanceState Bundle с сохраненнім состоянием
     * @return Объект диалогового окна
     */
    @Override
    public abstract Dialog onCreateDialog(Bundle savedInstanceState);

    /**
     * Установка текста заглавия.
     * Если не вызывать этот метод, то диалоговое окно не будет включать заглавие.
     * @param title текст заглавия
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Установка текста и слушателя Negative кнопки.
     * Если не вызывать этот метод, то диалоговое окно не будет включать Negative кнопку.
     * @param titleButton текст кнопки
     * @param listener слушатель
     */
    public void setNegativeButton(String titleButton, View.OnClickListener listener) {
        this.negativeButton = titleButton;
        this.negativeButtonListener = listener;
        if(positiveButton != null && neutralButton != null){
            buttonCount = 3;
        } else if(positiveButton != null || neutralButton != null){
            buttonCount = 2;
        } else {
            buttonCount = 1;
        }
    }

    /**
     * Установка текста и слушателя Positive кнопки.
     * Если не вызывать этот метод, то диалоговое окно не будет включать Positive кнопку.
     * @param titleButton текст кнопки
     * @param listener слушатель
     */
    public void setPositiveButton(String titleButton, View.OnClickListener listener) {
        this.positiveButton = titleButton;
        this.positiveButtonListener = listener;
        if(negativeButton != null && neutralButton != null){
            buttonCount = 3;
        } else if(negativeButton != null || neutralButton != null){
            buttonCount = 2;
        } else {
            buttonCount = 1;
        }
    }

    /**
     * Установка текста и слушателя Neutral кнопки.
     * Если не вызывать этот метод, то диалоговое окно не будет включать Neutral кнопку.
     * @param titleButton текст кнопки
     * @param listener слушатель
     */
    public void setNeutralButton(String titleButton, View.OnClickListener listener) {
        this.neutralButton = titleButton;
        this.neutralButtonListener = listener;
        if(negativeButton != null && positiveButton != null){
            buttonCount = 3;
        } else if(negativeButton != null || positiveButton != null){
            buttonCount = 2;
        } else {
            buttonCount = 1;
        }
    }

    /**
     * Метод проверки, установлено ли заглавие у окна.
     * Если не установлено, то соответствующее view скрывается.
     */
    protected void checkTitle(){
        if(title == null){
            layTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }
    }

    /**
     * Метод проверки, установлены ли кнопки у окна.
     * Если не установлены, то соответствующие view скрываются.
     */
    protected void checkButtons(){
        switch (buttonCount){
            case 0:
                btnNegative.setVisibility(View.GONE);
                btnPositive.setVisibility(View.GONE);
                btnNeutral.setVisibility(View.GONE);
                break;
            case 1:
                btnNegative.setVisibility(View.GONE);
                btnPositive.setVisibility(View.GONE);
                if(negativeButton != null){
                    btnNeutral.setText(negativeButton);
                    btnNeutral.setOnClickListener(negativeButtonListener);
                } else if(positiveButton != null){
                    btnNeutral.setText(positiveButton);
                    btnNeutral.setOnClickListener(positiveButtonListener);
                } else {
                    btnNeutral.setText(neutralButton);
                    btnNeutral.setOnClickListener(neutralButtonListener);
                }
                break;
            case 2:
                btnNeutral.setVisibility(View.GONE);
                if(negativeButton != null){
                    btnNegative.setText(negativeButton);
                    btnNegative.setOnClickListener(negativeButtonListener);
                    if (positiveButton != null){
                        btnPositive.setText(positiveButton);
                        btnPositive.setOnClickListener(positiveButtonListener);
                    } else {
                        btnPositive.setText(neutralButton);
                        btnPositive.setOnClickListener(neutralButtonListener);
                    }
                } else {
                    btnNegative.setText(neutralButton);
                    btnNegative.setOnClickListener(neutralButtonListener);
                    btnPositive.setText(positiveButton);
                    btnPositive.setOnClickListener(positiveButtonListener);
                }
                break;
            case 3:
                btnNegative.setText(negativeButton);
                btnNegative.setOnClickListener(negativeButtonListener);
                btnNeutral.setText(neutralButton);
                btnNeutral.setOnClickListener(neutralButtonListener);
                btnPositive.setText(positiveButton);
                btnPositive.setOnClickListener(positiveButtonListener);
                break;
        }
    }
}
