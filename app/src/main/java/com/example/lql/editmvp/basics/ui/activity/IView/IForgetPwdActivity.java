package com.example.lql.editmvp.basics.ui.activity.IView;

import com.example.lql.editmvp.bean.GetSmsCode;
import com.example.lql.editmvp.bean.MyBasebean;

/**
 * Created by Admin on 2017/6/1.
 */

public interface IForgetPwdActivity {
    void showLoading();

    void hineLoading();

    void getSms(MyBasebean myBasebean , int code , String msg);

    void ForgetPwd(GetSmsCode myBasebean , int code , String msg);
}
