package com.zrb.houserental.constant;

/**
 * Created by Administrator on 2016/3/28.\
 * 网址管理
 */
public interface URL_Constant {
    String BaseURL = "http://house.xinuxz.com/";

    String Login = BaseURL + "api/login";
    String logout = BaseURL + "api/logout";
    String postPasswd = BaseURL + "api/postPasswd";
    String listLodger = BaseURL + "api/house/listLodger";
    String query = BaseURL + "api/house/query";
    String updateRoom = BaseURL + "api/house/updateRoom";
    String room = BaseURL + "api/house/room";
    String newLodger = BaseURL + "api/house/newLodger";
    String initRent = BaseURL + "api/house/initRent";
    String xuRent = BaseURL + "api/house/xuRent";
    String exitRent = BaseURL + "api/house/exitRent";
}
