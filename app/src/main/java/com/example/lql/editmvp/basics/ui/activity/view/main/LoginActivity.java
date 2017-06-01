package com.example.lql.editmvp.basics.ui.activity.view.main;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.preserent.activity.main.LoginActivityPreserent;
import com.example.lql.editmvp.basics.ui.activity.IView.ILoginActivity;
import com.example.lql.editmvp.basics.ui.activity.view.MainActivity;
import com.example.lql.editmvp.bean.GetSmsCode;
import com.example.lql.editmvp.bean.LoginBean;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.utils.CountDownUtils;
import com.example.lql.editmvp.utils.FinalData;
import com.example.lql.editmvp.utils.PreferenceUtils;
import com.example.lql.editmvp.utils.PublicStaticData;
import com.example.lql.editmvp.utils.RegularUtil;
import com.example.lql.editmvp.utils.T;


/**
 * 类描述：登录注册页面
 * 作  者：李清林
 * 时  间：2016/11/23
 * 修改备注：
 */


public class LoginActivity extends BaseActivity<ILoginActivity , LoginActivityPreserent> implements View.OnClickListener , ILoginActivity {

    private TextView loginradio;
    private TextView registerradio;

    private EditText loginphoneed;
    private EditText loginpwded;
    private TextView loginforgotpwdtv;
    private Button loginbut;
    private LinearLayout loginly;
    private LinearLayout registerly;
    private EditText registerphoneed;
    private EditText registercodeed;
    private TextView registergetcodetv;
    private EditText registerpwded;
    private EditText registerqpwded;
    private Button registerbut;
    private ImageView login_close;


    String RegistPhone;
    String RegistPwd;

    String LoginPhone;
    String LoginPwd;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        this.registerbut = (Button) findViewById(R.id.register_but);
        this.registerqpwded = (EditText) findViewById(R.id.register_qpwd_ed);
        this.registerpwded = (EditText) findViewById(R.id.register_pwd_ed);
        this.registergetcodetv = (TextView) findViewById(R.id.register_getcode_tv);
        this.registercodeed = (EditText) findViewById(R.id.register_code_ed);
        this.registerphoneed = (EditText) findViewById(R.id.register_phone_ed);
        this.loginly = (LinearLayout) findViewById(R.id.login_ly);
        this.registerly = (LinearLayout) findViewById(R.id.register_ly);
        this.loginbut = (Button) findViewById(R.id.login_but);
        this.loginforgotpwdtv = (TextView) findViewById(R.id.login_forgotpwd_tv);
        this.loginpwded = (EditText) findViewById(R.id.login_pwd_ed);
        this.loginphoneed = (EditText) findViewById(R.id.login_phone_ed);
        this.registerradio = (TextView) findViewById(R.id.register_radio);
        this.loginradio = (TextView) findViewById(R.id.login_radio);
        login_close = (ImageView) findViewById(R.id.login_close);


        registerradio.setOnClickListener(this);
        loginradio.setOnClickListener(this);
        registergetcodetv.setOnClickListener(this);//获取验证码
        loginforgotpwdtv.setOnClickListener(this);//忘记密码
        loginbut.setOnClickListener(this);
        registerbut.setOnClickListener(this);
        login_close.setOnClickListener(this);

        CountDownUtils.startTime(registergetcodetv, false);
    }


    @Override
    protected LoginActivityPreserent creatPresenter() {
        return new LoginActivityPreserent();
    }


    @Override
    protected void onResume() {
        CountDownUtils.startTime(registergetcodetv, false);
        super.onResume();
    }


    @Override
    protected void onStop() {
        CountDownUtils.time = 60;
        CountDownUtils.run = false;
        super.onStop();
    }

    private void steView(boolean isLogin) {
        if (isLogin) {
            loginradio.setBackground(getResources().getDrawable(R.drawable.logintext_left_sle));
            loginradio.setTextColor(getResources().getColor(R.color.color_FFFFFF));
            registerradio.setBackground(getResources().getDrawable(R.drawable.logintext_right_no));
            registerradio.setTextColor(getResources().getColor(R.color.color_1AB394));
            loginly.setVisibility(View.VISIBLE);
            registerly.setVisibility(View.GONE);
        } else {
            loginradio.setBackground(getResources().getDrawable(R.drawable.logintext_left_no));
            loginradio.setTextColor(getResources().getColor(R.color.color_1AB394));
            registerradio.setBackground(getResources().getDrawable(R.drawable.logintext_right_sle));
            registerradio.setTextColor(getResources().getColor(R.color.color_FFFFFF));
            loginly.setVisibility(View.GONE);
            registerly.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.register_radio://注册
                steView(false);
                break;
            case R.id.login_radio://登录
                steView(true);
                break;
            case R.id.register_getcode_tv://获取验证码
                if (!RegularUtil.isMobile(registerphoneed.getText().toString().trim())) {
                    T.shortToast(getApplicationContext(), "手机号输入格式错误");
                } else {
                    mPresenter.getSms(registerphoneed.getText().toString().trim());
                }
                break;
            case R.id.login_but://登录按钮
                loginVerification();//登录验证
                break;
            case R.id.register_but://注册按钮
                registerVerification();
                break;
            case R.id.login_forgotpwd_tv://忘记密码
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
                break;
            case R.id.login_close:
                finish();
                break;
        }
    }

    /**
     * 登录验证
     */
    public void loginVerification() {
        LoginPhone = loginphoneed.getText().toString().trim();
        LoginPwd = loginpwded.getText().toString().trim();
        if(TextUtils.isEmpty(LoginPhone)){
            T.shortToast(getApplicationContext(), "手机号不能为空");
        }else if (!RegularUtil.isMobile(LoginPhone)) {
            T.shortToast(getApplicationContext(), "手机号输入格式错误");
        } else if (TextUtils.isEmpty(LoginPwd)) {
            T.shortToast(getApplicationContext(), "密码不能为空");
        }else if (!RegularUtil.isPassword(LoginPwd)) {
            T.shortToast(getApplicationContext(), "请输入6~16位数字或字母");
        } else {
           mPresenter.Login(LoginPhone , LoginPwd);
        }
    }


    /**
     * 注册验证
     */
    public void registerVerification() {
        RegistPhone = registerphoneed.getText().toString().trim();
        String code = registercodeed.getText().toString().trim();
        RegistPwd = registerpwded.getText().toString().trim();
        String qpwd = registerqpwded.getText().toString().trim();

        if (!RegularUtil.isMobile(RegistPhone)) {
            T.shortToast(getApplicationContext(), "手机号输入格式错误");
        } else if (TextUtils.isEmpty(code)) {
            T.shortToast(getApplicationContext(), "请输入短信验证码");
        } else if (!RegularUtil.isPassword(RegistPwd)) {
            T.shortToast(getApplicationContext(), "请输入6~16位数字或字母");
        } else if (!qpwd.equals(RegistPwd)) {
            T.shortToast(getApplicationContext(), "两次密码不一致");
        } else {
            mPresenter.Regist(RegistPhone ,RegistPwd ,code);
        }
    }


    @Override
    protected void onDestroy() {
        CountDownUtils.time = 60;
        CountDownUtils.run = false;
        super.onDestroy();
    }


    @Override
    public void showLoading() {
        creatMyDialog(getString(R.string.loading_string));
        showMyDialog();
    }

    @Override
    public void hineLoading() {
        hideMyDialog();
    }

    @Override
    public void getSms(MyBasebean myBasebean, int code, String msg) {
        if(code== FinalData.OKHTTP_SUCCESS){
            if (myBasebean.getResultCode() == 1) {
                T.shortToast(getApplicationContext(), myBasebean.getMsg());
            } else {
                CountDownUtils.startTime(registergetcodetv, true);
            }
        }else{
            T.shortToast(LoginActivity.this,msg);
        }
    }

    @Override
    public void Login(LoginBean bean, int code, String msg) {
        if(code== FinalData.OKHTTP_SUCCESS){
            if (bean.getResultCode() == 0) {
                PreferenceUtils.setBoolean("Login", true);
                //登录方式    1：账号密码登录   2：第三方登录
                PreferenceUtils.setInt("LoginType", 1);
                PreferenceUtils.setString("name", LoginPhone);
                PreferenceUtils.setString("pwd", LoginPwd);
                PreferenceUtils.setString("Telphone", bean.getData().getData().getTelphone() + "");//电话
                PreferenceUtils.setString("UserId", bean.getData().getData().getUserId() + "");//id
                PreferenceUtils.setFloat("Balance", Float.parseFloat(bean.getData().getData().getBalance() + ""));//余额
                PreferenceUtils.setString("Img", bean.getData().getData().getImg());//头像
                PreferenceUtils.setString("UserName", bean.getData().getData().getUserName() + "");//昵称
                PreferenceUtils.setString("Sex", bean.getData().getData().getSex());//性别
                PreferenceUtils.setString("Birthday", bean.getData().getData().getBirthday());//生日
                PreferenceUtils.setString("School", bean.getData().getData().getSchool());//学校
                PreferenceUtils.setString("Job", bean.getData().getData().getJob());//职位
                PreferenceUtils.setString("Graduate", bean.getData().getData().getGraduate());//学历
                PreferenceUtils.setString("Profession", bean.getData().getData().getProfession());//职称
                PreferenceUtils.setString("Real", bean.getData().getData().getReal() + "");//实名认证状态   0:正在审核1：已实名认证2：未认证
                PreferenceUtils.setString("state", bean.getData().getState() + "");//是否申请工作室  0:未申请工作室   1：已申请

                PreferenceUtils.setBoolean("IsLogin", true);
                if(PublicStaticData.toMain){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    PublicStaticData.toMain=false;
                }
                finish();
            } else {
                T.shortToast(getApplicationContext(), bean.getMsg());
            }
        }else{
            T.shortToast(LoginActivity.this,msg);
        }
    }

    @Override
    public void Regist(GetSmsCode getSmsCode, int code, String msg) {
        if(code== FinalData.OKHTTP_SUCCESS){
            if(getSmsCode.getResultCode()==0){
                loginphoneed.setText(RegistPhone);
                loginpwded.setText(RegistPwd);
                steView(true);
            }else{
                T.shortToast(LoginActivity.this,getSmsCode.getMsg());
            }
        }else{
            T.shortToast(LoginActivity.this,msg);
        }
    }


}
