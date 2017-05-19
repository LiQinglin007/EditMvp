package com.example.lql.editmvp.basics.ui.activity.view;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.base.BasePreserent;
import com.example.lql.editmvp.basics.ui.fragment.view.MainFragment;
import com.example.lql.editmvp.basics.ui.fragment.view.MeFragment;
import com.example.lql.editmvp.basics.ui.fragment.view.ServiceFragment;
import com.example.lql.editmvp.basics.ui.fragment.view.StudioFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    public static LinearLayout mainly;
    public  static LinearLayout servicely;
    public static LinearLayout studioly;
    public static LinearLayout mely;

    ImageView mainimg;
    ImageView serviceimg;
    ImageView studioimg;
    ImageView meimg;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        this.mely = (LinearLayout) findViewById(R.id.me_ly);
        this.studioly = (LinearLayout) findViewById(R.id.studio_ly);
        this.servicely = (LinearLayout) findViewById(R.id.service_ly);
        this.mainly = (LinearLayout) findViewById(R.id.main_ly);


        mainimg = (ImageView) findViewById(R.id.main_img);
        serviceimg = (ImageView) findViewById(R.id.service_img);
        studioimg = (ImageView) findViewById(R.id.studio_img);
        meimg = (ImageView) findViewById(R.id.me_img);


        this.mainly.setOnClickListener(this);
        this.servicely.setOnClickListener(this);
        this.studioly.setOnClickListener(this);
        this.mely.setOnClickListener(this);

        setView ();
        mainimg.setImageResource(R.drawable.icon_home_sel);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ly, MainFragment.getInstance()).commit();
    }

    @Override
    protected BasePreserent creatPresenter() {
        return null;
    }

    @Override
    public void onClick(View view) {
        setView ();
        switch (view.getId()){
            case R.id.me_ly:
                meimg.setImageResource(R.drawable.icon_mine_sel);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ly, MeFragment.getInstance()).commit();
                break;
            case R.id.studio_ly:
                studioimg.setImageResource(R.drawable.icon_stor_sel);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ly, StudioFragment.getInstance()).commit();
                break;
            case R.id.service_ly:
                serviceimg.setImageResource(R.drawable.icon_sevice_sel);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ly, ServiceFragment.getInstance()).commit();
                break;
            case R.id.main_ly:
                mainimg.setImageResource(R.drawable.icon_home_sel);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ly, MainFragment.getInstance()).commit();
                break;
        }
    }


    private void setView (){
        mainimg.setImageResource(R.drawable.icon_home_nor);
        serviceimg.setImageResource(R.drawable.icon_sevice_nor);
        studioimg.setImageResource(R.drawable.icon_stor_nor);
        meimg.setImageResource(R.drawable.icon_mine_nor);
    }


    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
