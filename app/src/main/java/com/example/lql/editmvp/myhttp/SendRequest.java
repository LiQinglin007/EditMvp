package com.example.lql.editmvp.myhttp;


import okhttp3.FormBody;
import okhttp3.RequestBody;

import static com.example.lql.editmvp.myhttp.OkHttpUtils.MyPost;

/**
 * 类描述：请求数据的方法
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class SendRequest {
    /**
     * 登录
     *
     * @param Telphone
     * @param Password
     * @param myCallBack
     */
    public static void Login1(String Telphone, String Password, MOkCallBack myCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("Telphone", Telphone)
                .add("Password", Password)
                .build();
        MyPost(formBody, MyUrl.UserLogin, myCallBack);
    }

    /**
     * 36、修改密码
     *
     * @param oldPwd      旧密码
     * @param newPwd      新密码
     * @param mOkCallBack
     */
    public static void UpdataPassWord(String Userid, String oldPwd, String newPwd, MOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder().
                add("userId", Userid).
                add("oldPwd", oldPwd).
                add("newPwd", newPwd).
                add("affirmPwd", newPwd).
                build();
        MyPost(formBody, MyUrl.UpdataPassWord, mOkCallBack);
    }



    /**
     * 3、获取验证码
     *
     * @param Telphone 电话号码
     * @param Type     1:注册  2：忘记密码 3：绑定手机号
     */
    public static void getregistersms(String Telphone, String Type, MOkCallBack myCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("Telphone", Telphone)
                .add("Type", Type)
                .build();
        MyPost(formBody, MyUrl.getregistersms, myCallBack);
    }


    /**
     * 4、用户注册
     * Telphone:string				电话号码
     * Password:string				密码
     * Confirmpassword:string			确认密码
     * Registercode:string 			验证码
     * Type:int						1:注册2：忘记密码
     */
    public static void userregister(String Telphone, String Password, String Registercode, String Type, MOkCallBack myCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("Telphone", Telphone)
                .add("Type", Type)
                .add("Password", Password)
                .add("Registercode", Registercode)
                .add("Confirmpassword", Password)
                .build();
        MyPost(formBody, MyUrl.userregister, myCallBack);
    }


    /**
     * 5.首页获取轮播图
     */
    public static void ImgList(MOkCallBack myCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .build();
        MyPost(formBody, MyUrl.ImgList, myCallBack);
    }


    /**
     * 6、首页服务列表（可分页）
     *
     * @param Page 页数
     * @param Rows 行数
     */
    public static void ServiceList(int Page, int Rows, MOkCallBack myCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("Page", Page + "")
                .add("Rows", Rows + "")
                .build();
        MyPost(formBody, MyUrl.ServiceList, myCallBack);
    }


    /**
     * 7.查重/速审/降重
     *
     * @param Keywords   关键字
     * @param Type       1:查重2：降重3：速审
     * @param Searchtype 1:销量2：信誉3：人气
     * @param Direction  type为1时，期刊职称，学位论文，硕博论文
     * @param Page       页数
     * @param Rows       行数
     * @param myCallBack
     */
    public static void repeatlist(String Keywords, String Type, String Searchtype, String Direction, String Page, String Rows, MOkCallBack myCallBack) {


        RequestBody formBody = new FormBody.Builder()
                .add("keywords", Keywords)
                .add("type", Type)
                .add("seachType", Searchtype)
                .add("direction", Direction)
                .add("page", Page)
                .add("rows", Rows)
                .build();
        MyPost(formBody, MyUrl.repeatlist, myCallBack);
    }


    /**
     * 8、服务详情
     *
     * @param serviceId   服务ID
     * @param mOkCallBack 回调
     */
    public static void ServiceDetail(String serviceId, String Userid, MOkCallBack mOkCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("Id", serviceId)
                .add("Userid", Userid)
                .build();
        MyPost(formBody, MyUrl.ServiceDetail, mOkCallBack);

    }

    /**
     * 45、用户收藏、取消收藏服务、工作室
     *
     * @param serviceId   服务编号/工作室编号
     * @param type        1:服务2：工作室
     * @param mOkCallBack
     */
    public static void UserFavorite(String Userid, String serviceId, int type, MOkCallBack mOkCallBack) {


        RequestBody formBody = new FormBody.Builder()
                .add("Id", serviceId)
                .add("userid", Userid)
                .add("Type", type + "")
                .build();
        MyPost(formBody, MyUrl.UserFavorite, mOkCallBack);
    }

    /**
     * 12、学科列表   0:学科列表    1:学历列表   2:职称列表
     *
     * @param mOkCallBack
     */
    public static void ProjectList(MOkCallBack mOkCallBack, int type) {
        RequestBody formBody = new FormBody.Builder()
                .build();
        if (type == 0) {
            MyPost(formBody, MyUrl.ProjectList, mOkCallBack);
        } else if (type == 1) {
            MyPost(formBody, MyUrl.SchoolList, mOkCallBack);
        } else if (type == 2) {
            MyPost(formBody, MyUrl.Professtion, mOkCallBack);
        }
    }


    /**
     * 10、工作室列表
     *
     * @param Keywords    关键字
     * @param Searchtype  0:默认1：保证金2：信誉3：销量
     * @param Page        页数
     * @param Rows        行数
     * @param mOkCallBack
     */
    public static void StudioList(String Keywords, String Searchtype, String Page, String Rows, MOkCallBack mOkCallBack) {
        //        http://192.168.29.134:66/serviceRecord/StudioList?keywords=&seachType=0&page=1&rows=5
        RequestBody formBody = new FormBody.Builder()
                .add("keywords", Keywords)
                .add("seachType", Searchtype)
                .add("page", Page)
                .add("rows", Rows)
                .build();
        MyPost(formBody, MyUrl.StudioList, mOkCallBack);
    }

    /**
     * 11、工作室详情
     *
     * @param Id          工作室编号
     * @param mOkCallBack
     */
    public static void ShopDetail(String Userid, String Id, MOkCallBack mOkCallBack) {

        RequestBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("Id", Id)
                .build();
        MyPost(formBody, MyUrl.ShopDetail, mOkCallBack);
    }


    /**
     * 44、工作室评价列表
     *
     * @param Workid      工作室编号
     * @param Page        页数
     * @param Rows        行数
     * @param mOkCallBack
     */
    public static void ProductCommentList(String Workid, String Page, String Rows, MOkCallBack mOkCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .add("WorkId", Workid)
                .add("Page", Page)
                .add("Rows", Rows)
                .build();
        MyPost(formBody, MyUrl.ProductCommentList, mOkCallBack);
    }


    /**
     * 41、公告
     *
     * @param mOkCallBack
     */
    public static void Notice(MOkCallBack mOkCallBack) {
        RequestBody formBody = new FormBody.Builder()
                .build();
        MyPost(formBody, MyUrl.Notice, mOkCallBack);
    }

    /**
     * 43、查询用户余额、头像、昵称
     * @param userid
     * @param mOkCallBack
     */
    public static void UserDetail(String userid, MOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .add("userid", userid)
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.UserDetail, mOkCallBack);
    }

    /**
     * 版本更新
     *
     * @param mOkCallBack
     */
    public static void UpdateVersion(MOkCallBack mOkCallBack) {
        FormBody formBody = new FormBody.Builder()
                .build();
        OkHttpUtils.MyPost(formBody, MyUrl.UpdateVersion, mOkCallBack);
    }

    /**
     * 29、意见反馈
     *
     * @param Conetnt     意见
     * @param mOkCallBack
     */
    public static void AddSuggestion(String Userid, String Conetnt, MOkCallBack mOkCallBack) {


        FormBody formBody = new FormBody.Builder()
                .add("Userid", Userid)
                .add("Suggestion_", Conetnt)
                .build();
        MyPost(formBody, MyUrl.AddSuggestion, mOkCallBack);
    }
}
