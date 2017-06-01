package com.example.lql.editmvp.basics.model.activity.main;

import com.example.lql.editmvp.basics.model.ModelCallBack;

/**
 * Created by Admin on 2017/6/1.
 */

public interface IForgetPwdActivityModel {

    void getSms(String phone , ModelCallBack modelCallBack);

    void forgetPwd(String phone , String pwd , String code  , ModelCallBack modelCallBack);
}
