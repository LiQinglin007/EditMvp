package com.example.lql.editmvp.basics.ui.activity.IView;

import com.example.lql.editmvp.bean.GetSmsCode;
import com.example.lql.editmvp.bean.LoginBean;
import com.example.lql.editmvp.bean.MyBasebean;

/**
 * Created by Admin on 2017/5/31.
 */

public interface ILoginActivity  {

    void showLoading();

    void hineLoading();

    void getSms(MyBasebean myBasebean , int code ,String msg);

    void Login(LoginBean mLoginBean , int code , String msg);

    void Regist(GetSmsCode getSmsCode , int code , String msg);

}
