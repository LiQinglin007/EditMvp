package com.example.lql.editmvp.basics.ui.activity.view.me;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.basics.base.BaseActivity;
import com.example.lql.editmvp.basics.preserent.activity.me.FeedbackActivityPreserent;
import com.example.lql.editmvp.basics.ui.activity.IView.IFeedbackActivity;
import com.example.lql.editmvp.bean.MyBasebean;
import com.example.lql.editmvp.utils.FinalData;
import com.example.lql.editmvp.utils.PreferenceUtils;
import com.example.lql.editmvp.utils.T;


/**
 * 类描述：意见反馈页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */
public class FeedbackActivity extends BaseActivity <IFeedbackActivity , FeedbackActivityPreserent> implements View.OnClickListener ,IFeedbackActivity{
    private TextView title;
    private ImageView leftback;
    private EditText feedbackcontented;
    private Button feedbacksubmitbut;

    String Userid="";


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_feedback;
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
        title.setText("意见建议");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        this.feedbacksubmitbut = (Button) findViewById(R.id.feedback_submit_but);
        this.feedbackcontented = (EditText) findViewById(R.id.feedback_content_ed);

        leftback.setOnClickListener(this);
        feedbacksubmitbut.setOnClickListener(this);
    }


    @Override
    protected FeedbackActivityPreserent creatPresenter() {
        return new FeedbackActivityPreserent();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.feedback_submit_but:
                String feedbackStr=feedbackcontented.getText().toString().trim();
                if(!TextUtils.isEmpty(feedbackStr)){

                }else{
                    T.shortToast(getApplicationContext(),"反馈内容不能为空");
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
    public void submit(MyBasebean myBasebean, int code, String msg) {
        if(code== FinalData.OKHTTP_SUCCESS){
            if(myBasebean.getResultCode()==0){
                finish();
            }
            T.shortToast(getApplicationContext(),myBasebean.getMsg());
        }else{
            T.shortToast(getApplicationContext(),msg);
        }
    }
}
