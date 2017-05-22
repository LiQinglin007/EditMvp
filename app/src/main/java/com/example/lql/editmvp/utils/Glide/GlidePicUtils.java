package com.example.lql.editmvp.utils.Glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lql.editmvp.R;


/**
 * Created by LQL on 2017/3/11.
 */

public class GlidePicUtils {

    /**
     * 正常图片
     * @param mContext
     * @param Url
     * @param img
     */
    public static void NormalPic(Context mContext, String Url, ImageView img){
        if(null==mContext){
            return;
        }
        if(null==Url){
            return;
        }
        if(null==img){
            return;
        }
        Glide.with(mContext)
                .load(Url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//设置缓存（缓存源资源和转换后的资源）
                .placeholder(R.drawable.gongzuoshi1)//加载中的图片
                .error(R.drawable.gongzuoshi1)//加载失败的图片
//                .thumbnail(0.1f)//先加载缩略图，再加载大图
                .into(img);
    }


    /**
     * 正常图片(头像图)
     * @param mContext
     * @param Url
     * @param img
     */
    public static void NormalHeadPic(Context mContext, String Url, ImageView img){
        if(null==mContext){
            return;
        }

        if(null==Url){
            return;
        }

        if(null==img){
            return;
        }
        Glide.with(mContext)
                .load(Url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//设置缓存（缓存源资源和转换后的资源）
                .placeholder(R.mipmap.ic_launcher)//加载中的图片
                .error(R.mipmap.ic_launcher)//加载失败的图片
//                .thumbnail(0.1f)//先加载缩略图，再加载大图
                .into(img);
    }

    /**
     * 圆形图片
     * @param mContext
     * @param Url
     * @param img
     */
    public static void  CircleHeadPic(Context mContext, String Url, ImageView img) {
        if(null==mContext){
            return;
        }

        if(null==Url){
            return;
        }

        if(null==img){
            return;
        }
        Glide.with(mContext)
                .load(Url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//设置缓存（缓存源资源和转换后的资源）
                .transform(new GlideCircleTransform(mContext))//圆形图片
                .placeholder(R.drawable.gongzuoshi1)//加载中的图片
                .error(R.drawable.gongzuoshi1)//加载失败的图片
//                .thumbnail(0.1f)//先加载缩略图，再加载大图
                .into(img);
    }



    /**
     *  圆角图片（圆角为10px）
     * @param mContext
     * @param Url
     * @param img
     */
    public static void  RoundPic(Context mContext, String Url, ImageView img){
        if(null==mContext){
            return;
        }

        if(null==Url){
            return;
        }

        if(null==img){
            return;
        }


        Glide.with(mContext)
                .load(Url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//设置缓存（缓存源资源和转换后的资源）
//                .transform(new GlideRoundTransform(mContext))//圆角图片
                .transform(new GlideRoundTransform(mContext,10))//圆角图片
                .placeholder(R.drawable.gongzuoshi1)//加载中的图片
                .error(R.drawable.gongzuoshi1)//加载失败的图片
//                .thumbnail(0.1f)//先加载缩略图，再加载大图
                .into(img);
    }

    /**
     * 圆形图片
     * @param mContext
     * @param Url
     * @param img
     */
    public static void  CirclePic(Context mContext, String Url, ImageView img){
        if(null==mContext){
            return;
        }

        if(null==Url){
            return;
        }

        if(null==img){
            return;
        }
        Glide.with(mContext)
                .load(Url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//设置缓存（缓存源资源和转换后的资源）
                .transform(new GlideCircleTransform(mContext))//圆形图片
                .placeholder(R.drawable.gongzuoshi1)//加载中的图片
                .error(R.drawable.gongzuoshi1)//加载失败的图片
//                .thumbnail(0.1f)//先加载缩略图，再加载大图
                .into(img);



//        Glide.with(LoginActivity.this)
//                .load("http://4493bz.1985t.com/uploads/allimg/150127/4-15012G52133.jpg")
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//设置缓存（缓存源资源和转换后的资源）
////              .diskCacheStrategy(DiskCacheStrategy.NONE)//设置缓存（不作任何磁盘缓存）
////              .diskCacheStrategy(DiskCacheStrategy.SOURCE)//设置缓存（缓存源资源）
////              .diskCacheStrategy(DiskCacheStrategy.RESULT)//设置缓存（缓存转换后的资源）
//                .transform(new GlideRoundTransform(mContext))//圆角图片
//                .transform(new GlideCircleTransform(this))//圆形图片
//                .placeholder(R.mipmap.ic_launcher)//加载中的图片
//                .error(R.mipmap.ic_launcher)//加载失败的图片
//                .thumbnail(0.1f)//先加载缩略图，再加载大图
//                .asGif()//加载gif图，如果传入静态图片，会显示错误图片
//                .asBitmap()//加载静态图，如果传入gif图片，会显示第一帧图片
//                .override(800, 800)//设置加载图片尺寸
//                .skipMemoryCache(true)//跳过内存缓存
//                .into(heatview);
    }






}
