package com.hyena.coretext.blocks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/4/8.
 */
public class CYLineBlock {

    private int mLineHeight;
    private List<CYBlock> mBlocks = new ArrayList<CYBlock>();

    public void addBlock(CYBlock block){
        mBlocks.add(block);
    }

    public void clear(){
        mLineHeight = 0;
        mBlocks.clear();
    }

    public List<CYBlock> getBlocks(){
        return mBlocks;
    }

    public int getLineHeight() {
        if (mLineHeight <= 0) {
            measure();
        }
        return mLineHeight;
    }

    public void measure() {
        measureLineHeight();
        syncBlocksHeight();
    }

    public void updateLineY(int lineY){
        if (mBlocks != null) {
            for (int i = 0; i < mBlocks.size(); i++) {
                mBlocks.get(i).lineY = lineY;
            }
        }
    }

    private void measureLineHeight(){
        if (mBlocks != null) {
            mLineHeight = 0;
            for (int i = 0; i < mBlocks.size(); i++) {
                CYBlock block = mBlocks.get(i);
                if (block instanceof CYTextBlock || (block instanceof CYPlaceHolderBlock
                        && ((CYPlaceHolderBlock)block).getAlignStyle() == CYPlaceHolderBlock
                        .AlignStyle.Style_Single_Line)) {
                    if (block.getHeight() > mLineHeight) {
                        mLineHeight = block.getHeight();
                    }
                }
            }
            if (mLineHeight <= 0) {
                mLineHeight = getMaxBlockHeightInLine();
            }
        }
    }

    public int getMaxBlockHeightInLine(){
        int maxHeight = 0;
        if (mBlocks != null) {
            for (int i = 0; i < mBlocks.size(); i++) {
                CYBlock block = mBlocks.get(i);
                if (block.getHeight() > maxHeight) {
                    maxHeight = block.getHeight();
                }
            }
        }
        return maxHeight;
    }

    private void syncBlocksHeight(){
        if (mBlocks != null) {
            for (int i = 0; i < mBlocks.size(); i++) {
                CYBlock block = mBlocks.get(i);
                block.lineHeight = mLineHeight;
            }
        }
    }

}
