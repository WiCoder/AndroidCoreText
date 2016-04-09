package com.hyena.coretext.blocks;

import android.graphics.Canvas;

/**
 * Created by yangzc on 16/4/8.
 */
public class CYPlaceHolderBlock extends CYBlock {

    public enum AlignStyle {
        Style_Round,
        Style_Single_Line
    }

    private AlignStyle mAlignStyle;
    private int mWidth, mHeight;

    public CYPlaceHolderBlock(String content){
        super(content);
    }

    public AlignStyle getAlignStyle(){
        return mAlignStyle;
    }
    public CYPlaceHolderBlock setAlignStyle(AlignStyle style){
        this.mAlignStyle = style;
        return this;
    }

    public CYPlaceHolderBlock setWidth(int width){
        this.mWidth = width;
        return this;
    }

    public CYPlaceHolderBlock setHeight(int height){
        this.mHeight = height;
        return this;
    }

    @Override
    public int getWidth() {
        return mWidth;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
