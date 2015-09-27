package com.neirx.neirdialogsdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.neirx.neirdialogs.dialog.ChoiceDialogFragment;
import com.neirx.neirdialogs.interfaces.ChoiceItem;
import com.neirx.neirdialogs.interfaces.NeirDialogInterface;

import java.util.ArrayList;
import java.util.List;

public class SingleChoiceDialogActivity extends Activity implements View.OnClickListener, NeirDialogInterface.OnClickListener {
    Button btnTitleAllBtn, btnTitleTwoBtn, btnTitleOneBtn, btnNoTitleOneBtn, btnNoTitleNoBtn;
    CustomDialogCreator dialogCreator;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choice_dialog);

        btnTitleAllBtn = (Button) findViewById(R.id.btnTitleAllBtn);
        btnTitleTwoBtn = (Button) findViewById(R.id.btnTitleTwoBtn);
        btnTitleOneBtn = (Button) findViewById(R.id.btnTitleOneBtn);
        btnNoTitleOneBtn = (Button) findViewById(R.id.btnNoTitleOneBtn);
        btnNoTitleNoBtn = (Button) findViewById(R.id.btnNoTitleNoBtn);

        btnTitleAllBtn.setOnClickListener(this);
        btnTitleTwoBtn.setOnClickListener(this);
        btnNoTitleOneBtn.setOnClickListener(this);
        btnNoTitleOneBtn.setOnClickListener(this);
        btnNoTitleNoBtn.setOnClickListener(this);

        dialogCreator = CustomDialogCreator.getInstance(this);
        manager = getFragmentManager();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnTitleAllBtn:
                ChoiceDialogFragment dialog = dialogCreator.getChoiceDialog(false);
                dialog.setTitle("Title");
                List<String> items = new ArrayList<>();
                items.add("First");
                items.add("Second");
                items.add("Third");
                dialog.setItems(items, 1);
                dialog.setPositiveButton("Ok");
                dialog.setNegativeButton("Cancel");
                dialog.setOnClickListener(this, "btnTitleAllBtn");
                dialog.show(manager, "btnTitleAllBtn");
                break;
            case R.id.btnTitleTwoBtn:
                break;
            case R.id.btnTitleOneBtn:
                break;
            case R.id.btnNoTitleOneBtn:
                break;
            case R.id.btnNoTitleNoBtn:
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
        if(tag.equals("btnTitleAllBtn")){
            int pos = (int) extraData;
            Toast.makeText(this, tag + "  " + button + " Pos = " + pos, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, tag + "  " + button, Toast.LENGTH_SHORT).show();
        }
    }
}
