package com.zrb.baseapp.base;

import android.os.Message;

/**
 * Created by Administrator on 2016/3/28.
 */
public interface BaseActivityInterface {
    /**初始化控件*/
    void init();
    /**设置监听*/
    void setListenner();
    /**获取数据，或执行耗时操作*/
    void getData();
    void RunOnUI(Message msg);
}
