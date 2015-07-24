package com.neirx.neirdialog.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neirx.neirdialog.R;

public class SimpleDialogFragment extends DialogFragment {
    AlertDialog.Builder builder;
    View view;
    LinearLayout layTitle;
    View dividerTitle;
    Button btnLeft, btnMiddle, btnRight;
    TextView tvTitle, tvMessage;
    private String message, negativeButton, positiveButton, neutralButton, title;
    private View.OnClickListener negativeButtonListener, positiveButtonListener, neutralButtonListener;
    int buttonCount;

    public void setMessage(String message) {
        this.message = message;
    }

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

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.holo_message_dialog, null);
        btnLeft = (Button) view.findViewById(R.id.btnNegative);
        btnMiddle = (Button) view.findViewById(R.id.btnNeutral);
        btnRight = (Button) view.findViewById(R.id.btnPositive);
        layTitle = (LinearLayout) view.findViewById(R.id.layTitle);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        dividerTitle = view.findViewById(R.id.dividerTitle);

        checkMessage();
        checkTitle();
        checkButtons();

        builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void checkTitle(){
        if(title == null){
            layTitle.setVisibility(View.GONE);
            dividerTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }
    }

    private void checkMessage(){
        if(message == null){
            message = "";
        }
        tvMessage.setText(message);
    }

    private void checkButtons(){
        switch (buttonCount){
            case 0:
                btnLeft.setVisibility(View.GONE);
                btnRight.setVisibility(View.GONE);
                btnMiddle.setVisibility(View.GONE);
                break;
            case 1:
                btnLeft.setVisibility(View.GONE);
                btnRight.setVisibility(View.GONE);
                if(negativeButton != null){
                    btnMiddle.setText(negativeButton);
                    btnMiddle.setOnClickListener(negativeButtonListener);
                } else if(positiveButton != null){
                    btnMiddle.setText(positiveButton);
                    btnMiddle.setOnClickListener(positiveButtonListener);
                } else {
                    btnMiddle.setText(neutralButton);
                    btnMiddle.setOnClickListener(neutralButtonListener);
                }
                break;
            case 2:
                btnMiddle.setVisibility(View.GONE);
                if(negativeButton != null){
                    btnLeft.setText(negativeButton);
                    btnLeft.setOnClickListener(negativeButtonListener);
                    if (positiveButton != null){
                        btnRight.setText(positiveButton);
                        btnRight.setOnClickListener(positiveButtonListener);
                    } else {
                        btnRight.setText(neutralButton);
                        btnRight.setOnClickListener(neutralButtonListener);
                    }
                } else {
                    btnLeft.setText(neutralButton);
                    btnLeft.setOnClickListener(neutralButtonListener);
                    btnRight.setText(positiveButton);
                    btnRight.setOnClickListener(positiveButtonListener);
                }
                break;
            case 3:
                btnLeft.setText(negativeButton);
                btnLeft.setOnClickListener(negativeButtonListener);
                btnMiddle.setText(neutralButton);
                btnMiddle.setOnClickListener(neutralButtonListener);
                btnRight.setText(positiveButton);
                btnRight.setOnClickListener(positiveButtonListener);
                break;
        }
    }

}
