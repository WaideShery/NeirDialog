package com.neirx.neirdialogsdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.neirx.neirdialogs.dialog.ListDialogFragment;
import com.neirx.neirdialogs.interfaces.NeirDialogInterface;

/**
 * Created by waide on 21.09.15.
 *
 */
public class ListDialogActivity extends Activity implements View.OnClickListener, NeirDialogInterface.OnItemClickListener {
    Button btnWithTitle, btnWithoutTitle;
    private CustomDialogCreator dialogCreator;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dialog);

        btnWithTitle = (Button) findViewById(R.id.btnWithTitle);
        btnWithoutTitle = (Button) findViewById(R.id.btnWithoutTitle);

        btnWithTitle.setOnClickListener(this);
        btnWithoutTitle.setOnClickListener(this);

        dialogCreator = CustomDialogCreator.getInstance(this);
        manager = getFragmentManager();
    }

    @Override
    public void onClick(View view) {
        ListDialogFragment dialogFragment;
        switch (view.getId()){
            case R.id.btnWithTitle:
                dialogFragment = dialogCreator.getListDialog();
                dialogFragment.setTitle("Title");
                dialogFragment.setItems(new String[]{"Первый", "Второй", "Третий"});
                dialogFragment.setOnItemClickListener(this, "btnWithTitle");
                dialogFragment.show(manager, "btnWithTitle");
                break;
            case R.id.btnWithoutTitle:
                break;
        }
    }

    @Override
    public void onItemClick(String tag, int itemId) {
        String item = "";
        switch (itemId){
            case 0:
                item = "Первый";
                break;
            case 1:
                item = "Второй";
                break;
            case 2:
                item = "Третий";
                break;
        }
        Toast.makeText(this, tag + "  " + item, Toast.LENGTH_SHORT).show();
    }
}
