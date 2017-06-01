package com.example.lql.editmvp.basics.model.activity.main;

import com.example.lql.editmvp.basics.model.ModelCallBack;

/**
 * Created by Admin on 2017/5/31.
 */

public interface ILoginActivityModel {

    void getSms(String phone , ModelCallBack modelCallBack);

    void Login(String name ,String pwd ,ModelCallBack modelCallBack);

    void Regist(String name ,String pwd , String code ,ModelCallBack modelCallBack);

}
