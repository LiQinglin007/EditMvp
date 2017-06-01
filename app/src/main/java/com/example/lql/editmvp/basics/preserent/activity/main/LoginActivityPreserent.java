package com.example.lql.editmvp.basics.preserent.activity.main;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.activity.main.ILoginActivityModel;
import com.example.lql.editmvp.basics.model.activity.main.LoginActivityModel;
import com.example.lql.editmvp.basics.ui.activity.IView.ILoginActivity;
import com.example.lql.editmvp.bean.GetSmsCode;
import com.example.lql.editmvp.bean.LoginBean;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.utils.FinalData;

/**
 * Created by Admin on 2017/5/31.
 */

public class LoginActivityPreserent extends BasePreserent<ILoginActivity> implements  ILoginActivityPreserent{

    ILoginActivityModel mILoginActivityModel;
    ILoginActivity mILoginActivity;

    public LoginActivityPreserent() {
        mILoginActivityModel=new LoginActivityModel();
    }

    @Override
    public void getSms(String phone) {
        mILoginActivity=getView();
        mILoginActivity.showLoading();
        mILoginActivityModel.getSms(phone, new ModelCallBack<MyBasebean>() {
            @Override
            public void onSuccess(MyBasebean response) {
                mILoginActivity.hineLoading();
                mILoginActivity.getSms(response , FinalData.OKHTTP_SUCCESS ,"");
            }
            @Override
            public void onFailure(String e) {
                mILoginActivity.hineLoading();
                mILoginActivity.getSms(null , FinalData.OKHTTP_FAILURE ,e);
            }
        });
    }

    @Override
    public void Login(String name, String pwd) {
        mILoginActivity=getView();
        mILoginActivity.showLoading();
        mILoginActivityModel.Login(name, pwd, new ModelCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean response) {
                mILoginActivity.hineLoading();
                mILoginActivity.Login(response , FinalData.OKHTTP_SUCCESS ,"");
            }

            @Override
            public void onFailure(String e) {
                mILoginActivity.hineLoading();
                mILoginActivity.Login(null , FinalData.OKHTTP_FAILURE ,e);
            }
        });
    }

    @Override
    public void Regist(String name, String pwd, String RegistCode) {
        mILoginActivity=getView();
        mILoginActivityModel.Regist(name, pwd, RegistCode, new ModelCallBack<GetSmsCode>() {
            @Override
            public void onSuccess(GetSmsCode response) {
                mILoginActivity.hineLoading();
                mILoginActivity.Regist(response , FinalData.OKHTTP_SUCCESS ,"");
            }

            @Override
            public void onFailure(String e) {
                mILoginActivity.hineLoading();
                mILoginActivity.Regist(null , FinalData.OKHTTP_FAILURE ,e);
            }
        });
    }
}
