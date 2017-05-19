package com.example.lql.editmvp.basics.model;

/**
 * 类描述：Model的回调
 * 作  者：LQL
 * 时  间：2017/4/20
 * 修改备注：
 */
public interface ModelCallBack<T> {

   void onSuccess(T response);

   void onFailure(String e);

}
