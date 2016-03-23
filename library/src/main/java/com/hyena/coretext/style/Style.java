package com.hyena.coretext.style;

/**
 * 样式
 * Created by yangzc on 16/3/23.
 */
public abstract class Style {

    private int mStartIndex;
    private int mEndIndex;

    public Style() {
    }

    public Style(int startIndex, int endIndex) {
        this.mStartIndex = startIndex;
        this.mEndIndex = endIndex;
    }

    public void setStartIndex(int startIndex) {
        this.mStartIndex = startIndex;
    }

    public int getStartIndex() {
        return mStartIndex;
    }

    public void setEndIndex(int endIndex) {
        this.mEndIndex = endIndex;
    }

    public int getEndIndex() {
        return mEndIndex;
    }
}
