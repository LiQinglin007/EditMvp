package com.example.lql.editmvp.basics.ui.activity.view.service;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.adapter.service.PicAdapter;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.preserent.activity.service.ServiceDetailsActivityPreserent;
import com.example.lql.editmvp.basics.ui.activity.IView.IServiceDetailsActivity;
import com.example.lql.editmvp.basics.ui.activity.view.main.LoginActivity;
import com.example.lql.editmvp.basics.ui.activity.view.studio.StudioDetailsActivity;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.bean.ServiceDetailsBean;
import com.example.lql.editmvp.myhttp.MyUrl;
import com.example.lql.editmvp.utils.Glide.GlidePicUtils;
import com.example.lql.editmvp.utils.PreferenceUtils;
import com.example.lql.editmvp.utils.PublicStaticData;
import com.example.lql.editmvp.utils.T;
import com.example.lql.editmvp.view.MyListView;

import java.util.ArrayList;

import static com.example.lql.editmvp.R.id.ServiceDetails_scroll;
import static com.example.lql.editmvp.R.id.service_bootom_collection_tv;
import static com.example.lql.editmvp.R.id.service_bootom_type_ly;
import static com.example.lql.editmvp.R.id.studio_ly;
import static com.example.lql.editmvp.utils.BaseUtils.isSpecialApplInstalled;
import static com.example.lql.editmvp.utils.FinalData.OKHTTP_SUCCESS;

/**
 * 类描述：服务详情
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class ServiceDetailsActivity extends BaseActivity <IServiceDetailsActivity , ServiceDetailsActivityPreserent>
        implements IServiceDetailsActivity , View.OnClickListener{

    private TextView title;
    private ImageView leftback;

    private android.widget.ImageView servicedetailsimg;
    private android.widget.TextView servicedetailsnametv;
    private android.widget.TextView servicedetailsnumbertv;
    private android.widget.TextView servicedetailspricetv;
    private android.widget.TextView servicedetailspriceoldtv;
    private android.widget.ImageView heatservicedetailsimg;
    private android.widget.TextView heatservicedetailsnametv;
    private android.widget.TextView heatservicedetailsnametv1;
    private android.widget.TextView heatservicedetailsnametv2;
    private android.widget.TextView heatservicedetailsnametv3;
    private android.widget.LinearLayout studioly;
    private android.widget.TextView explainly1tv1;
    private android.widget.TextView explainly1tv2;
    private android.widget.TextView explainly1tv3;
    private android.widget.TextView explainly1tv4;
    private android.widget.TextView explainly1tv5;
    private android.widget.TextView explainly1tv6;
    private android.widget.TextView explainly1tv7;
    private android.widget.LinearLayout explainly1;
    private android.widget.TextView explainly2tv;
    private android.widget.LinearLayout explainly2;
    private android.widget.TextView explainly3tv;
    private android.widget.LinearLayout explainly3;
    private android.widget.TextView textView2;
    private MyListView servicelistview;
    private android.widget.ScrollView ServiceDetailsscroll;
    private android.widget.ImageView servicebootomcollectionimg;
    private android.widget.TextView servicebootomcollectiontv;
    private android.widget.LinearLayout servicebootomcollectionly;
    private android.widget.LinearLayout servicebootomqqly;
    private android.widget.TextView servicebootomtypetv;
    private android.widget.LinearLayout servicebootomtypely;

    int number=-1;
    String serviceId="";

    PicAdapter mPicAdapter;
    ServiceDetailsBean mBean ;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_service_details;
    }



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        serviceId = PublicStaticData.ServiceId;
        number=PublicStaticData.PopNumberTitle;

        title = (TextView) findViewById(R.id.title_title);
        title.setText("服务详情");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);

        this.servicebootomtypely = (LinearLayout) findViewById(service_bootom_type_ly);
        this.servicebootomtypetv = (TextView) findViewById(R.id.service_bootom_type_tv);
        this.servicebootomqqly = (LinearLayout) findViewById(R.id.service_bootom_qq_ly);
        this.servicebootomcollectionly = (LinearLayout) findViewById(R.id.service_bootom_collection_ly);
        this.servicebootomcollectiontv = (TextView) findViewById(service_bootom_collection_tv);
        this.servicebootomcollectionimg = (ImageView) findViewById(R.id.service_bootom_collection_img);
        this.ServiceDetailsscroll = (ScrollView) findViewById(ServiceDetails_scroll);
        this.servicelistview = (MyListView) findViewById(R.id.service_listview);
        this.textView2 = (TextView) findViewById(R.id.textView2);
        this.explainly3 = (LinearLayout) findViewById(R.id.explain_ly3);
        this.explainly3tv = (TextView) findViewById(R.id.explain_ly3_tv);
        this.explainly2 = (LinearLayout) findViewById(R.id.explain_ly2);
        this.explainly2tv = (TextView) findViewById(R.id.explain_ly2_tv);
        this.explainly1 = (LinearLayout) findViewById(R.id.explain_ly1);
        this.explainly1tv7 = (TextView) findViewById(R.id.explain_ly1_tv7);
        this.explainly1tv6 = (TextView) findViewById(R.id.explain_ly1_tv6);
        this.explainly1tv5 = (TextView) findViewById(R.id.explain_ly1_tv5);
        this.explainly1tv4 = (TextView) findViewById(R.id.explain_ly1_tv4);
        this.explainly1tv3 = (TextView) findViewById(R.id.explain_ly1_tv3);
        this.explainly1tv2 = (TextView) findViewById(R.id.explain_ly1_tv2);
        this.explainly1tv1 = (TextView) findViewById(R.id.explain_ly1_tv1);
        this.studioly = (LinearLayout) findViewById(studio_ly);
        this.heatservicedetailsnametv3 = (TextView) findViewById(R.id.heat_service_details_name_tv3);
        this.heatservicedetailsnametv2 = (TextView) findViewById(R.id.heat_service_details_name_tv2);
        this.heatservicedetailsnametv1 = (TextView) findViewById(R.id.heat_service_details_name_tv1);
        this.heatservicedetailsnametv = (TextView) findViewById(R.id.heat_service_details_name_tv);
        this.heatservicedetailsimg = (ImageView) findViewById(R.id.heat_service_details_img);
        this.servicedetailspriceoldtv = (TextView) findViewById(R.id.service_details_priceold_tv);
        this.servicedetailspricetv = (TextView) findViewById(R.id.service_details_price_tv);
        this.servicedetailsnumbertv = (TextView) findViewById(R.id.service_details_number_tv);
        this.servicedetailsnametv = (TextView) findViewById(R.id.service_details_name_tv);
        this.servicedetailsimg = (ImageView) findViewById(R.id.service_details_img);

        servicebootomtypely.setOnClickListener(this);
        servicebootomcollectionly.setOnClickListener(this);
        servicebootomqqly.setOnClickListener(this);
        studioly.setOnClickListener(this);


        String userid;
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            userid =PreferenceUtils.getString("UserId","");
        }else{
            userid="0";
        }

        setView();

        mPresenter.getData(serviceId , userid);

        servicelistview.setFocusable(false);
    }

    @Override
    protected ServiceDetailsActivityPreserent creatPresenter() {
        return new ServiceDetailsActivityPreserent();
    }


    @Override
    public void showLoading() {
        creatMyDialog(getResources().getString(R.string.loading_string));
        showMyDialog();
    }

    @Override
    public void hineLoading() {
        hideMyDialog();
    }

    @Override
    public void setdata(ServiceDetailsBean mBean, int code, String msg) {
        if(code==OKHTTP_SUCCESS){
            this.mBean=mBean;
            if (mBean.getResultCode() == 0) {

//                    servicedetailsimg//服务图片
                //设置图片
                GlidePicUtils.NormalPic(ServiceDetailsActivity.this,
                        MyUrl.Pic + mBean.getData().getDetail().getPicture(),servicedetailsimg);

                //服务名称
                servicedetailsnametv.setText(mBean.getData().getDetail().getServiceName());
                //原价  OriginalCost
                double numbers=mBean.getData().getDetail().getOriginalCost();
                if(numbers==0){
                    servicedetailspriceoldtv.setVisibility(View.GONE);
                }else{
                    servicedetailspriceoldtv.setText("￥"+ PublicStaticData.ddf.format(numbers));
                    servicedetailspriceoldtv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
                //现在
                servicedetailspricetv.setText("￥"+mBean.getData().getDetail().getServicePrice() + "");
                //服务月销量
                servicedetailsnumbertv.setText("月销量：" + mBean.getData().getDetail().getCount());
//                    工作室名称
                heatservicedetailsnametv.setText(mBean.getData().getDetail().getName() + "");

                PublicStaticData.ShopName=mBean.getData().getDetail().getName();
//                    "deposit": "100",											工作室保证金
                heatservicedetailsnametv1.setText("诚信保证金：" + mBean.getData().getDetail().getDeposit());
//                    "credit": 0,													工作室信誉
                heatservicedetailsnametv3.setText("信誉值:" + mBean.getData().getDetail().getCredit() + "");
//                    "totalCount": 0,											工作室月销量
                heatservicedetailsnametv2.setText("月销量：" + mBean.getData().getDetail().getTotalCount());
//                    "img": null,													工作室头像

                GlidePicUtils.CircleHeadPic(ServiceDetailsActivity.this,
                        MyUrl.Pic + mBean.getData().getDetail().getImg(),heatservicedetailsimg );


//                    刊物级别：北大核心期刊
                explainly1tv1.setText("刊物级别：" + mBean.getData().getDetail().getPublicationLevel());
//                    复合影响因子：0.165
                explainly1tv2.setText("复合影响因子：" + mBean.getData().getDetail().getInfluenceFactor());
                //综合影响因子
                explainly1tv3.setText("综合影响因子：" + mBean.getData().getDetail().getTotalInfluenceFactor());
                //录用学科范围
                explainly1tv4.setText("录用学科范围：" + mBean.getData().getDetail().getCheckType().substring(0,mBean.getData().getDetail().getCheckType().length()-1));
                //初审周期：
                explainly1tv5.setText("初审周期：" + mBean.getData().getDetail().getPrePeriod());
                //复审周期
                explainly1tv6.setText("复审周期：" + mBean.getData().getDetail().getReviewCycle());
                //终审周期
                explainly1tv7.setText("终审周期：" + mBean.getData().getDetail().getReviewCycle());
                // 检测类型
                explainly2tv.setText("检测类型:" +mBean.getData().getDetail().getCheckType().substring(0,mBean.getData().getDetail().getCheckType().length()-1));
                //  学科范围
                explainly3tv.setText("学科范围:" + mBean.getData().getDetail().getCheckType().substring(0,mBean.getData().getDetail().getCheckType().length()-1));

                if (number == 2||number==3) {
                    if(Double.parseDouble(mBean.getData().getDetail().getServicePrice())==mBean.getData().getDetail().getOriginalCost()||mBean.getData().getDetail().getOriginalCost()==0){
                        servicedetailspriceoldtv.setVisibility(View.GONE);
                    }else{
                        servicedetailspriceoldtv.setVisibility(View.VISIBLE);
                    }
                }
//                    "collect": 0,								是否收藏1：收藏0：未收藏
                if (mBean.getData().getDetail().getCollect() == 1) {
                    servicebootomcollectionimg.setImageResource(R.drawable.icon_collection_sel);
                    servicebootomcollectiontv.setText("已收藏");
                }

                ArrayList<ServiceDetailsBean.DataBean.ServiceimgBean> img=new ArrayList<ServiceDetailsBean.DataBean.ServiceimgBean>();
                img.addAll( mBean.getData().getServiceimg());

                mPicAdapter=new PicAdapter(img,ServiceDetailsActivity.this);
                servicelistview.setAdapter(mPicAdapter);

            }
        }else{
            T.shortToast(getApplicationContext(),msg);
        }
    }

    @Override
    public void setCollection(MyBasebean myBasebean, int code, String msg) {
        if(code==OKHTTP_SUCCESS){
            if(myBasebean.getResultCode()==0){
                if (mBean.getData().getDetail().getCollect() == 1) {
                    servicebootomcollectionimg.setImageResource(R.drawable.icon_collection_nor);
                    mBean.getData().getDetail().setCollect(0);
                    servicebootomcollectiontv.setText("收藏服务");
                } else {
                    mBean.getData().getDetail().setCollect(1);
                    servicebootomcollectionimg.setImageResource(R.drawable.icon_collection_sel);
                    servicebootomcollectiontv.setText("已收藏");
                }
            }
            T.shortToast(getApplicationContext(),myBasebean.getMsg());
        }else{
            T.shortToast(getApplicationContext(),msg);
        }
    }


    /**
     * 显示数据
     */
    private void setView() {
//        private int number=-1;//1：速审  2：查重   3：降重
        if (number == 1) {
            servicedetailspriceoldtv.setVisibility(View.GONE);
            explainly1.setVisibility(View.VISIBLE);
            explainly2.setVisibility(View.GONE);
            explainly3.setVisibility(View.GONE);
            servicebootomtypetv.setText("我要投稿");
        } else if (number == 2) {
            explainly1.setVisibility(View.GONE);
            explainly2.setVisibility(View.VISIBLE);
            explainly3.setVisibility(View.GONE);
            servicebootomtypetv.setText("我要查重");
        } else if (number == 3) {
            explainly1.setVisibility(View.GONE);
            explainly2.setVisibility(View.GONE);
            explainly3.setVisibility(View.VISIBLE);
            servicebootomtypetv.setText("我要降重");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.studio_ly:
                PublicStaticData.StudioId=mBean.getData().getDetail().getShopId()+"";
                startActivity(new Intent(ServiceDetailsActivity.this, StudioDetailsActivity.class));
                break;
            case R.id.titleBar_left_img:
                finish();
                break;

            case R.id.service_bootom_collection_ly://收藏
                boolean IsLogin= PreferenceUtils.getBoolean("IsLogin",false);
                if(IsLogin){
                    mPresenter.collection(PreferenceUtils.getString("UserId",""),serviceId);
                }else{
                    T.shortToast(ServiceDetailsActivity.this,"请先登录");
                    startActivity(new Intent(ServiceDetailsActivity.this, LoginActivity.class));
                }
                break;

            case R.id.service_bootom_qq_ly://QQ
                if(!isSpecialApplInstalled(this,"com.tencent.mobileqq")){
                    T.shortToast(getApplicationContext(),"您没有安装QQ");
                    return;
                }

                String QQStr=mBean.getData().getDetail().getQQ();
                if(TextUtils.isEmpty(QQStr)){
                    T.shortToast(getApplicationContext(),"该店铺未设置QQ");
                }else{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqq://im/chat?chat_type=crm&uin="+QQStr)));
                }
                break;

            case R.id.service_bootom_type_ly://提交
                boolean IsLogin1= PreferenceUtils.getBoolean("IsLogin",false);
                if(IsLogin1){
                    T.shortToast(ServiceDetailsActivity.this,"请先实名认证");
                }else{
                    T.shortToast(ServiceDetailsActivity.this,"请先登录");
                    startActivity(new Intent(ServiceDetailsActivity.this, LoginActivity.class));
                }
                break;
        }
    }
}
