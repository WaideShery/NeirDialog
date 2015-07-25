package com.neirx.neirdialogs.helper;

/**
 * Created by Waide Shery on 25.07.2015.
 */
public class ChoiceItem {
    String title;
    boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public String getTitle() {
        return title;
    }

    public ChoiceItem(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }
}
