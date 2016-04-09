package com.hyena.coretext.blocks;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/4/8.
 */
public class CYTextBlock extends CYBlock {

    private String text;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<CYBlock> mSubBlocks = new ArrayList<CYBlock>();

    {
        mPaint.setTextSize(20);
    }

    public CYTextBlock(String content){
        super(content);
        this.text = content;
    }

    public CYTextBlock(String text, Paint paint){
        super(null);
        this.text = text;
        this.mPaint = paint;
    }

    public CYTextBlock(String text, int fontSize){
        super(null);
        this.text = text;
        mPaint.setTextSize(fontSize);
    }

    public CYTextBlock setTextColor(int color){
        mPaint.setColor(color);
        return this;
    }

    public CYTextBlock setTypeFace(Typeface typeface){
        mPaint.setTypeface(typeface);
        return this;
    }

    public CYTextBlock setTextSize(int fontSize){
        mPaint.setTextSize(fontSize);
        return this;
    }

    @Override
    public List<CYBlock> getSubBlocks() {
        if (mSubBlocks == null || mSubBlocks.isEmpty()) {
            parseSubBlocks();
        }
        return mSubBlocks;
    }

    private void parseSubBlocks(){
        if (mSubBlocks == null)
            mSubBlocks = new ArrayList<CYBlock>();
        mSubBlocks.clear();
        if (!TextUtils.isEmpty(text)) {
            for (int i = 0; i < text.length(); i++) {
                String word = text.substring(i, i + 1);
                CYTextBlock block = new CYTextBlock(word, mPaint);
                mSubBlocks.add(block);
            }
        }
    }

    @Override
    public int getWidth() {
        return (int) mPaint.measureText(text);
    }

    @Override
    public int getHeight() {
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 12;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(text, x, lineY + lineHeight - 10, mPaint);
    }
}
