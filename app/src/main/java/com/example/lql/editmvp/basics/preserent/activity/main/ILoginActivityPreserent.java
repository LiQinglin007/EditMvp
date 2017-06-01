package com.example.lql.editmvp.basics.preserent.activity.main;

/**
 * Created by Admin on 2017/5/31.
 */

public interface ILoginActivityPreserent {

    void getSms(String phone);

    void Login(String name , String pwd);

    void Regist(String name ,String pwd , String RegistCode);

}
