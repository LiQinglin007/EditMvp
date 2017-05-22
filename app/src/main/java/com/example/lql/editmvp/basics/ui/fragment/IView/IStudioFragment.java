package com.example.lql.editmvp.basics.ui.fragment.IView;

import com.example.lql.editmvp.bean.StudioListBean;

/**
 * 类描述：工作室列表
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public interface IStudioFragment {

    void showLoading();

    void hineLoading();

    void setDataList(StudioListBean mStudioListBean, int code , String msg);

}
