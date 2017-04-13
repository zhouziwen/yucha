package com.example.hnTea.widget;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hnTea.R;
import com.example.hnTea.apcontains.FragmentTags;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.home.shop.ShoppingFragment;
import com.example.hnTea.utils.ShowFragmentUtils;
import com.example.hnTea.utils.toast.ApToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopFragment extends BaseFragment {
    private ImageView image1,image2,image3,dismiss;
    private Handler mHandler =new Handler(Looper.getMainLooper());
    private ShoppingFragment mShoppingFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pop2, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        image1 =mFindViewUtils.findViewById(R.id.imageView1);
        image2 =mFindViewUtils.findViewById(R.id.imageView2);
        image3 =mFindViewUtils.findViewById(R.id.imageView3);
        dismiss =mFindViewUtils.findViewById(R.id.pop_dismiss);
    }

    @Override
    protected void setListener() {
        super.setListener();
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //消息
                ApToast.showCenterText("该功能暂未开放，请期待后续版本。");
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //电话
                showDialogWithMsg("是否拨打客服电话4006-831536？");
                mDialog.setLeftButtonListener(new BaseDialog.LeftListener() {
                    @Override
                    public void onLeftListener() {
                        mDialog.dismiss();
                    }
                });
                mDialog.setRightButtonListener(new BaseDialog.RightListener() {
                    @Override
                    public void onRightListener() {
                        mDialog.dismiss();
                        String number ="400-628-6662";
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
                        startActivity(intent);
                    }
                });
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //购物车
                if (mShoppingFragment==null){
                    mShoppingFragment =new ShoppingFragment();
                }
                ShowFragmentUtils.showFragment(getActivity(),
                        mShoppingFragment.getClass(),
                        FragmentTags.FRAGMENT_SHOPPING,
                        null,true);
            }
        });
        setDismissListener();
    }

    @Override
    protected void setData() {
        super.setData();

    }

    private void setDismissListener(){
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image3.animate().xBy(0f).yBy(400f).alpha(0).start();
                    }
                },150);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image2.animate().xBy(0f).yBy(400f).alpha(0).start();
                    }
                },200);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image1.animate().xBy(0f).yBy(400f).alpha(0).start();
                    }
                },250);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popSelf();
                    }
                },500);
            }
        });

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                image1.animate().xBy(10f).yBy(-400f).alpha(1).setDuration(150).start();
            }
        },150);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playImage2();
            }
        },200);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playImage3();
            }
        },250);
    }

    private void playImage2(){
        image2.animate().xBy(10f).yBy(-400f).alpha(1).setDuration(150).start();
    }
    private void playImage3(){
        image3.animate().xBy(10f).yBy(-400f).alpha(1).setDuration(150).start();
    }
}
