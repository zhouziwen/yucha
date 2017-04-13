package com.example.hnTea.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 太能 on 2016/11/10.
 */
public class PreManager {
    private static PreManager mInstance;

    private SharedPreferences mPreferences;

    private SharedPreferences.Editor mEditor;

    private PreManager(){

    };

    public static PreManager instance(){
        if (mInstance==null){
            mInstance =new PreManager();
        }
        return mInstance;
    }


    public void init(Context context){
        mPreferences=
                context.getSharedPreferences("preference",Context.MODE_APPEND);
        if (mPreferences!=null){
            mEditor=mPreferences.edit();
            mEditor.apply();
        }
    }

    //***********************  bool  *************************
    public void saveBool(String key,boolean bool){
        if (mEditor!=null){
            mEditor.putBoolean(key,bool);
            mEditor.commit();
        }
    }

    public boolean getBool(String key){
        if (mPreferences!=null){
            return mPreferences.getBoolean(key,false);
        }
        return false;
    }

    //***********************  string *************************
    public void saveString(String key,String value){
        if (mEditor!=null){
            mEditor.putString(key,value);
            mEditor.commit();
        }
    }


    public String getString(String key){
        if (mPreferences!=null){
            return mPreferences.getString(key,"");
        }
        return "";
    }



    public void remove(String key){
        mEditor.remove(key);
        mEditor.commit();
    }
}
