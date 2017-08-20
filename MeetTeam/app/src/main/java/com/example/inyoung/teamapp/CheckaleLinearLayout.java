package com.example.inyoung.teamapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.FrameLayout;

/**
 * Created by MYpc on 2017-08-20.
 */

public class CheckaleLinearLayout extends FrameLayout implements Checkable {

    public CheckaleLinearLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    public void setChecked(boolean checked) {

    }

    @Override
    public boolean isChecked() {
        CheckBox cb = (CheckBox) findViewById(R.id.manager_checkbox);
        return cb.isChecked();
    }

    @Override
    public void toggle() {

    }
}
