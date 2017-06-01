package com.example.lql.editmvp.basics.model.activity.me;

import com.alibaba.fastjson.JSON;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.myhttp.MOkCallBack;
import com.example.lql.editmvp.myhttp.SendRequest;
import com.example.lql.editmvp.utils.LogUtils;

/**
 * Created by Admin on 2017/6/1.
 */

public class FeedbackActivityModel implements  IFeedbackActivityModel {

    @Override
    public void submit(String userid ,String content,final  ModelCallBack modelCallBack) {
        SendRequest.AddSuggestion(userid,content, new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if(response.contains("<html>")){
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try {
                    MyBasebean myBasebean= JSON.parseObject(response,MyBasebean.class);
                    modelCallBack.onSuccess(myBasebean);
                }catch (Exception e){
                    LogUtils.Loge(e.toString());
                    modelCallBack.onFailure("数据解析错误");
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
