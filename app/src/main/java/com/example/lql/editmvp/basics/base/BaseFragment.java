package com.example.lql.editmvp.basics.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lql.editmvp.R;

/**
 * @Title: ${file_name}
 * @author: 王博华
 * @data: 2017/3/31 0031 , 14:45
 * @version: V-1.0
 * @Description: 需要实现Mvp模式   则填入泛型否则不填写泛型
 */
public abstract class BaseFragment<V, P extends BasePreserent<V>> extends Fragment {
    private ProgressDialog pDialog;
    //刷新控件
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected P mPresenter;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        initData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        if (0 != createViewLayoutId()) {
            rootView = inflater.inflate(createViewLayoutId(), container, false);
        }
        initView(rootView, savedInstanceState);
        if (null != swipeRefreshLayout) {
            this.swipeRefreshLayout.setColorSchemeResources(R.color.wheel_timebtn_nor);
        }
        return rootView;
    }

    protected abstract P createPresenter();

    protected abstract int createViewLayoutId();


    /**
     * 初始化Data
     */
    protected abstract void initData();

    /**
     * 初始化控件
     */
    protected abstract void initView(View rootView, Bundle savedInstanceState);

    /**
     * 设置SwipeRefreshLayout可以刷新
     */
    protected void setRefreshAble() {
        if (null != swipeRefreshLayout) {
            swipeRefreshLayout.setEnabled(true);
        }
    }

    /**
     * 设置SwipeRefreshLayout不可以刷新
     */
    protected void setRefreshUnable() {
        if (null != swipeRefreshLayout) {
            swipeRefreshLayout.setEnabled(false);
        }
    }


    /**
     * 设置SwipeRefreshLayout的刷新图标的颜色
     *
     * @param colorInt Color的资源引用
     */
    protected void setSwipeRefreshLayout(int colorInt) {
        if (null != swipeRefreshLayout) {
            try {
                this.swipeRefreshLayout.setColorSchemeResources(colorInt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (null != pDialog) {
            pDialog.dismiss();
        }
    }


    public void creatMyDialog(String message) {
        if (pDialog == null) {
            pDialog = new ProgressDialog(getActivity());
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


    //    切换动画
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        (getActivity()).overridePendingTransition(R.anim.in_from_right,
                R.anim.out_to_left);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        (getActivity()).overridePendingTransition(R.anim.toleft,
                R.anim.infright);
    }
}
