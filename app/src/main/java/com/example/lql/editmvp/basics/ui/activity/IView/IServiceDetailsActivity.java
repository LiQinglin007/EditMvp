package com.example.lql.editmvp.basics.ui.activity.IView;

import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.bean.ServiceDetailsBean;

/**
 * Created by Admin on 2017/5/26.
 */

public interface IServiceDetailsActivity {

    void showLoading();

    void hineLoading();

    void setdata(ServiceDetailsBean mServiceDetailsBean , int code , String msg);

    void setCollection(MyBasebean myBasebean , int code ,String msg);
}
