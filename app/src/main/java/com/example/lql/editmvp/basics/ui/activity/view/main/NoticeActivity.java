package com.example.lql.editmvp.basics.ui.activity.view.main;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.adapter.main.NoticeAdapter;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.preserent.activity.main.NoticeActivityPreserent;
import com.example.lql.editmvp.basics.ui.activity.IView.INoticeActivity;
import com.example.lql.editmvp.bean.NoticeBean;
import com.example.lql.editmvp.utils.RecyclerView.OnItemClickListener;
import com.example.lql.editmvp.utils.T;

import java.util.ArrayList;

import static com.example.lql.editmvp.utils.FinalData.OKHTTP_SUCCESS;

/**
 * 类描述：公告页面
 * 作  者：李清林
 * 时  间：2017.5.22
 * 修改备注：
 */
public class NoticeActivity extends BaseActivity<INoticeActivity,NoticeActivityPreserent> implements INoticeActivity {

    private ImageView back_ly;
    private TextView title_tv;

    RecyclerView notice_re;
    NoticeAdapter mNoticeAdapter;
    ArrayList<NoticeBean.DataBean> mList = new ArrayList<>();
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_notice;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        back_ly= (ImageView) findViewById(R.id.titleBar_left_img);
        title_tv= (TextView) findViewById(R.id.title_title);
        title_tv.setText("公告");
        back_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        notice_re= (RecyclerView) findViewById(R.id.notice_re);
        notice_re.setLayoutManager(new LinearLayoutManager(this));
        mNoticeAdapter=new NoticeAdapter(this);
        mNoticeAdapter.setList(mList);
        notice_re.setAdapter(mNoticeAdapter);

        mPresenter.setNotice();

        mNoticeAdapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent mIntent=new Intent(NoticeActivity.this,NoticeDetailsActivity.class);
                mIntent.putExtra("Notice",mList.get(position).getNotice());
                mIntent.putExtra("Content",mList.get(position).getContent());
                mIntent.putExtra("CreateTime",mList.get(position).getCreateTime());
                startActivity(mIntent);
            }
        });
    }

    @Override
    protected NoticeActivityPreserent creatPresenter() {
        return new NoticeActivityPreserent();
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
    public void setNotice(NoticeBean mNoticeBean, int code, String msg) {
        if(code==OKHTTP_SUCCESS){
            mList.clear();
            mList.addAll(mNoticeBean.getData());
            mNoticeAdapter.setList(mList);
        }else{
            T.shortToast(getApplicationContext(),msg);
        }
    }
}
