package com.example.lql.editmvp.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/1.
 */

public class GetImglist extends BaseBean {

    @Override
    public String toString() {
        return "GetImglist{" +
                "Data=" + Data +
                '}';
    }

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "Imgurl='" + Imgurl + '\'' +
                    '}';
        }

        /**
         * Imgurl : /Upload/img/20161130/CP20161130181107560.png
         */

        private String Imgurl="";

        public String getImgurl() {
            return Imgurl;
        }

        public void setImgurl(String Imgurl) {
            this.Imgurl = Imgurl;
        }
    }
}
