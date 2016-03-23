package com.hyena.coretext;

import com.hyena.coretext.style.Style;

/**
 * Created by yangzc on 16/3/21.
 */
public class StyledStringBuilder {

    private StyledStringBuilder(){}

    public static StyledStringBuilder create(String text){
        StyledStringBuilder builder = new StyledStringBuilder();
        builder.setText(text);
        return builder;
    }

    public void replaceStyle(int start, int end, Style style) {
        if (mStyles == null)
            return;

        for (int i = 0; i < mStyles.length; i++) {
            if (mStartStyles[i] == start && mEndStyles[i] == end) {
                //replace
                mStyles[i] = style;
                return;
            }
        }

        resizeStyles(mStyles.length + 1);
        mStartStyles[mStyles.length - 1] = start;
        mEndStyles[mStyles.length - 1] = end;
        mStyles[mStyles.length - 1] = style;
    }

    /**
     * 设置文本内容
     * @param text 文本内容
     */
    private void setText(String text){
        this.mText = text;
    }

    /**
     * 获得文本
     * @return 获得文本
     */
    public String getText(){
        return mText;
    }

    /**
     * 获得字符串中第几个内容的style
     * @param strIndex 字符串索引
     * @return span索引
     */
    public Style getStyle(int strIndex){
        for (int i = 0; i < mStyles.length; i++) {
            if (strIndex >= mStartStyles[i] && strIndex <= mEndStyles[i]) {
                //replace
                return mStyles[i];
            }
        }
        return null;
    }

    public int getStyleStart(Object style){
        for (int i = 0; i < mStyles.length; i++) {
            if (mStyles[i] == style) {
                return mStartStyles[i];
            }
        }
        return -1;
    }

    public int getStyleEnd(Object style){
        for (int i = 0; i < mStyles.length; i++) {
            if (mStyles[i] == style) {
                return mEndStyles[i];
            }
        }
        return -1;
    }

    /**
     * resizeSpans
     * @param size 新的spanSize
     */
    private void resizeStyles(int size) {
        if (mStartStyles == null || mEndStyles == null || mStyles == null) {
            mStartStyles = new int[size];
            mEndStyles = new int[size];
            mStyles = new Style[size];
        } else if(mStartStyles.length < size){
            int startStyles[] = new int[size];
            int endStyles[] = new int[size];
            Style styles[] = new Style[size];
            System.arraycopy(mStartStyles, 0, startStyles, 0, mStartStyles.length);
            System.arraycopy(mEndStyles, 0, endStyles, 0, mEndStyles.length);
            System.arraycopy(mStyles, 0, styles, 0, mStyles.length);
            this.mStartStyles = startStyles;
            this.mEndStyles = endStyles;
            this.mStyles = styles;
        } else if(mStartStyles.length > size){
            int startStyles[] = new int[size];
            int endStyles[] = new int[size];
            Style styles[] = new Style[size];
            System.arraycopy(mStartStyles, 0, startStyles, 0, size);
            System.arraycopy(mEndStyles, 0, endStyles, 0, size);
            System.arraycopy(mStyles, 0, styles, 0, size);
            this.mStartStyles = startStyles;
            this.mEndStyles = endStyles;
            this.mStyles = styles;
        }
    }

    private String mText;
    private int mStartStyles[];
    private int mEndStyles[];
    private Style mStyles[];
}
