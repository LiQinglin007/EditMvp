package com.example.lql.editmvp.basics.preserent.activity.me;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.activity.me.IUpdatePwdActivityModel;
import com.example.lql.editmvp.basics.model.activity.me.UpdatePwdActivityModel;
import com.example.lql.editmvp.basics.ui.activity.IView.IUpdatePwdActivity;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.utils.FinalData;

/**
 * Created by Admin on 2017/6/1.
 */

public class UpdatePwdActivityPresenter extends BasePreserent<IUpdatePwdActivity> implements IUpdatePwdActivityPresenter{

    IUpdatePwdActivityModel mIUpdatePwdActivityModel;
    IUpdatePwdActivity mIUpdatePwdActivity;


    public UpdatePwdActivityPresenter() {
        mIUpdatePwdActivityModel=new UpdatePwdActivityModel();
    }

    @Override
    public void updatePwd(String userid, String pwd, String oldpwd) {
        mIUpdatePwdActivity=getView();
        mIUpdatePwdActivity.showLoading();
        mIUpdatePwdActivityModel.updatepwd(userid, pwd, oldpwd, new ModelCallBack<MyBasebean>() {
            @Override
            public void onSuccess(MyBasebean response) {
                mIUpdatePwdActivity.hineLoading();
                mIUpdatePwdActivity.updatePwd(response , FinalData.OKHTTP_SUCCESS , "");
            }

            @Override
            public void onFailure(String e) {
                mIUpdatePwdActivity.hineLoading();
                mIUpdatePwdActivity.updatePwd(null , FinalData.OKHTTP_SUCCESS , e);
            }
        });
    }
}
