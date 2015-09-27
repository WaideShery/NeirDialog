package com.neirx.neirdialogs.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.neirx.neirdialogs.R;
import com.neirx.neirdialogs.interfaces.NeirDialogInterface;


public class MessageDialogFragment extends BaseDialogFragment {
    protected TextView tvMessage;
    protected ScrollView svMessage;
    protected String message;
    protected int messageColor;
    protected float messageSize;
    protected TextStyle messageStyle;
    protected Typeface messageTypeface;

    /**
     * Установка текста сообщения диалогового окна.
     * Если не вызывать этот метод, то область сообщения будет пустой.
     *
     * @param message текст сообщения диалогового окна
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Установка цвета сообщения диалогового окна.
     *
     * @param color ресурс цвета
     */
    public void setMessageColor(int color) {
        messageColor = color;
    }

    /**
     * Установка размера шрифта сообщения диалогового окна.
     *
     * @param sizeSp размер шрифта
     */
    public void setMessageSize(float sizeSp) {
        messageSize = sizeSp;
    }

    /**
     * Установка шрифта и стиля отображения сообщения диалогового окна.
     *
     * @param tf    шрифт
     * @param style стиль текста
     */
    public void setMessageTypeface(Typeface tf, TextStyle style) {
        messageTypeface = tf;
        messageStyle = style;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.holo_message_dialog, null);
        lineBtnHorizontal = view.findViewById(R.id.viewTop);
        lineBtnFirst = view.findViewById(R.id.viewLeft);
        lineBtnSecond = view.findViewById(R.id.viewRight);
        btnNegative = (Button) view.findViewById(R.id.btnNegative);
        btnNeutral = (Button) view.findViewById(R.id.btnNeutral);
        btnPositive = (Button) view.findViewById(R.id.btnPositive);
        layTitle = (LinearLayout) view.findViewById(R.id.layTitle);
        layButtons = view.findViewById(R.id.layButtons);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        dividerTitle = view.findViewById(R.id.dividerTitle);
        tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        svMessage = (ScrollView) view.findViewById(R.id.svMessage);

        checkDialogBackground();
        checkTitle();
        checkButtons();
        checkMessage();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    /**
     * Метод нажатия на кнопки диалогового окна.
     * Вызывает NeirDialogInterface.OnClickListener.onClick(String tag, int buttonId, Object extraData).
     * В объекте extraData передает null.
     */
    @Override
    public void onClick(View view) {
        dismiss();
        if (onClickListener == null) {
            return;
        }
        int id = view.getId();
        if (id == R.id.btnPositive) {
            onClickListener.onClick(tag, NeirDialogInterface.BUTTON_POSITIVE, null);
        } else if (id == R.id.btnNegative) {
            onClickListener.onClick(tag, NeirDialogInterface.BUTTON_NEGATIVE, null);
        } else if (id == R.id.btnNeutral) {
            onClickListener.onClick(tag, NeirDialogInterface.BUTTON_NEUTRAL, null);
        }
    }

    /**
     * Метод проверки, установлен ли текст сообщения диалогового окна.
     * Если не установлен, то область сообщения остается пустой.
     * Установка параметров тесктового поля.
     */
    protected void checkMessage() {
        if (message == null) {
            message = "";
        }
        tvMessage.setText(message);
        tvMessage.setTextColor(messageColor);
        tvMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, messageSize);
        tvMessage.setTypeface(messageTypeface, messageStyle.getValue());
    }
}
