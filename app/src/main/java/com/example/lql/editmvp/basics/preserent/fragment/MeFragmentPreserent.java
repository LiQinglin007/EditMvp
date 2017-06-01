package com.example.lql.editmvp.basics.preserent.fragment;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.fragment.IMeFragmentModel;
import com.example.lql.editmvp.basics.model.fragment.MeFragmentModel;
import com.example.lql.editmvp.basics.ui.fragment.IView.IMeFragment;
import com.example.lql.editmvp.bean.GetNameBean;
import com.example.lql.editmvp.utils.FinalData;

/**
 * Created by Admin on 2017/5/26.
 */

public class MeFragmentPreserent extends BasePreserent<IMeFragment> implements IMeFragmentPreserent{
    IMeFragment mIMeFragment;
    IMeFragmentModel mIMeFragmentModel;

    public MeFragmentPreserent() {
        mIMeFragmentModel=new MeFragmentModel();
    }

    @Override
    public void getData(String userid) {
        mIMeFragment=getView();
        mIMeFragment.showLoading();
        mIMeFragmentModel.getData(userid, new ModelCallBack<GetNameBean>() {
            @Override
            public void onSuccess(GetNameBean response) {
                mIMeFragment.hindLoading();
                mIMeFragment.getData(response, FinalData.OKHTTP_SUCCESS ,"");
            }

            @Override
            public void onFailure(String e) {
                mIMeFragment.hindLoading();
                mIMeFragment.getData(null , FinalData.OKHTTP_FAILURE ,e);
            }
        });

    }
}
