package com.hyena.coretext.blocks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/4/8.
 */
public class CYPageBlock {

    private List<CYLineBlock> mLines = new ArrayList<CYLineBlock>();

    public void addLines(CYLineBlock line){
        mLines.add(line);
    }

    public void clear(){
        mLines.clear();
    }

    public List<CYLineBlock> getLines(){
        return mLines;
    }

}
