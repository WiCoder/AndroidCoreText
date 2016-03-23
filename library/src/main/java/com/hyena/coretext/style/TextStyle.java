package com.hyena.coretext.style;

import android.graphics.Paint;

/**
 * Created by yangzc on 16/3/23.
 */
public class TextStyle extends Style {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TextStyle() {}

    public TextStyle(int startIndex, int endIndex) {
        super(startIndex, endIndex);
    }

    public Paint getPaint(){
        return mPaint;
    }
}
