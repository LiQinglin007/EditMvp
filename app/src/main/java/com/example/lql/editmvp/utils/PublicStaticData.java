package com.example.lql.editmvp.utils;

import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
* 类描述：静态数据存放类
* 作  者：李清林
* 时  间：2017/5/8
* 修改备注：
*/
public class PublicStaticData {
   public static SharedPreferences mySharedPreferences;//SharedPreferences存储
   public static DecimalFormat ddf   = new DecimalFormat("######0.00");//金额格式化
   public static DateFormat dateformat = new SimpleDateFormat("yyyy.MM.dd");//时间显示格式
   public static DateFormat dateformatHm = new SimpleDateFormat("HH:mm");//时间显示格式
   public static String downLoadUrl="";//下载地址

}
