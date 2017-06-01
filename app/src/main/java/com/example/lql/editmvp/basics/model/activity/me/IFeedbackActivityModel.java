package com.example.lql.editmvp.basics.model.activity.me;

import com.example.lql.editmvp.basics.model.ModelCallBack;

/**
 * Created by Admin on 2017/6/1.
 */

public interface IFeedbackActivityModel {
    void submit(String userid ,String content , ModelCallBack modelCallBack);
}
