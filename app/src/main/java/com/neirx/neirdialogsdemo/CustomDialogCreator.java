package com.neirx.neirdialogsdemo;

import android.content.Context;

import com.neirx.neirdialogs.HoloDialogFactory;

/**
 * Created by Waide Shery on 25.07.2015.
 *
 */
public class CustomDialogCreator extends HoloDialogFactory {
    private static CustomDialogCreator instance;

    public static CustomDialogCreator getInstance(Context context){
        if(instance == null){
            instance = new CustomDialogCreator(context);
        }
        return instance;
    }

    public CustomDialogCreator(Context context) {
        super(context);
    }
}
