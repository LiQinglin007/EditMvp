package com.example.lql.editmvp.basics.ui.fragment.IView;

import com.example.lql.editmvp.bean.ServiceBean;

/**
 * Created by Admin on 2017/5/19.
 */

public interface IServiceFragment {

    void showLoading();

    void hineLoading();

    void getServiceData(ServiceBean mServiceBean , int code ,String msg);

}
