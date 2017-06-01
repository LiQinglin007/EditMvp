package com.example.lql.editmvp.basics.preserent.fragment;

import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.model.ModelCallBack;
import com.example.lql.editmvp.basics.model.fragment.IStudioFragmentModel;
import com.example.lql.editmvp.basics.model.fragment.StudioFragmentModel;
import com.example.lql.editmvp.basics.ui.fragment.IView.IStudioFragment;
import com.example.lql.editmvp.bean.StudioListBean;

import static com.example.lql.editmvp.utils.FinalData.OKHTTP_FAILURE;
import static com.example.lql.editmvp.utils.FinalData.OKHTTP_SUCCESS;

/**
 * 类描述：工作室列表
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class StudioFragmentPreserent extends BasePreserent<IStudioFragment> implements IStudioFragmentPreserent{

    IStudioFragmentModel  mIStudioFragmentModel;
    IStudioFragment mIStudioFragment;

    public StudioFragmentPreserent() {
        mIStudioFragmentModel=new StudioFragmentModel();
    }


    /**
     *
     * @param SearchConetnt  搜索内容
     * @param SearchType  搜索类型
     * @param page 页码
     * @param rows  行数
     * @param type  是否下拉  0：普通 1：下拉
     */
    @Override
    public void getData(String SearchConetnt, int SearchType , int page, int rows , final int type) {
        mIStudioFragment=getView();
        if(type==0){
            mIStudioFragment.showLoading();
        }
        mIStudioFragmentModel.getData(SearchConetnt, SearchType, page, rows, new ModelCallBack<StudioListBean>() {
            @Override
            public void onSuccess(StudioListBean response) {
                mIStudioFragment.hineLoading();
                mIStudioFragment.setDataList(response,OKHTTP_SUCCESS,"");
            }

            @Override
            public void onFailure(String e) {
                mIStudioFragment.hineLoading();
                mIStudioFragment.setDataList(null , OKHTTP_FAILURE , e);
            }
        });
    }
}
