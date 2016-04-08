package com.hyena.coretext.placeholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.hyena.coretext.blocks.CYPlaceHolderBlock;

/**
 * Created by yangzc on 16/4/8.
 */
public class CYImageBlock extends CYPlaceHolderBlock {

    private Bitmap mBitmap;

    public CYImageBlock(Context context, int resId, AlignStyle style) {
        super(style);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(mBitmap, null, getRect(), null);
    }

    @Override
    public int getWidth() {
        return mBitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return mBitmap.getHeight();
    }
}
