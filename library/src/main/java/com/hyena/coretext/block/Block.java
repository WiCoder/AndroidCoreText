package com.hyena.coretext.block;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.hyena.coretext.style.Style;

/**
 * Created by yangzc on 16/3/21.
 */
public abstract class Block {

    private Paint mDefaultPaint;

    public abstract Size getSize();
    public abstract void onDraw(Canvas canvas, Paint paint);

    public void setPaint(Paint paint){
        this.mDefaultPaint = paint;
    }

    public Paint getPaint(){
        if (mDefaultPaint == null)
            mDefaultPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        return mDefaultPaint;
    }

    public void setStyle(Style style){

    }

    public class Size {
        public int mWidth;
        public int mHeight;

        public Size(int width, int height){
            this.mWidth = width;
            this.mHeight = height;
        }
    }
}
