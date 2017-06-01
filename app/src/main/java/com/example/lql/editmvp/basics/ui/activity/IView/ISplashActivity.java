package com.example.lql.editmvp.basics.ui.activity.IView;

import com.example.lql.editmvp.bean.LoginBean;

/**
 * Created by Admin on 2017/5/27.
 */

public interface ISplashActivity {

    void showLoading();

    void hineLoading();

    void setData(LoginBean mBean , int code , String msg);

}
