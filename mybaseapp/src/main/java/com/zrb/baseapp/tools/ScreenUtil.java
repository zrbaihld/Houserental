package com.zrb.baseapp.tools;


import com.zrb.baseapp.base.AppManager;

/**
 * Created by Administrator on 2016/4/7 0007.
 */
public class ScreenUtil {


    public  static int getscreenY(){
        return (AppManager.getApplicationContext().getResources().getDisplayMetrics().heightPixels);
    }
    public  static int getscreenX(){
        return (AppManager.getApplicationContext().getResources().getDisplayMetrics().widthPixels);
    }

    public  static int   getXpx(int sizex){
        return getscreenX()*sizex/720;
    }
    public  static int   getYpx(int sizex){
        return getscreenY()*sizex/1280;
    }
}
