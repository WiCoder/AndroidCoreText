package com.hyena.coretext.blocks;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/4/8.
 */
public class CYTextBlock extends CYBlock {

    private String text;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        paint.setTextSize(20);
    }

    public CYTextBlock(String text, Paint paint){
        this.text = text;
        this.paint = paint;
    }

    public CYTextBlock(String text, int fontSize){
        this.text = text;
        paint.setTextSize(fontSize);
    }

    @Override
    public List<CYBlock> getSubBlocks() {
        if (!TextUtils.isEmpty(text)) {
            List<CYBlock> blocks = new ArrayList<>();
            for (int i = 0; i < text.length(); i++) {
                String word = text.substring(i, i + 1);
                CYTextBlock block = new CYTextBlock(word, paint);
                blocks.add(block);
            }
            return blocks;
        }
        return null;
    }

    @Override
    public int getWidth() {
        return (int) paint.measureText(text);
    }

    @Override
    public int getHeight() {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 12;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(text, x, lineY + lineHeight - 10, paint);
    }
}
