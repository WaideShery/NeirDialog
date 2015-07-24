package com.neirx.neirdialog.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.neirx.neirdialog.R;


public class ListDialogFragment extends DialogFragment {
    AlertDialog.Builder builder;
    View view;
    LinearLayout layTitle;
    View dividerTitle;
    Button btnLeft, btnMiddle, btnRight;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
