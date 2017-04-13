package com.example.hnTea.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.example.hnTea.R;
import com.example.hnTea.manager.PreManager;
import com.example.hnTea.ui.MainActivity;

public class SplashActivity extends Activity {
    private Handler handler = new Handler();
    private Runnable runnable;
    private RelativeLayout mRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_splash);
        if (PreManager.instance().getBool("once")) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }, 100);
        } else {
            //是第一次启动
            Intent intent = new Intent();
            intent.setClass(SplashActivity.this,UserGuideActivity.class);
            startActivity(intent);
        }
        mRootView = (RelativeLayout) findViewById(R.id.root);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(10);
        mRootView.startAnimation(animation);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            if (runnable != null)
                handler.removeCallbacks(runnable);
        }
        return super.onTouchEvent(event);
    }
}
