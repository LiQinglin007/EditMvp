package com.example.lql.editmvp.basics.preserent.activity.main;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.activity.main.ISplashActivityModel;
import com.example.lql.editmvp.basics.model.activity.main.SplashActivityModel;
import com.example.lql.editmvp.basics.ui.activity.IView.ISplashActivity;
import com.example.lql.editmvp.bean.LoginBean;
import com.example.lql.editmvp.utils.FinalData;

/**
 * Created by Admin on 2017/5/27.
 */

public class SplashActivityPreserent extends BasePreserent<ISplashActivity> implements  ISplashActivityPreserent {

    ISplashActivityModel mISplashActivityModel;
    ISplashActivity mISplashActivity;

    public SplashActivityPreserent() {
        mISplashActivityModel=new SplashActivityModel();
    }

    @Override
    public void Login(String name, String pwd) {
        mISplashActivity=getView();
        mISplashActivityModel.Login(name, pwd, new ModelCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean response) {
                mISplashActivity.setData(response , FinalData.OKHTTP_SUCCESS ,"");
            }

            @Override
            public void onFailure(String e) {
                mISplashActivity.setData(null , FinalData.OKHTTP_FAILURE ,e);
            }
        });
    }
}
