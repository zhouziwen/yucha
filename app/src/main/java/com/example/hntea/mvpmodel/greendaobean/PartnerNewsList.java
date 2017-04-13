package com.example.hnTea.mvpmodel.greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jason_syf on 2017/2/16.
 * Email: jason_sunyf@163.com
 */
@Entity(nameInDb = "news")
public class PartnerNewsList {
    @Id
    private Long id;
    private String newsId;
    private String newsTitle;
    private String newsTime;
    private int newsType;
    @Generated(hash = 2137379060)
    public PartnerNewsList(Long id, String newsId, String newsTitle,
            String newsTime, int newsType) {
        this.id = id;
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsTime = newsTime;
        this.newsType = newsType;
    }
    @Generated(hash = 1647328533)
    public PartnerNewsList() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNewsId() {
        return this.newsId;
    }
    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
    public String getNewsTitle() {
        return this.newsTitle;
    }
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
    public String getNewsTime() {
        return this.newsTime;
    }
    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }
    public int getNewsType() {
        return this.newsType;
    }
    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }
}
