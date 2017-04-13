package com.example.hnTea.cache;

import com.example.hnTea.db.DaoSession;

import java.util.ArrayList;

import static com.example.hnTea.db.PartnerNewsListDao.TABLENAME;

/**
 * Created by jason_syf on 2017/2/16.
 * Email: jason_sunyf@163.com
 */

public abstract class BaseCache<T> {
    public static final String DB_NAME = TABLENAME;

    protected static DaoSession mDaoSession;

    public abstract void clearAllCache();

    public abstract ArrayList<T> getCacheById(int  page);

    public abstract void addResultCache(Long id,String newsId,String newsTitle,String newsTime,
                                        int newsType);
}
