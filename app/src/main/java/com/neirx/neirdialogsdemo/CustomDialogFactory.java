package com.neirx.neirdialogsdemo;

import android.content.Context;

import com.neirx.neirdialogs.HoloDialogFactory;

/**
 * Created by Waide Shery on 25.07.2015.
 *
 */
public class CustomDialogFactory extends HoloDialogFactory {
    private static CustomDialogFactory instance;

    public static CustomDialogFactory getInstance(Context context){
        if(instance == null){
            instance = new CustomDialogFactory(context);
        }
        return instance;
    }

    public CustomDialogFactory(Context context) {
        super(context);
        setBackgroundDialogRes(R.drawable.fon);
    }
}
