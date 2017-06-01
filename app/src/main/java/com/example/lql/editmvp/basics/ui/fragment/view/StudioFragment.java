package com.example.lql.editmvp.basics.ui.fragment.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.adapter.studio.StudioAdapter;
import com.example.lql.editmvp.basics.base.BaseFragment;
import com.example.lql.editmvp.basics.preserent.fragment.StudioFragmentPreserent;
import com.example.lql.editmvp.basics.ui.activity.view.studio.StudioDetailsActivity;
import com.example.lql.editmvp.basics.ui.fragment.IView.IStudioFragment;
import com.example.lql.editmvp.bean.StudioListBean;
import com.example.lql.editmvp.utils.RecyclerView.DividerGridItemDecoration;
import com.example.lql.editmvp.utils.RecyclerView.OnItemClickListener;
import com.example.lql.editmvp.utils.T;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.example.lql.editmvp.utils.FinalData.OKHTTP_SUCCESS;
import static com.example.lql.editmvp.utils.RecyclerView.IsBottom.isSlideToBottom;


/**
 * 工作室
 */
public class StudioFragment extends BaseFragment <IStudioFragment , StudioFragmentPreserent>implements View.OnClickListener ,IStudioFragment{

    private static StudioFragment studioFragment;

    public static  StudioFragment getInstance(){
        if(studioFragment==null){
            studioFragment=new StudioFragment();
        }
        return  studioFragment;
    }


    TextView defaulttv;//默认
    TextView bondtv;//保证金
    TextView reputationtv;//信誉
    TextView salesvolumetv;//销量
    RecyclerView studio_re;//list


    EditText search;

    StudioAdapter mStudioAdapter;//工作室的适配器


    int Page = 1;
    int Rows = 20;
    int  Searchtype = 0;
    int getDataType=0;  // 0:上拉  1：下拉
    boolean IsNeedClear=false;//  是否需要清空数据

    ArrayList<StudioListBean.DataBean> mList = new ArrayList<>();

    LinearLayout loading;
    int Count = 1;
    SwipeRefreshLayout mySwipeRefresh;


    @Override
    protected StudioFragmentPreserent createPresenter() {
        return new StudioFragmentPreserent();
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_studio;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {
        defaulttv = (TextView) rootView.findViewById(R.id.studio_default_tv);
        bondtv = (TextView) rootView.findViewById(R.id.studio_bond_tv);
        reputationtv = (TextView) rootView.findViewById(R.id.studio_reputation_tv);
        salesvolumetv = (TextView) rootView.findViewById(R.id.studio_salesvolume_tv);
        studio_re = (RecyclerView) rootView.findViewById(R.id.studio_re);

        search = (EditText) rootView.findViewById(R.id.search_ed);
        loading = (LinearLayout) rootView.findViewById(R.id.loading);
        mySwipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.mySwipeRefresh);
        mySwipeRefresh.setColorSchemeResources(android.R.color.holo_green_light);
        //设置下拉刷新监听
        mySwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Page = 1;
                        getDataType=1;
                        IsNeedClear = true;
                        mPresenter.getData(search.getText().toString(),Searchtype,
                                Page , Rows ,getDataType);
                    }
                }, 0);
            }
        });
        studio_re.setLayoutManager(new LinearLayoutManager(getActivity()));
        mStudioAdapter = new StudioAdapter(getActivity());
        mStudioAdapter.setList(mList);
        studio_re.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        studio_re.setAdapter(mStudioAdapter);

        mStudioAdapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), StudioDetailsActivity.class);
                intent.putExtra("studioid",mList.get(position).getId()+"");
                startActivity(intent);
            }
        });

        defaulttv.setOnClickListener(this);
        bondtv.setOnClickListener(this);
        reputationtv.setOnClickListener(this);
        salesvolumetv.setOnClickListener(this);


        // 设置搜索框搜索取消搜索按钮
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1,
                                          KeyEvent arg2) {
                if (arg1 == 0) {
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    IsNeedClear= true;
                    Page=1;

                    mPresenter.getData(search.getText().toString(),Searchtype,
                            Page , Rows ,getDataType);
                }
                return false;
            }
        });


        studio_re.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy != 0) {
                    if (isSlideToBottom(recyclerView)) {
                        if (Count > 0) {
                            Page++;
                            loading.setVisibility(View.VISIBLE);

                            IsNeedClear= false;
                            mPresenter.getData(search.getText().toString(),Searchtype,
                                    Page , Rows ,getDataType);

                        } else {
                            Toast.makeText(getActivity(), "没有更多内容了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


        mPresenter.getData(search.getText().toString(),Searchtype,
                Page , Rows ,getDataType);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.studio_default_tv:
                Searchtype = 0;
                setTitleView();
                defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
                IsNeedClear = true;
                Page=1;
                mPresenter.getData(search.getText().toString(),Searchtype,
                        Page , Rows ,getDataType);
                break;
            case R.id.studio_bond_tv:
                Searchtype = 1;
                setTitleView();
                bondtv.setTextColor(getResources().getColor(R.color.color_1AB394));
                IsNeedClear = true;
                Page=1;
                mPresenter.getData(search.getText().toString(),Searchtype,
                        Page , Rows ,getDataType);
                break;
            case R.id.studio_reputation_tv:
                Searchtype = 2;
                setTitleView();
                reputationtv.setTextColor(getResources().getColor(R.color.color_1AB394));
                IsNeedClear = true;
                Page=1;
                mPresenter.getData(search.getText().toString(),Searchtype,
                        Page , Rows ,getDataType);
                break;
            case R.id.studio_salesvolume_tv:
                Searchtype = 3;
                setTitleView();
                salesvolumetv.setTextColor(getResources().getColor(R.color.color_1AB394));
                IsNeedClear = true;
                Page=1;
                mPresenter.getData(search.getText().toString(),Searchtype,
                        Page , Rows ,getDataType);
                break;
        }
    }


    //    color_8E8E8E
    private void setTitleView() {
        defaulttv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        bondtv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        reputationtv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        salesvolumetv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
    }


    @Override
    public void showLoading() {
        creatMyDialog(getActivity().getResources().getString(R.string.loading_string));
        showMyDialog();
    }

    @Override
    public void hineLoading() {
        hideMyDialog();
        mySwipeRefresh.setRefreshing(false);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void setDataList(StudioListBean mStudioListBean, int code, String msg) {
        if(code==OKHTTP_SUCCESS){
            if(IsNeedClear){
                mList.clear();
            }
            mList.addAll(mStudioListBean.getData());
            mStudioAdapter.setList(mList);
        }else{
            T.shortToast(getActivity(),msg);
        }
    }
}
