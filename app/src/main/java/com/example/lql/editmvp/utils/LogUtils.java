package com.example.lql.editmvp.utils;

import android.util.Log;

/**
 * Created by Admin on 2017/5/9.
 */

public class LogUtils {
    static boolean show=true;

    public static void Loge(String conetnt){
        if(show){
            Log.e("###",conetnt+"###");
        }
    }


}
