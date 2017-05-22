package com.example.lql.editmvp.basics.model.activity;

import com.alibaba.fastjson.JSON;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.bean.NoticeBean;
import com.example.lql.editmvp.myhttp.MOkCallBack;
import com.example.lql.editmvp.myhttp.SendRequest;

/**
 * 类描述：
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */

public class NoticeModel implements INoticeModel {

    @Override
    public void getNotice(final ModelCallBack modelCallBack) {
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
