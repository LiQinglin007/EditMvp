package com.example.lql.editmvp.basics.model.activity.main;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.alibaba.fastjson.JSON;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.bean.GetSmsCode;
import com.example.lql.editmvp.bean.LoginBean;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.myhttp.MOkCallBack;
import com.example.lql.editmvp.myhttp.SendRequest;
import com.example.lql.editmvp.utils.LogUtils;

/**
 * 类描述：登录注册
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class LoginActivityModel implements  ILoginActivityModel{
    @Override
    public void getSms(String phone, final ModelCallBack modelCallBack) {
        SendRequest.getregistersms(phone, "1", new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                LogUtils.Loge(response);
                if (response.contains("<html>")) {
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try{
                    //请求成功
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
    public void Login(String name, final String pwd, final ModelCallBack modelCallBack) {
        SendRequest.Login1(name, pwd, new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                LogUtils.Loge(response);
                if (response.contains("<html>")) {
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try{
                    //请求成功
                    LoginBean bean = JSON.parseObject(response, LoginBean.class);
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

    @Override
    public void Regist(String name, final String pwd, String code, final ModelCallBack modelCallBack) {
        SendRequest.userregister(name, pwd, code, "1", new MOkCallBack() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onSuccess(String response) {
                LogUtils.Loge(response);
                if (response.contains("<html>")) {
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
