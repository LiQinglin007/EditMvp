package com.example.lql.editmvp.basics.model.fragment;

import com.example.lql.editmvp.basics.model.ModelCallBack;

/**
 * Created by Admin on 2017/5/19.
 */

public interface IMainFragmentModel {

    //获取轮播图
    void getImageList( ModelCallBack modelCallBack);
    //获取首页服务
    void getServiceList(int page , int rows , ModelCallBack modelCallBack);
    //获取公告
    void getNotice( ModelCallBack modelCallBack);

}
