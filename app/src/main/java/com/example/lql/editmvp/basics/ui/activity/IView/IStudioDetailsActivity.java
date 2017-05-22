package com.example.lql.editmvp.basics.ui.activity.IView;

import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.bean.StudioDetailsBean;

/**
 * Created by Admin on 2017/5/22.
 */

public interface IStudioDetailsActivity {

    void showLoading();

    void hineLoading();


    void setDta(StudioDetailsBean mNoticeBean, int code , String msg);

    void setCollection(MyBasebean mNoticeBean, int code , String msg);

}
