package com.zrb.baseapp.tools;

import android.util.Log;

/**
 * 日记Log
 *
 * @version 1.0
 * @date 2015-05-15
 */
public class MyLogUtils {
    private MyLogUtils() {
            /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static final String TAG = "exce";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            if (msg != null)
                if(msg.length() > 4000) {
                    for(int i=0;i<msg.length();i+=4000){
                        if(i+4000<msg.length())
                            Log.d(TAG,msg.substring(i, i+4000));
                        else
                            Log.d(TAG,msg.substring(i, msg.length()));
                    }
                } else
                    Log.d(TAG,msg);
    }

    public static void e(String msg) {
        if (isDebug)
            if (msg != null)
                if(msg.length() > 4000) {
                    for(int i=0;i<msg.length();i+=4000){
                        if(i+4000<msg.length())
                            Log.e(TAG,msg.substring(i, i+4000));
                        else
                            Log.e(TAG,msg.substring(i, msg.length()));
                    }
                } else
                    Log.e(TAG,msg);

    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }
}
