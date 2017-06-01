package com.example.lql.editmvp.basics.ui.activity.view.me;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.utils.PublicStaticData;


/**
 * 类描述： 关于我们页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */
public class AboutUsActivity extends BaseActivity {
    private TextView title;
    private ImageView leftback;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("关于我们");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected BasePreserent creatPresenter() {
        return null;
    }


}
