package com.example.lql.editmvp.basics.preserent.activity.main;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.activity.main.ForgetPwdActivityModel;
import com.example.lql.editmvp.basics.model.activity.main.IForgetPwdActivityModel;
import com.example.lql.editmvp.basics.ui.activity.IView.IForgetPwdActivity;
import com.example.lql.editmvp.bean.GetSmsCode;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.utils.FinalData;

/**
 * Created by Admin on 2017/6/1.
 */

public class ForgetPwdActivityPreserent extends BasePreserent<IForgetPwdActivity> implements  IForgetPwdActivityPreserent {

    IForgetPwdActivityModel mIForgetPwdActivityModel;
    IForgetPwdActivity mIForgetPwdActivity;

    public ForgetPwdActivityPreserent() {
        mIForgetPwdActivityModel=new ForgetPwdActivityModel();
    }

    @Override
    public void getSms(String phone) {
        mIForgetPwdActivity=getView();
        mIForgetPwdActivity.showLoading();
        mIForgetPwdActivityModel.getSms(phone, new ModelCallBack<MyBasebean>() {
            @Override
            public void onSuccess(MyBasebean response) {
                mIForgetPwdActivity.hineLoading();
                mIForgetPwdActivity.getSms(response , FinalData.OKHTTP_SUCCESS ,"");
            }

            @Override
            public void onFailure(String e) {
                mIForgetPwdActivity.hineLoading();
                mIForgetPwdActivity.getSms(null , FinalData.OKHTTP_FAILURE , e.toString());
            }
        });
    }

    @Override
    public void forgetPwd(String phone, String pwd, String code) {
        mIForgetPwdActivity=getView();
        mIForgetPwdActivity.showLoading();
        mIForgetPwdActivityModel.forgetPwd(phone, pwd, code, new ModelCallBack<GetSmsCode>() {
            @Override
            public void onSuccess(GetSmsCode response) {
                mIForgetPwdActivity.hineLoading();
                mIForgetPwdActivity.ForgetPwd(response , FinalData.OKHTTP_SUCCESS ,"");
            }

            @Override
            public void onFailure(String e) {
                mIForgetPwdActivity.hineLoading();
                mIForgetPwdActivity.ForgetPwd(null , FinalData.OKHTTP_FAILURE , e.toString());
            }
        });
    }
}
