package com.example.lql.editmvp.basics.ui.activity.view.me;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.preserent.activity.me.UpdatePwdActivityPresenter;
import com.example.lql.editmvp.basics.ui.activity.IView.IUpdatePwdActivity;
import com.example.lql.editmvp.basics.ui.activity.view.main.LoginActivity;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.utils.FinalData;
import com.example.lql.editmvp.utils.PreferenceUtils;
import com.example.lql.editmvp.utils.PublicStaticData;
import com.example.lql.editmvp.utils.RegularUtil;
import com.example.lql.editmvp.utils.T;




/**
 * 类描述：修改密码页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */


public class UpdatePwdActivity extends BaseActivity <IUpdatePwdActivity , UpdatePwdActivityPresenter>implements View.OnClickListener ,IUpdatePwdActivity {
    private TextView title;
    private ImageView leftback;
    private EditText updateoldpwded;
    private EditText updatenewpwded;
    private EditText updatenewpwdagained;
    private Button updatePwdsubmitbut;

    String Userid="";





    @Override
    protected int provideContentViewId() {
        return R.layout.activity_update_pwd;
    }

    @Override
    protected void initData() {
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }
    }

    @Override
    protected void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("修改登录密码");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        this.updatePwdsubmitbut = (Button) findViewById(R.id.updatePwd_submit_but);
        this.updatenewpwdagained = (EditText) findViewById(R.id.update_newpwdagain_ed);
        this.updatenewpwded = (EditText) findViewById(R.id.update_newpwd_ed);
        this.updateoldpwded = (EditText) findViewById(R.id.update_oldpwd_ed);


        leftback.setOnClickListener(this);
        updatePwdsubmitbut.setOnClickListener(this);
    }


    @Override
    protected UpdatePwdActivityPresenter creatPresenter() {
        return new UpdatePwdActivityPresenter();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();

                break;
            case R.id.updatePwd_submit_but:
                String oldPwd=updateoldpwded.getText().toString().trim();
                String newPwd=updatenewpwded.getText().toString().trim();
                String newPwdAgain=updatenewpwdagained.getText().toString().trim();

                if(!RegularUtil.isPassword(oldPwd)){
                    T.shortToast(getApplicationContext(),"原密码格式不正确");
                }else if(!RegularUtil.isPassword(newPwd)){
                    T.shortToast(getApplicationContext(),"新密码格式不正确");
                }else if(!newPwdAgain.equals(newPwd)){
                    T.shortToast(getApplicationContext(),"两次密码不一致");
                }else{
                    mPresenter.updatePwd(Userid,oldPwd , newPwd);
                }
                break;
        }
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
    public void updatePwd(MyBasebean myBasebean, int code, String msg) {
        if(code== FinalData.OKHTTP_SUCCESS){
            com.example.lql.editmvp.utils.T.shortToast(getApplicationContext(),myBasebean.getMsg());
            if(myBasebean.getResultCode()==0){
                PreferenceUtils.setBoolean("IsLogin",false);
                PublicStaticData.MainFragmentNmuber=0;
                PublicStaticData.toMain=true;
                startActivity(new Intent(UpdatePwdActivity.this, LoginActivity.class));
                finish();
            }
        }else{
            com.example.lql.editmvp.utils.T.shortToast(getApplicationContext(),msg);
        }
    }
}
