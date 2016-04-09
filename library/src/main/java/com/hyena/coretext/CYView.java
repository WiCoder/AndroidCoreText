package com.hyena.coretext;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hyena.coretext.blocks.CYBlock;
import com.hyena.coretext.blocks.CYLineBlock;
import com.hyena.coretext.blocks.CYPageBlock;
import com.hyena.coretext.event.CYEventDispatcher;
import com.hyena.coretext.event.CYLayoutEventListener;
import com.hyena.coretext.layout.CYHorizontalLayout;
import com.hyena.coretext.layout.CYLayout;
import com.hyena.coretext.utils.CYBlockUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/4/8.
 */
public class CYView extends View {

    private CYLayout mLayout = new CYHorizontalLayout();

    private List<CYPageBlock> pages = new ArrayList<CYPageBlock>();

    private List<CYBlock> mBlocks = new ArrayList<CYBlock>();

    public CYView(Context context) {
        super(context);
        init();
    }

    public CYView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CYView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        CYEventDispatcher.getEventDispatcher().addLayoutEventListener(mLayoutEventListener);
    }

    public void release() {
        CYEventDispatcher.getEventDispatcher().removeLayoutEventListener(mLayoutEventListener);
    }

    private CYLayoutEventListener mLayoutEventListener = new CYLayoutEventListener() {

        @Override
        public void onLayout(int width, int height) {
            if (mBlocks == null || mBlocks.isEmpty())
                return;

            pages = mLayout.parsePage(mBlocks, width, height);
            postInvalidate();
        }

        @Override
        public void onInvalidate() {
            postInvalidate();
        }

    };

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        CYEventDispatcher.getEventDispatcher()
                .requestLayout(false, getWidth() - getPaddingLeft() - getPaddingRight()
                        , getHeight() - getPaddingTop() - getPaddingBottom());
    }

    public void setLayout(CYLayout layout) {
        this.mLayout = layout;
    }

    public void setBlocks(List<CYBlock> blocks) {
        this.mBlocks = blocks;
    }

    private CYBlock mTouchBlock;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                CYBlock touchBlock = CYBlockUtils.findBlockByPosition(mBlocks,
                        (int) event.getX(), (int) event.getY());
                if (touchBlock != mTouchBlock) {
                    if (mTouchBlock != null)
                        mTouchBlock.setFocus(false);

                    mTouchBlock = touchBlock;

                    if (mTouchBlock != null)
                        mTouchBlock.setFocus(true);
                }
                if (mTouchBlock != null) {
                    mTouchBlock.onTouchEvent(action, event.getX() - mTouchBlock.x,
                            event.getY() - mTouchBlock.lineY);
                }
                break;
            }
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE: {
                if (mTouchBlock != null) {
                    mTouchBlock.onTouchEvent(action, event.getX() - mTouchBlock.x,
                            event.getY() - mTouchBlock.lineY);
                }
                break;
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (pages == null || pages.isEmpty())
            return;

        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());

        for (int i = 0; i < pages.size(); i++) {
            CYPageBlock page = pages.get(i);
            List<CYLineBlock> lines = page.getLines();
            for (int j = 0; j < lines.size(); j++) {
                CYLineBlock line = lines.get(j);
                for (int k = 0; k < line.getBlocks().size(); k++) {
                    line.getBlocks().get(k).draw(canvas);
                }
            }
        }

        canvas.restore();
    }
}
