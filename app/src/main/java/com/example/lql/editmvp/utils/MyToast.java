package com.example.lql.editmvp.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * 类描述：把一个布局用Toast的方式显示出来
 * 作  者：LQL
 * 时  间：2017/5/8
 * 修改备注：
 */
public class MyToast {
    private static Toast mToast;

    /**
     * @param mContext
     * @param duration  显示时间
     * @param layout    布局
     * @return
     */
    public  static void showToast(Context mContext,int duration,int layout) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        View view = LayoutInflater.from(mContext).inflate(layout, null);
        mToast = new Toast(mContext);
        mToast.setGravity(Gravity.CENTER, 0, 2);
        mToast.setDuration(duration);
        mToast.setView(view);
        mToast.show();
    }



    /**
     * 设置位置
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }


}
