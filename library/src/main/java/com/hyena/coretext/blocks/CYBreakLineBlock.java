package com.hyena.coretext.blocks;

import android.graphics.Canvas;

/**
 * Created by yangzc on 16/4/9.
 */
public class CYBreakLineBlock extends CYBlock {

    public CYBreakLineBlock(String content) {
        super(content);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
