package com.example.lql.editmvp.basics.ui.activity.view.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.base.BasePreserent;

/**
 * 类描述：公告详情
 * 作  者：李清林
 * 时  间：2017.5.22
 * 修改备注：
 */

public class NoticeDetailsActivity extends BaseActivity{

    private TextView title;
    private ImageView leftback;
    private TextView noticetitle;
    private TextView noticetime;
    private TextView noticecontent;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_notice_details;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("公告详情");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        this.noticecontent = (TextView) findViewById(R.id.notice_content);
        this.noticetime = (TextView) findViewById(R.id.notice_time);
        this.noticetitle = (TextView) findViewById(R.id.notice_title);


        this.noticetitle.setText(getIntent().getStringExtra("Notice"));
        this.noticetime.setText(getIntent().getStringExtra("CreateTime"));
        this.noticecontent.setText(getIntent().getStringExtra("Content"));
    }

    @Override
    protected BasePreserent creatPresenter() {
        return null;
    }
}
