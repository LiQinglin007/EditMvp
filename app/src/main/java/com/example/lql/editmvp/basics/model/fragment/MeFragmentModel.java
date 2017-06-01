package com.example.lql.editmvp.basics.model.fragment;

import com.alibaba.fastjson.JSON;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.bean.GetNameBean;
import com.example.lql.editmvp.myhttp.MOkCallBack;
import com.example.lql.editmvp.myhttp.SendRequest;
import com.example.lql.editmvp.utils.LogUtils;
import com.example.lql.editmvp.utils.PreferenceUtils;

/**
 * Created by Admin on 2017/5/26.
 */

public class MeFragmentModel implements IMeFragmentModel {

    @Override
    public void getData(String userid, final ModelCallBack modelCallBack) {
        SendRequest.UserDetail(PreferenceUtils.getString("UserId", ""), new MOkCallBack() {
            @Override
            public void onSuccess(String response) {
                LogUtils.Loge(response+"UserDetail");
                if(response.contains("</html>")){
                    modelCallBack.onFailure("服务器异常");
                    return;
                }
                try{
                    GetNameBean mGetNameBean= JSON.parseObject(response,GetNameBean.class);
                    modelCallBack.onSuccess(mGetNameBean);
                }catch (Exception e){
                    LogUtils.Loge(e.toString()+"UserDetail");
                    modelCallBack.onFailure("亲，请检查网络");
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
