package com.example.hnTea.ui.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.https.BaseUrl;
import com.example.hnTea.mvpmodel.user.bean.UserhelperModel;
import com.example.hnTea.mvppresenter.login.IViewLogin;
import com.example.hnTea.mvppresenter.login.LoginPresenter;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserHelperPresenter;
import com.example.hnTea.ui.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

// *****************************改界面可以当 注册 和忘记密码界面
//
//  1 :注册界面
//  2 :忘记密码界面
//
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private int mPageType;
    private TextView mBckTv, mRegisterTv, mSmsTv, mUserConfigTv;
    private ImageView mUserConfigImg;
    private EditText mPhoneNum, mSms, mPsw, mResultPsw;
    private LoginPresenter mLoginPresenter;
    private String phone;
    private int smsNum;
    private UserHelperPresenter mUserHelperPresenter;
    private WebView mWebView;
    private Timer mTimer;
    private boolean SmsBool = false;
    private boolean isSelectorConfig = false;
    private int smsCode = 60;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (smsCode == 0) {
                mSmsTv.setText("获取");
                smsCode = 60;
                SmsBool = false;
                mTimer.cancel();
            } else {
                smsCode--;
                mSmsTv.setText("" + smsCode + "s");
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        mUserHelperPresenter = new UserHelperPresenter(null);
        mPageType = intent.getIntExtra("PageType", 1);
        mUserConfigTv = mFindViewUtils.findViewById(R.id.register_tv_userConfig);
        mUserConfigImg = mFindViewUtils.findViewById(R.id.register_img_userConfig);
        if (mPageType == 2) {
            // 忘记密码界面 隐藏布局
            LinearLayout linearLayout =mFindViewUtils.findViewById(R.id.register_userConfig);
            linearLayout.setVisibility(View.GONE);
        }
        mLoginPresenter = new LoginPresenter(null);
        mBckTv = (TextView) findViewById(R.id.register_top);
        mRegisterTv = (TextView) findViewById(R.id.login_loginTv);
        mSmsTv = (TextView) findViewById(R.id.register_getSms);
        mPhoneNum = (EditText) findViewById(R.id.register_userName);
        mSms = (EditText) findViewById(R.id.register_sms);
        mPsw = (EditText) findViewById(R.id.login_psw);
        mResultPsw = (EditText) findViewById(R.id.login_psw_right);
        toSetText();
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBckTv.setOnClickListener(this);
        mRegisterTv.setOnClickListener(this);
        mSmsTv.setOnClickListener(this);
        if (mUserConfigTv != null) {
            mUserConfigTv.setOnClickListener(this);
        }
        if (mUserConfigImg != null) {
            mUserConfigImg.setOnClickListener(this);
        }
    }

    @Override
    protected void setData() {
        super.setData();
    }

    private void getRegisterXieyi() {
        mUserHelperPresenter.getUserHelperData("register", new IViewUser<UserhelperModel>() {
            @Override
            public void onSuccess(UserhelperModel response) {
                mWebView.loadDataWithBaseURL(BaseUrl.getBaseUrl(), response.getContent(), "text/html", "utf-8", null);
                mHandler.postDelayed(() -> hiddenLoading(), 500);
//                hiddenLoading();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_top:
                //返回按钮
                finish();
                break;
            case R.id.login_loginTv:
                //注册按钮
                if (mPageType == 1) {
                    //注册
                    requestRegister();
                } else {
                    //忘记密码
                    requestForget();
                }
                break;
            case R.id.register_getSms:
                //注册获取验证码
                if (!SmsBool) {
                    if (checkOutPhoneNum(mPhoneNum)) {
                        showAlertWithMsg("验证码已发送,请注意查收。");
                        Sms();
                        SmsBool = true;
                        phone = getText(mPhoneNum);
                        requestSmsCode();
                    }
                }
                break;
            case R.id.register_tv_userConfig:

                //注册界面的用户协议
                View view = LayoutInflater.from(this)
                        .inflate(R.layout.dialog_user_config, null);
                mWebView = (WebView) view.findViewById(R.id.register_webView);
                WebSettings webSettings = mWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
                webSettings.setSupportZoom(true);
//        webSettings.setTextSize(WebSettings.TextSize.SMALLER);
                webSettings.setBuiltInZoomControls(false);
                mWebView.setWebViewClient(new WebViewClient());
                mWebView.setWebChromeClient(new WebChromeClient());
                mHandler.post(this::getRegisterXieyi);
                Dialog dialog = new AlertDialog.Builder(this)
                        .setTitle("豫茶用户注册协议")
                        .setView(view)
                        .setPositiveButton("确定", (dialog1, which) -> dialog1.dismiss())
                        .setCancelable(false)
                        .create();
                mHandler.postDelayed(dialog::show
                , 500);
//                dialog.show();
                break;
            case R.id.register_img_userConfig:
                //注册界面 用户协议旁边的图标
                if (!isSelectorConfig) {
                    mUserConfigImg.setImageResource(R.mipmap.register_user_config_nomal);
                    isSelectorConfig = true;
                } else {
                    mUserConfigImg.setImageResource(R.mipmap.register_user_config_selector);
                    isSelectorConfig = false;
                }
                break;
        }
    }

    private void requestForget() {
        if (checkOutOtherNum()) {
            if (checkOutOtherNum()) {
                mLoginPresenter.getForget(getText(mPhoneNum),
                        getText(mPsw),
                        getText(mResultPsw),
                        getText(mSms),
                        new IViewLogin<String>() {
                            @Override
                            public void onSuccess(String data) {
                                hiddenLoading();
                                showAlertWithMsg("重置密码成功");
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 1500);
                            }

                            @Override
                            public void onPhpFail(String var) {
                                hiddenLoading();
                                showAlertWithMsg(var);
                            }

                            @Override
                            public void onSmsCode(int code) {
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
        }
    }

    private void requestRegister() {
        if (checkOutPhoneNum(mPhoneNum)) {
            if (checkOutOtherNum()) {
                mLoginPresenter.getRegister(getText(mPhoneNum),
                        getText(mPsw),
                        getText(mResultPsw),
                        getText(mSms),
                        new IViewLogin<String>() {
                            @Override
                            public void onSuccess(String data) {
                                hiddenLoading();
                                showAlertWithMsg("注册成功");
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 1500);
                            }

                            @Override
                            public void onPhpFail(String var) {
                                hiddenLoading();
                                showAlertWithMsg(var);
                            }

                            @Override
                            public void onSmsCode(int code) {
                                //没用 当时有点懒 不想再写个接口了 所以多了个回调
                            }

                            @Override
                            public void onStart(String var) {
                                showLoading();
                            }

                            @Override
                            public void onFail(VolleyError volleyError) {
                                hiddenLoading();
                                showAlertWithMsg("" + volleyError);
                            }
                        });
            }
        }
    }

    private void requestSmsCode() {
        if (checkOutPhoneNum(mPhoneNum)) {
            mLoginPresenter.getSmsCode(new IViewLogin<String>() {
                @Override
                public void onSuccess(String data) {

                }

                @Override
                public void onPhpFail(String var) {
                    showAlertWithMsg(var);
                }

                @Override
                public void onSmsCode(int code) {
                    smsNum = code;
                }

                @Override
                public void onStart(String var) {

                }

                @Override
                public void onFail(VolleyError volleyError) {

                }
            }, getText(mPhoneNum), mPageType);
        }
    }

    private boolean checkOutOtherNum() {
        if (TextUtils.isEmpty(getText(mSms))) {
            showAlertWithMsg("请输入验证码");
            return false;
        }
        if (TextUtils.isEmpty(getText(mPsw))) {
            showAlertWithMsg("请输入密码");
            return false;
        }
        if (getText(mPsw).length() < 6) {
            showAlertWithMsg("密码不能小于6位数");
            return false;
        }
        if (TextUtils.isEmpty(getText(mResultPsw))) {
            showAlertWithMsg("两次输入的密码不一致");
            return false;
        }
        if (!getText(mPsw).equals(getText(mResultPsw))) {
            showAlertWithMsg("两次输入的密码不一致");
            return false;
        }
        String s = "" + smsNum;
        if (!getText(mSms).equals(s)) {
            showAlertWithMsg("请输入正确的验证码");
            return false;
        }
        if (!getText(mPhoneNum).equals(phone)) {
            showAlertWithMsg("请用发送验证码的手机号注册");
            return false;
        }
        return true;
    }

    private void toSetText() {
        if (mPageType == 2) {
            //注册
            mBckTv.setText("重置密码");
            mPsw.setHint("新密码");
            mResultPsw.setHint("确认新密码");
            mRegisterTv.setText("重置密码");
        }
    }

    private void Sms() {
        mTimer = new Timer();
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 0;
                mHandler.sendMessage(message);
            }
        };
        mTimer.schedule(mTimerTask, 1000, 1000);
    }

    private String getText() {
        return "电力电免费交易平台是由河南太能电气股份有限公司开发运营，电力电免费交易平台依据以下条件和条款为您提供所享有的服务，请仔细阅读并遵守。\n" +
                "欢迎阅读电力电网站服务条款协议(下称“本协议”)。本协议阐述之条款和条件适用于您使用电力电网站（所涉域名为 ：www.dianlidian.com，dianlidian.com下同），所提供的在全球企业间(B-TO-C)电子市场(e-market)中进行贸易和交流的各种工具和服务 (下称“服务”)。\n" +
                "一、接受条款\n" +
                "\n" +
                "1、本协议内容包括协议正文及所有电力电网站已经发布或将来可能发布的各类规则。所有规则为协议不可分割的一部分，与协议正文具有同等法律效力。\n" +
                "\n" +
                "2、以任何方式进入电力电网站网站并使用服务即表示您已充分阅读、理解并同意接受本协议的条款和条件(以下合称“条款”)。\n" +
                "\n" +
                "3、电力电网站有权根据业务需要酌情修订“条款”，并以网站公告的形式进行更新，不再单独通知予您。经修订的“条款”一经在电力电网站中国站公布，即产生效力。如您不同意相关修订，请您立即停止使用“服务”。如您继续使用“服务”，则将视为您已接受经修订的“条款”，当您与电力电网站发生争议时，应以最新的“条款”为准。\n" +
                "二、注册\n" +
                "1、服务使用对象\n" +
                "\n" +
                "您确认，在您完成注册程序或以其他电力电网站允许的方式实际使用服务时，您应当是具备完全民事权利能力和与所从事的民事行为相适应的行为能力的自然人、法人或其他组织。若您不具备前述主体资格，请勿使用服务，否则您及您的监护人应承担因此而导致的一切后果，且电力电网站有权注销（永久冻结）您的账户，并向您及您的监护人索偿。如您代表一家公司或其他法律主体在电力电网站登记，则您声明和保证，您有权使该公司或该法律主体受本协议“条款”的约束。\n" +
                "2、注册账户\n" +
                "\n" +
                "2.1、在您按照注册页面提示填写信息、阅读并同意本协议且完成全部注册程序后，或在您按照激活页面提示填写信息、阅读并同意本协议且完成全部激活程序后，您即受本协议的约束。您可以使用您提供或确认的邮箱、手机号码作为登录手段进入电力电网站。\n" +
                "\n" +
                "2.2、您了解并同意，如您系在本网站完成的注册程序，只要您注册成功，您可以通过您的用户名和密码直接登录电力电网站网站。\n" +
                "\n" +
                "2.3、您注册成功后,您的用户名(手机号或邮箱)不得侵犯或涉嫌侵犯他人合法权益。如您设置的用户名涉嫌侵犯他人合法权益，电力电网站有权终止向您提供服务，并注销您的账户。账户注销后，相应的用户名将开放给其他有权用户登记使用。\n" +
                "\n" +
                "2.4、在完成注册或激活流程时，您应当按照法律法规要求，按相应页面的提示准确提供并及时更新您的资料，以使之真实、及时，完整和准确。如有合理理由怀疑您提供的资料错误、不实、过时或不完整的，电力电网站有权向您发出询问及/或要求改正的通知，并有权直接做出删除相应资料的处理，直至中止、终止对您提供部分或全部电力电网站服务，电力电网站对此不承担任何责任，您将承担因此产生的任何直接或间接损失及不利后果。\n" +
                "2、注册账户\n" +
                "\n" +
                "2.1、在您按照注册页面提示填写信息、阅读并同意本协议且完成全部注册程序后，或在您按照激活页面提示填写信息、阅读并同意本协议且完成全部激活程序后，您即受本协议的约束。您可以使用您提供或确认的邮箱、手机号码作为登录手段进入电力电网站。\n" +
                "\n" +
                "2.2您了解并同意，如您系在本网站完成的注册程序，只要您注册成功，您可以通过您的用户名和密码直接登录电力电网站网站。\n" +
                "\n" +
                "2.3您注册成功后,您的用户名(手机号或邮箱)不得侵犯或涉嫌侵犯他人合法权益。如您设置的用户名涉嫌侵犯他人合法权益，电力电网站有权终止向您提供服务，并注销您的账户。账户注销后，相应的用户名将开放给其他有权用户登记使用。\n" +
                "\n" +
                "2.4在完成注册或激活流程时，您应当按照法律法规要求，按相应页面的提示准确提供并及时更新您的资料，以使之真实、及时，完整和准确。如有合理理由怀疑您提供的资料错误、不实、过时或不完整的，电力电网站有权向您发出询问及/或要求改正的通知，并有权直接做出删除相应资料的处理，直至中止、终止对您提供部分或全部电力电网站服务，电力电网站对此不承担任何责任，您将承担因此产生的任何直接或间接损失及不利后果。\n" +
                "三、账户安全\n" +
                "\n" +
                "您须自行负责对您的用户名和密码保密，且须对该用户名和密码下发生的所有活动（包括但不限于信息披露、发布信息、网上点击同意或提交各类规则协议、网上续签协议或购买服务等）承担责任。您同意：\n" +
                "\n" +
                "(a) 如发现任何人未经授权使用您的用户名和密码，或发生违反保密规定的任何其他情况，您会立即通知电力电网站；\n" +
                "\n" +
                "(b) 确保您在每个上网时段结束时，以正确步骤离开网站。电力电网站不能也不会对因您未能遵守本款规定而发生的任何损失或损毁负责。您理解电力电网站对您的请求采取行动需要合理时间，电力电网站对在采取行动前已经产生的后果（包括但不限于您的任何损失）不承担任何责任。除非有法律规定或司法裁定，且征得电力电网站的同意。\n" +
                "四、服务终止\n" +
                "1、服务终止：\n" +
                "\n" +
                "1.1您同意，在电力电网站未向您收费的情况下，电力电网站可自行全权决定以任何理由 (包括但不限于电力电网站认为您已违反本协议的字面意义和精神，或您以不符合本协议的字面意义和精神的方式行事) 终止您的“服务”密码、用户(或其任何部份) 或您对“服务”的使用。您同意，在电力电网站向您收费的情况下，电力电网站应基于合理的怀疑且经电子邮件通知的情况下实施上述终止服务的行为。您进一步承认和同意，电力电网站根据本协议规定终止您服务的情况下可立即使您的用户名无效，或注销您的账户以及在您的账户内的所有相关资料和档案，和/或禁止您进一步接入该等档案或“服务”。账户终止后，电力电网站没有义务为您保留原账户中或与之相关的任何信息，或转发任何未曾阅读或发送的信息给您或第三方。此外，您同意，电力电网站不会就终止向您提供“服务”而对您或任何第三者承担任何责任。\n" +
                "\n" +
                "1.2您有权向电力电网站要求注销您的账户，经电力电网站审核同意的，电力电网站将注销您的账户，届时，您与电力电网站基于本协议的合同关系即终止。您的账户被注销后，电力电网站没有义务为您保留或向您披露您账户中的任何信息，也没有义务向您或第三方转发任何您未曾阅读或发送过的信息。\n" +
                "\n" +
                "1.3.您理解并同意，您与电力电网站的合同关系终止后：\n" +
                "\n" +
                "a) 电力电网站有权继续保存您的资料。\n" +
                "\n" +
                "b) 您在使用服务期间存在违法行为或违反本协议和/或规则的行为的，电力电网站仍可依据本协议向您主张权利。\n" +
                "\n" +
                "C）您在使用服务期间因使用服务与其他用户之间发生的关系，不因本协议的终止而终止，其他用户仍有权向您主张权利，您应继续按您的承诺履行义务。\n" +
                "五、用户名的注销\n" +
                "\n" +
                "1、如您连续三年未使用您的邮箱、手机或本网站认可的其他方式和密码登录过本网站，您的用户名可能被注销，不能再登录本网站，所有与本网站服务将同时终止。\n" +
                "\n" +
                "2、您同意并授权电力电网站，如您在电力电网站有欺诈、发布或销售伪劣商品、侵权他人合法权益或其他严重违反任意电力电网站规则的行为，您的用户名可能被注销，不能再登录本网站，所有网站服务同时终止。\n" +
                "六、关于费用。\n" +
                "\"+\n" +
                "\n" +
                "电力电网站保留在根据第一条第3款通知您后，收取“服务”费用的权利。另外，您因进行交易、向电力电网站获取有偿服务或接触电力电网站服务器而发生的所有应纳税赋，以及相关硬件、软件、通讯、网络服务及其他方面的费用均由您自行承担。电力电网站保留在无须发出书面通知，仅在网站公示的情况下，暂时或永久地更改或停止部分或全部“服务”的权利。\n" +
                "七、电力电网站平台服务和地位\n" +
                "\n" +
                "1、通过电力电网站提供的平台服务，您可在电力电网站上发布交易信息、查询产品和服务信息、达成交易意向并进行交易、参加电力电网站组织的活动以及使用其它信息服务及技术服务。\n" +
                "\n" +
                "2、电力电网站仅作为用户物色交易对象，就货物和服务的交易进行协商，以及获取各类与贸易相关的服务的地点。同时，电力电网站不涉及用户间因交易而产生的法律关系及法律纠纷，不会且不能牵涉进交易各方的交易当中。敬请注意，电力电网站不能控制或保证商贸信息的真实性、合法性、准确性，亦不能控制或保证交易所涉及的物品的质量、安全或合法性，以及相关交易各方履行在贸易协议项下的各项义务的能力。电力电网站不能也不会控制交易各方能否履行协议义务。此外，您应注意到，与以欺诈手段行事的人进行交易的风险是客观存在的。电力电网站希望您在使用电力电网站时，小心谨慎并运用常识。\n" +
                "八、服务使用规范\n" +
                "1、关于您的资料的规则\n" +
                "\n" +
                "1.1“您的资料”包括您在注册、发布信息或交易等过程中、在任何公开信息场合或通过任何电子邮件形式，向电力电网站或其他用户提供的任何资料 ，包括数据、文本、软件、图纸、声响、照片、图画、影像、词句或其他材料。您应对“您的资料”负全部责任，而电力电网站仅作为您在网上发布和刊登“您的资料”的被动渠道。\n" +
                "\n" +
                "1.2您同意并承诺，“您的资料”和您供在电力电网站上交易的任何“物品”（泛指一切可供依法交易的、有形的或无形的、以各种形态存在的某种具体的物品，或某种权利或利益，或某种票据或证券，或某种服务或行为。本协议中“物品”一词均含此义）:\n" +
                "\n" +
                "a. 不会有欺诈成份，与售卖伪造或盗窃无涉；\n" +
                "\n" +
                "b. 不会侵犯任何第三者对该物品享有的物权，或版权、专利、商标、商业秘密或其他知识产权，或隐私权、名誉权；\n" +
                "\n" +
                "c. 不会违反任何法律、法规、条例或规章 (包括但不限于关于规范出口管理、凭许可证经营、贸易配额、保护消费者、不正当竞争或虚假广告的法律、法 规、条例或规章)、本协议及相关规则；\n" +
                "\n" +
                "d. 不会含有诽谤（包括商业诽谤）、非法恐吓或非法骚扰的内容；\n" +
                "\n" +
                "e. 不会含有淫秽、或包含任何儿童色情内容；\n" +
                "\n" +
                "f. 不会含有蓄意毁坏、恶意干扰、秘密地截取或侵占任何系统、数据或个人资料的任何病毒、伪装破坏程序、电脑蠕虫、定时程序炸弹或 其他电脑程序；\n" +
                "\n" +
                "g. 不会直接或间接与下述各项货物或服务连接，或包含对下述各项货物或服务的描述：\n" +
                "\n" +
                "(i) 本协议项下禁止的货物或服务；\n" +
                "\n" +
                "(ii) 您无权连接或包含的货物或服务。此外，您同意不会；\n" +
                "\n" +
                "（ⅲ）在与任何连锁信件、大量胡乱邮寄的电子邮件、滥发电子邮件或任何复制或多余的信息有关的方面使用“服务”；\n" +
                "\n" +
                "(ⅳ)未经其他人士同意，利用“服务”收集其他人士的电子邮件地址及其他资料；\n" +
                "\n" +
                "（ⅴ）利用“服务”制作虚假的电子邮件地址，或以其他形式试图在发送人的身份或信息的来源方面误导其他人士；\n" +
                "\n" +
                "h. 不含有电力电网站认为应禁止或不适合通过电力电网站宣传或交易。\n" +
                "\n" +
                "1.3您同意，您不会对任何资料作商业性利用，包括但不限于在未经电力电网站事先书面批准的情况下，复制在电力电网站上展示的任何资料并用于商业用途。\n" +
                "2、关于交易的规则\n" +
                "\n" +
                "2.1添加产品描述条目。\n" +
                "\n" +
                "产品描述是由您提供的在电力电网站上展示的文字描述、图画和/或照片等相关资料，可以是\n" +
                "\n" +
                "(a) 对您拥有而您希望出售的产品的描述；\n" +
                "\n" +
                "(b)对您正寻找的产品的描述。您须将该等产品描述归入正确的类目内。电力电网站不对产品描述的准确性或内容负责。\n" +
                "\n" +
                "2.2就交易进行协商。交易各方通过在电力电网站上明确描述报盘和回盘，进行相互协商。所有各方接纳报盘或回盘将使所涉及的电力电网站用户有义务完成交易。除非在特殊情况下，诸如用户在您提出报盘后实质性地更改对物品的描述或澄清任何文字输入错误，或您未能证实交易所涉及的用户的身份等 ，报盘和承诺均不得撤回。\n" +
                "\n" +
                "2.3不得操纵交易。您同意不利用帮助实现蒙蔽或欺骗意图的同伙(下属的客户或第三方)，操纵与另一交易方所进行的商业谈判的结果。\n" +
                "\n" +
                "2.4不得干扰交易系统。您同意，您不得使用任何装置、软件或例行程序干预或试图干预电力电网站的正常运作或正在电力电网站上进行的任何交易。您不得采取对任何将不合理或不合比例的庞大负载加诸电力电网站网络结构的行动。\n" +
                "\n" +
                "2.5关于交易反馈。您不得采取任何可能损害信息反馈系统的完整性的行动，诸如：利用第二会员身份标识或第三者为您本身留下正面反馈；利用第二会员身份标识或第三者为其他用户留下负面反馈 (反馈数据轰炸)；或在用户未能履行交易范围以外的某些行动时，留下负面的反馈 (反馈恶意强加)。\n" +
                "\n" +
                "2.6关于处理交易争议。\n" +
                "\n" +
                "(i)电力电网站不涉及用户间因交易而产生的法律关系及法律纠纷，不会且不能牵涉进交易各方的交易当中。倘若您与一名或一名以上用户，或与您通过电力电网站获取其服务的第三者服务供应商发生争议，您免除电力电网站 (及电力电网站代理人和雇员) 在因该等争议而引起的， 或在任何方面与该等争议有关的不同种类和性质的任何(实际和后果性的) 权利主张、要求和损害赔偿等方面的责任。\n" +
                "\n" +
                "(ii)电力电网站有权受理并调处您与其他用户因交易产生的争议，同时有权单方面独立判断其他用户对您的投诉及(或)索偿是否成立，若电力电网站判断索偿成立，则您应及时使用自有资金进行偿付，否则电力电网站有权使用您交纳的保证金（如有）或扣减已购电力电网站及其关联公司的产品或服务中未履行部分的费用的相应金额或您在电力电网站所有账户下的其他资金(或权益)等进行相应偿付。\" + \"电力电网站没有使用自用资金进行偿付的义务，但若进行了该等支付，您应及时赔偿电力电网站的全部损失，否则电力电网站有权通过前述方式抵减相应资金或权益，如仍无法弥补电力电网站损失，则电力电网站保留继续追偿的权利。因电力电网站非司法机构，您完全理解并承认，电力电网站对证据的鉴别能力及对纠纷的处理能力有限，受理贸易争议完全是基于您之委托，不保证争议处理结果符合您的期望，亦不对争议处理结果承担任何责任。电力电网站有权决定是否参与争议的调处。\n" +
                "\n" +
                "(iii) 电力电网站有权通过电子邮件等联系方式向您了解情况，并将所了解的情况通过电子邮件等方式通知对方，您有义务配合电力电网站的工作，否则电力电网站有权做出对您不利的处理结果。\n" +
                "3、违反规则的后果。\n" +
                "\n" +
                "3.1倘若电力电网站认为“您的资料”可能使电力电网站承担任何法律或道义上的责任，或可能使电力电网站 (全部或部分地) 失去电力电网站的互联网服务供应商或其他供应商的服务，则电力电网站可自行全权决定对“您的资料”采取电力电网站认为必要或适当的任何行动，包括但不限于删除该类资料。您特此保证，您对提交给电力电网站的“您的资料”拥有全部权利，包括全部版权。您确认，电力电网站没有责任去认定或决定您提交给电力电网站的资料哪些是应当受到保护的，对享有“服务”的其他用户使用“您的资料”，电力电网站也不必负责。\n" +
                "\n" +
                "3.2对于您涉嫌违反承诺的行为对任意第三方造成损害的，您均应当以自己的名义独立承担所有的法律责任，并应确保电力电网站免责。\n" +
                "\n" +
                "3.3在不限制其他补救措施的前提下，发生下述任一情况，电力电网站可立即发出警告，暂时中止、永久中止或终止您的会员资格，删除您的任何现有产品信息，以及您在网站上展示的任何其他资料：(i) 您违反本协议；(ii) 电力电网站无法核实或鉴定您向电力电网站提供的任何资料；或 (iii) 电力电网站相信您的行为可能会使您、电力电网站用户或通过电力电网站或电力电网站提供服务的第三者服务供应商发生任何法律责任。在不限制任何其他补救措施的前提下，若发现您从事涉及电力电网站的诈骗活动，电力电网站可暂停或终止您的账户。\n" +
                "\n" +
                "3.4经生效法律文书确认用户存在违法或违反本协议行为或者电力电网站自行判断用户涉嫌存在违法或违反本协议行为的，电力电网站有权在电力电网站上以网络发布形式公布用户的违法行为并做出处罚（包括但不限于限权、终止服务等）。\n" +
                "九、 您授予的许可使用权。\n" +
                "您完全理解并同意不可撤销地授予电力电网站及其关联公司下列权利：\n" +
                "\"+\n" +
                "\n" +
                "1、 对于您提供的资料，您授予电力电网站及其关联公司独家的、全球通用的、永久的、免费的许可使用权利 (并有权在多个层面对该权利进行再授权)，使电力电网站及其关联公司有权(全部或部份地) 使用、复制、修订、改写、发布、翻译、分发、执行和展示\\\"您的资料\\\"或制作其派生作品，和/或以现在已知或日后开发的任何形式、媒体或技术，将\\\"您的资料\\\"纳入其他作品内。\n" +
                "\"+\n" +
                "\n" +
                "2、 当您违反本协议或与电力电网站签订的其他协议的约定，电力电网站有权以任何方式通知关联公司，要求其对您的权益采取限制措施，要求关联公司中止、终止对您提供部分或全部服务，且在其经营或实际控制的任何网站公示您的违约情况。\n" +
                "\n" +
                "3、 同样，当您向电力电网站关联公司作出任何形式的承诺，且相关公司已确认您违反了该承诺，则电力电网站有权立即按您的承诺约定的方式 对您的账户采取限制措施，包括但不限于中止或终止向您提供服务，并公示相关公司确认的您的违约情况。您了解并同意，电力电网站无须就相关 确认与您核对事实，或另行征得您的同意，且电力电网站无须就此限制措施或公示行为向您承担任何的责任。\n" +
                "十、隐私。\n" +
                "\n" +
                "尽管有第九条所规定的许可使用权，但基于保护您的隐私是电力电网站的一项基本原则，为此电力电网站还将根据电力电网站的隐私声明使用\\\"您的资料\\\"。电力电网站隐私声明的全部条款属于本协议的一部份，因此，您必须仔细阅读。请注意，您一旦自愿地在电力电网站交易地点披露\\\"您的资料\\\"， 该等资料即可能被其他人士获取和使用。\n" +
                "十一、 责任范围和责任限制。\n" +
                "\n" +
                "1、您明确理解和同意，电力电网站不对因下述任一情况而发生的任何损害赔偿承担责任，包括但不限于利润、商誉、使用、数据等方面的损失或其他无形损失的损害赔偿 (无论电力电网站是否已被告知该等损害赔偿的可能性)：(i) 使用或未能使用“服务”；(ii) 因通过或从“服务”购买或获取任何货物、样品、数据、资料或服务，或通过或从“服务”接收任何信息或缔结任何交易所产生的获取替代货物和服务的费用；(iii) 未经批准接入或更改您的传输资料或数据；(iv) 任何第三者对“服务”的声明或关于“服务”的行为；或 (v) 因任何原因而引起的与“服务”有关的任何其他事宜，包括疏忽。\n" +
                "\n" +
                "2、电力电网站会尽一切努力使您在使用电力电网站的过程中得到乐趣。遗憾的是，电力电网站不能随时预见到任何技术上的问题或其他困难。该等困难可能会导致数据损失或其他服务中断。为此，您明确理解和同意，您使用“服务”的风险由您自行承担，且“服务”以“按现状”和“按可得到 ”的状态提供。电力电网站明确声明不作任何种类的明示或暗示的保证，包括但不限于关于适销性、适用于某一特定用途和无侵权行为等方面的保证。电力电网站对下述内容不作保证：(i)“服务”会符合您的要求；(ii)“服务”不会中断，且适时、安全和不带任何错误；(iii) 通过使用“服务”而可能获取的结果将是准确或可信赖的；及 (iv) 您通过“服务”而购买或获取的任何产品、服务、资料或其他材料的质量将符合您的预期。通过使用“服务”而下载或以其他形式获取任何材料是由您自行全权决定进行的，且与此有关的风险由您自行承担，对于因您下载任何该等材料而发生的您的电脑系统的任何损毁或任何数据损失，您将自行承担责任。您从电力电网站或通过或从“服务”获取的任何口头或书面意见或资料，均不产生未在本协议内明确载明的任何保证责任。\n" +
                "十二. 赔偿。\n" +
                "\n" +
                "您同意，如因您违反本协议或经在此提及而纳入本协议的其他文件，或因您违反法律侵害了第三方的合法权利，或因您违反法律须承担行政或刑事责任，而使第三方或行政、司法机关对电力电网站及关联公司、董事、职员、代理人提出索赔或处罚要求（包括司法费用和其他专业人士的费用），您必须全额赔偿给电力电网站及关联公司、董事、职员、代理人，并使其等免遭损失。\n" +
                "十三、 链接。\n" +
                "\n" +
                "“服务”或第三者均可提供与其他万维网网站或资源的链接。由于电力电网站并不控制该等网站和资源，您承认并同意，电力电网站并不对该等外在网站或资源的可用性负责，且不认可该等网站或资源上或可从该等网站或资源获取的任何内容、宣传、产品、服务或其他材料，也不对其等负责或承担任何责任。您进一步承认和同意，对于任何因使用或信赖从此类网站或资源上获取的此类内容、宣传、产品、服务或其他材料而造成（或声称造成）的任何直接或间接损失，电力电网站均不承担责任。\n" +
                "十四、通知。\n" +
                "\n" +
                "1、您应当准确填写并及时更新您提供的电子邮件地址、联系电话、联系地址、邮政编码等联系方式，以便电力电网站或其他用户与您进行有效联系，因通过这些联系方式无法与您取得联系，导致您在使用电力电网站服务过程中产生任何损失或增加费用的，应由您完全独自承担。您了解并同意，您有义务保持你提供的联系方式的有效性，如有变更需要更新的，您应按电力电网站的要求进行操作。\n" +
                "\n" +
                "2、除非另有明确规定，任何您与电力电网站之间的通知应以电子邮件形式发送，(就电力电网站而言) 电子邮件地址为service@dianlidian.com，或(就您而言)发送到您在登记注册过程中向电力电网站提供的电子邮件地址，或有关方指明的该等其他地址。在电子邮件发出二十四 (24)小时后，通知应被视为已送达，除非发送人被告知相关电子邮件地址已作废。或者，电力电网站可通过邮资预付挂号邮件并要求回执的方式，将通知发到您在登记过程中向电力电网站提供的地址。在该情况下，在付邮当日三 (3) 天后通知被视为已送达。\n" +
                "十五、不可抗力。\n" +
                "\n" +
                "对于因电力电网站合理控制范围以外的原因，包括但不限于自然灾害、罢工或骚乱、物质短缺或定量配给、暴动、战争行为、政府行为、通讯或其他设施故障或严重伤亡事故等，致使电力电网站延迟或未能履约的，电力电网站不对您承担任何责任。\n" +
                "十六. 法律适用、管辖及其他\n" +
                "\n" +
                "1、本协议之效力、解释、变更、执行与争议解决均适用中华人民共和国大陆地区法律，如无相关法律规定的，则应参照通用国际商业惯例和（或）行业惯例。\n" +
                "\n" +
                "2、您与电力电网站仅为独立订约人关系。本协议无意结成或创设任何代理、合伙、合营、雇佣与被雇佣或特性授权与被授权关系。\n" +
                "\n" +
                "3、您同意电力电网站因经营业务需要有权将本协议项下的权力义务就部分或全部进行转让，而无须再通知予您并取得您的同意。\n" +
                "\n" +
                "4、因本协议或电力电网站服务所引起或与其有关的任何争议应向电力电网站所在地人民法院提起诉讼。\n" +
                "\n" +
                "5、本协议取代您和电力电网站先前就相同事项订立的任何书面或口头协议。倘若本协议任何条款被裁定为无效或不可强制执行，该项条款应被撤销，而其余条款应予遵守和执行。条款标题仅为方便参阅而设，并不以任何方式界定、限制、解释或描述该条款的范围或限度。电力电网站未就您或其他人士的某项违约行为采取行动，并不表明电力电网站撤回就任何继后或类似的违约事件采取动的权利。";
    }
}
