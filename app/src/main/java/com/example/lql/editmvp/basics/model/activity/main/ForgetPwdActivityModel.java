package com.example.lql.editmvp.basics.model.activity.main;

import com.alibaba.fastjson.JSON;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.bean.GetSmsCode;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.myhttp.MOkCallBack;
import com.example.lql.editmvp.myhttp.SendRequest;
import com.example.lql.editmvp.utils.LogUtils;

/**
 * Created by Admin on 2017/6/1.
 */

public class ForgetPwdActivityModel implements  IForgetPwdActivityModel {
    @Override
    public void getSms(String phone, final ModelCallBack modelCallBack) {
        SendRequest.getregistersms(phone, "2", new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                LogUtils.Loge(response);
                if(response.contains("<html>")){
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try{
                    MyBasebean myBasebean = JSON.parseObject(response, MyBasebean.class);
                    modelCallBack.onSuccess(myBasebean);
                } catch (Exception e) {
                    LogUtils.Loge(e.toString());
                    modelCallBack.onFailure("数据异常");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Throwable e) {
                LogUtils.Loge(e.toString());
                modelCallBack.onFailure("亲，请检查网络");
            }
        });
    }

    @Override
    public void forgetPwd(String phone, String pwd, String code, final ModelCallBack modelCallBack) {
        SendRequest.userregister(phone, pwd, code, "2", new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                LogUtils.Loge(response);
                if(response.contains("<html>")){
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try{
                    GetSmsCode bean = JSON.parseObject(response, GetSmsCode.class);
                    modelCallBack.onSuccess(bean);
                } catch (Exception e) {
                    LogUtils.Loge(e.toString());
                    modelCallBack.onFailure("数据异常");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Throwable e) {
                LogUtils.Loge(e.toString());
                modelCallBack.onFailure("亲，请检查网络");
            }
        });
    }
}
