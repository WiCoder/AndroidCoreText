package com.hyena.coretext.event;

/**
 * Created by yangzc on 16/4/9.
 */
public interface CYLayoutEventListener {

    void onLayout(int pageWidth, int pageHeight);

    void onInvalidate();
}
