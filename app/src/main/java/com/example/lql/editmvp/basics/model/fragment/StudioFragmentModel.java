package com.example.lql.editmvp.basics.model.fragment;

import com.alibaba.fastjson.JSONObject;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.bean.StudioListBean;
import com.example.lql.editmvp.myhttp.MOkCallBack;
import com.example.lql.editmvp.myhttp.SendRequest;

/**
 * Created by Admin on 2017/5/22.
 */

public class StudioFragmentModel implements  IStudioFragmentModel{

    @Override
    public void getData(String SearchContent,int  Searchtype , int page, int rows, final ModelCallBack modelCallBack) {

        SendRequest.StudioList(SearchContent, Searchtype+"", page + "", rows+"", new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if (response.contains("<html>")) {
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try {
                    StudioListBean roomListBean= JSONObject.parseObject(response,StudioListBean.class);
                    modelCallBack.onSuccess(roomListBean);
                }catch (Exception e){
                    modelCallBack.onFailure("数据异常");
                }
            }

            @Override
            public void onFailure(Throwable e) {
                modelCallBack.onFailure("亲，请检查网络");
            }
        });
    }
}
