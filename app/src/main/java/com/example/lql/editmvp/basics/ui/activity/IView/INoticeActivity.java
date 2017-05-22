package com.example.lql.editmvp.basics.ui.activity.IView;

import com.example.lql.editmvp.bean.NoticeBean;

/**
 * Created by Admin on 2017/5/22.
 */

public interface INoticeActivity {
    void showLoading();

    void hineLoading();

    //获取公告
    void setNotice(NoticeBean mNoticeBean, int code , String msg);

}
