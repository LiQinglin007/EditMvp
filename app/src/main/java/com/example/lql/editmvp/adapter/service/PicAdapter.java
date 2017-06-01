package com.example.lql.editmvp.adapter.service;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.bean.ServiceDetailsBean;
import com.example.lql.editmvp.myhttp.MyUrl;
import com.example.lql.editmvp.utils.Glide.GlidePicUtils;
import com.example.lql.editmvp.utils.ScreenUtils;

import java.util.ArrayList;

/**
 * Created by LQL on 2016/12/13.
 */

public class PicAdapter extends BaseAdapter {
    ArrayList<ServiceDetailsBean.DataBean.ServiceimgBean> imgs;
    Context mContext;

    public PicAdapter(ArrayList<ServiceDetailsBean.DataBean.ServiceimgBean> imgs, Context context) {
        this.imgs = imgs;
        mContext = context;


    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public Object getItem(int i) {
        return imgs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view= LayoutInflater.from(mContext).inflate(R.layout.item_picadapter,null);
        ImageView pic_item_picadapter= (ImageView) view.findViewById(R.id.pic_item_picadapter);

        if(TextUtils.isEmpty(imgs.get(i).getWidth())||imgs.get(i).getWidth().equals("null")){
            return view;
        }


        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) pic_item_picadapter.getLayoutParams(); //取控件textView当前的布局参数

        linearParams.width = ScreenUtils.getScreenWidth(mContext);

        linearParams.height = (int)(linearParams.width * Double.parseDouble(imgs.get(i).getWidth()));// 控件的高强制设成20

        pic_item_picadapter.setLayoutParams(linearParams); //使设置好的布局参数应用到控件

        GlidePicUtils.NormalPic(mContext, MyUrl.Pic+imgs.get(i).getImg(),pic_item_picadapter);


        return view;
    }
}
