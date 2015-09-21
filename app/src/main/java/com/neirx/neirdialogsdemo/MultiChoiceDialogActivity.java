package com.neirx.neirdialogsdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.neirx.neirdialogsdemo.R;

public class MultiChoiceDialogActivity extends Activity implements View.OnClickListener {
    Button btnTitleAllBtn, btnTitleTwoBtn, btnTitleOneBtn, btnNoTitleOneBtn, btnNoTitleNoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choice_dialog);

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnTitleAllBtn:
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
}
