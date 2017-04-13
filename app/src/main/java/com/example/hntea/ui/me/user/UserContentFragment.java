package com.example.hnTea.ui.me.user;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hnTea.MyApplication;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.apcontains.FragmentTags;
import com.example.hnTea.rxjava.RxEventBus;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.rxjava.eventBean.EventUserBean;
import com.example.hnTea.ui.me.user.bean.UserContentModel;
import com.example.hnTea.utils.BitmapUtils;
import com.example.hnTea.utils.ShowFragmentUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserContentFragment extends BaseFragment {
    private ListView mListView;
    private CommonAdapter<UserContentModel> mAdapter;
    private View mHeaderView;
    private AddressFragment mAddressFragment;
    private UserNameModifyFragment mUserNameModifyFragment;
    private UserPhoneNumModifyFragment mUserPhoneNumModifyFragment;
    private ModifyPwdFragment mModifyPwdFragment;
    private ImageView mHeadIcon;

    private Subscription mSubscription;

    private static final int PICK_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int ZOOM_REQUEST_CODE = 2;

    private static final String IMAGE_FILE_NAME = "photo.jpg";

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public void onStart() {
        mSubscription = RxEventBus.getDefault().toObservable(EventUserBean.class)
                .subscribe(userEvent -> refreshList(userEvent.getNickName(), userEvent.getAddress()),
                        throwable -> {
                        });
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_content, container, false);

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAppTitleBar.getTitle().setText("我的信息");
        mListView = mFindViewUtils.findViewById(R.id.userContent_listView);
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.user_content_foot, null);
        mListView.addHeaderView(mHeaderView);
        //通过headervView 寻找里面的头像IV
        mHeadIcon = (ImageView) mHeaderView.findViewById(R.id.me_listHeader_icon);
        // 首先判断本地中有没有保存图片：如果没有图片，显示默认图片；
        if (MyApplication.getInstance().getUserLocalHeader() != null) {
            mHeadIcon.setImageBitmap(MyApplication.getInstance().getUserLocalHeader());
        } else {
            mHeadIcon.setImageResource(R.mipmap.default_user);
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        mHeaderView.setOnClickListener(v -> {
            //头像点击事件
            List<String> list = new ArrayList<String>();
            list.add("拍照");
            list.add("从相册选择");
            list.add("取消");
            showActionSheet(list,
                    R.layout.fragment_user_content,
                    position -> {
                        switch (position) {
                            case 0:
                                //相机
                                if (BitmapUtils.isSdcardExisting()) {
                                    Intent intent_camera = new Intent(
                                            "android.media.action.IMAGE_CAPTURE");
                                    intent_camera.putExtra(MediaStore.EXTRA_OUTPUT,
                                            getImageUri());
                                    intent_camera.putExtra(
                                            MediaStore.EXTRA_VIDEO_QUALITY, 0);
                                    startActivityForResult(intent_camera,
                                            CAMERA_REQUEST_CODE);

                                } else {
                                    Toast.makeText(getActivity(), "请插入sd卡",
                                            Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 1:
                                //从相册选择
                                Intent intent_gallery = new Intent(
                                        Intent.ACTION_PICK,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent_gallery
                                        .setDataAndType(
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                                "image/*");
                                startActivityForResult(intent_gallery,
                                        PICK_REQUEST_CODE);

                                break;
                            case 2:
                                //取消
                                break;
                        }
                        mActionSheet.dismissWindow();
                    });
        });
    }

    @Override
    protected void setData() {
        super.setData();
        List<UserContentModel> list = new ArrayList<>();
        list.add(new UserContentModel("名称",
                TextUtils.isEmpty(MyApplication.getUserNickName()) ? "未设置" : MyApplication.getUserNickName()));
        list.add(new UserContentModel("会员状态", "普通会员"));
        list.add(new UserContentModel("手机号",
                TextUtils.isEmpty(MyApplication.getUserName()) ? "未设置" : MyApplication.getUserName()));
        list.add(new UserContentModel("我的密码",""));
        list.add(new UserContentModel("我的地址",
                TextUtils.isEmpty(MyApplication.getUserDefaultAddress()) ? "未设置" : MyApplication.getUserDefaultAddress()));
        mAdapter = new CommonAdapter<UserContentModel>(getContext(), list, R.layout.user_content_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnClickListener(R.id.userContent_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, UserContentModel item) {
                holder.setText(R.id.userContent_title, item.getTitle())
                        .setText(R.id.userContent_var, item.getVar());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                switch (position) {
                    case 0:
                        if (mUserNameModifyFragment == null) {
                            mUserNameModifyFragment = new UserNameModifyFragment();
                        }
                        ShowFragmentUtils.showFragment(getActivity(),
                                mUserNameModifyFragment.getClass(), FragmentTags.FRAGMENT_USERNAME_MODIFY, null, true);
                        break;
                    case 1:
                        break;
                    case 2:
                        if (mUserPhoneNumModifyFragment == null) {
                            mUserPhoneNumModifyFragment = new UserPhoneNumModifyFragment();
                        }
                        ShowFragmentUtils.showFragment(getActivity(),
                                mUserPhoneNumModifyFragment.getClass(), FragmentTags.FRAGMENT_USERPHONENUM_MODIFY, null, true);
                        break;
                    case 3:
                        if (mModifyPwdFragment == null) {
                            mModifyPwdFragment=new ModifyPwdFragment();
                        }
                        ShowFragmentUtils.showFragment(getActivity(),
                                mModifyPwdFragment.getClass(), FragmentTags.FRAGMENT_MODIFY_PASSWORD, null, true);
                        break;

                    case 4:
                        //我的地址
                        if (mAddressFragment == null) {
                            mAddressFragment = new AddressFragment();
                        }
                        Bundle bundle =new Bundle();
                        bundle.putString("address_type","1");
                        ShowFragmentUtils.showFragment(getActivity(),
                                mAddressFragment.getClass(), FragmentTags.FRAGMENT_MANAGE_ADDRESS, bundle, true);
                        break;
                }
            }
        };
        mListView.setAdapter(mAdapter);
    }

    public void refreshList(String nickName, String address) {
        List<UserContentModel> list = new ArrayList<>();
        list.add(new UserContentModel("名称", nickName));
        list.add(new UserContentModel("会员状态", MyApplication.getUserRank()));
        list.add(new UserContentModel("手机号", MyApplication.getUserName()));
        list.add(new UserContentModel("我的密码", ""));
        list.add(new UserContentModel("我的地址", address));
        mAdapter.update(list);
    }

    // 在从拍照或者图片啼选择后返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        } else {
            switch (requestCode) {
                case PICK_REQUEST_CODE:
                    // 从图片啼选择图片后，直接返回的是uri，然后对通过uri取得图片，进行剪切；
                    startPhotoZoom(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    // 首先判断sd卡是否挂载；
                    if (BitmapUtils.isSdcardExisting()) {
                        startPhotoZoom(getImageUri());
                    } else {
                        Toast.makeText(getActivity(), "未找到存储卡，无法存储照片！",
                                Toast.LENGTH_LONG).show();
                    }
                    break;
                // 启动剪切图片；
                case ZOOM_REQUEST_CODE:
                    if (data != null) {
                        showImage(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // 对图片进行剪切；
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 360);
        intent.putExtra("outputY", 360);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, ZOOM_REQUEST_CODE);
    }

    // 最张显示图片；但是在实际中，在进入activity中要判断sd卡中是否有图片，如果有图片要首先显示图片。
    // 把图片保存到本地，并且上传给服务器；
    private void showImage(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Bitmap photo_round = BitmapUtils.toRoundBitmap(photo);

            // saveServer();保存到服务器；
            // saveSD();保存到sd卡;
            // 显示在界面中；
            BitmapUtils.saveToSDBitmap(getActivity(), "icon.png",
                    photo_round);
            mHeadIcon.setImageBitmap(photo_round);
            //给前一个界面发一个通知
            RxEventBus.getDefault().postEvent(photo_round);
        }
    }

    private Uri getImageUri() {
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                IMAGE_FILE_NAME));
    }

    //一定要在生命周期結束后 取消订阅事件
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
