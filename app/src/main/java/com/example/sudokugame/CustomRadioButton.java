package com.example.sudokugame;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import androidx.appcompat.widget.AppCompatRadioButton;

public class CustomRadioButton extends AppCompatRadioButton {
    public CustomRadioButton(Context context) {
        super(context);
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void toggle() {
        // if button object is checked change to unchecked
        if(this.isChecked()) {
            // cant use setchecked = false due to the logic of radio buttons...have to clear radiogroup
            if(getParent() instanceof RadioGroup) {
                ((RadioGroup) getParent()).clearCheck();
            }
        } else {
            this.setChecked(true);
        }
//        Log.i("toggleRadioButton", "_"+this.isChecked());
    }


}

