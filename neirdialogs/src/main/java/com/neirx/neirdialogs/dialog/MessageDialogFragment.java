package com.neirx.neirdialogs.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.neirx.neirdialogs.R;


public class MessageDialogFragment extends BaseDialogFragment {
    protected TextView tvMessage;
    protected ScrollView svMessage;
    protected String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.holo_message_dialog, null);
        btnNegative = (Button) view.findViewById(R.id.btnNegative);
        btnNeutral = (Button) view.findViewById(R.id.btnNeutral);
        btnPositive = (Button) view.findViewById(R.id.btnPositive);
        layTitle = (LinearLayout) view.findViewById(R.id.layTitle);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        dividerTitle = view.findViewById(R.id.dividerTitle);
        tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        svMessage = (ScrollView) view.findViewById(R.id.svMessage);

        checkTitle();
        checkButtons();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    protected void checkMessage() {
        if (message == null) {
            message = "";
        }
        tvMessage.setText(message);
    }
}
