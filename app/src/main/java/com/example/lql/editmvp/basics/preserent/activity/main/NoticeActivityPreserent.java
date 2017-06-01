package com.example.lql.editmvp.basics.preserent.activity.main;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.activity.main.INoticeModel;
import com.example.lql.editmvp.basics.model.activity.main.NoticeModel;
import com.example.lql.editmvp.basics.ui.activity.IView.INoticeActivity;
import com.example.lql.editmvp.bean.NoticeBean;

import static com.example.lql.editmvp.utils.FinalData.OKHTTP_FAILURE;
import static com.example.lql.editmvp.utils.FinalData.OKHTTP_SUCCESS;

/**
 * 类描述：公告列表
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class NoticeActivityPreserent extends BasePreserent<INoticeActivity> implements INoticeActivityPreserent{

    INoticeModel mINoticeModel;
    INoticeActivity mINoticeActivity;

    public NoticeActivityPreserent() {
        mINoticeModel=new NoticeModel();
    }

    @Override
    public void setNotice() {
        mINoticeActivity=getView();
        mINoticeActivity.showLoading();
        mINoticeModel.getNotice(new ModelCallBack<NoticeBean>() {
            @Override
            public void onSuccess(NoticeBean response) {
                mINoticeActivity.hineLoading();
                mINoticeActivity.setNotice(response, OKHTTP_SUCCESS,"");
            }

            @Override
            public void onFailure(String e) {
                mINoticeActivity.setNotice(null,OKHTTP_FAILURE,e);
            }
        });
    }
}
