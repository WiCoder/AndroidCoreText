package com.hyena.coretext.samples;

import android.app.Activity;
import android.os.Bundle;

import com.hyena.coretext.CYView;

/**
 * Created by yangzc on 16/3/19.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CYView(this));
    }
}
