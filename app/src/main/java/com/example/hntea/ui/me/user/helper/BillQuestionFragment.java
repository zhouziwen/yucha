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
public class BillQuestionFragment extends BaseFragment {
    private TextView mTextView;

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bill_question, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAppTitleBar.getTitle().setText("发票制度");
        mTextView = mFindViewUtils.findViewById(R.id.billQuestion_tv);
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        mTextView.setText(" 发票分为普通发票和增值税专用发票二种\n" +
                "\n" +
                "1、首次开具增值税专用发票的客户，需要提供加盖公章的营业执照副本、税务登记证副本、一般纳税人资格证书、银行开户许可证复印件，由您的客户经理负责提交审核；\n" +
                "\n" +
                "2、发票需在货物送达客户并完成签收后开具；\n" +
                "\n" +
                "3、发票信息应与定单保持一致。货物名称与实物一致，发票金额不能高于订单金额；\n" +
                "\n" +
                "4、客户收到发票后如发现票据抬头、内容或金额错误，请在自开票日期起30日内联系您的客户经理，由客户经理为您安排办理换发票事宜。\n");
    }
}
