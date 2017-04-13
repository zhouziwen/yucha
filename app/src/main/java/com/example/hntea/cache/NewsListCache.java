package com.example.hnTea.cache;

import android.content.Context;

import com.example.hnTea.MyApplication;
import com.example.hnTea.db.PartnerNewsListDao;
import com.example.hnTea.mvpmodel.greendaobean.PartnerNewsList;
import com.example.hnTea.mvpmodel.partner.bean.PartnerNewsModel;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;

/**
 * Created by jason_syf on 2017/2/16.
 * Email: jason_sunyf@163.com
 */

public class NewsListCache extends BaseCache {
    private static NewsListCache instance;
    private static PartnerNewsListDao newsListDao;

    public NewsListCache() {
    }

    public static NewsListCache getInstance(Context context) {

        if (instance == null) {

            synchronized (NewsListCache.class) {
                if (instance == null) {
                    instance = new NewsListCache();
                }
            }

            mDaoSession = MyApplication.getDaoSession();
            newsListDao = mDaoSession.getPartnerNewsListDao();
        }
        return instance;
    }

    public void clearAllCache() {
        newsListDao.deleteAll();
    }

    @Override
    public ArrayList getCacheById(int page) {
        QueryBuilder<PartnerNewsList> query = newsListDao.queryBuilder().limit(10).offset(10 * page);
        if (query.list().size() > 0) {
            return (ArrayList<PartnerNewsModel>) JSONParser.toObject(query.list().toString(),
                    new TypeToken<ArrayList<PartnerNewsModel>>() {
                    }.getType());
        } else {
            return new ArrayList<PartnerNewsModel>();
        }
    }

    @Override
    public void addResultCache(Long id, String newsId, String newsTitle, String newsTime, int newsType) {
        PartnerNewsList newsList = new PartnerNewsList();
        newsList.setId(id);
        newsList.setNewsId(newsId);
        newsList.setNewsTitle(newsTitle);
        newsList.setNewsTime(newsTime);
        newsList.setNewsType(newsType);
        newsListDao.insert(newsList);
    }
}
