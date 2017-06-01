package com.example.lql.editmvp.basics.ui.activity.view.main;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.preserent.activity.main.SplashActivityPreserent;
import com.example.lql.editmvp.basics.ui.activity.IView.ISplashActivity;
import com.example.lql.editmvp.basics.ui.activity.view.MainActivity;
import com.example.lql.editmvp.bean.LoginBean;
import com.example.lql.editmvp.utils.LogUtils;
import com.example.lql.editmvp.utils.PreferenceUtils;
import com.example.lql.editmvp.utils.T;

/**
 * Created by Admin on 2017/5/27.
 */

public class SplashActivity extends BaseActivity<ISplashActivity , SplashActivityPreserent> implements ISplashActivity {
    String name="";
    String pwd="";

    boolean run=true;
    int time=0;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        boolean Login=PreferenceUtils.getBoolean("Login",false);
        if(Login){
            LogUtils.Loge("111111111111");
             name=PreferenceUtils.getString("name","");
             pwd=PreferenceUtils.getString("pwd","");
             mPresenter.Login(name,pwd);
        }else{
            LogUtils.Loge("2222222222");
            new MyThread().start();
        }
    }

    @Override
    protected SplashActivityPreserent creatPresenter() {
        return new SplashActivityPreserent();
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
    public void setData(LoginBean bean, int code, String msg) {
        if(code==0){
            if(bean.getResultCode()==0){
                PreferenceUtils.setBoolean("Login",true);
                PreferenceUtils.setString("name",name);
                PreferenceUtils.setString("pwd",pwd);

                PreferenceUtils.setString("Telphone",bean.getData().getData().getTelphone()+"");//电话
                PreferenceUtils.setString("UserName",bean.getData().getData().getUserName()+"");//昵称
                PreferenceUtils.setString("UserId",bean.getData().getData().getUserId()+"");//id
                PreferenceUtils.setFloat("Balance",Float.parseFloat(bean.getData().getData().getBalance()+""));//余额
                PreferenceUtils.setString("Img",bean.getData().getData().getImg());//头像
                PreferenceUtils.setString("Sex",bean.getData().getData().getSex());//性别
                PreferenceUtils.setString("Birthday",bean.getData().getData().getBirthday());//生日
                PreferenceUtils.setString("School",bean.getData().getData().getSchool());//学校
                PreferenceUtils.setString("Job",bean.getData().getData().getJob());//职位
                PreferenceUtils.setString("Graduate",bean.getData().getData().getGraduate());//学历
                PreferenceUtils.setString("Profession",bean.getData().getData().getProfession());//职称
                PreferenceUtils.setString("Real",bean.getData().getData().getReal()+"");//实名认证状态   0:正在审核1：已实名认证2：未认证
                PreferenceUtils.setString("state",bean.getData().getState()+"");//是否申请工作室  0:未申请工作室   1：已申请

                PreferenceUtils.setBoolean("IsLogin",true);
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }else{
                T.shortToast(getApplicationContext(),bean.getMsg());
                PreferenceUtils.setBoolean("Login", false);
                PreferenceUtils.setBoolean("IsLogin", false);
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }else{
            PreferenceUtils.setBoolean("Login", false);
            PreferenceUtils.setBoolean("IsLogin", false);
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }
    }


    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time++;
            if(time>2){
                run=false;
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }
    };


    class MyThread  extends  Thread{
        @Override
        public void run() {
            while (run){
                try {
                    mHandler.sendEmptyMessage(0);
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }
}
