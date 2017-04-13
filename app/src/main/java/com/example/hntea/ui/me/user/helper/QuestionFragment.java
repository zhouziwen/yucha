package com.example.hnTea.ui.me.user.helper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hnTea.R;
import com.example.hnTea.ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends BaseFragment {
    private TextView mTextView;

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAppTitleBar.getTitle().setText("常见问题");
        mTextView =mFindViewUtils.findViewById(R.id.question_tv);
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        mTextView.setText("\n" +
                "       如在电力电平台遇到任何问题，可直接联系平台客服进行咨询，或者拨打4006-831-536，电力电工作人员将竭诚为您服务。\n" +
                "       问题反馈可发送邮件至dianlidian@831536.net。 ");
    }
}
