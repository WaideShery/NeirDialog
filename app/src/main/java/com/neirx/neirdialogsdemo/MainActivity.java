package com.neirx.neirdialogsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.neirx.neirdialogs.DialogCreator;
import com.neirx.neirdialogs.dialog.MessageDialogFragment;
import com.neirx.neirdialogs.dialog.SelectDialogFragment;
import com.neirx.neirdialogs.helper.ListItem;
import com.neirx.neirdialogs.interfaces.ChoiceItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {
    Button btnMessageDialogs, btnListDialogs, btnMultiChoiceDialogs, btnSingleChoiceDialogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMessageDialogs = (Button) findViewById(R.id.btnMessageDialogs);
        btnListDialogs = (Button) findViewById(R.id.btnListDialogs);
        btnMultiChoiceDialogs = (Button) findViewById(R.id.btnMultiChoiceDialogs);
        btnSingleChoiceDialogs = (Button) findViewById(R.id.btnSingleChoiceDialogs);

        btnMessageDialogs.setOnClickListener(this);
        btnListDialogs.setOnClickListener(this);
        btnMultiChoiceDialogs.setOnClickListener(this);
        btnSingleChoiceDialogs.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMessageDialogs:
                startActivity(new Intent(this, MessageDialogActivity.class));
                break;
            case R.id.btnListDialogs:
                startActivity(new Intent(this, ListDialogActivity.class));
                break;
            case R.id.btnMultiChoiceDialogs:
                startActivity(new Intent(this, MultiChoiceDialogActivity.class));
                break;
            case R.id.btnSingleChoiceDialogs:
                startActivity(new Intent(this, SingleChoiceDialogActivity.class));
                break;
        }

    }
}
