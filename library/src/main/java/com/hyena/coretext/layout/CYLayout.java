package com.hyena.coretext.layout;

import com.hyena.coretext.blocks.CYBlock;
import com.hyena.coretext.blocks.CYLineBlock;
import com.hyena.coretext.blocks.CYPageBlock;

import java.util.List;

/**
 * Created by yangzc on 16/4/8.
 */
public interface CYLayout {

    /**
     * parse block to page
     * @param blocks blocks
     * @param pageWidth pageWidth
     * @param pageHeight pageHeight
     * @return pages
     */
    public List<CYPageBlock> parsePage(List<CYBlock> blocks, int pageWidth, int pageHeight);

}
