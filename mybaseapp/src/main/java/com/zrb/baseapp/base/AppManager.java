package com.zrb.baseapp.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.zrb.baseapp.tools.MyLogUtils;
import com.zrb.baseapp.tools.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;



/**
 * actvity管理器
 * @author 10028
 * @date 2015-05-15
 */
public class AppManager {

	private static Stack<Activity> activityStack;
	private static AppManager instance;
	public static Context applicationContext;
	public static String version;
	public static String versionCode;

	public static Context getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(Context applicationContext) {
		AppManager.applicationContext = applicationContext;
	}

	private AppManager() {

	}
	/**
	 * 获取版本号
	 */
	public static String getVersion() {
		if (!TextUtil.isEmptyString(version)){
			return version;
		}
		try {
			PackageManager manager = getApplicationContext().getPackageManager();
			PackageInfo info = manager.getPackageInfo(getApplicationContext().getPackageName(), 0);
			 version = info.versionName;
			return  version;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 获取版本号
	 */
	public static String getVersionCode() {
		if (!TextUtil.isEmptyString(versionCode)) {
			return versionCode;
		}
		try {
			PackageManager manager = getApplicationContext().getPackageManager();
			PackageInfo info = manager.getPackageInfo(getApplicationContext().getPackageName(), 0);
			versionCode = info.versionCode + "";
			return versionCode;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 单一实例
	 */
	public static AppManager getAppManager() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);

		Log.e("all:", activityStack+"");
	}

	public int getActivityStackSize(){
		return activityStack.size();

	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 回到某个页面
	 *
	 * @param cls
	 */
	public void goActivity(Class<?> cls) {
		try {
			List<Activity> activities = new ArrayList<Activity>();
			for (int i = activityStack.size() - 1; i > 0; i--) {
				Activity activity = activityStack.get(i);
				MyLogUtils.e(activity.getClass().toString() + "~~" + cls.toString());
				if (activity != null && !activity.getClass().equals(cls)) {
					activities.add(activity);
					activity.finish();
					activity = null;
				} else {
					break;
				}
			}
			for (int i = 0; i < activities.size(); i++) {
				activityStack.remove(activities.get(i));
			}
			activities.clear();
			activities = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		try {
			if (activity != null) {
				try {
					activityStack.remove(activity);
					activity.finish();
					activity = null;
					Log.e("activity:", currentActivity()+"");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移除指定的Activity 并未进行finish操作
	 */
	public void removeActivity(Activity activity) {
		try {
			if (activity != null) {
				activityStack.remove(activity);
				activity = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		try {
			for (Activity activity : activityStack) {
				if (activity.getClass().equals(cls)) {
					finishActivity(activity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 结束所有Activity
	 */
	public static void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				System.out.print("--------" + activityStack.size());
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
		}
	}



}