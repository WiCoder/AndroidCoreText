package com.hyena.coretext.line;

import com.hyena.coretext.block.ACTBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/3/19.
 */
public class ACTLine {

    private int mLineNumber;
    private List<ACTBlock> mBlockList = new ArrayList<>();

    public ACTLine(int lineNumber) {
        this.mLineNumber = lineNumber;
    }

    public void addBlock(ACTBlock block) {
        mBlockList.add(block);
    }

    public void removeBlock(ACTBlock block) {
        mBlockList.remove(block);
    }

    public List<ACTBlock> getBlockList() {
        return mBlockList;
    }

    public int getLineNumber() {
        return mLineNumber;
    }

    public ACTBlock getBlock(int x, int y) {
        if (mBlockList == null || mBlockList.isEmpty())
            return null;
        for (int i = 0; i < mBlockList.size(); i++) {
            if (mBlockList.get(i).getRect().contains(x, y)) {
                return mBlockList.get(i);
            }
        }
        return null;
    }

    
}
