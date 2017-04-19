package com.example.hnTea;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;

import com.example.hnTea.db.DaoMaster;
import com.example.hnTea.db.DaoSession;
import com.example.hnTea.manager.PreManager;
import com.example.hnTea.utils.DisPlayUtils;
import com.example.hnTea.utils.logger.LogLevel;
import com.example.hnTea.utils.logger.Logger;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.io.File;

import static com.example.hnTea.db.PartnerNewsListDao.TABLENAME;

/**
 * Created by 太能 on 2016/11/10.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;
    private static DaoSession mDaoSeession;
    private static Context mContext;
    public static String registrationId;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = this;
        PreManager.instance().init(this);
        DisPlayUtils.init(this);
        if (BuildConfig.DEBUG) {
            Logger.init().hideThreadInfo().setMethodCount(1).setLogLevel(LogLevel.FULL);
        }
        //********************************初始化JPush*********************
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//        registrationId = JPushInterface.getRegistrationID(this);
//        Logger.i("1099", "run:--------->registrationId： "+registrationId );
        //********************************初始化友盟分享*********************
        UMShareAPI.get(this);
//        Config.DEBUG = true;
        PlatformConfig.setWeixin("wx3458fa2569f13641", "fc6d2f86f25aed58c1ef327e9eeb06e9");
        PlatformConfig.setSinaWeibo("3553809294", "fd3698f6f5d6fcd1152221dfb0823d64");
        PlatformConfig.setQQZone("1106033983", "0Pc5jmcgGO8D13gl");
        initDataBase(this);
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mContext;
    }

    //数据库的初始化
    private void initDataBase(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, TABLENAME, null);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSeession = daoMaster.newSession();
    }

    //DaoSession 的实例化 单例
    public static DaoSession getDaoSession() {
        return mDaoSeession;
    }

    //返回是否登录
    public boolean isLogin() {
        //true是没登录 false是登录
        return TextUtils.isEmpty(PreManager.instance().getString("userName"));
    }



    //判断当前版本大于5.0
    public boolean is21Version() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        } else {
            return false;
        }
    }
    //****************************用户信息相关************************************
    //  userName  用户手机号
    //  token     识别用户的凭证
    //  userNickName 用户昵称
    //  address 用户默认收货地址
    //  userRank 用户类型
    //  uid 用户uid

    public static String getUserName() {
        return PreManager.instance().getString("userName");
    }

    public static String getUserToken() {
        return PreManager.instance().getString("token");
    }

    public static String getUserNickName() {
        return PreManager.instance().getString("userNickName");
    }

    public static String getUserDefaultAddress() {
        return PreManager.instance().getString("address");
    }

    public static String getUserRank() {
        return PreManager.instance().getString("userRank");
    }

    public static String getUid() {
        return PreManager.instance().getString("uid");
    }

    public Bitmap getUserLocalHeader() {
        File file = getExternalFilesDir("/photo");
        String url = file + "/ycicon.pngng";
        Bitmap b = BitmapFactory.decodeFile(url);
        return b;
    }

    //用于退出登录
    public void logOut() {
        PreManager.instance().remove("userName");
        PreManager.instance().remove("token");
        PreManager.instance().remove("userNickName");
        PreManager.instance().remove("address");
        PreManager.instance().remove("userRank");
        PreManager.instance().remove("uid");
        File file = getExternalFilesDir("/photo");
        file.delete();
    }
}
