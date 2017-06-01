package com.example.lql.editmvp.basics.model.activity.main;

import com.alibaba.fastjson.JSON;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.bean.LoginBean;
import com.example.lql.editmvp.myhttp.MOkCallBack;
import com.example.lql.editmvp.myhttp.SendRequest;
import com.example.lql.editmvp.utils.LogUtils;

/**
 * Created by Admin on 2017/5/27.
 */

public class SplashActivityModel implements ISplashActivityModel {

    @Override
    public void Login(String name, String pwd, final ModelCallBack modelCallBack) {
        SendRequest.Login1(name, pwd, new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                LogUtils.Loge(response+"==onSuccess");
                if(response.contains("<html>")){
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try{
                    LoginBean bean= JSON.parseObject(response,LoginBean.class);
                    modelCallBack.onSuccess(bean);
                } catch (Exception e) {
                    LogUtils.Loge(e.toString()+"==Loge");
                    modelCallBack.onFailure("数据解析失败");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable e) {
                LogUtils.Loge(e.toString()+"==Loge");
                modelCallBack.onFailure("亲，请检查网络");
            }
        });
    }
}
