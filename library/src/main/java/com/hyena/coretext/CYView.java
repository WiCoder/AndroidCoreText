package com.hyena.coretext;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.hyena.coretext.blocks.CYBlock;
import com.hyena.coretext.blocks.CYLineBlock;
import com.hyena.coretext.blocks.CYPageBlock;
import com.hyena.coretext.blocks.CYPlaceHolderBlock;
import com.hyena.coretext.blocks.CYTextBlock;
import com.hyena.coretext.layout.CYHorizontalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/4/8.
 */
public class CYView extends View {

    private CYHorizontalLayout layout = new CYHorizontalLayout();

    private List<CYPageBlock> pages = new ArrayList<>();

    List<CYBlock> blocks = new ArrayList<>();
    {
        blocks.add(new CYTextBlock("这", 50));
        blocks.add(new CYTextBlock("是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。", 30));
        blocks.add(new CYPlaceHolderBlock(100, 100, CYPlaceHolderBlock.AlignStyle.Style_Round));
        blocks.add(new CYTextBlock("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。", 20));
        blocks.add(new CYPlaceHolderBlock(100, 100, CYPlaceHolderBlock.AlignStyle.Style_Round));
        blocks.add(new CYTextBlock("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。", 30));
        blocks.add(new CYPlaceHolderBlock(100, 100, CYPlaceHolderBlock.AlignStyle.Style_Round));
        blocks.add(new CYTextBlock("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。", 30));
    }

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
        pages = layout.parsePage(blocks, getWidth(), getHeight());
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
