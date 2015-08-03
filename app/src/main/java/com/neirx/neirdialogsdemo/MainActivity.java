package com.neirx.neirdialogsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.neirx.neirdialogs.dialog.MessageDialogFragment;
import com.neirx.neirdialogs.dialog.SelectDialogFragment;
import com.neirx.neirdialogs.helper.ChoiceItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    CustomDialogCreator dialogCreator;
    List<ChoiceItem> listItems;
    Button btnSimpleDialogThreeBtn, btnSimpleDialogTwoBtn, btnSimpleDialogOneBtn, btnSimpleDialogNoBtnNoTitle,
            btnSingleChoiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimpleDialogOneBtn = (Button) findViewById(R.id.btnSimpleDialogOneBtn);
        btnSimpleDialogTwoBtn = (Button) findViewById(R.id.btnSimpleDialogTwoBtn);
        btnSimpleDialogThreeBtn = (Button) findViewById(R.id.btnSimpleDialogThreeBtn);
        btnSimpleDialogNoBtnNoTitle = (Button) findViewById(R.id.btnSimpleDialogNoBtnNoTitle);
        btnSingleChoiceBtn = (Button) findViewById(R.id.btnSingleChoiceBtn);

        dialogCreator = CustomDialogCreator.getInstance(this);

        setListenerMessageButtons();
        setListenerListButtons();

    }

    private void setListenerListButtons() {
        listItems = new ArrayList<>();
        listItems.add(new ChoiceItem("Портрет", false));
        listItems.add(new ChoiceItem("Альбом", false));
        listItems.add(new ChoiceItem("Авто", true));
        btnSingleChoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDialogFragment singleChoiceDialog = dialogCreator.getSelectDialog(false, listItems);
                singleChoiceDialog.setTitle("Title");
                singleChoiceDialog.setNegativeButton("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                singleChoiceDialog.show(getFragmentManager(), "tag");
            }
        });
    }

    private void setListenerMessageButtons(){
        btnSimpleDialogOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialogFragment standardDialog = dialogCreator.getMessageDialog();
                standardDialog.setMessage("Default message");
                standardDialog.setTitle("Title");;
                standardDialog.setNegativeButton("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                    }
                });
                standardDialog.show(getFragmentManager(), "tag");
            }
        });

        btnSimpleDialogTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialogFragment standardDialog = dialogCreator.getMessageDialog();
                standardDialog.setMessage("Default message");
                standardDialog.setTitle("Title");
                standardDialog.setNegativeButton("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                standardDialog.setPositiveButton("Done", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                });
                standardDialog.show(getFragmentManager(), "btnSimpleDialogTwoBtn");
            }
        });

        btnSimpleDialogThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ThisApp", "Step -2");
                MessageDialogFragment standardDialog = dialogCreator.getMessageDialog();
                standardDialog.setMessage("Default message");
                standardDialog.setTitle("Title");
                standardDialog.setNegativeButton("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                standardDialog.setNeutralButton("Neutral", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Neutral", Toast.LENGTH_SHORT).show();
                    }
                });
                standardDialog.setPositiveButton("Done", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                });
                standardDialog.show(getFragmentManager(), "btnSimpleDialogThreeBtn");
                Log.d("ThisApp", "Step -1");
            }
        });

        btnSimpleDialogNoBtnNoTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialogFragment standardDialog = dialogCreator.getMessageDialog();
                standardDialog.setMessage("Default message");
                standardDialog.show(getFragmentManager(), "btnSimpleDialogNoBtnNoTitle");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
