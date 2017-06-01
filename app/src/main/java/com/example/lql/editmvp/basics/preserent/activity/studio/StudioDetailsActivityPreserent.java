package com.example.lql.editmvp.basics.preserent.activity.studio;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.activity.stduio.IStudioDetailsActivityModel;
import com.example.lql.editmvp.basics.model.activity.stduio.StudioDetailsActivityModel;
import com.example.lql.editmvp.basics.ui.activity.IView.IStudioDetailsActivity;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.bean.StudioDetailsBean;

import static com.example.lql.editmvp.utils.FinalData.OKHTTP_FAILURE;
import static com.example.lql.editmvp.utils.FinalData.OKHTTP_SUCCESS;

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
                mIStudioDetailsActivity.setCollection(response,OKHTTP_SUCCESS,"");
            }

            @Override
            public void onFailure(String e) {
                mIStudioDetailsActivity.hineLoading();
                mIStudioDetailsActivity.setCollection(null,OKHTTP_FAILURE,e);
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
                mIStudioDetailsActivity.setDta(response, OKHTTP_SUCCESS,"");
            }

            @Override
            public void onFailure(String e) {
                mIStudioDetailsActivity.hineLoading();
                mIStudioDetailsActivity.setDta(null, OKHTTP_FAILURE,e);
            }
        });
    }
}
