package com.example.lql.editmvp.basics.preserent.activity.service;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.activity.service.IServiceDetailsActivityModel;
import com.example.lql.editmvp.basics.model.activity.service.ServiceDetailsActivityModel;
import com.example.lql.editmvp.basics.ui.activity.IView.IServiceDetailsActivity;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.bean.ServiceDetailsBean;

import static com.example.lql.editmvp.utils.FinalData.OKHTTP_FAILURE;
import static com.example.lql.editmvp.utils.FinalData.OKHTTP_SUCCESS;

/**
 * 类描述：服务详情
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class ServiceDetailsActivityPreserent extends BasePreserent<IServiceDetailsActivity> implements IServiceDetailsActivityPreserent {

    IServiceDetailsActivity mIServiceDetailsActivity;
    IServiceDetailsActivityModel mIServiceDetailsActivityModel;

    public ServiceDetailsActivityPreserent() {
        mIServiceDetailsActivityModel=new ServiceDetailsActivityModel();
    }

    @Override
    public void getData(String serviceId, String userid) {
        mIServiceDetailsActivity=getView();
        mIServiceDetailsActivity.showLoading();
        mIServiceDetailsActivityModel.getDta(serviceId, userid, new ModelCallBack<ServiceDetailsBean>() {
            @Override
            public void onSuccess(ServiceDetailsBean response) {
                mIServiceDetailsActivity.hineLoading();
                mIServiceDetailsActivity.setdata(response, OKHTTP_SUCCESS ,"");
            }

            @Override
            public void onFailure(String e) {
                mIServiceDetailsActivity.hineLoading();
                mIServiceDetailsActivity.setdata(null , OKHTTP_FAILURE , e);
            }
        });
    }

    @Override
    public void collection(String userid, String serviceId) {
        mIServiceDetailsActivity=getView();
        mIServiceDetailsActivity.showLoading();
        mIServiceDetailsActivityModel.collection(userid, serviceId, new ModelCallBack<MyBasebean>() {
            @Override
            public void onSuccess(MyBasebean response) {
                mIServiceDetailsActivity.hineLoading();
                mIServiceDetailsActivity.setCollection(response , OKHTTP_SUCCESS ,"");
            }

            @Override
            public void onFailure(String e) {
                mIServiceDetailsActivity.hineLoading();
                mIServiceDetailsActivity.setCollection(null , OKHTTP_FAILURE ,e);
            }
        });
    }
}
