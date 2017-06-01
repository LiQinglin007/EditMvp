package com.example.lql.editmvp.basics.model.fragment;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.bean.ServiceBean;
import com.example.lql.editmvp.myhttp.MOkCallBack;
import com.example.lql.editmvp.myhttp.SendRequest;
import com.example.lql.editmvp.utils.LogUtils;

/**
 * Created by Admin on 2017/5/26.
 */

public class ServiceFragmentModel implements  IServiceFragmentModel{
    @Override
    public void getServiceData(String Keywords, String Type, String seachtype, String Direction,
                               String Page, String rows, final ModelCallBack modelCallBack) {
        SendRequest.repeatlist(Keywords, Type, seachtype, Direction, Page , rows, new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                Log.e("==onSuccess",response);
                if (response.contains("<html>")) {
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try {
                    ServiceBean mService = JSON.parseObject(response, ServiceBean.class);
                    modelCallBack.onSuccess(mService);
                }catch (Exception e){
                    LogUtils.Loge(e.toString()+"repeatlist");
                    modelCallBack.onFailure("数据解析失败");
                }
            }

            @Override
            public void onFailure(Throwable e) {
                LogUtils.Loge(e.toString()+"onFailure");
                modelCallBack.onFailure("亲，请检查网络");
            }
        });
    }
}
