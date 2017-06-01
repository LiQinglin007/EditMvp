package com.example.lql.editmvp.basics.model.activity.service;

import com.example.lql.editmvp.basics.model.ModelCallBack;

/**
 * Created by Admin on 2017/5/26.
 */

public interface IServiceDetailsActivityModel {

    void getDta(String serviceId , String userid , ModelCallBack modelCallBack);

    void collection(String userid , String serviceId , ModelCallBack modelCallBack);
}
