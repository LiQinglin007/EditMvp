package com.example.lql.editmvp.basics.model.activity;

import com.alibaba.fastjson.JSON;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.bean.StudioDetailsBean;
import com.example.lql.editmvp.myhttp.MOkCallBack;
import com.example.lql.editmvp.myhttp.SendRequest;

/**
 * 类描述：工作室详情
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class StudioDetailsActivityModel implements  IStudioDetailsActivityModel {

    @Override
    public void collection(String Userid, String studioId, final ModelCallBack modelCallBack) {
        SendRequest.UserFavorite(Userid, studioId, 2, new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if (response.contains("<html>")) {
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try{
                    MyBasebean myBasebean = JSON.parseObject(response, MyBasebean.class);
                    modelCallBack.onSuccess(myBasebean);
                }catch (Exception e){
                    modelCallBack.onFailure("解析失败");
                }
            }

            @Override
            public void onFailure(Throwable e) {
                modelCallBack.onFailure("亲，请检查网络");
            }
        });
    }

    @Override
    public void ShopDetail(String Userid, String studioId, final ModelCallBack modelCallBack) {
        SendRequest.ShopDetail(Userid, studioId, new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if (response.contains("<html>")) {
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try{
                    StudioDetailsBean mBean = JSON.parseObject(response, StudioDetailsBean.class);
                    modelCallBack.onSuccess(mBean);
                }catch (Exception e){
                    modelCallBack.onFailure("解析失败");
                }
            }

            @Override
            public void onFailure(Throwable e) {
                modelCallBack.onFailure("亲，请检查网络");
            }
        });
    }



}
