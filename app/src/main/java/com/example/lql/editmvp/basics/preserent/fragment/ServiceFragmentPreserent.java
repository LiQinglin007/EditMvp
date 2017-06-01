package com.example.lql.editmvp.basics.preserent.fragment;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.fragment.IServiceFragmentModel;
import com.example.lql.editmvp.basics.model.fragment.ServiceFragmentModel;
import com.example.lql.editmvp.basics.ui.fragment.IView.IServiceFragment;
import com.example.lql.editmvp.bean.ServiceBean;

import static com.example.lql.editmvp.utils.FinalData.OKHTTP_FAILURE;
import static com.example.lql.editmvp.utils.FinalData.OKHTTP_SUCCESS;

/**
 * Created by Admin on 2017/5/26.
 */

public class ServiceFragmentPreserent extends BasePreserent<IServiceFragment> implements IServiceFragmentPreserent{
    IServiceFragment mIServiceFragment;
    IServiceFragmentModel mIServiceFragmentModel;

    public ServiceFragmentPreserent() {
        mIServiceFragmentModel=new ServiceFragmentModel();
    }

    @Override
    public void getServiceData(String Keywords, String Type, String seachtype, String Direction,
                               String Page, String rows) {
        mIServiceFragment=getView();
        mIServiceFragment.showLoading();
        mIServiceFragmentModel.getServiceData(Keywords, Type, seachtype, Direction,
                Page, rows, new ModelCallBack<ServiceBean>() {
                    @Override
                    public void onSuccess(ServiceBean response) {
                        mIServiceFragment.hineLoading();
                        mIServiceFragment.getServiceData(response , OKHTTP_SUCCESS , "");
                    }

                    @Override
                    public void onFailure(String e) {
                        mIServiceFragment.hineLoading();
                        mIServiceFragment.getServiceData(null  , OKHTTP_FAILURE , e);
                    }
                });
    }
}
