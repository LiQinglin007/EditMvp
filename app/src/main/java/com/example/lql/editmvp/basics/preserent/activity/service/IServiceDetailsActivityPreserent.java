package com.example.lql.editmvp.basics.preserent.activity.service;

/**
 * Created by Admin on 2017/5/26.
 */

public interface IServiceDetailsActivityPreserent {

    void getData(String serviceId , String userid);

    /**
     * 收藏
     * @param userid
     * @param serviceId
     */
    void collection(String userid , String serviceId );

}
