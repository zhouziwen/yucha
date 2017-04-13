package com.example.hnTea.wxapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hnTea.R;
import com.umeng.weixin.callback.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
    }
}
