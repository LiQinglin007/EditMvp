package com.example.lql.editmvp.basics.preserent.fragment;

/**
 * Created by Admin on 2017/5/26.
 */

public interface IServiceFragmentPreserent {


    /**
     * @param Keywords  关键字
     * @param Type      1:查重2：降重3：速审
     * @param seachtype 0:默认	1:销量2：信誉3：人气
     * @param Direction  type为1时，期刊职称，学位论文，硕博论文
     * @param Page
     * @param rows
     */
    void getServiceData(String Keywords,String  Type,String  seachtype,String  Direction,
                        String  Page ,String   rows);

}
