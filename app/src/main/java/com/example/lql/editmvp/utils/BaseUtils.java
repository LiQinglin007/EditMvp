package com.example.lql.editmvp.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.lql.editmvp.application.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 类描述：
 * 作  者：LQL
 * 时  间：2017/3/21
 * 修改备注：
 */
public class BaseUtils {
    /**
     * 判断手机设备是否安装指定包名的apk应用程序
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isSpecialApplInstalled(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 截取含有多个index的字符串,返回集合
     *
     * @param index
     * @param picture
     * @return
     */
    public static ArrayList<String> multiSubString(String index, String picture) {

        ArrayList<String> subArr = new ArrayList<String>();

        subStr(index, picture, subArr);

        return subArr;
    }

    private static void subStr(String index, String picture, List<String> subArr) {

        String substring = picture.substring(0, picture.indexOf(index));
        if (!TextUtils.isEmpty(substring)) {
            subArr.add(substring);
        }

        String substring1 = picture.substring(picture.indexOf(index) + 1, picture.length());
        if (!TextUtils.isEmpty(substring1) && substring1.contains(index) && substring1.length() > 1) {
            subStr(index, substring1, subArr);
        }
    }

    /**
     * 去登录页面
     *
     * @param mContext
     */
    public static void toLogin(final Context mContext) {
//        new AlertDialog(mContext)
//                .builder().setMsg("尚未登录，请先登录")
//                .setTitle("提示")
//                .setPositiveButton("确认", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mContext.startActivity(new Intent(mContext, LoginActivity.class));
//                        ((Activity) mContext).overridePendingTransition(R.anim.in_from_right,
//                                R.anim.out_to_left);
//                    }
//                })
//                .setNegativeButton("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                })
//                .show();
    }


    /**
     * 检查控件值是否为空
     *
     * @param value 控件的值
     * @param name  控件提示语
     * @return if return true 不为空 false 空
     */
    public static boolean isEmpty(String[] value, String[] name) {
        for (int i = 0; i < value.length; i++) {
            if (TextUtils.isEmpty(value[i])) {
                T.shortToast(MyApplication.context, name[i] + "不能为空");
                return false;
            }
        }
        return true;
    }


    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *               <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *               、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *               <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *               <p>电信的号段：133、153、180（未启用）、189</p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }


    /**
     * 设置字体大小
     * @param tv
     */
    public static void setTextSize(TextView... tv){
        for(int i=0;i<tv.length;i++){
            tv[i].setTextSize(20);
        }
    }



}
