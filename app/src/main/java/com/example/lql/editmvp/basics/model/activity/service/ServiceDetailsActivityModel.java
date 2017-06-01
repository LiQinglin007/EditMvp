package com.example.lql.editmvp.basics.model.activity.service;

import com.alibaba.fastjson.JSON;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.bean.ServiceDetailsBean;
import com.example.lql.editmvp.myhttp.MOkCallBack;
import com.example.lql.editmvp.myhttp.SendRequest;
import com.example.lql.editmvp.utils.LogUtils;

/**
 * Created by Admin on 2017/5/26.
 */

public class ServiceDetailsActivityModel implements  IServiceDetailsActivityModel{

    @Override
    public void getDta(String serviceId, String userid,final  ModelCallBack modelCallBack) {
        SendRequest.ServiceDetail(serviceId,userid, new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                LogUtils.Loge(response+"ServiceDetail");
                if(response.contains("<html>")){
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try {
                    ServiceDetailsBean mBean = JSON.parseObject(response, ServiceDetailsBean.class);
                    modelCallBack.onSuccess(mBean);
                }catch (Exception e){
                    LogUtils.Loge(e.toString()+"ServiceDetail");
                    modelCallBack.onFailure("数据解析错误");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                LogUtils.Loge(e.toString()+"ServiceDetail");
                modelCallBack.onFailure("亲，请检查网络");
            }
        });
    }

    @Override
    public void collection(String userid, String serviceId, final ModelCallBack modelCallBack) {
        SendRequest.UserFavorite(userid,serviceId, 1, new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                LogUtils.Loge(response+"UserFavorite");
                if(response.contains("<html>")){
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try {
                    MyBasebean myBasebean=JSON.parseObject(response,MyBasebean.class);
                    modelCallBack.onSuccess(myBasebean);
                }catch (Exception e){
                    LogUtils.Loge(e.toString()+"UserFavorite");
                    modelCallBack.onFailure("数据解析错误");
                }
            }

            @Override
            public void onFailure(Throwable e) {
                LogUtils.Loge(e.toString()+"UserFavorite");
                modelCallBack.onFailure("服务器异常");
            }
        });

    }
}
