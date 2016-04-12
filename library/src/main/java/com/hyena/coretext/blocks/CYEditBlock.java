package com.hyena.coretext.blocks;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

/**
 * Created by yangzc on 16/4/12.
 */
public class CYEditBlock extends CYPlaceHolderBlock {

    private static final int ACTION_FLASH = 1;

    private Paint mLinePaint;
    private Paint mBgPaint;

    private boolean mInputHintVisible = false;
    private Handler mHandler;

    public CYEditBlock(String content) {
        super(content);
        init();
    }

    private void init(){

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(Color.GRAY);
        mBgPaint.setStrokeWidth(1);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStrokeWidth(2);

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageImpl(msg);
            }
        };
    }

    private void handleMessageImpl(Message msg) {
        int what = msg.what;
        switch (what) {
            case ACTION_FLASH: {
                mInputHintVisible = !mInputHintVisible;

                postInvalidate();

                Message next = mHandler.obtainMessage(ACTION_FLASH);
                mHandler.sendMessageDelayed(next, 500);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void setFocus(boolean focus) {
        super.setFocus(focus);
        if (focus) {
            mHandler.removeMessages(ACTION_FLASH);
            Message next = mHandler.obtainMessage(ACTION_FLASH);
            mHandler.sendMessageDelayed(next, 500);
        } else {
            mHandler.removeMessages(ACTION_FLASH);
            mInputHintVisible = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // 绘制外边框
        canvas.drawRect(getRect(), mBgPaint);
    }
}
