package com.example.lql.editmvp.basics.model.activity;

import com.example.lql.editmvp.basics.model.ModelCallBack;

/**
 * Created by Admin on 2017/5/22.
 */

public interface IStudioDetailsActivityModel {
    /**
     * 收藏和取消收藏
     */
    void collection(String Userid, String studioId , ModelCallBack modelCallBack);


    void ShopDetail(String Userid, String studioId , ModelCallBack modelCallBack);
}
