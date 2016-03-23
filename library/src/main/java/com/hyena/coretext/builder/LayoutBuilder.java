package com.hyena.coretext.builder;

import com.hyena.coretext.StyledStringBuilder;
import com.hyena.coretext.block.LayoutBlock;

/**
 * Created by yangzc on 16/3/21.
 */
public interface LayoutBuilder {

    public LayoutBlock buildLayout(StyledStringBuilder attrStr
            , int start, int width, int height);

}
