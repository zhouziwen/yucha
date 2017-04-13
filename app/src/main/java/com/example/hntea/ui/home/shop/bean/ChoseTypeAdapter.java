package com.example.hnTea.ui.home.shop.bean;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hnTea.R;
import com.example.hnTea.mvpmodel.home.bean.ShopDetail_Attr;
import com.example.hnTea.mvpmodel.home.bean.ShopDetail_Num;
import com.example.hnTea.utils.SystemUtil;
import com.example.hnTea.widget.ChoseViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 太能 on 2017/2/7.
 */

public abstract class ChoseTypeAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShopDetail_Attr> mData;
    private List<List<String>> choseList = new ArrayList<>();
    //    private List<List<String>> mSessionList = new ArrayList<>();
    private List<TextView> mViews = new ArrayList<>();

    //用于保存用户的属性集合
    private HashMap<String, String> selectProMap = new HashMap<String, String>();

    public ChoseTypeAdapter(Context context, List<ShopDetail_Attr> data, List<ShopDetail_Num> num) {
        super();
        this.mContext = context;
        if (data != null) {
            mData = data;
        }
        if (num != null) {
            for (int i = 0; i < num.size(); i++) {
                choseList.add(num.get(i).getStrings());
            }
//            for (int i = 0; i < choseList.size(); i++) {
//                List<String> list = new ArrayList<>();
//                mSessionList.add(list);
//            }
//
//            for (int i = 0; i < choseList.size(); i++) {
//                List<String> list = choseList.get(i);
//                for (int j = 0; j < list.size(); j++) {
//                    mSessionList.get(j).add(list.get(j));
//                }
//            }
        }
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (mData != null) {
            return mData.get(position);
        } else {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.action_chose_type_list_item, null, true);
//            holder = new ViewHolder();
//            holder.tvPropName = (TextView) convertView
//                    .findViewById(R.id.choseType_listItem_title);
//            holder.mGroups = (ChoseViewGroup) convertView.findViewById(R.id.choseType_listItem_viewGroup);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
        ViewHolder holder = new ViewHolder();
        convertView = LayoutInflater.from(this.mContext).inflate(R.layout.action_chose_type_list_item, null, true);
        holder.tvPropName = (TextView) convertView
                .findViewById(R.id.choseType_listItem_title);
        holder.mGroups = (ChoseViewGroup) convertView.findViewById(R.id.choseType_listItem_viewGroup);

        if (mData != null) {
            String type = mData.get(position).getName();
            holder.tvPropName.setText(type);
            List<String> items = new ArrayList<>();
            if (mData.get(position).getItems() != null) {
                for (int i = 0; i < mData.get(position).getItems().size(); i++) {
                    items.add(mData.get(position).getItems().get(i).getAttr_value());
                }

                if (holder.mGroups.getChildCount() == 0) {
                    TextView[] textViews = new TextView[items.size()];
                    //设置每个标签的文本和布局
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(20, 20, 20, 20);
                    for (int i = 0; i < items.size(); i++) {
                        TextView textView = new TextView(mContext);
                        textView.setGravity(Gravity.CENTER);
                        textView.setLayoutParams(params);
                        textView.setPadding(25, 25, 25, 25);
                        textViews[i] = textView;
                        textViews[i].setText(items.get(i));
                        textViews[i].setTag(i);
                        setTextStates(textView, 1);
                        holder.mGroups.addView(textViews[i]);
                        // mViews.add(textView);
                    }
                    for (int j = 0; j < textViews.length; j++) {
                        textViews[j].setTag(textViews);
                        textViews[j].setOnClickListener(new ItemClickListener(position, j, type));
                    }

                    /**判断之前是否已选中标签*/
                    if (selectProMap.get(type) != null) {
                        for (int h = 0; h < holder.mGroups.getChildCount(); h++) {
                            TextView v = (TextView) holder.mGroups.getChildAt(h);
                            if (selectProMap.get(type).equals(v.getText().toString())) {
                                setTextStates(v, 2);
                                selectProMap.put(type, v.getText().toString());
                            }
                        }
                    }
                }
            }
        }
        return convertView;
    }

    private HashMap<String, String> mSave = new HashMap<>();
    private List<String> mPosition = new ArrayList<>();
    private List<String> mCount = new ArrayList<>();

    public abstract void changeStates(String s);

    public abstract void selectorAll(int position);


    public class ViewHolder {
        TextView tvPropName;
        ChoseViewGroup mGroups;
    }

    class ItemClickListener implements View.OnClickListener {
        //position为当前cell的行号
        private int position;
        //count为当前每一行的 item的索引值
        private int count;

        private String type;

        public ItemClickListener(int position, int count, String type) {
            this.position = position;
            this.count = count;
            this.type = type;
        }

        @Override
        public void onClick(View v) {
            TextView[] textViews = (TextView[]) v.getTag();
            TextView tv = (TextView) v;
            String now = mData.get(position).getItems().get(count).getGoods_attr_id();

            for (int i = 0; i < textViews.length; i++) {
                if (textViews[i].equals(tv)) {
                    setTextStates(textViews[i], 2);
                    selectProMap.put(type, textViews[i].getText().toString());
                } else {
                    setTextStates(textViews[i], 1);
                }
            }

            //用一个map存选中的值 key为position value为当前item的id
            if (mSave.size() == 0) {
                mSave.put("" + position, now);
            } else {
                if (mSave.containsKey("" + position)) {
                    mSave.remove("" + position);
                    mSave.put("" + position, now);
                } else {
                    mSave.put("" + position, now);
                }
            }

            mPosition = SystemUtil.getListByMap(mSave, true);
            mCount = SystemUtil.getListByMap(mSave, false);

//            for (List<String> list : choseList) {
//                int u = 0;
//                for (int i = 0; i < mPosition.size(); i++) {
//
//                    if (list.get(Integer.parseInt(mPosition.get(i))).equals(mCount.get(i))) {
//                        u++;
//                    }
//                    if (u == mPosition.size()) {
//                        value = 1000;
//                        break;
//                    }
//                }
//            }


//            if (mValue.size() == 0) {
//                for (int i = 0; i < choseList.size(); i++) {
//                    mValue.add("" + i);
//                }
//
//                mValue = find(mValue, now);
//            } else {
//
//                mValue = find(mValue, now);
//            }
//
//            Log.i("item", mValue.toString());


            boolean b = false;
            for (List<String> big : choseList) {
                if (as(big, mCount)) {
                    //有货
                    b = true;
                    break;
                }
            }

            if (b) {
                //有货
                changeStates("1");
                //如果用户 全部选择完毕
                if (mCount.size() == choseList.get(0).size()) {
                    int i = asSelectorAll();
                    if (i != -1) {
                        selectorAll(i);
                    }
                }
            } else {
                //无货
                changeStates("2");
            }
        }
    }

    private int asSelectorAll() {
        for (int i = 0; i < choseList.size(); i++) {
            List<String> list = choseList.get(i);
            if (list.containsAll(mCount)) {
                return i;
            }
        }
        return -1;
    }

    private boolean as(List<String> big, List<String> small) {
        boolean is = true;
        for (String s : small) {
            if (!big.contains(s)) {
                is = false;
            }
        }
        return is;
    }

//    private List<String> find(List<String> ints, String now) {
//        List<String> l = new ArrayList<>();
//        for (int i = 0; i < mPosition.size(); i++) {
//            List<String> list = mSessionList.get(Integer.parseInt(mPosition.get(i)));
//            for (int j = 0; j < ints.size(); j++) {
//                if (list.get(Integer.parseInt(ints.get(j))).equals(now)) {
//                    l.add("" + ints.get(j));
//                }
//            }
//        }
//
//        if (l.size() == 0) {
//            clicks("没货");
//        }
//        return l;
//    }


    // 3种 item状态
    // 1:可以点击 默认的
    // 2:可以点击 选中的
    // 3:不可以点击的
    private void setTextStates(TextView textView, int position) {
        switch (position) {
            case 1:
                textView.setBackgroundResource(R.drawable.chose_item_nomal);
                textView.setTextColor(Color.parseColor("#666666"));
                break;
            case 2:
                textView.setBackgroundResource(R.drawable.chose_item_selector);
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 3:
                textView.setTextColor(Color.parseColor("#bfc2c2"));
                textView.setBackgroundResource(R.drawable.chose_item_nomal);
                break;
        }
    }
}