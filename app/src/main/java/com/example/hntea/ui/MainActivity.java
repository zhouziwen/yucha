package com.example.hnTea.ui;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.manager.PreManager;
import com.example.hnTea.mvpmodel.user.bean.VersionModel;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserConfigPresenter;
import com.example.hnTea.ui.home.HomeFragment;
import com.example.hnTea.ui.me.MeFragment;
import com.example.hnTea.ui.partner.PartnerFragment;
import com.example.hnTea.ui.price.PriceFragment;
import com.example.hnTea.utils.FindViewUtils;
import com.example.hnTea.widget.PopFragment;
import com.umeng.socialize.UMShareAPI;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private UserConfigPresenter mUserConfigPresenter;
    private HomeFragment mHomeFragment;
    private PriceFragment mPriceFragment;
    private PartnerFragment mPartnerFragment;
    private MeFragment mMeFragment;
    private FragmentManager mFragmentManager;

    private RadioButton mTabHome, mTabPrice, mTabPartner, mTabMe;
    private ImageView mTabCenter;
    private PopFragment mPopFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        mFindViewUtils = new FindViewUtils(view);
        setContentView(view);
        changeStateNormal();
        registerReceiver(downloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    protected void initView() {
        super.initView();
        mUserConfigPresenter = new UserConfigPresenter(null);
        mFragmentManager = getSupportFragmentManager();
        mTabHome = (RadioButton) findViewById(R.id.main_tab_home);
        mTabPrice = (RadioButton) findViewById(R.id.main_tab_price);
        mTabPartner = (RadioButton) findViewById(R.id.main_tab_partner);
        mTabMe = (RadioButton) findViewById(R.id.main_tab_mine);
        mTabCenter = (ImageView) findViewById(R.id.main_tab_center);
        mHomeFragment = new HomeFragment();
//        mPartnerFragment = new PartnerFragment();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.container, mHomeFragment);
        transaction.commit();
    }

    @Override
    protected void setListener() {
        super.setListener();
        mTabHome.setOnClickListener(this);
        mTabPrice.setOnClickListener(this);
        mTabPartner.setOnClickListener(this);
        mTabMe.setOnClickListener(this);
        //tab下面的红枣
        mTabCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopFragment == null) {
                    mPopFragment = new PopFragment();
                }
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.add(android.R.id.content, mPopFragment);
                transaction.addToBackStack("pop");
                transaction.commit();
            }
        });
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        transaction.hide(mHomeFragment);
        if (mPriceFragment != null) {
            transaction.hide(mPriceFragment);
        }
        if (mPartnerFragment != null) {
            transaction.hide(mPartnerFragment);
        }
        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
        mTabHome.setChecked(false);
        mTabPrice.setChecked(false);
        mTabPartner.setChecked(false);
        mTabMe.setChecked(false);
    }

    @Override
    protected void setData() {
        super.setData();
        checkVersion();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideAllFragment(transaction);
        switch (v.getId()) {
            case R.id.main_tab_partner:
                if (mPartnerFragment == null) {
                    mPartnerFragment = new PartnerFragment();
                    transaction.add(R.id.container, mPartnerFragment);
                } else {
                    transaction.show(mPartnerFragment);
                }
                mTabPartner.setChecked(true);
                break;
            case R.id.main_tab_price:
                if (mPriceFragment == null) {
                    mPriceFragment = new PriceFragment();
                    transaction.add(R.id.container, mPriceFragment);
                } else {
                    transaction.show(mPriceFragment);
                }
                mTabPrice.setChecked(true);
                break;
            case R.id.main_tab_home:
                transaction.show(mHomeFragment);
                mTabHome.setChecked(true);
                break;
            case R.id.main_tab_mine:
                if (mMeFragment == null) {
                    mMeFragment = new MeFragment();
                    transaction.add(R.id.container, mMeFragment);
                } else {
                    transaction.show(mMeFragment);
                }
                mTabMe.setChecked(true);
                break;
        }
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        hiddenLoading();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final FragmentManager manager = getSupportFragmentManager();
            if (manager.getBackStackEntryCount() == 0) {
//                FragmentTransaction transaction = mFragmentManager.beginTransaction();
//                hideAllFragment(transaction);
//                transaction.show(mHomeFragment);
                //              mTabHome.setChecked(true);
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //不同的界面显示不同的状态栏
    private void changeStateNormal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    //友盟分享的activity的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    //***************************************版本更新*******************************************
    private void checkVersion() {
        String version = "";
        if (!TextUtils.isEmpty(PreManager.instance().getString("version"))) {
            version = PreManager.instance().getString("version");
        } else {
            version = "2.2";
        }
        mUserConfigPresenter.checkVersion(version, new IViewUser<VersionModel>() {
            @Override
            public void onSuccess(final VersionModel response) {
                //有新版本
                Dialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.btn_star)//设置对话框图标
                        .setTitle("更新提示：")//设置对话框标题
                        .setMessage(response.getIntro())//设置对话框内容
                        .setPositiveButton("确定", (dialog1, which) -> {
                            dialog1.dismiss();
                            //缓存版本号
                            PreManager.instance().saveString("version", response.getVersion());
                            //更新
//                                Intent intent = new Intent();
//                                intent.setAction("android.intent.action.VIEW");
//                                Uri content_url = Uri.parse(response.getSrc());
//                                intent.setData(content_url);
//                                startActivity(intent);
                            downLoadApk(response.getSrc());
                        })
                        .setNegativeButton("取消", (dialog12, which) -> dialog12.dismiss())
                        .setCancelable(false)
                        .create();
                dialog.show();
            }

            @Override
            public void onPhpFail(String var) {
                //没有新版本
                // showAlertWithMsg("您使用的是最新版");
            }

            @Override
            public void onStart(String var) {
            }

            @Override
            public void onFail(VolleyError volleyError) {
                showAlertWithMsg("请检查网络");
            }
        });
    }

    private void downLoadApk(String apkUrl) {
        Uri uri = Uri.parse(apkUrl);
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        // 设置允许使用的网络类型，这里是移动网络和wifi都可以
        request.setAllowedNetworkTypes(request.NETWORK_MOBILE | request.NETWORK_WIFI);
        //设置是否允许漫游
        request.setAllowedOverRoaming(false);
        //设置文件类型
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(apkUrl));
        request.setMimeType(mimeString);
        //在通知栏中显示
        request.setNotificationVisibility(request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("电力电安装包下载");
        request.setVisibleInDownloadsUi(true);
        //sdcard目录下的download文件夹
        request.setDestinationInExternalPublicDir("/download", "dld.apk");
        // 将下载请求放入队列
        downloadManager.enqueue(request);
    }

    private BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            installApk();
        }
    };

    private void installApk() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        String filePath = "/sdcard/download/dld.apk";
        i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
        startActivity(i);
    }
}
