package com.example.lql.editmvp.basics.ui.fragment.view;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.bean.StudioListBean;
import com.example.lql.editmvp.utils.T;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.example.lql.editmvp.utils.RecyclerView.IsBottom.isSlideToBottom;


/**
 * 工作室
 */
public class StudioFragment extends Fragment implements View.OnClickListener {

    private static StudioFragment studioFragment;

    public static  StudioFragment getInstance(){
        if(studioFragment==null){
            studioFragment=new StudioFragment();
        }
        return  studioFragment;
    }


    View view;
    TextView defaulttv;//默认
    TextView bondtv;//保证金
    TextView reputationtv;//信誉
    TextView salesvolumetv;//销量
    RecyclerView studio_re;//list


    EditText search;

//    StudioAdapter mStudioAdapter;//工作室的适配器


    int Page = 1;
    String Searchtype = "0";

    ArrayList<StudioListBean.DataBean> mList = new ArrayList<>();

    LinearLayout loading;
    int Count = 1;
    SwipeRefreshLayout mySwipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_studio, container, false);
        initView();
        return view;
    }



    private void initView() {
        defaulttv = (TextView) view.findViewById(R.id.studio_default_tv);
        bondtv = (TextView) view.findViewById(R.id.studio_bond_tv);
        reputationtv = (TextView) view.findViewById(R.id.studio_reputation_tv);
        salesvolumetv = (TextView) view.findViewById(R.id.studio_salesvolume_tv);
        studio_re = (RecyclerView) view.findViewById(R.id.studio_re);

        search = (EditText) view.findViewById(R.id.search_ed);
        loading = (LinearLayout) view.findViewById(R.id.loading);
        mySwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.mySwipeRefresh);
        mySwipeRefresh.setColorSchemeResources(android.R.color.holo_red_light);
        //设置下拉刷新监听
        mySwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Page = 1;
//                        getData(true, 0,false);
                    }
                }, 0);
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
                    if (TextUtils.isEmpty(search.getText().toString().trim())) {
                        T.shortToast(getActivity(), "搜索内容不能为空");
                        return true;
                    } else {
//                        getData(true, 1,true);
                        return true;
                    }
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
//                            MainActivity.mainly.setClickable(false);
//                            MainActivity.servicely.setClickable(false);
//                            MainActivity.studioly.setClickable(false);
//                            MainActivity.mely.setClickable(false);
                            Page++;
                            loading.setVisibility(View.VISIBLE);
//                            getData(false, 1,false);
                        } else {
                            Toast.makeText(getActivity(), "没有更多内容了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

//        getData(true, 1,true);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.studio_default_tv:
                Searchtype = "0";
                setTitleView();
                defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
//                getData(true, 1,false);
                break;
            case R.id.studio_bond_tv:
                Searchtype = "1";
                setTitleView();
                bondtv.setTextColor(getResources().getColor(R.color.color_1AB394));
//                getData(true, 1,false);
                break;
            case R.id.studio_reputation_tv:
                Searchtype = "2";
                setTitleView();
                reputationtv.setTextColor(getResources().getColor(R.color.color_1AB394));
//                getData(true, 1,false);
                break;
            case R.id.studio_salesvolume_tv:
                Searchtype = "3";
                setTitleView();
                salesvolumetv.setTextColor(getResources().getColor(R.color.color_1AB394));
//                getData(true, 1,false);
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



}
