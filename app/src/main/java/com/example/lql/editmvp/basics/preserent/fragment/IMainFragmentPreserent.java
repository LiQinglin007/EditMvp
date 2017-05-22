package com.example.lql.editmvp.basics.preserent.fragment;

/**
 * Created by Admin on 2017/5/19.
 */

public interface IMainFragmentPreserent {
    //获取轮播图
    void setImageList();
    //获取首页服务
    void setServiceList(int page ,int  rows);
    //获取公告
    void setNotice();
}
