package com.example.lql.editmvp.basics.ui.fragment.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lql.editmvp.R;
import com.example.lql.editmvp.adapter.main.MainServiceApapter;
import com.example.lql.editmvp.basics.base.BaseFragment;
import com.example.lql.editmvp.basics.ui.activity.view.main.ChooseCheckTypeActivity;
import com.example.lql.editmvp.basics.preserent.fragment.MainFragmentPreserent;
import com.example.lql.editmvp.basics.ui.activity.view.main.NoticeActivity;
import com.example.lql.editmvp.basics.ui.activity.view.service.ServiceDetailsActivity;
import com.example.lql.editmvp.basics.ui.fragment.IView.IMainFragment;
import com.example.lql.editmvp.bean.GetImglist;
import com.example.lql.editmvp.bean.MainGetService;
import com.example.lql.editmvp.bean.NoticeBean;
import com.example.lql.editmvp.myhttp.MyUrl;
import com.example.lql.editmvp.utils.LogUtils;
import com.example.lql.editmvp.utils.PublicStaticData;
import com.example.lql.editmvp.utils.RecyclerView.OnItemClickListener;
import com.example.lql.editmvp.utils.T;
import com.example.lql.editmvp.view.SwitchViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

import static com.example.lql.editmvp.utils.FinalData.OKHTTP_SUCCESS;


/**
 * 类描述：首页
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class MainFragment extends BaseFragment<IMainFragment , MainFragmentPreserent> implements View.OnClickListener ,IMainFragment {

    BGABanner  mBGABanner;
    SwitchViewGroup mSwitchViewGroup;
    ImageView  main_submission_img;//投稿
    ImageView  main_check_img;//查重
    ImageView  main_flag_img;//降重
    ImageView  main_service_img;//我要服务
    RecyclerView mRecyclerView;


    private ImageView back_ly;
    private TextView title_tv;




    MainServiceApapter mMainServiceApapter;
    View HeadView;
    ArrayList<MainGetService.DataBean> mDataBeanArrayList=new ArrayList<>();
    private List<String> datas = new ArrayList<>();

    ArrayList<String> picList=new ArrayList<>();//图片
    ArrayList<String> dataList=new ArrayList<>();//描述

    private static MainFragment mainFragment;

    public static  MainFragment getInstance(){
        if(mainFragment==null){
            mainFragment=new MainFragment();
        }
        return  mainFragment;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_submission_img://投稿
                PublicStaticData.PopNumberTitle=1;
                PublicStaticData.MainFragmentNmuber = 1;
                sendBrocast();
                break;
            case R.id.main_check_img://查重
                startActivity(new Intent(getActivity(), ChooseCheckTypeActivity.class));
                break;
            case R.id.main_flag_img://降重
                PublicStaticData.PopNumberTitle=3;
                PublicStaticData.MainFragmentNmuber = 1;
                sendBrocast();
                break;
            case R.id.main_service_img://我的服务
                PublicStaticData.MainFragmentNmuber = 3;
                sendBrocast();
                break;
        }
    }

    @Override
    protected MainFragmentPreserent createPresenter() {
        return new MainFragmentPreserent();
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {

        HeadView= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main_top,null);
        mBGABanner= (BGABanner) HeadView.findViewById(R.id.banner_main_depth);
        mSwitchViewGroup= (SwitchViewGroup) HeadView.findViewById(R.id.switchViewGroup);
        main_submission_img= (ImageView) HeadView.findViewById(R.id.main_submission_img);
        main_check_img= (ImageView) HeadView.findViewById(R.id.main_check_img);
        main_flag_img= (ImageView) HeadView.findViewById(R.id.main_flag_img);
        main_service_img= (ImageView) HeadView.findViewById(R.id.main_service_img);

        mRecyclerView= (RecyclerView) rootView.findViewById(R.id.fragment_main_recycle);


        back_ly= (ImageView) rootView.findViewById(R.id.titleBar_left_img);
        title_tv= (TextView)rootView.findViewById(R.id.title_title);
        title_tv.setText("首页");
        back_ly.setOnClickListener(this);
        back_ly.setVisibility(View.INVISIBLE);


        mMainServiceApapter=new MainServiceApapter(getActivity());
        mMainServiceApapter.setHeaderView(HeadView);
        mMainServiceApapter.setList(mDataBeanArrayList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mMainServiceApapter);

        mMainServiceApapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Type1:检测查重 2：修改降重3：编辑速审
                if(mDataBeanArrayList.get(position).getType()==1){
                    //1：速审  2：查重   3：降重
                    PublicStaticData.PopNumberTitle=2;
                }else  if(mDataBeanArrayList.get(position).getType()==2){
                    PublicStaticData.PopNumberTitle=3;
                }else{
                    PublicStaticData.PopNumberTitle=1;
                }
//                sendBrocast();
                PublicStaticData.ServiceId=mDataBeanArrayList.get(position).getId()+"";
                startActivity(new Intent(getActivity(), ServiceDetailsActivity.class));
            }
        });


        mBGABanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(getActivity())
                        .load(model)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });


        mPresenter.setImageList();
        mPresenter.setNotice();
        mPresenter.setServiceList(1,10);
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
    public void setImageList(GetImglist mGetImglist, int code , String msg) {
        LogUtils.Loge(mGetImglist.toString()+"mGetImglist");
        if(code==OKHTTP_SUCCESS){
            picList.clear();
            dataList.clear();
            for (int i = 0; i < mGetImglist.getData().size(); i++) {
                picList.add(MyUrl.Pic+mGetImglist.getData().get(i).getImgurl());
                dataList.add("");
            }
            //设置数据
            mBGABanner.setData(picList,dataList);
        }else{
            T.shortToast(getActivity(),msg);
        }
    }

    @Override
    public void setServiceList(MainGetService mainGetService, int code , String msg) {
        LogUtils.Loge(mainGetService.toString()+"mainGetService");
        if(code==OKHTTP_SUCCESS){
            mDataBeanArrayList.clear();
            mDataBeanArrayList.addAll(mainGetService.getData());
            mMainServiceApapter.setList(mDataBeanArrayList);
        }else{
            T.shortToast(getActivity(),msg+"a");
        }
    }

    @Override
    public void setNotice(NoticeBean mNoticeBean, int code , String msg) {
        LogUtils.Loge(mNoticeBean.toString()+"mNoticeBean");
        if(code==OKHTTP_SUCCESS){
            for (int i = 0; i < mNoticeBean.getData().size(); i++) {
                datas.add(mNoticeBean.getData().get(i).getNotice());
            }
            mSwitchViewGroup.addData(datas);
            mSwitchViewGroup.startScroll();
            mSwitchViewGroup.setOnClickTabListener(new SwitchViewGroup.OnClickTabListener() {
                @Override
                public void onClickTab(int index) {
                    //每个条目的点击事件
                    startActivity(new Intent(getActivity(), NoticeActivity.class));
                }
            });
        }else{
            T.shortToast(getActivity(),msg+"b");
        }
    }

    public void sendBrocast(){
        Intent intent = new Intent();
        intent.setAction("com.lql.setview");
        getActivity().sendBroadcast(intent);
    }

}
