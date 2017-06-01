package com.example.lql.editmvp.basics.ui.activity.view.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.utils.PublicStaticData;

import static com.example.lql.editmvp.R.id.titleBar_left_img;


/**
 * 类描述：选择查重类型页面
 * 作  者：李清林
 * 时  间：2016/11/24
 * 修改备注：
 */
public class ChooseCheckTypeActivity extends BaseActivity implements View.OnClickListener{

    private ImageView chooseperiodicalimg;
    private ImageView choosedegreeimg;
    private ImageView chooseundergraduateimg;
    private ImageView choosemasterimg;
    private TextView title;
    private ImageView leftback;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_choose_check_type;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("选择查重类型");
        leftback = (ImageView) findViewById(titleBar_left_img);
        this.choosemasterimg = (ImageView) findViewById(R.id.choose_master_img);
        this.chooseundergraduateimg = (ImageView) findViewById(R.id.choose_undergraduate_img);
        this.choosedegreeimg = (ImageView) findViewById(R.id.choose_degree_img);
        this.chooseperiodicalimg = (ImageView) findViewById(R.id.choose_periodical_img);
        leftback.setOnClickListener(this);
        choosemasterimg.setOnClickListener(this);
        chooseundergraduateimg.setOnClickListener(this);
        choosedegreeimg.setOnClickListener(this);
        chooseperiodicalimg.setOnClickListener(this);
    }


    @Override
    protected BasePreserent creatPresenter() {
        return null;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case titleBar_left_img:
                finish();
                break;
            case R.id.choose_periodical_img://img1
                PublicStaticData.MainFragmentNmuber = 1;
                PublicStaticData.PopNumberTitle=2;
                PublicStaticData.PopNumber=1;
                finish();
                break;
            case R.id.choose_degree_img://img2
                PublicStaticData.MainFragmentNmuber = 1;
                PublicStaticData.PopNumberTitle=2;
                PublicStaticData.PopNumber=2;
                finish();
                break;
            case R.id.choose_undergraduate_img://img3
                PublicStaticData.MainFragmentNmuber = 1;
                PublicStaticData.PopNumberTitle=2;
                PublicStaticData.PopNumber=3;
                finish();
                break;
            case R.id.choose_master_img://img4
                PublicStaticData.MainFragmentNmuber = 1;
                PublicStaticData.PopNumberTitle=2;
                PublicStaticData.PopNumber=4;
                finish();
                break;
        }
    }
}
