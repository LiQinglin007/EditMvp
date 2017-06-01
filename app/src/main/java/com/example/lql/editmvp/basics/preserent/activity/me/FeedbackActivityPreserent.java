package com.example.lql.editmvp.basics.preserent.activity.me;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.activity.me.FeedbackActivityModel;
import com.example.lql.editmvp.basics.model.activity.me.IFeedbackActivityModel;
import com.example.lql.editmvp.basics.ui.activity.IView.IFeedbackActivity;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.utils.FinalData;

/**
 * Created by Admin on 2017/6/1.
 */

public class FeedbackActivityPreserent extends BasePreserent<IFeedbackActivity> implements  IFeedbackActivityPreserent {
    IFeedbackActivityModel mIFeedbackActivityModel;
    IFeedbackActivity mIFeedbackActivity;

    public FeedbackActivityPreserent() {
        mIFeedbackActivityModel=new FeedbackActivityModel();
    }

    @Override
    public void submit(String userid, String content) {
        mIFeedbackActivity=getView();
        mIFeedbackActivity.showLoading();
        mIFeedbackActivityModel.submit(userid, content, new ModelCallBack<MyBasebean>() {
            @Override
            public void onSuccess(MyBasebean response) {
                mIFeedbackActivity.hineLoading();
                mIFeedbackActivity.submit(response , FinalData.OKHTTP_SUCCESS , "");
            }

            @Override
            public void onFailure(String e) {
                mIFeedbackActivity.hineLoading();
                mIFeedbackActivity.submit(null , FinalData.OKHTTP_FAILURE ,  e.toString());
            }
        });
    }
}
