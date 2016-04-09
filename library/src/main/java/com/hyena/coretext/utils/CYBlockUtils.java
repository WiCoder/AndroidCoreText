package com.hyena.coretext.utils;

import com.hyena.coretext.blocks.CYBlock;
import com.hyena.coretext.blocks.CYTextBlock;

import java.util.List;

/**
 * Created by yangzc on 16/4/9.
 */
public class CYBlockUtils {

    public static CYBlock findBlockByPosition(List<CYBlock> blocks, int x, int y){
        if (blocks == null || blocks.isEmpty())
            return null;
        for (int i = 0; i < blocks.size(); i++) {
            CYBlock block = blocks.get(i);
            if (block instanceof CYTextBlock) {
                List<CYBlock> subBlocks = block.getSubBlocks();
                if (subBlocks != null && !subBlocks.isEmpty()) {
                    for (int j = 0; j < subBlocks.size(); j++) {
                        CYBlock subBlock = subBlocks.get(j);
                        if (subBlock.getRect().contains(x, y)){
                            return block;
                        }
                    }
                }
            } else {
                if (block.getRect().contains(x, y)) {
                    return block;
                }
            }
        }
        return null;
    }

}
