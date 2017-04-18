package com.example.hnTea.ui;


import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hnTea.BuildConfig;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.apcontains.OnActionSheetClickBack;
import com.example.hnTea.apcontains.OnPopWinDisMisBack;
import com.example.hnTea.utils.ActionSheet;
import com.example.hnTea.utils.DisPlayUtils;
import com.example.hnTea.utils.FindViewUtils;
import com.example.hnTea.utils.logger.LogLevel;
import com.example.hnTea.utils.logger.Logger;
import com.example.hnTea.widget.AppTitleBar;
import com.example.hnTea.widget.BaseDialog;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

public class BaseFragment extends Fragment {
    protected InputMethodManager inputMethodManager;
    protected BaseActivity mBaseActivity;
    protected FindViewUtils mFindViewUtils;
    protected Bundle mArguments;
    protected AppTitleBar mAppTitleBar;
    protected BaseDialog mDialog;
    protected ActionSheet mActionSheet;
    private Window mWindow;
    protected Handler mHandler =
            new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArguments = savedInstanceState;
        if (BuildConfig.DEBUG) {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.FULL).hideThreadInfo();
        } else {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.NONE).hideThreadInfo();
        }
    }

    //topPadding
    //app_title_bar
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputMethodManager = (InputMethodManager) getActivity().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        mBaseActivity = (BaseActivity) getActivity();
        initView(view);
        setListener();
        setData();
    }

    protected void initView(View view) {
        mWindow = getActivity().getWindow();
        mFindViewUtils = new FindViewUtils(view);
        mAppTitleBar = (AppTitleBar) view.findViewById(R.id.app_title_bar);
        int fragmentBg = getResources().getColor(R.color.fragment_bg);
        view.setBackgroundColor(fragmentBg);
        view.setClickable(true);
    }

    protected void setListener() {
        if (mAppTitleBar != null) {
            mAppTitleBar.getBack().setOnClickListener(v -> {
                hiddenLoading();
                popSelf();
            });
            mAppTitleBar.getAction().setOnClickListener(v -> {

            });
        }

    }

    protected void setData() {

    }

    protected String getText(EditText editText) {
        return editText.getText().toString();
    }

    //隐藏键盘
    protected void hideSoftKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
        }
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    /**
     * 返回键处理
     *
     * @return
     */
    public boolean onBackPressed() {
        hiddenLoading();
        return false;
    }

    public void popSelf() {
        popBackStack();
        // hideSoftKeyboard();
    }

    private void popBackStack() {
        try {
            if (isDetached() || isRemoving() || getFragmentManager() == null) {
                return;
            }
            if (isResumed()) {
                getFragmentManager().popBackStackImmediate();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hiddenLoading();
    }


    public void showLoading() {
        mBaseActivity.showLoading();
    }

    public void showLoading(int textResId) {
        mBaseActivity.showLoading(textResId);
    }

    public void hiddenLoading() {
        if (mBaseActivity != null) {
            mBaseActivity.hiddenLoading();
        }
    }

    public void showAlertWithMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //透明啊状态栏的处理
    protected void setStateBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mAppTitleBar != null) {
                mAppTitleBar.getState().setHeight(getStatusHeight(getActivity()));
            } else {
                TextView textView = mFindViewUtils.findViewById(R.id.state_bar);
                if (textView != null) {
                    textView.setHeight(getStatusHeight(getActivity()));
                }
            }
        }
    }

    //计算状态栏的高度
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    //dialog的show方法
    protected void showDialogWithMsg(String var) {
        mDialog = new BaseDialog(getContext(), var);
        mDialog.show();
    }

    //***************************popWindow从底部弹出来的弹窗************************
    protected int utilHeight = DisPlayUtils.getHeightPx() / 14 / 2;

    protected void showActionSheet(final List<String> strings,
                                   int parentId,
                                   final OnActionSheetClickBack onActionSheetClickBack) {
        int height = strings.size() * DisPlayUtils.dip2px(50);
        mActionSheet = new ActionSheet(getContext(),
                R.layout.action_sheet_list,
                DisPlayUtils.getWidthPx(),
                height, new OnPopWinDisMisBack() {
            @Override
            public void onPopWindowDismiss() {
                // popWindow消失时的回调
                setWindowBg(1.f);
            }
        }) {
            @Override
            public void setData(View view) {
                ListView listView = (ListView) view.findViewById(R.id.action_sheet_listView);
                CommonAdapter<String> adapter = new CommonAdapter<String>(getContext(),
                        strings, R.layout.action_sheet_list_item) {
                    @Override
                    protected void setListeners(BaseViewHolder holder, View view, int position) {
                        holder.setOnClickListener(R.id.action_sheet_lis_item_tv);
                    }

                    @Override
                    protected void setViewDimen(View convertView) {

                    }

                    @Override
                    protected void setViewData(int position, BaseViewHolder holder, String item) {
                        holder.setText(R.id.action_sheet_lis_item_tv, item);
                    }

                    @Override
                    public void onClickBack(int position, View view, BaseViewHolder holder) {
                        onActionSheetClickBack.onActionSheetClickBack(position);
                    }
                };
                listView.setAdapter(adapter);
            }
        };
        View v = LayoutInflater.from(getContext()).inflate(parentId, null);
        mActionSheet.showAsDownWindow(v);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setWindowBg(0.5f);
            }
        }, 300);
    }

    public void setWindowBg(float bg) {
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        lp.alpha = bg; //0.0-1.0
        mWindow.setAttributes(lp);
    }

    //********************************************************************************8

    //数据为空时添加默认信息图片
    public void setEmptyView(ListView mListView, int ImageViewId) {
        ImageView emptyView = new ImageView(getContext());
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        emptyView.setImageResource(ImageViewId);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup) mListView.getParent()).addView(emptyView);
        mListView.setEmptyView(emptyView);
    }

    //数据为空时添加默认信息图片
    public void setEmptyView(PullToRefreshListView mListView, int ImageViewId) {
        ImageView emptyView = new ImageView(getContext());
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        emptyView.setImageResource(ImageViewId);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup) mListView.getParent()).addView(emptyView);
        mListView.setEmptyView(emptyView);
    }

    //textview在代码里边设置drawable
    public void setTvDrawable(TextView tv, int image) {
        Drawable d = getResources().getDrawable(image);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        tv.setCompoundDrawables(d, null, null, null);
    }

    //List  toString后去掉括号，逗号和最后一个元素
    public String formatList(List<?> list) {
        StringBuilder b = new StringBuilder();
        for (Object o : list) {
            b.append(o);
        }
        return b.toString().substring(0, b.toString().length() - 1);
    }
}