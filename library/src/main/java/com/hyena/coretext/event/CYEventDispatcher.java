package com.hyena.coretext.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/4/9.
 */
public class CYEventDispatcher {

    private static CYEventDispatcher mDispatcher;

    private List<CYLayoutEventListener> mLayoutListeners;

    private CYEventDispatcher() {
    }

    public static CYEventDispatcher getEventDispatcher() {
        if (mDispatcher == null)
            mDispatcher = new CYEventDispatcher();
        return mDispatcher;
    }

    public void addLayoutEventListener(CYLayoutEventListener listener) {
        if (mLayoutListeners == null)
            mLayoutListeners = new ArrayList<CYLayoutEventListener>();
        if (!mLayoutListeners.contains(listener)) {
            mLayoutListeners.add(listener);
        }
    }

    public void removeLayoutEventListener(CYLayoutEventListener listener) {
        if (mLayoutListeners == null)
            return;
        mLayoutListeners.remove(listener);
    }

    public void requestLayout() {
        requestLayout(false);
    }

    public void requestLayout(boolean force) {
        requestLayout(force, mPageWidth, mPageHeight);
    }

    private int mPageWidth, mPageHeight;

    public void requestLayout(boolean force, int pageWidth, int pageHeight) {
        if (mLayoutListeners == null || mLayoutListeners.isEmpty())
            return;

        if (force || pageWidth != mPageWidth || pageHeight != mPageHeight) {
            this.mPageWidth = pageWidth;
            this.mPageHeight = pageHeight;
            for (int i = 0; i < mLayoutListeners.size(); i++) {
                CYLayoutEventListener listener = mLayoutListeners.get(i);
                listener.onLayout(pageWidth, pageHeight);
            }
        }
    }

    public void postInvalidate() {
        if (mLayoutListeners == null || mLayoutListeners.isEmpty())
            return;
        for (int i = 0; i < mLayoutListeners.size(); i++) {
            CYLayoutEventListener listener = mLayoutListeners.get(i);
            listener.onInvalidate();
        }
    }
}
