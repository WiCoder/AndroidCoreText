package com.hyena.coretext.parser;

import com.hyena.coretext.frame.ACTFrame;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangzc on 16/3/19.
 */
public class ACTParser {

    public List<ACTFrame> parse(String rawTxt) {
        String tagPattern = getTagRegPatten();
//        if (TextUtils.isEmpty(tagPattern)) {
//            throw new RuntimeException("please override getTagPattern and tagPattern can be null!!!");
//        }
        Pattern pattern = Pattern.compile(tagPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(rawTxt);

        List<TextBlock> mTextBlock = new LinkedList<>();
        int index = 0;
        while (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();

            TextBlock block = new TextBlock();
            block.mStartIndex = index;
            block.mEndIndex = startIndex;
            if (index != startIndex) {
                mTextBlock.add(block);
            }

            TextBlock tagBlock = new TextBlock();
            tagBlock.mStartIndex = startIndex;
            tagBlock.mEndIndex = endIndex;
            if (tagBlock.mStartIndex != tagBlock.mEndIndex) {
                mTextBlock.add(tagBlock);
            }
            index = endIndex;
        }

        for (int i = 0; i < mTextBlock.size(); i++) {
            TextBlock block = mTextBlock.get(i);
            System.out.println(rawTxt.substring(block.mStartIndex, block.mEndIndex));
        }
        return null;
    }

    private String getTagRegPatten() {
        return "<.*?>";
    }

    public static void main(String[] params){
        System.out.println("Hello world!!!");

        ACTParser parser = new ACTParser();
        String html = "<root>123<div>456<font>789</font>012</div>345<root>";
        parser.parse(html);
    }

    class TextBlock {
        int mStartIndex;
        int mEndIndex;
    }
}
