package com.hyena.coretext.builder;

import android.text.TextUtils;

import com.hyena.coretext.StyledStringBuilder;
import com.hyena.coretext.block.Block;
import com.hyena.coretext.block.LayoutBlock;
import com.hyena.coretext.block.LineBlock;
import com.hyena.coretext.block.ReplaceBlock;
import com.hyena.coretext.block.TextBlock;
import com.hyena.coretext.style.ReplaceStyle;
import com.hyena.coretext.style.Style;
import com.hyena.coretext.style.TextStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/3/23.
 */
public class DefaultLayouBuilder implements LayoutBuilder {

    @Override
    public LayoutBlock buildLayout(StyledStringBuilder attrStr
            , int start, int width, int height) {
        List<Block> blocks = getBlocks(attrStr);
        return createLayout(blocks, width);
    }

    private LayoutBlock createLayout(List<Block> blocks, int layoutWidth) {
        int lineWidth = 0;
        LayoutBlock layout = new LayoutBlock();
        LineBlock lineBlock = new LineBlock();
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            Block.Size size = block.getSize();

            if (lineWidth + size.mWidth > layoutWidth) {
                //break line
                lineBlock = new LineBlock();
                layout.addLines(lineBlock);

                lineBlock.addBlock(block);
                lineWidth = size.mWidth;
            } else {
                lineBlock.addBlock(block);
                lineWidth += size.mWidth;
            }
        }
        return layout;
    }

    /**
     * 重新构造Styles
     * @param attrStr
     * @return
     */
    private List<Block> getBlocks(StyledStringBuilder attrStr){
        List<Block> blocks = new ArrayList<Block>();
        TextBlock textBlock = null;
        int startIndex = 0;
        int endIndex = 0;
        if (attrStr != null && !TextUtils.isEmpty(attrStr.getText())) {
            for (int i = 0; i < attrStr.getText().length(); i++) {
                Style style = attrStr.getStyle(i);
                if (style != null) {
                    if (style instanceof ReplaceStyle) {
                        if (textBlock != null && startIndex != endIndex) {
                            textBlock.setText(attrStr.getText().substring(startIndex, endIndex));
                            blocks.add(textBlock);
                        }
                        textBlock = null;
                        ReplaceBlock block = new ReplaceBlock();
                        block.setStyle(style);
                        blocks.add(block);

                        i = style.getEndIndex();
                    } else if (style instanceof TextStyle){
                        if (textBlock != null && startIndex != endIndex) {
                            textBlock.setText(attrStr.getText().substring(startIndex, endIndex));
                            blocks.add(textBlock);
                        }
                        textBlock = null;
                        TextBlock block = new TextBlock();
                        block.setStyle(style);
                        blocks.add(block);

                        i = style.getEndIndex();
                    } else {
                        if (textBlock == null) {
                            textBlock = new TextBlock();
                            startIndex = i;
                        }
                        endIndex = i;
                    }
                } else {
                    if (textBlock == null) {
                        textBlock = new TextBlock();
                        startIndex = i;
                    }
                    endIndex = i;
                }
            }
        }
        return blocks;
    }
}
