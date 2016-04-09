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

    public CYImageBlock(String content){
        super(content);
    }

    public CYImageBlock setResId(Context context, int resId){
        mBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        return this;
    }

    @Override
    public CYImageBlock setAlignStyle(AlignStyle style) {
        return (CYImageBlock) super.setAlignStyle(style);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mBitmap != null)
            canvas.drawBitmap(mBitmap, null, getRect(), null);
    }

    @Override
    public int getWidth() {
        if (mBitmap != null)
            return mBitmap.getWidth();
        return 100;
    }

    @Override
    public int getHeight() {
        if (mBitmap != null)
            return mBitmap.getHeight();
        return 100;
    }
}
