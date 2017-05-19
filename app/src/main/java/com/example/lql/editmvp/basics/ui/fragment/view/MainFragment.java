package com.example.lql.editmvp.basics.ui.fragment.view;


import android.os.Bundle;
import android.view.View;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseFragment;
import com.example.lql.editmvp.basics.preserent.MainFragmentPreserent;
import com.example.lql.editmvp.basics.ui.fragment.IView.IMainFragment;
import com.example.lql.editmvp.bean.GetImglist;
import com.example.lql.editmvp.bean.MainGetService;
import com.example.lql.editmvp.bean.NoticeBean;
import com.example.lql.editmvp.utils.LogUtils;


/**
 * 类描述：首页
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class MainFragment extends BaseFragment<IMainFragment , MainFragmentPreserent> implements View.OnClickListener ,IMainFragment {

    private static MainFragment mainFragment;

    public static  MainFragment getInstance(){
        if(mainFragment==null){
            mainFragment=new MainFragment();
        }
        return  mainFragment;
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    protected MainFragmentPreserent createPresenter() {
        return new MainFragmentPreserent();
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {
        mPresenter.setImageList();
        mPresenter.setNotice();
        mPresenter.setServiceList(1,10);
    }

    @Override
    public void showLoading() {
        creatMyDialog(getActivity().getString(R.string.loading_string));
        showMyDialog();
    }

    @Override
    public void hineLoading() {
        hideMyDialog();
    }

    @Override
    public void setImageList(GetImglist mGetImglist, int code , String msg) {
        LogUtils.Loge(mGetImglist.toString()+"mGetImglist");
    }

    @Override
    public void setServiceList(MainGetService mainGetService, int code , String msg) {
        LogUtils.Loge(mainGetService.toString()+"mainGetService");
    }

    @Override
    public void setNotice(NoticeBean mNoticeBean, int code , String msg) {
        LogUtils.Loge(mNoticeBean.toString()+"mNoticeBean");
    }


}
