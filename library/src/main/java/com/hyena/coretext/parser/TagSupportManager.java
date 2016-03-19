package com.hyena.coretext.parser;

import java.util.Hashtable;

/**
 * Created by yangzc on 16/3/19.
 */
public class TagSupportManager {

    private Hashtable<String, Class> mTagSupport = new Hashtable<String, Class>();

    private static TagSupportManager mInstance = null;

    private TagSupportManager() {

    }

    public static TagSupportManager getTagSupportManager() {
        if (mInstance == null)
            mInstance = new TagSupportManager();
        return mInstance;
    }
}
