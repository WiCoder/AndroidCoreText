package com.hyena.coretext.samples;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.hyena.coretext.AttributedString;
import com.hyena.coretext.CYView;
import com.hyena.coretext.blocks.CYBlock;
import com.hyena.coretext.blocks.CYPlaceHolderBlock;
import com.hyena.coretext.blocks.CYTextBlock;
import com.hyena.coretext.layout.CYHorizontalLayout;
import com.hyena.coretext.placeholder.CYImageBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/3/19.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CYView view = new CYView(this);

        List<CYBlock> blocks = new ArrayList<CYBlock>();
        blocks.add(new CYTextBlock("这").setTextSize(50).setTextColor(Color.RED));
        blocks.add(new CYTextBlock("是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。").setTextSize(30));
        blocks.add(new CYImageBlock("").setResId(this, R.drawable.baidu).setAlignStyle(CYPlaceHolderBlock.AlignStyle.Style_Single_Line));
        blocks.add(new CYTextBlock("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。").setTextSize(30));
        blocks.add(new CYImageBlock("").setResId(this, R.drawable.baidu).setAlignStyle(CYPlaceHolderBlock.AlignStyle.Style_Round));
        blocks.add(new CYTextBlock("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。").setTextSize(30));
        blocks.add(new CYImageBlock("").setResId(this, R.drawable.baidu).setAlignStyle(CYPlaceHolderBlock.AlignStyle.Style_Round));
        blocks.add(new CYTextBlock("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。").setTextSize(30));

        AttributedString string = new AttributedString("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。");
        string.replaceBlock(0, 1, CYTextBlock.class).setTextSize(50).setTextColor(Color.RED)
                .setTypeFace(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC));

        string.replaceBlock(30, 31, CYImageBlock.class).setResId(this, R.drawable.baidu)
                .setAlignStyle(CYPlaceHolderBlock.AlignStyle.Style_Round);

        view.setLayout(new CYHorizontalLayout());
//        view.setBlocks(blocks);
        view.setBlocks(string.buildBlocks());

        setContentView(view);
    }
}
