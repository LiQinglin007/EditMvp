package com.example.lql.editmvp.myhttp;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
* 类描述：okhttp请求
* 作  者：李清林
* 时  间：
* 修改备注：
*/
public class OkHttpUtils {

   /**
    * 异步post
    * @param formBody  请求参数
    * @param Url   请求地址
    * @param myCallBack  回调
    */
   public static void MyPost(RequestBody formBody, String Url, MOkCallBack myCallBack) {
       Request request = new Request.Builder()
               .url(Url)
               .post(formBody)
               .build();
       Call call = HttpUtils.getInstance().newCall(request);
       call.enqueue(myCallBack);
   }


   /**
    * 异步get
    * @param Url  地址
    * @param myCallBack 回调
    */
   public static void MyGet(String Url,MOkCallBack myCallBack){
       Request requestBuilder = new Request.Builder()
               .url(Url)
               .build();
       Call call=HttpUtils.getInstance().newCall(requestBuilder);
       call.enqueue(myCallBack);
   }

}
