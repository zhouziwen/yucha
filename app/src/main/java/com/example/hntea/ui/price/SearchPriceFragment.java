package com.example.hnTea.ui.price;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.apcontains.FragmentTags;
import com.example.hnTea.apcontains.OnPopWinDisMisBack;
import com.example.hnTea.mvpmodel.price.bean.B_SingleModel;
import com.example.hnTea.mvpmodel.price.bean.X_ProjectModel;
import com.example.hnTea.mvpmodel.price.bean.X_SingleModel;
import com.example.hnTea.mvppresenter.price.IViewPrice;
import com.example.hnTea.mvppresenter.price.PricePresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.utils.ActionSheet;
import com.example.hnTea.utils.DisPlayUtils;
import com.example.hnTea.utils.ShowFragmentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchPriceFragment extends BaseFragment implements View.OnClickListener {
    private PricePresenter mPricePresenter;
    private TextView mBack;
    private ImageButton mPop, mClear;
    private EditText mEditText;
    private ActionSheet mActionSheet;
    private CommonAdapter<String> mLeftAdapter, mRightAdapter;
    private ListView mX_Single_List,mX_Project_List,mB_Single_List;
    private CommonAdapter<X_SingleModel> mX_singleAdapter;
    private CommonAdapter<X_ProjectModel> mX_projectAdapter;
    private CommonAdapter<B_SingleModel> mB_singleAdapter;
    private PriceDetailsFragment mPriceDetailsFragment;
    private int leftId = 0;
    private int rightId=100;

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_price, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mPricePresenter =new PricePresenter(null);
        mBack = mFindViewUtils.findViewById(R.id.title_back);
        mPop = mFindViewUtils.findViewById(R.id.top_search_to);
        mClear = mFindViewUtils.findViewById(R.id.top_search_clear);
        mEditText = mFindViewUtils.findViewById(R.id.query);
        mX_Single_List =mFindViewUtils.findViewById(R.id.X_Single_listView);
        mX_Project_List =mFindViewUtils.findViewById(R.id.X_Project_listView);
        mB_Single_List =mFindViewUtils.findViewById(R.id.B_Single_listView);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBack.setOnClickListener(this);
        mPop.setOnClickListener(this);
        mClear.setOnClickListener(this);
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    hideSoftKeyboard();
                    if (rightId==100){
                        showAlertWithMsg("请在左侧选择分类");
                    }else {
                        if (TextUtils.isEmpty(getText(mEditText))){
                            showAlertWithMsg("请输入搜索内容");
                        }else {
                            //开始搜索
                            setListHidden(leftId);
                            switch (leftId){
                                case 0:
                                    //产品询价
                                    upDataX_Single(rightId,getText(mEditText),1);
                                    break;
                                case 1:
                                    //项目询价
                                    upDataX_Project(rightId,getText(mEditText),1);
                                    break;
                                case 2:
                                    //产品报价
                                    upDataB_Single(rightId,getText(mEditText),1);
                                    break;
                            }
                        }
                    }
                }
                return false;
            }
        });
    }

    private void upDataX_Single(int who,String var,int page){
        String brand="";
        String product_name ="";
        switch (who){
            case 0:
                brand =var;
                break;
            case 1:
                product_name =var;
                break;
        }
        mPricePresenter.getX_SinglePriceListData(brand, "", product_name, page,
                new IViewPrice<List<X_SingleModel>>() {
            @Override
            public void onSuccess(List<X_SingleModel> data) {
                hiddenLoading();
                if (data==null){
                    showAlertWithMsg("暂无相关搜索");
                }else {
                    mX_singleAdapter.update(data);
                }
            }

            @Override
            public void onPhpFail(String var) {
                hiddenLoading();
                showAlertWithMsg(var);
            }

            @Override
            public void onStart(String var) {
                showLoading();
            }

            @Override
            public void onFail(VolleyError volleyError) {
                hiddenLoading();
                showAlertWithMsg("请检查网络");
            }
        });
    }

    private void upDataX_Project(int who,String var,int page){
        String provinc ="";
        String project_type ="";
        String project_name ="";
        switch (who){
            case 0:
                provinc =var;
                break;
            case 1:
                project_type =var;
                break;
            case 2:
                project_name =var;
                break;
        }
        mPricePresenter.getX_ProjectListData(provinc, "", project_type, project_name, page,
                new IViewPrice<List<X_ProjectModel>>() {
                    @Override
                    public void onSuccess(List<X_ProjectModel> data) {
                        hiddenLoading();
                        if (data==null){
                            showAlertWithMsg("暂无相关搜索");
                        }else {
                            mX_projectAdapter.update(data);
                        }
                    }

                    @Override
                    public void onPhpFail(String var) {
                        hiddenLoading();
                        showAlertWithMsg(var);
                    }

                    @Override
                    public void onStart(String var) {
                        showLoading();
                    }

                    @Override
                    public void onFail(VolleyError volleyError) {
                        hiddenLoading();
                        showAlertWithMsg("请检查网络");
                    }
                });

    }

    private void upDataB_Single(int who,String var,int page){
        String brand ="";
        String product_name ="";
        switch (who){
            case 0:
                brand =var;
                break;
            case 1:
                product_name =var;
                break;
        }
        mPricePresenter.getB_SinglePriceListData(page, brand, "", product_name,
                new IViewPrice<List<B_SingleModel>>() {
                    @Override
                    public void onSuccess(List<B_SingleModel> data) {
                        hiddenLoading();
                        if (data==null){
                            showAlertWithMsg("暂无相关搜索");
                        }else {
                            mB_singleAdapter.update(data);
                        }
                    }

                    @Override
                    public void onPhpFail(String var) {
                        hiddenLoading();
                        showAlertWithMsg(var);
                    }

                    @Override
                    public void onStart(String var) {
                        showLoading();
                    }

                    @Override
                    public void onFail(VolleyError volleyError) {
                        hiddenLoading();
                    }
                });

    }

    @Override
    protected void setData() {
        super.setData();
        setX_Single_ListViewData();
        setX_Project_ListViewData();
        setB_Single_ListViewData();
    }

    private void setX_Single_ListViewData(){
        mX_singleAdapter = new CommonAdapter<X_SingleModel>(getContext(),
                null,
                R.layout.x_price_single_list_item
        ) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnClickListener(R.id.x_price_single_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, X_SingleModel item) {
                holder.setText(R.id.XPrice_listItem_title,
                        "【" + item.getInquiry_sn() + "】  " + item.getProduct_name())
                        .setText(R.id.XPrice_listItem_time, item.getTime())
                        .setText(R.id.XPrice_listItem_location, item.getBrand())
                        .setText(R.id.XPrice_listItem_type, item.getQuantity() + item.getUnit());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                Bundle bundle = new Bundle();
                bundle.putString("inquiry_sn2", mX_singleAdapter.getItem(position).getInquiry_sn());

                if (mPriceDetailsFragment == null){
                    mPriceDetailsFragment =new PriceDetailsFragment();
                }
                bundle.putInt("type",2);
                ShowFragmentUtils.showFragment(getActivity(),
                        mPriceDetailsFragment.getClass(),
                        FragmentTags.FRAGMENT_PRICE_DETAIL,
                        bundle,true);
            }
        };
        mX_Single_List.setAdapter(mX_singleAdapter);
    }

    private void setX_Project_ListViewData(){
        mX_projectAdapter =new CommonAdapter<X_ProjectModel>(getContext(), null, R.layout.xprice_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnclickListener(R.id.XPrice_Layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, X_ProjectModel item) {
                holder.setText(R.id.XPrice_listItem_title,
                        "【" + item.getInquiry_sn() + "】  " + item.getProject_name())
                        .setText(R.id.XPrice_listItem_time,item.getTime())
                        .setText(R.id.XPrice_listItem_location,item.getProvince())
                        .setText(R.id.XPrice_listItem_type,item.getProject_type())
                        .setText(R.id.XPrice_listItem_type1,item.getStage());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                Bundle bundle = new Bundle();
                bundle.putString("inquiry_sn1", mX_projectAdapter.getItem(position).getInquiry_sn());
                if (mPriceDetailsFragment == null){
                    mPriceDetailsFragment =new PriceDetailsFragment();
                }

                bundle.putInt("type",1);
                ShowFragmentUtils.showFragment(getActivity(),
                        mPriceDetailsFragment.getClass(),
                        FragmentTags.FRAGMENT_PRICE_DETAIL,
                        bundle,true);
            }
        };
        mX_Project_List.setAdapter(mX_projectAdapter);
    }

    private void setB_Single_ListViewData(){
        mB_singleAdapter = new CommonAdapter<B_SingleModel>(getContext(), null, R.layout.b_price_single_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnclickListener(R.id.b_price_single_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, B_SingleModel item) {
                //XPrice_listItem_type1 截事件
                holder.setText(R.id.XPrice_listItem_title,
                        "【" + item.getInquiry_sn() + "】  "+item.getProduct_name())
                        .setText(R.id.XPrice_listItem_time,item.getComany_name())
                        .setText(R.id.XPrice_listItem_location,item.getQuantity()+item.getUnit())
                        .setText(R.id.XPrice_listItem_type,item.getNum()+"次")
                        .setText(R.id.XPrice_listItem_type1,"截止时间："+item.getPendtime());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                Bundle bundle = new Bundle();
                bundle.putString("offer_sn2", mB_singleAdapter.getItem(position).getOffer_sn());
                if (mPriceDetailsFragment == null){
                    mPriceDetailsFragment =new PriceDetailsFragment();
                }
                bundle.putInt("type",4);
                ShowFragmentUtils.showFragment(getActivity(),
                        mPriceDetailsFragment.getClass(),
                        FragmentTags.FRAGMENT_PRICE_DETAIL,
                        bundle,true);
            }
        };
        mB_Single_List.setAdapter(mB_singleAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                hideSoftKeyboard();
                popSelf();
                break;
            case R.id.top_search_to:
                //弹出选择的菜单
                showActionSheet();
                break;
            case R.id.top_search_clear:
                mEditText.setText("");
                break;
        }
    }

    private void showActionSheet() {
        int height = DisPlayUtils.getHeightPx() - 45;
        mActionSheet = new ActionSheet(getContext(),
                R.layout.price_search_pop,
                DisPlayUtils.getWidthPx(),
                DisPlayUtils.dip2px(height),
                new OnPopWinDisMisBack() {
                    @Override
                    public void onPopWindowDismiss() {
                        //消失时的回调

                    }
                }
        ) {
            @Override
            public void setData(View view) {
                final ListView left = (ListView) view.findViewById(R.id.left_list);
                ListView right = (ListView) view.findViewById(R.id.right_list);
                mLeftAdapter
                        = new CommonAdapter<String>(getContext(), getList_Left(), R.layout.search_price_list_item) {
                    @Override
                    protected void setListeners(BaseViewHolder holder, View view, int position) {
                        holder.setOnClickListener(R.id.search_price_listItem_layout);
                    }

                    @Override
                    protected void setViewDimen(View convertView) {

                    }

                    @Override
                    protected void setViewData(int position, BaseViewHolder holder, String item) {
                        holder.setText(R.id.search_price_listItem_tv, item);
                        TextView textView = holder.getView(R.id.search_price_listItem_tv);
                        if (leftId == position) {
                            textView.setTextColor(getResources().getColor(R.color
                                    .ycMainColor));
                        } else {
                            textView.setTextColor(getResources().getColor(R.color
                                    .price_textColor_pressed));
                        }
                    }

                    @Override
                    public void onClickBack(int position, View view, BaseViewHolder holder) {
                        List<String> list = getList_Right(position);
                        mRightAdapter.update(list);
                        rightId=100;
                        leftId = position;
                        mLeftAdapter.notifyDataSetChanged();
                    }
                };
                left.setAdapter(mLeftAdapter);

                mRightAdapter =
                        new CommonAdapter<String>(getContext(), getList_Right(leftId), R.layout.search_price_list_item) {
                            @Override
                            protected void setListeners(BaseViewHolder holder, View view, int position) {
                                holder.setOnClickListener(R.id.search_price_listItem_layout);
                            }

                            @Override
                            protected void setViewDimen(View convertView) {

                            }

                            @Override
                            protected void setViewData(int position, BaseViewHolder holder, String item) {
                                holder.setText(R.id.search_price_listItem_tv, item);
                                TextView textView = holder.getView(R.id.search_price_listItem_tv);
                                if (rightId == position) {
                                    textView.setTextColor(getResources().getColor(R.color
                                            .ycMainColor));
                                } else {
                                    textView.setTextColor(getResources().getColor(R.color
                                            .price_textColor_pressed));
                                }
                            }

                            @Override
                            public void onClickBack(int position, View view, BaseViewHolder holder) {
                                mActionSheet.dismissWindow();
                                rightId =position;
                                String left =getList_Left().get(leftId);
                                String right =getList_Right(leftId).get(position);
                                mEditText.setHint("搜索 "+left+": "+right);
                            }
                        };
                right.setAdapter(mRightAdapter);
                ImageView imageView = (ImageView) view.findViewById(R.id.action_list_bgView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActionSheet.dismissWindow();
                    }
                });
            }
        };
        View parentView = mFindViewUtils.findViewById(R.id.price_search_topLayout);
        mActionSheet.showAsParentView(parentView);
    }

    private List<String> getList_Left() {
        List<String> list = new ArrayList<>();
        list.add("产品询价");
        list.add("项目询价");
        list.add("产品报价");
        return list;
    }

    private List<String> getList_Right(int position) {
        List<String> list = new ArrayList<>();
        switch (position) {
            case 0:
                list.add("按产品品牌搜索");
                list.add("按产品名称搜索");
                break;
            case 1:
                list.add("按项目地区搜索");
                list.add("按项目类型搜索");
                list.add("按项目名称搜索");
                break;
            case 2:
                list.add("按搜索品牌搜索");
                list.add("按产品名称搜索");
                break;
        }
        return list;
    }

    private void setListHidden(int who){
        switch (who){
            case 0:
                mX_Single_List.setVisibility(View.VISIBLE);
                mX_Project_List.setVisibility(View.GONE);
                mB_Single_List.setVisibility(View.GONE);
                break;
            case 1:
                mX_Single_List.setVisibility(View.GONE);
                mX_Project_List.setVisibility(View.VISIBLE);
                mB_Single_List.setVisibility(View.GONE);
                break;
            case 2:
                mX_Single_List.setVisibility(View.GONE);
                mX_Project_List.setVisibility(View.GONE);
                mB_Single_List.setVisibility(View.VISIBLE);
                break;
        }
    }
}
