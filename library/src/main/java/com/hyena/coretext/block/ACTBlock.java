package com.hyena.coretext.block;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by yangzc on 16/3/19.
 */
public class ACTBlock {

    //x, y 均相对于父Block
    private int mX, mY;
    private int mWidth, mHeight;

    public void setX(int x) {
        this.mX = x;
    }

    public int getX() {
        return mX;
    }

    public void setY(int y) {
        this.mY = y;
    }

    public int getY() {
        return mY;
    }

    public void setWidth(int width) {
        this.mWidth = width;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public int getHeight() {
        return mHeight;
    }

    public Rect getRect() {
        return new Rect(mX, mY, mX + mWidth, mY + mHeight);
    }

    public void onDraw(Canvas canvas) {

    }
}
