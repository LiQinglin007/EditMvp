package com.example.lql.editmvp.basics.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.lql.editmvp.R;


/**
 * @Title: ${file_name}
 * @author: 王博华
 * @data: 2017/3/31 0031 , 14:44
 * @version: V-1.0
 * @Description: BaseActivity   需要实现Mvp模式则填入泛型  否则不填写泛型
 */
public abstract class BaseActivity<V,P extends BasePreserent<V>> extends FragmentActivity {
    private ProgressDialog pDialog;
    //刷新控件
    protected SwipeRefreshLayout swipeRefreshLayout;

    protected P mPresenter;
    protected Context mContext;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(provideContentViewId());
        }catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("布局文件丢失");
        }

//        MyStatusBarUtil.setStatusColor(BaseActivity.this, ContextCompat.getColor(BaseActivity.this, R.color.tool_bar));//设置状态栏颜色

        if (null!=creatPresenter()){
            mPresenter=creatPresenter();
            mPresenter.attachView((V) this);
        }
        mContext=this;
        initData();
        initView();

        if(null!=swipeRefreshLayout){
            this.swipeRefreshLayout.setColorSchemeResources(R.color.wheel_timebtn_nor);
        }
    }

    /**
     * 引用布局
     * @return  布局的resId
     */
    protected abstract int provideContentViewId();

    /**
     * 初始化Data
     */
    protected abstract void initData();


    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 获取Presenter的引用
     * @return
     */
    protected abstract P creatPresenter();


    /**
     * 设置SwipeRefreshLayout可以刷新
     */
    protected void setRefreshAble(){
        if (null!=swipeRefreshLayout){
            swipeRefreshLayout.setEnabled(true);
        }
    }

    /**
     * 设置SwipeRefreshLayout不可以刷新
     */
    protected void setRefreshUnable(){
        if (null!=swipeRefreshLayout){
            swipeRefreshLayout.setEnabled(false);
        }
    }


    /**
     * 设置SwipeRefreshLayout的刷新图标的颜色
     *
     * @param colorInt Color的资源引用
     */
    protected void setSwipeRefreshLayout(int colorInt) {
        if (null!=swipeRefreshLayout){
            try {
                this.swipeRefreshLayout.setColorSchemeResources(colorInt);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=mPresenter){
            mPresenter.detachView();
        }
        if(null!=pDialog){
            pDialog.dismiss();
        }
    }


    public void creatMyDialog(String message) {
        if (pDialog == null) {
            pDialog = new ProgressDialog(mContext);
        }
//        pDialog.setTitle("提示");
        pDialog.setMessage(message);
    }

    public void hideMyDialog() {
        if (null != pDialog)
            pDialog.hide();
    }

    public void showMyDialog() {
        if (null != pDialog)
            pDialog.show();
    }

    public void destoryMyDialog() {
        try {
            if (pDialog != null) {
                pDialog.dismiss();
                pDialog = null;
            }
        } catch (Exception e) {
        }
    }

    /**
     * 返回的切换动画
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.toleft,
                R.anim.infright);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.in_from_right,
                R.anim.out_to_left);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.toleft, R.anim.infright);
    }
}
