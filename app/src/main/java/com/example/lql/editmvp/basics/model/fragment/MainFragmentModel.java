package com.example.lql.editmvp.basics.model.fragment;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.bean.GetImglist;
import com.example.lql.editmvp.bean.MainGetService;
import com.example.lql.editmvp.bean.NoticeBean;
import com.example.lql.editmvp.myhttp.MOkCallBack;
import com.example.lql.editmvp.myhttp.SendRequest;
import com.example.lql.editmvp.utils.LogUtils;
import com.example.lql.editmvp.utils.T;

/**
 * Created by Admin on 2017/5/19.
 */

public class MainFragmentModel implements  IMainFragmentModel{

    @Override
    public void getImageList(final ModelCallBack modelCallBack) {
        SendRequest.ImgList(new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                Log.e("###onSuccess",response+"response");
                if(response.contains("<html>")){
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try {
                    GetImglist roomListBean= JSONObject.parseObject(response,GetImglist.class);
                    modelCallBack.onSuccess(roomListBean);
                }catch (Exception e){
                    modelCallBack.onFailure(e.toString());
                }
            }
            @Override
            public void onFailure(Throwable e) {
                modelCallBack.onFailure(e.toString());
            }
        });
    }

    @Override
    public void getServiceList( int page, int rows,final  ModelCallBack modelCallBack) {
        SendRequest.ServiceList(page, rows, new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if(response.contains("<html>")){
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try {
                    MainGetService mainGetService=JSON.parseObject(response,MainGetService.class);
                    modelCallBack.onSuccess(mainGetService);
                }catch (Exception e){
                    modelCallBack.onFailure(e.toString());
                    LogUtils.Loge(e.toString());
                }
            }

            @Override
            public void onFailure(Throwable e) {
                modelCallBack.onFailure(e.toString());
            }
        });
    }

    @Override
    public void getNotice( final ModelCallBack modelCallBack) {
        SendRequest.Notice(new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if(response.contains("<html>")){
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try {
                    NoticeBean mBean= JSON.parseObject(response,NoticeBean.class);
                    modelCallBack.onSuccess(mBean);
                }catch (Exception e){
                    modelCallBack.onFailure(e.toString());
                    LogUtils.Loge(e.toString());
                }
            }

            @Override
            public void onFailure(Throwable e) {
                modelCallBack.onFailure(e.toString());
            }
        });
    }
}
