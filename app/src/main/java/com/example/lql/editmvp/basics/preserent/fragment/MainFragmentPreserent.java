package com.example.lql.editmvp.basics.preserent.fragment;

import android.os.Handler;
import android.os.Message;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.fragment.IMainFragmentModel;
import com.example.lql.editmvp.basics.model.fragment.MainFragmentModel;
import com.example.lql.editmvp.basics.ui.fragment.IView.IMainFragment;
import com.example.lql.editmvp.bean.GetImglist;
import com.example.lql.editmvp.bean.MainGetService;
import com.example.lql.editmvp.bean.NoticeBean;

import static com.example.lql.editmvp.utils.FinalData.OKHTTP_FAILURE;
import static com.example.lql.editmvp.utils.FinalData.OKHTTP_SUCCESS;

/**
 * 类描述：首页
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class MainFragmentPreserent  extends BasePreserent <IMainFragment> implements IMainFragmentPreserent{

    IMainFragmentModel mMainFragmentModel;
    IMainFragment mIMainFragment;

    int number=0;

    public MainFragmentPreserent() {
        mMainFragmentModel = new MainFragmentModel();
    }

    @Override
    public void setImageList() {
        mIMainFragment=getView();
        mIMainFragment.showLoading();
        mMainFragmentModel.getImageList(new ModelCallBack<GetImglist>() {
            @Override
            public void onSuccess(GetImglist response) {
                mHandler.sendEmptyMessage(0);
                mIMainFragment.setImageList(response , OKHTTP_SUCCESS ,"");
            }
            @Override
            public void onFailure(String e) {
                mHandler.sendEmptyMessage(0);
                mIMainFragment.setImageList(null , OKHTTP_FAILURE ,e);
            }
        });
    }

    @Override
    public void setServiceList(int page, int rows) {
        mIMainFragment=getView();
        mIMainFragment.showLoading();
        mMainFragmentModel.getServiceList(page, rows, new ModelCallBack<MainGetService>() {
            @Override
            public void onSuccess(MainGetService response) {
                mHandler.sendEmptyMessage(0);
                mIMainFragment.setServiceList(response , OKHTTP_SUCCESS ,"");
            }

            @Override
            public void onFailure(String e) {
                mHandler.sendEmptyMessage(0);
                mIMainFragment.setServiceList(null , OKHTTP_FAILURE ,e);
            }
        });
    }

    @Override
    public void setNotice() {
        mIMainFragment=getView();
        mIMainFragment.showLoading();
        mMainFragmentModel.getNotice(new ModelCallBack<NoticeBean>() {
            @Override
            public void onSuccess(NoticeBean response) {
                mHandler.sendEmptyMessage(0);
                mIMainFragment.setNotice(response , OKHTTP_SUCCESS ,"");
            }

            @Override
            public void onFailure(String e) {
                mHandler.sendEmptyMessage(0);
                mIMainFragment.setNotice(null , OKHTTP_FAILURE , e);
            }
        });
    }


    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            number++;
            if(number == 3){
                mIMainFragment.hineLoading();
            }
        }
    };
}
