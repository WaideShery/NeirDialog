package com.neirx.neirdialogsdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.neirx.neirdialogs.dialogs.EditTextDialogFragment;
import com.neirx.neirdialogs.interfaces.NeirDialogInterface;

public class EditTextDialogActivity extends Activity implements View.OnClickListener,
        NeirDialogInterface.OnClickListener {
    final String MESSAGE = "The quick brown fox jumps over the lazy dog. " +
            "Jackdaws love my big sphinx of quartz. " +
            "The five boxing wizards jump QUICKLY";
    Button btnTitleAllBtn, btnTitleTwoBtn, btnTitleOneBtn, btnNoTitleAndBtn;
    CustomDialogCreator dialogCreator;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_dialog);

        btnTitleAllBtn = (Button) findViewById(R.id.btnTitleAllBtn);

        btnTitleAllBtn.setOnClickListener(this);

        dialogCreator = CustomDialogCreator.getInstance(this);
        manager = getFragmentManager();
    }

    @Override
    public void onClick(View view) {
        EditTextDialogFragment dialogFragment;
        switch (view.getId()){
            case R.id.btnTitleAllBtn:
                dialogFragment = dialogCreator.getEditTextDialog();
                dialogFragment.setTitle("Title");
                dialogFragment.setHintText("Напиши");
                dialogFragment.setPositiveButton("Ok");
                dialogFragment.setNegativeButton("Cancel");
                dialogFragment.setNeutralButton("Don't know");
                dialogFragment.setOnClickListener(this, "btnTitleAllBtn");
                dialogFragment.show(manager, "btnTitleAllBtn");
                break;

        }
    }

    @Override
    public void onClick(String tag, int buttonId, Object extraData) {
        String button = "";
        switch (buttonId){
            case NeirDialogInterface.BUTTON_POSITIVE:
                button = "BUTTON_POSITIVE";
                break;
            case NeirDialogInterface.BUTTON_NEGATIVE:
                button = "BUTTON_NEGATIVE";
                break;
            case NeirDialogInterface.BUTTON_NEUTRAL:
                button = "BUTTON_NEUTRAL";
                break;
        }
        Toast.makeText(this, tag + "  " + button, Toast.LENGTH_SHORT).show();
    }


}
