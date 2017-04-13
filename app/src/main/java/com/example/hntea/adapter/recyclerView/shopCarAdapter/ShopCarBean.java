package com.example.hnTea.adapter.recyclerView.shopCarAdapter;

import java.util.List;

/**
 * Created by jason_syf on 2017/2/8.
 * Email: jason_sunyf@163.com
 */

public class ShopCarBean<T> {
        private String text;
        private List<T> list;
    private int status;

    public ShopCarBean() {
    }

    public ShopCarBean(String text, List<T> list, int status) {
        this.text = text;
        this.list = list;
        this.status = status;
    }


    public int getStatus() {
        return status;
    }



    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }
}
