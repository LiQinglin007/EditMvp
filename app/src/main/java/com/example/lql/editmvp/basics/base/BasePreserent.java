package com.example.lql.editmvp.basics.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Title: ${file_name}
 * @author: 王博华
 * @data: 2017/3/31 0031 , 14:45
 * @version: V-1.0
 * @Description: BasePresenter 可以加载请求网络的实体类，由于View实现了接口，其实是获取接口的引用
 */
public abstract class BasePreserent<V> {

    //布局的若引用
    protected Reference<V> viewRef;

    /**
     * 获取View的弱引用
     *
     * @param v
     */
    public void attachView(V v) {
        viewRef = new WeakReference<V>(v);
    }

    /**
     * 获取View对象
     *
     * @return 对象(View)
     */
    protected V getView() {
        return viewRef.get();
    }

    /**
     * 判断是否引用了
     * @return
     */
    public boolean isViewAttached() {
        return null != viewRef && null != viewRef.get();
    }

    /**
     * 解除引用,释放内存
     */
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

}
