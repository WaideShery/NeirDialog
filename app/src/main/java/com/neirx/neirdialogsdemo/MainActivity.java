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
import com.neirx.neirdialogs.helper.ListItem;
import com.neirx.neirdialogs.interfaces.ChoiceItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements SelectDialogFragment.SelectItemListener {
    CustomDialogCreator dialogCreator;
    List<ChoiceItem> listItems;
    Button btnSimpleDialogThreeBtn, btnSimpleDialogTwoBtn, btnSimpleDialogOneBtn, btnSimpleDialogNoBtnNoTitle,
            btnSingleChoiceBtn, btnMultiChoiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimpleDialogOneBtn = (Button) findViewById(R.id.btnSimpleDialogOneBtn);
        btnSimpleDialogTwoBtn = (Button) findViewById(R.id.btnSimpleDialogTwoBtn);
        btnSimpleDialogThreeBtn = (Button) findViewById(R.id.btnSimpleDialogThreeBtn);
        btnSimpleDialogNoBtnNoTitle = (Button) findViewById(R.id.btnSimpleDialogNoBtnNoTitle);
        btnSingleChoiceBtn = (Button) findViewById(R.id.btnSingleChoiceBtn);
        btnMultiChoiceBtn = (Button) findViewById(R.id.btnMultiChoiceBtn);

        dialogCreator = CustomDialogCreator.getInstance(this);

        setListenerMessageButtons();
        setListenerListButtons();

    }

    private void setListenerListButtons() {
        listItems = new ArrayList<>();
        listItems.add(new ListItem("Портрет", false));
        listItems.add(new ListItem("Альбом", false));
        listItems.add(new ListItem("Авто", true));
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
                singleChoiceDialog.setSelectItemListener(MainActivity.this);
                singleChoiceDialog.show(getFragmentManager(), "tag");
            }
        });
        btnMultiChoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SelectDialogFragment multiChoiceDialog = dialogCreator.getSelectDialog(true, listItems);
                multiChoiceDialog.setPositiveButton("Done", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        multiChoiceDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                });
                multiChoiceDialog.setNeutralButton("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        multiChoiceDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                multiChoiceDialog.setSelectItemListener(MainActivity.this, 1122);
                multiChoiceDialog.show(getFragmentManager(), "multi-tag");
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

    @Override
    public void onFinishSelectDialog(int requestCode, boolean[] checkedItems) {
        if(requestCode == 1122){
            int count = 0;
            for (boolean isChecked : checkedItems) {
                if (isChecked) {
                    count++;
                }
            }
            Toast.makeText(this, "выбранно " + count+ " элементов ", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < checkedItems.length; i++) {
                if (checkedItems[i]) {
                    Toast.makeText(this, "id выбранного элемента: " + i, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
