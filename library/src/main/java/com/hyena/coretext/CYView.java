package com.hyena.coretext;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.hyena.coretext.blocks.CYBlock;
import com.hyena.coretext.blocks.CYLineBlock;
import com.hyena.coretext.blocks.CYPageBlock;
import com.hyena.coretext.layout.CYHorizontalLayout;
import com.hyena.coretext.layout.CYLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/4/8.
 */
public class CYView extends View {

    private CYLayout mLayout = new CYHorizontalLayout();

    private List<CYPageBlock> pages = new ArrayList<>();

    private List<CYBlock> mBlocks = new ArrayList<>();

    public CYView(Context context) {
        super(context);
    }

    public CYView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CYView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        pages = mLayout.parsePage(mBlocks, getWidth(), getHeight());
    }

    public void setLayout(CYLayout layout){
        this.mLayout = layout;
    }

    public void setBlocks(List<CYBlock> blocks) {
        this.mBlocks = blocks;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (pages == null || pages.isEmpty())
            return;

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
    }
}
