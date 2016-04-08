package com.hyena.coretext.layout;

import android.graphics.Rect;

import com.hyena.coretext.blocks.CYBlock;
import com.hyena.coretext.blocks.CYPlaceHolderBlock;
import com.hyena.coretext.blocks.CYLineBlock;
import com.hyena.coretext.blocks.CYPageBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/4/8.
 */
public class CYHorizontalLayout implements CYLayout {

    @Override
    public List<CYPageBlock> parsePage(List<CYBlock> blocks, int pageWidth, int pageHeight) {
        List<CYLineBlock> lines = parseLines(blocks, pageWidth);

        List<CYPageBlock> pages = new ArrayList<CYPageBlock>();
        CYPageBlock page = new CYPageBlock();
        if (lines != null) {
            for (int i = 0; i < lines.size(); i++) {
                page.addLines(lines.get(i));
            }
        }
        pages.add(page);
        return pages;
    }

    private List<CYLineBlock> parseLines(List<CYBlock> blocks, int pageWidth) {
        List<CYLineBlock> lines = new ArrayList<CYLineBlock>();
        List<CYPlaceHolderBlock> placeHolderBlocks = new ArrayList<CYPlaceHolderBlock>();

        int leftWidth = pageWidth;
        CYLineBlock line = new CYLineBlock();
        lines.add(line);

        List<CYPlaceHolderBlock> linePlaceHolderBlocks = new ArrayList<CYPlaceHolderBlock>();
        int y = 0;
        for (int i=0; i< blocks.size(); i++) {
            CYBlock itemBlock = blocks.get(i);
            if (itemBlock instanceof CYPlaceHolderBlock) {
                placeHolderBlocks.add((CYPlaceHolderBlock) itemBlock);

                if (itemBlock.getWidth() < leftWidth) {
                    itemBlock.x = pageWidth - leftWidth;
                    itemBlock.lineY = y;
                    leftWidth -= itemBlock.getWidth();
                } else {
                    y += line.getLineHeight();
                    leftWidth = pageWidth - itemBlock.getWidth();
                    line = new CYLineBlock();
                    lines.add(line);
                    linePlaceHolderBlocks = getLinePlaceHolderBlocks(placeHolderBlocks, y);
                    itemBlock.lineY = y;
                }
                line.addBlock(itemBlock);
            } else {
                List<CYBlock> subBlocks = itemBlock.getSubBlocks();
                if (subBlocks != null) {
                    for (int j = 0; j < subBlocks.size(); j++) {
                        CYBlock block = subBlocks.get(j);
                        if (block.getWidth() < leftWidth) {
                            CYPlaceHolderBlock hitCell;
                            while ((hitCell = getHitCell(linePlaceHolderBlocks, pageWidth - leftWidth, y,
                                    block.getWidth(), block.getHeight())) != null) {
                                leftWidth = leftWidth - hitCell.getWidth() - (hitCell.x - (pageWidth - leftWidth));
                            }

                            if (block.getWidth() < leftWidth) {
                                block.x = pageWidth - leftWidth;
                                block.lineY = y;
                                leftWidth -= block.getWidth();
                            } else {
                                y += line.getLineHeight();
                                linePlaceHolderBlocks = getLinePlaceHolderBlocks(placeHolderBlocks, y);
                                leftWidth = pageWidth - block.getWidth();
                                line = new CYLineBlock();
                                lines.add(line);
                                block.lineY = y;
                            }
                        } else {
                            leftWidth = pageWidth;
                            linePlaceHolderBlocks = getLinePlaceHolderBlocks(placeHolderBlocks, y);
                            y += line.getLineHeight();
                            CYBlock hitCell;
                            while ((hitCell = getHitCell(linePlaceHolderBlocks, pageWidth - leftWidth,y,
                                    block.getWidth(), block.getHeight())) != null) {
                                leftWidth = leftWidth - hitCell.getWidth() - (hitCell.x - (pageWidth - leftWidth));
                            }

                            line = new CYLineBlock();
                            lines.add(line);
                            block.lineY = y;
                            block.x = pageWidth - leftWidth;
                            leftWidth -= block.getWidth();
                        }

                        line.addBlock(block);
                    }
                }
            }
        }
        if (line != null) {
            line.measure();
        }
        return lines;
    }

    private List<CYPlaceHolderBlock> getLinePlaceHolderBlocks(
            List<CYPlaceHolderBlock> placeHolderBlocks, int y) {

        if (placeHolderBlocks == null || placeHolderBlocks.isEmpty()) {
            return null;
        }
        List<CYPlaceHolderBlock> linePlaceHolderBlocks = new ArrayList<>();
        for (int i = 0; i < placeHolderBlocks.size(); i++) {
            CYPlaceHolderBlock block = placeHolderBlocks.get(i);
            if (y >= block.lineY && y <= block.lineY + block.getHeight()) {
                linePlaceHolderBlocks.add(block);
            }
        }
        return linePlaceHolderBlocks;
    }

    private CYPlaceHolderBlock getHitCell(List<CYPlaceHolderBlock> linePlaceHolderBlocks
            , int x, int y, int width, int height){
        if (linePlaceHolderBlocks == null || linePlaceHolderBlocks.isEmpty())
            return null;
        for (int i = 0; i < linePlaceHolderBlocks.size(); i++) {
            CYPlaceHolderBlock cell = linePlaceHolderBlocks.get(i);

            if (new Rect(cell.x, cell.lineY, cell.x + cell.getWidth(),
                    cell.lineY + cell.getHeight()).intersect(new Rect(x, y, x+ width, y + height))) {
                return cell;
            }

        }
        return null;
    }
}
