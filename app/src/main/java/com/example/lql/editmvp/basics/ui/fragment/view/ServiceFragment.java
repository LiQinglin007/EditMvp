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
import com.example.lql.editmvp.bean.ServiceBean;
import com.example.lql.editmvp.utils.T;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.example.lql.editmvp.utils.RecyclerView.IsBottom.isSlideToBottom;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends Fragment implements View.OnClickListener {

    private static ServiceFragment serviceFragment;

    public static  ServiceFragment getInstance(){
        if(serviceFragment==null){
            serviceFragment=new ServiceFragment();
        }
        return  serviceFragment;
    }


    View view;
    TextView defaulttv;//默认
    TextView bondtv;//销量
    TextView reputationtv;//信誉
    TextView salesvolumetv;//人气
    RecyclerView studio_re;//list


    EditText search;
    TextView titleright;

    TextView service_screen_tv;
    LinearLayout service_screen_lv;
    int Page = 1;
    String seachtype = "0";
    String Direction = "";

    ArrayList<ServiceBean.DataBean> mList = new ArrayList<>();

    int AdapterType = 1;

    LinearLayout loading;
    int Count = 1;
    SwipeRefreshLayout mySwipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service, container, false);
        initView();
        return view;
    }


    private void initView() {
        defaulttv = (TextView) view.findViewById(R.id.service_default_tv);
        bondtv = (TextView) view.findViewById(R.id.service_bond_tv);
        reputationtv = (TextView) view.findViewById(R.id.service_reputation_tv);
        salesvolumetv = (TextView) view.findViewById(R.id.service_salesvolume_tv);

        studio_re = (RecyclerView) view.findViewById(R.id.service_re);
        service_screen_lv = (LinearLayout) view.findViewById(R.id.service_screen_lv);


        search = (EditText) view.findViewById(R.id.service_search_ed);
        titleright = (TextView) view.findViewById(R.id.service_search_tv);
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
        service_screen_lv.setOnClickListener(this);
        titleright.setOnClickListener(this);

//        setView();

        // 设置搜索框搜索取消搜索按钮
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1,
                                          KeyEvent arg2) {
                if (arg1 == 0) {
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    if(TextUtils.isEmpty(search.getText().toString().trim())){
                        T.shortToast(getActivity(),"搜索内容不能为空");
                        return true;
                    }else{
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
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service_default_tv://m默认
                seachtype = "0";
                setTitleView();
                defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
                break;
            case R.id.service_bond_tv://销量
                seachtype = "1";
                setTitleView();
                bondtv.setTextColor(getResources().getColor(R.color.color_1AB394));
                break;
            case R.id.service_reputation_tv://信誉
                seachtype = "2";
                setTitleView();
                reputationtv.setTextColor(getResources().getColor(R.color.color_1AB394));
                break;
            case R.id.service_salesvolume_tv://人气
                seachtype = "3";
                setTitleView();
                salesvolumetv.setTextColor(getResources().getColor(R.color.color_1AB394));
                break;
            case R.id.service_screen_lv://筛选
                break;
            case R.id.service_search_tv://顶部筛选

                break;
        }
    }


    private void setTitleView() {
        defaulttv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        bondtv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        reputationtv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        salesvolumetv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
    }

}
