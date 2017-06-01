package com.example.lql.editmvp.basics.ui.activity.IView;

import com.example.lql.editmvp.bean.MyBasebean;

/**
 * Created by Admin on 2017/6/1.
 */

public interface IFeedbackActivity {

    void showLoading();

    void hineLoading();

    void submit(MyBasebean myBasebean , int code , String msg);

}
