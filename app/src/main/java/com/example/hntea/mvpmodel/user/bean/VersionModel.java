package com.example.hnTea.mvpmodel.user.bean;

/**
 * Created by 太能 on 2017/1/12.
 */

public class VersionModel {
    private String id;
    private String version;
    private String src;
    private String intro;
    private String content;
    private String is_must;
    private String time;

    public VersionModel(String id, String version, String src, String intro, String content, String is_must, String time) {
        this.id = id;
        this.version = version;
        this.src = src;
        this.intro = intro;
        this.content = content;
        this.is_must = is_must;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIs_must() {
        return is_must;
    }

    public void setIs_must(String is_must) {
        this.is_must = is_must;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
