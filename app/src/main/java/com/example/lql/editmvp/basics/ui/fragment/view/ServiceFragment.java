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
import com.example.lql.editmvp.adapter.service.ServiceAdapter;
import com.example.lql.editmvp.basics.base.BaseFragment;
import com.example.lql.editmvp.basics.preserent.fragment.ServiceFragmentPreserent;
import com.example.lql.editmvp.basics.ui.activity.view.service.ServiceDetailsActivity;
import com.example.lql.editmvp.basics.ui.fragment.IView.IServiceFragment;
import com.example.lql.editmvp.bean.ServiceBean;
import com.example.lql.editmvp.utils.FinalData;
import com.example.lql.editmvp.utils.PopuWindowUtils;
import com.example.lql.editmvp.utils.PublicStaticData;
import com.example.lql.editmvp.utils.RecyclerView.DividerItemDecoration;
import com.example.lql.editmvp.utils.RecyclerView.MyDecoration;
import com.example.lql.editmvp.utils.RecyclerView.OnItemClickListener;
import com.example.lql.editmvp.utils.T;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.example.lql.editmvp.utils.RecyclerView.IsBottom.isSlideToBottom;


/**
 * 类描述：服务列表
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class ServiceFragment extends BaseFragment<IServiceFragment ,ServiceFragmentPreserent> implements IServiceFragment, View.OnClickListener {

    private static ServiceFragment serviceFragment;

    public static  ServiceFragment getInstance(){
        if(serviceFragment==null){
            serviceFragment=new ServiceFragment();
        }
        return  serviceFragment;
    }

    TextView defaulttv;//默认
    TextView bondtv;//销量
    TextView reputationtv;//信誉
    TextView salesvolumetv;//人气
    RecyclerView studio_re;//list


    EditText search;
    TextView titleright;

    TextView service_screen_tv;
    LinearLayout service_screen_lv;

    int Page = 1 ;//页码
    int Rows = 20 ;//每页的数量

    String seachtype = "0";
    String Direction = "";

    ArrayList<ServiceBean.DataBean> mList = new ArrayList<>();

    int AdapterType = 1;

    LinearLayout loading;
    int Count = 1;
    SwipeRefreshLayout mySwipeRefresh;


    boolean NeedClear=false;//是否需要清空原来的数据
    boolean NeedToast=false;//是否需要提示


    ServiceAdapter mServiceAdapter;

    @Override
    protected ServiceFragmentPreserent createPresenter() {
        return new ServiceFragmentPreserent();
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {
        defaulttv = (TextView) rootView.findViewById(R.id.service_default_tv);
        bondtv = (TextView) rootView.findViewById(R.id.service_bond_tv);
        reputationtv = (TextView) rootView.findViewById(R.id.service_reputation_tv);
        salesvolumetv = (TextView) rootView.findViewById(R.id.service_salesvolume_tv);

        studio_re = (RecyclerView) rootView.findViewById(R.id.service_re);
        service_screen_lv = (LinearLayout) rootView.findViewById(R.id.service_screen_lv);


        search = (EditText) rootView.findViewById(R.id.service_search_ed);
        titleright = (TextView) rootView.findViewById(R.id.service_search_tv);
        loading = (LinearLayout) rootView.findViewById(R.id.loading);
        mySwipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.mySwipeRefresh);
        mySwipeRefresh.setColorSchemeResources(android.R.color.holo_red_light);
        //设置下拉刷新监听
        mySwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Page = 1;
                        NeedToast=true;
                        NeedClear=true;
                        getData();
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


        // 设置搜索框搜索取消搜索按钮
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1,
                                          KeyEvent arg2) {
                if (arg1 == 0) {
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    NeedToast=true;
                    NeedClear=true;
                    getData();
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
                            NeedClear=false;
                            NeedToast=true;

                            getData();
                        } else {
                            Toast.makeText(getActivity(), "没有更多内容了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        setView();
        getData();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service_default_tv://m默认
                seachtype = "0";
                setTitleView();
                defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
                NeedClear=true;
                NeedToast=true;
                getData();
                break;
            case R.id.service_bond_tv://销量
                seachtype = "1";
                setTitleView();
                bondtv.setTextColor(getResources().getColor(R.color.color_1AB394));
                NeedClear=true;
                NeedToast=true;
                getData();
                break;
            case R.id.service_reputation_tv://信誉
                seachtype = "2";
                setTitleView();
                reputationtv.setTextColor(getResources().getColor(R.color.color_1AB394));
                NeedClear=true;
                NeedToast=true;
                getData();
                break;
            case R.id.service_salesvolume_tv://人气
                seachtype = "3";
                setTitleView();
                salesvolumetv.setTextColor(getResources().getColor(R.color.color_1AB394));
                NeedClear=true;
                NeedToast=true;
                getData();
                break;
            case R.id.service_screen_lv://筛选
                PopuWindowUtils.showSinglePopup(getActivity(), service_screen_lv, new PopuWindowUtils.SingePopwCallback() {
                    @Override
                    public void SingePopCallback(int number) {
                        if (number == 1) {
                            //期刊职称论文查重
                            Direction = "期刊职称论文查重";
                            PublicStaticData.PopNumber = 1;
                            NeedToast=true;
                            NeedClear=true;
                            getData();
                        } else if (number == 2) {
                            //学位分段论文查重
                            Direction = "学位论文分段查重";
                            PublicStaticData.PopNumber = 2;
                            NeedToast=true;
                            NeedClear=true;
                            getData();
                        } else if (number == 3) {
                            //本科学位PMLC查重
                            Direction = "本科学位PMLC查重";
                            PublicStaticData.PopNumber = 3;
                            NeedToast=true;
                            NeedClear=true;
                            getData();
                        } else if (number == 4) {
                            //硕博论文VIP系统查重
                            Direction = "硕博论文VIP系统查重";
                            PublicStaticData.PopNumber = 4;
                            NeedToast=true;
                            NeedClear=true;
                            getData();
                        }else{
                            Direction = "";
                            PublicStaticData.PopNumber = 0;
                            NeedToast=true;
                            NeedClear=true;
                            getData();
                        }
                    }
                });
                break;
            case R.id.service_search_tv://顶部筛选
                PopuWindowUtils.showSinglePopupTitle(getActivity(), titleright, new PopuWindowUtils.SingePopwCallback() {
                    @Override
                    public void SingePopCallback(int number) {
                        if (number == 1) {
                            //        Type1:检测查重 2：修改降重3：编辑速审
//        1、只有速审没有原价
                            PublicStaticData.ServiceType=3;
                            //1：速审  2：查重   3：降重
                            PublicStaticData.PopNumberTitle = 1;
                            //速审
                            AdapterType = 1;
                            titleright.setText("速审");
                            service_screen_lv.setVisibility(View.GONE);
                            seachtype = "0";
                            Direction = "";
                            setTitleView();
                            defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
                            PublicStaticData.PopNumber = 0;
                            NeedClear=true;
                            NeedToast=true;
                            getData();
                        } else if (number == 2) {
                            //查重
                            PublicStaticData.ServiceType=1;
                            PublicStaticData.PopNumberTitle = 2;
                            AdapterType = 2;
                            titleright.setText("查重");
                            Direction = "";
                            service_screen_lv.setVisibility(View.VISIBLE);

                            seachtype = "0";
                            setTitleView();
                            defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
                            PublicStaticData.PopNumber = 0;
                            NeedClear=true;
                            NeedToast=true;
                            getData();
                        } else if (number == 3) {
                            //降重
                            PublicStaticData.ServiceType=2;
                            PublicStaticData.PopNumberTitle = 3;
                            AdapterType = 3;
                            titleright.setText("降重");
                            service_screen_lv.setVisibility(View.GONE);
                            seachtype = "0";
                            Direction = "";
                            setTitleView();
                            defaulttv.setTextColor(getResources().getColor(R.color.color_1AB394));
                            PublicStaticData.PopNumber = 0;
                            NeedClear=true;
                            NeedToast=true;
                            getData();
                        }
                    }
                });
                break;
        }
    }

    private  void  getData(){

        /**
         * @param Keywords  关键字
         * @param Type      1:查重2：降重3：速审
         * @param seachtype 0:默认	1:销量2：信誉3：人气
         * @param Direction  type为1时，期刊职称，学位论文，硕博论文
         * @param Page
         * @param rows
         */

        String Keywords= search.getText().toString().trim();
        String Type = "";

        //1：速审  2：查重   3：降重
        if (PublicStaticData.PopNumberTitle == 1) {
            Type = "3";
            PublicStaticData.ServiceType=3;
        } else if (PublicStaticData.PopNumberTitle == 2) {
            Type = "1";
            PublicStaticData.ServiceType=1;
        } else if (PublicStaticData.PopNumberTitle == 3) {
            Type = "2";
            PublicStaticData.ServiceType=2;
        }
        mPresenter.getServiceData(Keywords , Type , seachtype , Direction ,  Page+"" , Rows+"" );
    }


    public void setView() {
        //  1:速审   2:查重   3:降重
        if (PublicStaticData.PopNumberTitle == 1) {
            Direction = "";
            //        Type1:检测查重 2：修改降重3：编辑速审
//        1、只有速审没有原价
            PublicStaticData.ServiceType=3;
            //速审
            AdapterType = 1;
            titleright.setText("速审");
            service_screen_lv.setVisibility(View.GONE);
        } else if (PublicStaticData.PopNumberTitle == 2) {
            PublicStaticData.ServiceType=1;
            //查重
            AdapterType = 2;
            titleright.setText("查重");
            if(PublicStaticData.PopNumber==1){
                Direction = "期刊职称论文查重";
            }else if(PublicStaticData.PopNumber==2){
                Direction = "学位论文分段查重";
            }else if(PublicStaticData.PopNumber==3){
                Direction = "本科学位PMLC查重";
            }else if(PublicStaticData.PopNumber==4){
                Direction = "硕博论文VIP系统查重";
            }
            service_screen_lv.setVisibility(View.VISIBLE);
        } else if (PublicStaticData.PopNumberTitle == 3) {
            Direction = "";
            PublicStaticData.ServiceType=2;
            //降重
            AdapterType = 3;
            titleright.setText("降重");
            service_screen_lv.setVisibility(View.GONE);
        }

        studio_re.setLayoutManager(new LinearLayoutManager(getActivity()));
        mServiceAdapter = new ServiceAdapter( getActivity() );
        mServiceAdapter.setList(mList , AdapterType);
        studio_re.addItemDecoration(new DividerItemDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        studio_re.setAdapter(mServiceAdapter);

        mServiceAdapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PublicStaticData.ServiceId = mList.get(position).getId() + "";
                Intent intent = new Intent(getActivity(), ServiceDetailsActivity.class);
                startActivity(intent);
            }
        });

        getData();
    }

    private void setTitleView() {
        defaulttv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        bondtv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        reputationtv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
        salesvolumetv.setTextColor(getResources().getColor(R.color.color_8E8E8E));
    }

    @Override
    public void showLoading() {
        creatMyDialog(getActivity().getString(R.string.loading_string));
        showMyDialog();
    }

    @Override
    public void hineLoading() {
        hideMyDialog();
    }

    @Override
    public void getServiceData(ServiceBean mServiceBean, int code, String msg) {
        loading.setVisibility(View.GONE);
        mySwipeRefresh.setRefreshing(false);
        if(code == FinalData.OKHTTP_SUCCESS){
            if(mServiceBean.getResultCode()==0){
                Count=mServiceBean.getCount();
                if(NeedClear){
                    mList.clear();
                }
                if(NeedToast&&mServiceBean.getData().size()==0){
                    T.shortToast(getActivity(),"暂无数据");
                }
                mList.addAll(mServiceBean.getData());
                mServiceAdapter.setList(mList , AdapterType);
            }else{
                T.shortToast(getActivity(),mServiceBean.getMsg());
            }
        }else{
            T.shortToast(getActivity(),msg);
        }
    }
}
