package com.example.lql.editmvp.basics.ui.fragment.IView;

import com.example.lql.editmvp.bean.GetImglist;
import com.example.lql.editmvp.bean.MainGetService;
import com.example.lql.editmvp.bean.NoticeBean;

/**
 * Created by Admin on 2017/5/19.
 */

public interface IMainFragment {

    void showLoading();

    void hineLoading();

    //获取轮播图
    void setImageList(GetImglist mGetImglist, int code ,String msg);
    //获取首页服务
    void setServiceList(MainGetService mainGetService, int code ,String msg);
    //获取公告
    void setNotice(NoticeBean mNoticeBean, int code ,String msg);

}
