package com.example.hnTea.adapter.recyclerView.shopCarAdapter;

/**
 * Created by jason_syf on 2017/2/8.
 * Email: jason_sunyf@163.com
 */

public class Cbean {
    private int status;
    private String title;
    private String price;
    private int count;
    private String pic;

    public Cbean(int status, String title, String price, int count, String pic) {
        this.status = status;
        this.title = title;
        this.price = price;
        this.count = count;
        this.pic = pic;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
