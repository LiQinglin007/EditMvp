package com.example.lql.editmvp.basics.ui.activity.view.studio;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.adapter.studio.StudioDetailsAdapter;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.preserent.activity.StudioDetailsActivityPreserent;
import com.example.lql.editmvp.basics.ui.activity.IView.IStudioDetailsActivity;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.bean.StudioDetailsBean;
import com.example.lql.editmvp.myhttp.MyUrl;
import com.example.lql.editmvp.utils.Glide.GlidePicUtils;
import com.example.lql.editmvp.utils.PreferenceUtils;
import com.example.lql.editmvp.utils.T;

import java.util.ArrayList;



/**
 * 类描述：工作室详情况页面
 * 作  者：李清林
 * 时  间：2016/11/27
 * 修改备注：
 */

public class StudioDetailsActivity extends BaseActivity <IStudioDetailsActivity,StudioDetailsActivityPreserent>implements View.OnClickListener , IStudioDetailsActivity {

    final public int REQUEST_CODE_CALLPHONE_PERMISSIONS = 10001;//拨打电话

    private android.support.v7.widget.RecyclerView studiore;
    private ImageView studiocollectionimg;
    private LinearLayout studiocollectionly;
    private LinearLayout studioqqly;
    private TextView studiophonetv;
    private LinearLayout studiophonely;
    private TextView title;
    private ImageView leftback;
    TextView studioName;//店铺名称
    TextView studioName1;//诚信保证金
    TextView studioName2;//月销量
    TextView studioName3;//信誉值
    TextView introduce;//店铺简介
    TextView number;//评论数量
    TextView username;//用户名称
    TextView usercontent;//评论内容
    TextView viewall;//查看全部
    TextView studio_collection_tv;//查看全部

    ImageView StudioPic;//工作室头像

    LinearLayout heat_studiodetails_user_lv;
    ImageView heat_studiodetails_user_img1;

    View HeadView;//头布局

    StudioDetailsAdapter mStudioDetailsAdapter;//适配器

    ArrayList<StudioDetailsBean.DataBean.ServiceBean> mList = new ArrayList<>();


    String studioId = "";
    StudioDetailsBean mBean;

    String Userid = "";

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_studio_details;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("工作室");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);

        this.studiophonely = (LinearLayout) findViewById(R.id.studio_phone_ly);
        this.studiophonetv = (TextView) findViewById(R.id.studio_phone_tv);
        this.studioqqly = (LinearLayout) findViewById(R.id.studio_qq_ly);
        this.studiocollectionly = (LinearLayout) findViewById(R.id.studio_collection_ly);
        this.studiocollectionimg = (ImageView) findViewById(R.id.studio_collection_img);
        this.studiore = (RecyclerView) findViewById(R.id.studio_re);
        this.studio_collection_tv = (TextView) findViewById(R.id.studio_collection_tv);


        HeadView = getLayoutInflater().inflate(R.layout.head_studio_details, null);

        studiore.setLayoutManager(new LinearLayoutManager(this));
        mStudioDetailsAdapter = new StudioDetailsAdapter(StudioDetailsActivity.this);
        mStudioDetailsAdapter.setList(mList);
        mStudioDetailsAdapter.setHeadView(HeadView);
        studiore.setAdapter(mStudioDetailsAdapter);

        studioName = (TextView) HeadView.findViewById(R.id.heat_studiodetails_name_tv);
        studioName1 = (TextView) HeadView.findViewById(R.id.heat_studiodetails_name_tv1);
        studioName2 = (TextView) HeadView.findViewById(R.id.heat_studiodetails_name_tv2);
        studioName3 = (TextView) HeadView.findViewById(R.id.heat_studiodetails_name_tv3);
        introduce = (TextView) HeadView.findViewById(R.id.heat_studiodetails_introduce_tv);
        number = (TextView) HeadView.findViewById(R.id.heat_studiodetails_number_tv);
        username = (TextView) HeadView.findViewById(R.id.heat_studiodetails_user_tv);
        usercontent = (TextView) HeadView.findViewById(R.id.heat_studiodetails_usercontent_tv);
        viewall = (TextView) HeadView.findViewById(R.id.heat_studiodetails_viewall_tv);//查看全部
        heat_studiodetails_user_lv = (LinearLayout) HeadView.findViewById(R.id.heat_studiodetails_user_lv);//查看全部

        StudioPic = (ImageView) HeadView.findViewById(R.id.heat_studiodetails_img);
        heat_studiodetails_user_img1 = (ImageView) HeadView.findViewById(R.id.heat_studiodetails_user_img1);

        viewall.setOnClickListener(this);
        studiocollectionly.setOnClickListener(this);
        studioqqly.setOnClickListener(this);
        studiophonely.setOnClickListener(this);

        if (PreferenceUtils.getBoolean("IsLogin", false)) {
            Userid = PreferenceUtils.getString("UserId", "");
        } else {
            Userid = "0";
        }

        studioId=getIntent().getStringExtra("studioid");

        mPresenter.ShopDetail(Userid, studioId);
    }


    @Override
    protected StudioDetailsActivityPreserent creatPresenter() {
        return new StudioDetailsActivityPreserent();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.studio_phone_ly://打电话   6.0记得申请权限
                String TelphoneStr = mBean.getData().getTelphone();
                if (TextUtils.isEmpty(TelphoneStr)) {
                    T.shortToast(getApplicationContext(), "该店铺未设置联系方式");
                    return;
                }

                new zhangphil.iosdialog.widget.AlertDialog(this)
                        .builder().setMsg("确认拨打" + TelphoneStr + "?")
                        .setTitle("提示")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (Build.VERSION.SDK_INT >= 23) {
                                    int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CAMERA);
                                    if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                                        if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                                            showMessageOKCancel("You need to allow access to Contacts",
                                                    new DialogInterface.OnClickListener() {
                                                        @RequiresApi(api = Build.VERSION_CODES.M)
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                                                    REQUEST_CODE_CALLPHONE_PERMISSIONS);
                                                        }
                                                    });
                                            return;
                                        }
                                    }
                                    ToCallPhone();
                                } else {
                                    ToCallPhone();
                                }
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();

                break;
            case R.id.studio_collection_ly://收藏
                boolean IsLogin = PreferenceUtils.getBoolean("IsLogin", false);
                if (IsLogin) {
                    mPresenter.collection(Userid, studioId);
                }else{
                    T.shortToast(StudioDetailsActivity.this, "请先登录");
//                    startActivity(new Intent(StudioDetailsActivity.this, LoginActivity.class));
                }
                break;
            case R.id.studio_qq_ly://QQ
                if(!isSpecialApplInstalled(this,"com.tencent.mobileqq")){
                    T.shortToast(getApplicationContext(),"您没有安装QQ");
                    return;
                }

                String QQStr = mBean.getData().getQq();
                if (TextUtils.isEmpty(QQStr)) {
                    T.shortToast(getApplicationContext(), "该店铺未设置QQ");
                } else {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqq://im/chat?chat_type=crm&uin=" + QQStr)));
                }
                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(StudioDetailsActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CALLPHONE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ToCallPhone();
                } else {
                    Toast.makeText(StudioDetailsActivity.this, "请先开启拨打电话权限", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    private void ToCallPhone() {
        //用intent启动拨打电话
        String TelphoneStr = mBean.getData().getTelphone();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + TelphoneStr));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }
    /**
     * 判断手机设备是否安装指定包名的apk应用程序
     * @param context
     * @param packageName
     * @return
     */
    public  boolean isSpecialApplInstalled(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void showLoading() {
        creatMyDialog(getString(R.string.loading_string));
        showMyDialog();
    }

    @Override
    public void hineLoading() {
        hideMyDialog();
    }

    @Override
    public void setDta(StudioDetailsBean mBean, int code, String msg) {

        if(code==0){
            if (mBean.getResultCode() == 0) {
                if (!TextUtils.isEmpty(mBean.getData().getHeadImg())) {
                    StudioPic.setImageURI(Uri.parse(MyUrl.Pic + mBean.getData().getHeadImg()));//图片
//                        StudioPic.setImageURI(Uri.parse(MyUrl.Pic+"/Upload/img/20161210/20161210021225.jpg"));//图片
                }
                studioName.setText(mBean.getData().getWorkName() + "");
                studioName1.setText("诚信保证金：" + mBean.getData().getDeposit() + "");
                studioName2.setText("月销量：" + mBean.getData().getCount() + "");
                studioName3.setText("信誉值：" + mBean.getData().getCredit() + "");
                number.setText("用户评论(" + mBean.getData().getComment()+ ")");
                if (mBean.getData().getComment() == 0) {
                    heat_studiodetails_user_lv.setVisibility(View.GONE);
                    usercontent.setVisibility(View.GONE);
                }
                introduce.setText(mBean.getData().getDis() + "");

                if (mBean.getData().getCommentDetail() != null) {
                    username.setText(mBean.getData().getCommentDetail().getName() + "");
                    usercontent.setText(mBean.getData().getCommentDetail().getComContent() + "");
                    GlidePicUtils.NormalPic(StudioDetailsActivity.this,MyUrl.Pic + mBean.getData().getCommentDetail().getImg(),heat_studiodetails_user_img1);
                }
                mList.clear();
                mList.addAll(mBean.getData().getService());
                mStudioDetailsAdapter.setList(mList);

//                    "collect": 0,								是否收藏1：收藏0：未收藏
                if (mBean.getData().getCollect() == 0) {
                    studiocollectionimg.setImageResource(R.drawable.icon_collection_nor);
                } else {
                    studiocollectionimg.setImageResource(R.drawable.icon_collection_sel);
                    studio_collection_tv.setText("已收藏");
                }
                studiophonetv.setText(mBean.getData().getTelphone() + "");//电话

            } else {
                T.shortToast(getApplicationContext(), mBean.getMsg());
            }
        }else{
            T.shortToast(getApplicationContext(),msg);
        }
    }

    @Override
    public void setCollection(MyBasebean mNoticeBean, int code, String msg) {
        if(code==0){
            T.shortToast(getApplicationContext(),mNoticeBean.getMsg());
            if (mNoticeBean.getResultCode() == 0) {
                if (mBean.getData().getCollect() == 0) {
                    mBean.getData().setCollect(1);
                    studiocollectionimg.setImageResource(R.drawable.icon_collection_sel);
                    studio_collection_tv.setText("已收藏");
                } else {
                    mBean.getData().setCollect(0);
                    studiocollectionimg.setImageResource(R.drawable.icon_collection_nor);
                    studio_collection_tv.setText("收藏工作室");
                }
            }
        }else{
            T.shortToast(getApplicationContext(),msg);
        }
    }


}
