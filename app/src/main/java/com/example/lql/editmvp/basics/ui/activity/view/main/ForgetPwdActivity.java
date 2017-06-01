package com.example.lql.editmvp.basics.ui.activity.view.main;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.preserent.activity.main.ForgetPwdActivityPreserent;
import com.example.lql.editmvp.basics.ui.activity.IView.IForgetPwdActivity;
import com.example.lql.editmvp.bean.GetSmsCode;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.utils.CountDownUtils;
import com.example.lql.editmvp.utils.FinalData;
import com.example.lql.editmvp.utils.PreferenceUtils;
import com.example.lql.editmvp.utils.PublicStaticData;
import com.example.lql.editmvp.utils.RegularUtil;
import com.example.lql.editmvp.utils.T;


/**
 * 类描述：忘记密码页面
 * 作  者：李清林
 * 时  间：2016/11/23 13:47
 * 修改备注：
 */
public class ForgetPwdActivity extends BaseActivity<IForgetPwdActivity, ForgetPwdActivityPreserent> implements View.OnClickListener, IForgetPwdActivity {

    private EditText forgetphoneed;
    private EditText forgetcodeed;
    private TextView forgetgetcodetv;
    private EditText forgetpwded;
    private EditText forgetqpwded;
    private Button forgetsubmitbut;
    private TextView title;
    private ImageView leftback;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("忘记密码");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setImageResource(R.drawable.btn_back_lv);
        this.forgetsubmitbut = (Button) findViewById(R.id.forget_submit_but);
        this.forgetqpwded = (EditText) findViewById(R.id.forget_qpwd_ed);
        this.forgetpwded = (EditText) findViewById(R.id.forget_pwd_ed);
        this.forgetgetcodetv = (TextView) findViewById(R.id.forget_getcode_tv);
        this.forgetcodeed = (EditText) findViewById(R.id.forget_code_ed);
        this.forgetphoneed = (EditText) findViewById(R.id.forget_phone_ed);

        leftback.setOnClickListener(this);
        forgetgetcodetv.setOnClickListener(this);
        forgetsubmitbut.setOnClickListener(this);
        CountDownUtils.startTime(forgetgetcodetv, false);
    }


    @Override
    protected ForgetPwdActivityPreserent creatPresenter() {
        return new ForgetPwdActivityPreserent();
    }

    @Override
    protected void onResume() {
        CountDownUtils.startTime(forgetgetcodetv, false);
        super.onResume();
    }


    @Override
    protected void onStop() {
        CountDownUtils.time = 60;
        CountDownUtils.run = false;
        super.onStop();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.forget_getcode_tv:
                if (!RegularUtil.isMobile(forgetphoneed.getText().toString().trim())) {
                    T.shortToast(getApplicationContext(), "手机号格式不正确");
                } else {
                    mPresenter.getSms(forgetphoneed.getText().toString().trim());
                }
                break;

            case R.id.forget_submit_but:
                registerVerification();
                break;
            case R.id.titleBar_left_img:
                finish();
                break;
        }
    }

    /**
     * 找回密码验证
     */
    public void registerVerification() {
        String phone = forgetphoneed.getText().toString().trim();
        String code = forgetcodeed.getText().toString().trim();
        String pwd = forgetpwded.getText().toString().trim();
        String qpwd = forgetqpwded.getText().toString().trim();

        if (!RegularUtil.isMobile(phone)) {
            T.shortToast(getApplicationContext(), "手机号格式不正确");
        } else if (TextUtils.isEmpty(code)) {
            T.shortToast(getApplicationContext(), "请输入短信验证码");
        } else if (!RegularUtil.isPassword(pwd)) {
            T.shortToast(getApplicationContext(), "请输入6~16位数字或字母");
        } else if (!qpwd.equals(pwd)) {
            T.shortToast(getApplicationContext(), "两次密码不一致");
        } else {
            mPresenter.forgetPwd(phone , pwd , code);
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

    }

    @Override
    public void hineLoading() {

    }

    @Override
    public void getSms(MyBasebean myBasebean, int code, String msg) {
        if(code== FinalData.OKHTTP_SUCCESS){
            if (myBasebean.getResultCode() == 1) {
                T.shortToast(getApplicationContext(), myBasebean.getMsg());
            }else{
                CountDownUtils.startTime(forgetgetcodetv,true);
            }
        }else{
            T.shortToast(getApplicationContext(),msg);
        }
    }

    @Override
    public void ForgetPwd(GetSmsCode myBasebean, int code, String msg) {
        if(code== FinalData.OKHTTP_SUCCESS){
            if (myBasebean.getResultCode() == 0) {
                PreferenceUtils.setBoolean("IsLogin",false);
                PublicStaticData.MainFragmentNmuber = 0;
                PublicStaticData.toMain=true;
                startActivity(new Intent(ForgetPwdActivity.this, LoginActivity.class));
                finish();
            } else {
                T.shortToast(getApplicationContext(), myBasebean.getMsg());
            }
        }else{
            T.shortToast(getApplicationContext(),msg);
        }
    }
}
