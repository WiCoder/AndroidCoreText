package com.hyena.coretext.style;

/**
 * 可替换的样式
 * Created by yangzc on 16/3/23.
 */
public class ReplaceStyle extends Style {

    private int mWidth, mHeight;

    public ReplaceStyle(int startIndex, int endIndex) {
        super(startIndex, endIndex);
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setWidth(int width) {
        this.mWidth = width;
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }


}
