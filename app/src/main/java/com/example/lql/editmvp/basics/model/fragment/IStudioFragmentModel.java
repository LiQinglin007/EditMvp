package com.example.lql.editmvp.basics.model.fragment;

import com.example.lql.editmvp.basics.model.ModelCallBack;

/**
 * 类描述：工作室列表
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public interface IStudioFragmentModel {
    void getData(String SearchContent ,int  Searchtype ,  int page , int rows , ModelCallBack modelCallBack);
}
