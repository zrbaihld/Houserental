package com.zrb.baseapp.constant;

import android.os.Environment;

/**
 * Created by Administrator on 2016/3/28.
 * app常量配置
 */
public final class Constant_C {
    /**链接请求初始页数*/
    public static final int INDEX = 0;
    /**链接每页请求数*/
    public static final int PAGER = 10;
    /**app文件夹*/
    public static final String BASEFILE = "/zrb";



    public static final class SAVEPATH{
        /** 数据库保存目录路径 */
        public static final String DBPATH = Environment.getExternalStorageDirectory().toString() + BASEFILE+"/db/";
        /**拍照完后图片保存目录 */
        public static final String IMGPATH = Environment.getExternalStorageDirectory().toString() +  BASEFILE+"/Photo/";
        /**下载图片保存位置*/
        public static final String LOADIMGPATH = Environment.getExternalStorageDirectory().toString() +  BASEFILE+"/LoadImg/";
        /**图片缓存路径*/
        public static final String CACHEPATH = Environment.getExternalStorageDirectory().toString() +  BASEFILE+"/cache/";
        /**用户本地保存*/
        public static final String USER_MSG_SPPATH = "count";
    }

    public static final class ACTIVITY{
    }

    public static final class SPPATH{
        /**用户本地保存*/
        public static final String USER_MSG_SPPATH = "count";
        /**自动登录*/
        public static final String AUTO_LOGIN = "autologin";
        /**用户名*/
        public static final String USER_NAME = "username";
        /**密码*/
        public static final String USER_PW = "userpw";
        /**融云token*/
        public static final String RY_TOKEN = "rongyun";
        /**用户id*/
        public static final String USERID = "userid";
        /***/
        public static final String SESSION = "session";
        /**百度云推送用户id*/
        public static final String PUSH_USER_ID ="pushUserID";
        /**百度云推送频道id*/
        public static final String PUSH_CHANNEL_ID ="channelID";
        /**手机号*/
        public static final String MOBILE ="mobile";
        /**是否已经创建店铺*/
        public static final String SHOPID = "havestore";
        /**是否有可报名比赛*/
        public static final String IS_COMP = "is_comp";
        /**分销导入商品最大数量*/
        public static final String MAX_IMPORT = "max_import";
        /***/
        public static final String HOST_NAME = "hostname";
        public static final String SHOP_AUDIT = "shop_audit";
        public static final String OPEN_TICKET = "open_ticket";
        public static final String ALLOW_PUSH = "push";
    }

    public static final class OTHER{
    }
}
