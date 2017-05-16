package com.zrb.baseapp.tools;

/**
 * Created by Administrator on 2016/3/30 0030.
 */


import android.content.Context;
import android.widget.Toast;

import com.zrb.baseapp.base.AppManager;


public class XToast {
    public static void show(String msg) {
        show(AppManager.applicationContext, msg);
    }

    public static void show(int resId) {
        show(AppManager.applicationContext, resId);
    }

    public static void showLongTime(int resId) {
        showLongTime(AppManager.applicationContext, resId);
    }

    public static void showLongTime(String msg) {
        showLongTime(AppManager.applicationContext, msg);
    }

    public static Toast mToast;

    public static void show(Context context, String msg) {
        if (context==null)
            return;
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    public static void show(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
        }
        mToast.show();
    }

    public static void showLongTime(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
        }
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showLongTime(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }
}
