package com.example.lql.editmvp.basics.preserent.activity;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.activity.IStudioDetailsActivityModel;
import com.example.lql.editmvp.basics.model.activity.StudioDetailsActivityModel;
import com.example.lql.editmvp.basics.ui.activity.IView.IStudioDetailsActivity;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.bean.StudioDetailsBean;

/**
 * 类描述：工作室详情
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class StudioDetailsActivityPreserent extends BasePreserent<IStudioDetailsActivity> implements IStudioDetailsActivityPreserent{

    IStudioDetailsActivityModel mIStudioDetailsActivityModel;
    IStudioDetailsActivity mIStudioDetailsActivity;


    public StudioDetailsActivityPreserent() {
        mIStudioDetailsActivityModel = new StudioDetailsActivityModel();
    }

    @Override
    public void collection(String Userid, String studioId) {
        mIStudioDetailsActivity=getView();
        mIStudioDetailsActivity.showLoading();
        mIStudioDetailsActivityModel.collection(Userid, studioId, new ModelCallBack<MyBasebean>() {
            @Override
            public void onSuccess(MyBasebean response) {
                mIStudioDetailsActivity.hineLoading();
                mIStudioDetailsActivity.setCollection(response,0,"");
            }

            @Override
            public void onFailure(String e) {
                mIStudioDetailsActivity.hineLoading();
                mIStudioDetailsActivity.setCollection(null,1,e);
            }
        });
    }

    @Override
    public void ShopDetail(String Userid, String studioId) {
        mIStudioDetailsActivity=getView();
        mIStudioDetailsActivity.showLoading();

        mIStudioDetailsActivityModel.ShopDetail(Userid, studioId, new ModelCallBack<StudioDetailsBean>() {
            @Override
            public void onSuccess(StudioDetailsBean response) {
                mIStudioDetailsActivity.hineLoading();
                mIStudioDetailsActivity.setDta(response,0,"");
            }

            @Override
            public void onFailure(String e) {
                mIStudioDetailsActivity.hineLoading();
                mIStudioDetailsActivity.setDta(null,1,e);
            }
        });
    }
}
