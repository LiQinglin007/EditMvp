package com.example.lql.editmvp.basics.ui.fragment.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseFragment;
import com.example.lql.editmvp.basics.preserent.fragment.MeFragmentPreserent;
import com.example.lql.editmvp.basics.ui.activity.view.main.LoginActivity;
import com.example.lql.editmvp.basics.ui.activity.view.me.AboutUsActivity;
import com.example.lql.editmvp.basics.ui.activity.view.me.FeedbackActivity;
import com.example.lql.editmvp.basics.ui.activity.view.me.UpdatePwdActivity;
import com.example.lql.editmvp.basics.ui.fragment.IView.IMeFragment;
import com.example.lql.editmvp.bean.GetNameBean;
import com.example.lql.editmvp.myhttp.MyUrl;
import com.example.lql.editmvp.utils.FinalData;
import com.example.lql.editmvp.utils.Glide.GlidePicUtils;
import com.example.lql.editmvp.utils.PreferenceUtils;
import com.example.lql.editmvp.utils.PublicStaticData;
import com.example.lql.editmvp.utils.T;

import java.text.DecimalFormat;

import zhangphil.iosdialog.widget.AlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment<IMeFragment ,MeFragmentPreserent>   implements View.OnClickListener , IMeFragment {

    private static MeFragment meFragment;


    public static  MeFragment getInstance(){
        if(meFragment==null){
            meFragment=new MeFragment();
        }
        return  meFragment;
    }

    ScrollView myScroll;
    SwipeRefreshLayout me_swipe;
    private android.widget.TextView myaccountphonetv;
    private android.widget.TextView myaccountmoneytv;
    private android.widget.ImageView myaccountnextimg;
    private android.widget.LinearLayout myaccountmyservicely;
    private android.widget.LinearLayout myaccountcheckly;
    private android.widget.LinearLayout myaccountsummaryly;
    private android.widget.LinearLayout myaccountfallsly;
    private android.widget.LinearLayout myaccountcollectionly;
    private android.widget.LinearLayout myaccountwalletly;
    private android.widget.LinearLayout myaccountevaluately;
    private android.widget.LinearLayout myaccountauthenticationly;
    private android.widget.LinearLayout myaccountchangepwdly;
    private android.widget.LinearLayout myaccountfeedbackly;
    private android.widget.LinearLayout myaccountaboutly;
    private android.widget.LinearLayout myaccounttoupdately;
    private android.widget.LinearLayout myaccountStudioApplicationly;
    Button singout_save_but;//退出


    boolean IsLogin;
    TextView real_tv;
    TextView state_tv;
    String real;
    String state;
    private TextView title;
    private ImageView left;
    private ImageView myaccountheatimg;

    private LinearLayout title_me;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myaccount_next_img://编辑个人资料
                if (IsLogin) {
//                    startActivity(new Intent(getActivity(), MyAccountSettingActivity.class));
                    T.shortToast(getActivity(),"暂停服务");
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.title_me:
                if (IsLogin) {
                    T.shortToast(getActivity(),"暂停服务");
//                    startActivity(new Intent(getActivity(), MyAccountSettingActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_check_ly://查重订单
                if (IsLogin) {
//                    startActivity(new Intent(getActivity(), CheckOrderActivity.class));
                    T.shortToast(getActivity(),"暂停服务");
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_summary_ly://速审订单
                if (IsLogin) {
//                    startActivity(new Intent(getActivity(), SummaryOrderActivity.class));
                    T.shortToast(getActivity(),"暂停服务");
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_falls_ly://降重订单
                if (IsLogin) {
                    T.shortToast(getActivity(),"暂停服务");
//                    startActivity(new Intent(getActivity(), DeclineOrderActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_collection_ly://我的收藏
                if (IsLogin) {
//                    startActivity(new Intent(getActivity(), MyCollectionActivity.class));
                    T.shortToast(getActivity(),"暂停服务");
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_wallet_ly://钱包账户
                if (IsLogin) {
                    T.shortToast(getActivity(),"暂停服务");
//                    startActivity(new Intent(getActivity(), WalletActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_evaluate_ly://我的评价
                if (IsLogin) {
                    T.shortToast(getActivity(),"暂停服务");
//                    startActivity(new Intent(getActivity(), MyEvaluateActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_authentication_ly://实名认证
                if (IsLogin) {
                    if (real.equals("2")) {
                        T.shortToast(getActivity(),"暂停服务");
//                        startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                    } else if (real.equals("1")) {
                        T.shortToast(getActivity(), "已实名认证");
                    } else {
                        T.shortToast(getActivity(), "正在审核");
                    }
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_changepwd_ly://修改密码
                if (IsLogin) {
                    startActivity(new Intent(getActivity(), UpdatePwdActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_StudioApplication_ly://工作室申请
                if (IsLogin) {
                    T.shortToast(getActivity(),"暂停服务");
//                    startActivity(new Intent(getActivity(), StudioApply.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_feedback_ly://意见反馈
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.myaccount_about_ly://关于我们
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.myaccount_toupdate_ly://检测更新
                T.shortToast(getActivity(),"当前已是最新版本");
                break;
            case R.id.singout_save_but://退出登录
                new AlertDialog(getActivity())
                        .builder().setMsg("是否退出登录？")
                        .setTitle("提示")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PreferenceUtils.setBoolean("IsLogin", false);
                                PreferenceUtils.setBoolean("Login", false);
                                PublicStaticData.MainFragmentNmuber = 0;
                                sendBrocast();
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();
                break;
        }
    }

    @Override
    protected MeFragmentPreserent createPresenter() {
        return new MeFragmentPreserent();
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        myScroll= (ScrollView) view.findViewById(R.id.myScroll);
        me_swipe= (SwipeRefreshLayout) view.findViewById(R.id.me_swipe);


        if (myScroll != null) {
            myScroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    if (me_swipe != null) {
                        me_swipe.setEnabled(myScroll.getScrollY() == 0);
                    }
                }
            });
        }

        title_me= (LinearLayout) view.findViewById(R.id.title_me);
        myScroll= (ScrollView) view.findViewById(R.id.myScroll);
        real_tv = (TextView) view.findViewById(R.id.real_tv);
        state_tv = (TextView) view.findViewById(R.id.state_tv);
        me_swipe= (SwipeRefreshLayout) view.findViewById(R.id.me_swipe);
//        "real": 0							0:正在审核1：已实名认证2：未认证


        state = PreferenceUtils.getString("state", "0");
        if (state.equals("0")) {
            state_tv.setText("未申请");
        } else {
            state_tv.setText("已申请");
        }


        this.myaccounttoupdately = (LinearLayout) view.findViewById(R.id.myaccount_toupdate_ly);
        this.myaccountaboutly = (LinearLayout) view.findViewById(R.id.myaccount_about_ly);
        this.myaccountfeedbackly = (LinearLayout) view.findViewById(R.id.myaccount_feedback_ly);
        this.myaccountStudioApplicationly = (LinearLayout) view.findViewById(R.id.myaccount_StudioApplication_ly);
        this.singout_save_but = (Button) view.findViewById(R.id.singout_save_but);
        myaccounttoupdately.setOnClickListener(this);
        myaccountaboutly.setOnClickListener(this);
        myaccountfeedbackly.setOnClickListener(this);
        myaccountStudioApplicationly.setOnClickListener(this);
        this.singout_save_but.setOnClickListener(this);

        this.myaccountchangepwdly = (LinearLayout) view.findViewById(R.id.myaccount_changepwd_ly);
        this.myaccountauthenticationly = (LinearLayout) view.findViewById(R.id.myaccount_authentication_ly);
        this.myaccountevaluately = (LinearLayout) view.findViewById(R.id.myaccount_evaluate_ly);
        myaccountchangepwdly.setOnClickListener(this);
        myaccountauthenticationly.setOnClickListener(this);
        myaccountevaluately.setOnClickListener(this);

        this.myaccountwalletly = (LinearLayout) view.findViewById(R.id.myaccount_wallet_ly);
        this.myaccountcollectionly = (LinearLayout) view.findViewById(R.id.myaccount_collection_ly);
        this.myaccountfallsly = (LinearLayout) view.findViewById(R.id.myaccount_falls_ly);
        myaccountwalletly.setOnClickListener(this);
        myaccountcollectionly.setOnClickListener(this);
        myaccountfallsly.setOnClickListener(this);

        this.myaccountsummaryly = (LinearLayout) view.findViewById(R.id.myaccount_summary_ly);
        this.myaccountcheckly = (LinearLayout) view.findViewById(R.id.myaccount_check_ly);
        this.myaccountmyservicely = (LinearLayout) view.findViewById(R.id.myaccount_myservice_ly);
        myaccountsummaryly.setOnClickListener(this);
        myaccountcheckly.setOnClickListener(this);
        myaccountmyservicely.setOnClickListener(this);

        this.myaccountnextimg = (ImageView) view.findViewById(R.id.myaccount_next_img);
        myaccountnextimg.setOnClickListener(this);
        title_me.setOnClickListener(this);

        this.myaccountmoneytv = (TextView) view.findViewById(R.id.myaccount_money_tv);
        this.myaccountphonetv = (TextView) view.findViewById(R.id.myaccount_phone_tv);
        this.myaccountheatimg = (ImageView) view.findViewById(R.id.myaccount_heat_img);


        IsLogin = PreferenceUtils.getBoolean("IsLogin", false);
        if(IsLogin){
            mPresenter.getData(PreferenceUtils.getString("UserId", ""));
        }else{
            setView();
        }
        
        title = (TextView) view.findViewById(R.id.title_title);
        title.setText("我的");
        left = (ImageView) view.findViewById(R.id.titleBar_left_img);
        left.setVisibility(View.INVISIBLE);

        me_swipe.setColorSchemeResources(android.R.color.holo_red_light);
        me_swipe.setEnabled(false);
        //设置下拉刷新监听
        me_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IsLogin = PreferenceUtils.getBoolean("IsLogin", false);
                        if(IsLogin){
//                            getData();
                        }else{
                            setView();
                        }
                    }
                }, 0);
            }
        });


        if (myScroll != null) {
            myScroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    if (me_swipe != null) {
                        IsLogin = PreferenceUtils.getBoolean("IsLogin", false);
                        if(IsLogin){
                            me_swipe.setEnabled(myScroll.getScrollY() == 0);
                        }else{
                            me_swipe.setEnabled(false);
                        }
                    }
                }
            });
        }
        mPresenter.getData(PreferenceUtils.getString("UserId", ""));
    }

    /**
     * 设置图片  文字
     */
    private void setView() {
        if (IsLogin) {
            String nickname=PreferenceUtils.getString("UserName","").trim();
            String phone=PreferenceUtils.getString("Telphone","").trim();
            myaccountphonetv.setText(TextUtils.isEmpty(nickname)? phone:nickname);
            float mfloat=PreferenceUtils.getFloat("Balance",0);

            double doublem=Double.parseDouble(mfloat+"");
            DecimalFormat df   = new DecimalFormat("######0.00");
            myaccountmoneytv.setText("账户余额：￥" + df.format(doublem));
            GlidePicUtils.CircleHeadPic(getActivity(),MyUrl.Pic+PreferenceUtils.getString("Img",""),myaccountheatimg);
            this.singout_save_but.setVisibility(View.VISIBLE);

            real = PreferenceUtils.getString("Real", "2");

            if (real.equals("2")) {
                real_tv.setText("未认证");
            } else if (real.equals("1")) {
                real_tv.setText("已实名认证");
            } else {
                real_tv.setText("正在审核");
            }

        } else {
            myaccountphonetv.setText("未登录");
            myaccountmoneytv.setText("账户余额：0");
            real_tv.setText("");
            myaccountheatimg.setImageResource(R.drawable.icon_head_nor1);
            this.singout_save_but.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoading() {
        creatMyDialog(getActivity().getResources().getString(R.string.loading_string));
        showMyDialog();
    }

    @Override
    public void hindLoading() {
        hideMyDialog();
    }

    @Override
    public void getData(GetNameBean mBean, int code, String msg) {
        if(code== FinalData.OKHTTP_SUCCESS){
            if(mBean.getResultCode()==0){
                PreferenceUtils.setFloat("Balance",Float.parseFloat(mBean.getData().getBalance()+""));
                PreferenceUtils.setString("Img",mBean.getData().getImg());//头像
                PreferenceUtils.setString("UserName",mBean.getData().getUserName()+"");//昵称
                PreferenceUtils.setString("Real",mBean.getData().getReal()+"");//认证状态
                setView();
            }else{
                T.shortToast(getActivity(),mBean.getMsg());
            }
        }else{
            T.shortToast(getActivity(),msg);
        }
    }

    public void sendBrocast() {
        Intent intent = new Intent();
        intent.setAction("com.lql.toMain");
        getActivity().sendBroadcast(intent);
    }

    public void sendBrocast1(){
        Intent intent = new Intent();
        intent.setAction("com.lql.MainActivity.setClick");
        getActivity().sendBroadcast(intent);
    }
}
