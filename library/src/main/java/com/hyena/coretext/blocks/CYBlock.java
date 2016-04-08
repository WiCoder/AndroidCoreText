package com.hyena.coretext.blocks;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.List;

/**
 * Created by yangzc on 16/4/8.
 */
public abstract class CYBlock {

    public int x;
    //Top left
    public int lineY;
    public int lineHeight;

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract void draw(Canvas canvas);

    public List<CYBlock> getSubBlocks() {
        return null;
    }

    public Rect getRect(){
        return new Rect(x, lineY, x + getWidth(), lineY + getHeight());
    }
}
