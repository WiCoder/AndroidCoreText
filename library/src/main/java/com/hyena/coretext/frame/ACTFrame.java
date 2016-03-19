package com.hyena.coretext.frame;

import com.hyena.coretext.block.ACTBlock;
import com.hyena.coretext.block.BlankBlock;
import com.hyena.coretext.line.ACTLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/3/19.
 */
public class ACTFrame extends ACTBlock {

    private List<ACTLine> mLineList = new ArrayList<ACTLine>();
    private List<BlankBlock> mBlankBlock = new ArrayList<BlankBlock>();

    public void addLine(ACTLine line) {
        mLineList.add(line);
    }

    public void removeLine(ACTLine line) {
        mLineList.remove(line);
    }

    public List<ACTLine> getLineList() {
        return mLineList;
    }

    public void addBlankBlock(BlankBlock block) {
        mBlankBlock.add(block);
    }

    public void removeBlankBlock(BlankBlock block) {
        mBlankBlock.remove(block);
    }

    public List<BlankBlock> getBlankBlock() {
        return mBlankBlock;
    }
}
