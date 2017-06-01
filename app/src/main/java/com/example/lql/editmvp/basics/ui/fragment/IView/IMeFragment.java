package com.example.lql.editmvp.basics.ui.fragment.IView;

import com.example.lql.editmvp.bean.GetNameBean;

/**
 * 类描述：我的
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public interface IMeFragment {

    void showLoading();

    void hindLoading();

    void getData(GetNameBean mBean , int code , String msg);
}
