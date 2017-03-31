package com.zrb.baseapp.tools;

import android.content.Context;
import android.os.Environment;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import java.io.File;

public class ClearData {
	/*
	 *  * 文 件 名: DataCleanManager.java * 描 述:
	 * 主要功能有清除内/外缓存，清除数据库，清除sharedPreference，清除files和清除自定义目录
	 */
	/** * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context */
	private static final String APP_CACAHE_DIRNAME = "/webcache";
	
	public static void cleanInternalCache(Context context) {
		deleteFilesByDirectory(context.getCacheDir());
	}

	/** * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * @param context */
	public static void cleanDatabases(Context context) {
		deleteFilesByDirectory(new File("/data/data/"
				+ context.getPackageName() + "/databases"));
	}

	/**
	 * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) * * @param
	 * context
	 */
	public static void cleanSharedPreference(Context context) {
		deleteFilesByDirectory(new File("/data/data/"
				+ context.getPackageName() + "/shared_prefs"));
	}

	/** * 按名字清除本应用数据库 * * @param context * @param dbName */
	public static void cleanDatabaseByName(Context context, String dbName) {
		context.deleteDatabase(dbName);
	}

	/** * 清除/data/data/com.xxx.xxx/files下的内容 * * @param context */
	public static void cleanFiles(Context context) {
		deleteFilesByDirectory(context.getFilesDir());
	}

	/**
	 * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
	 * context
	 */
	public static void cleanExternalCache(Context context) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			deleteFilesByDirectory(context.getExternalCacheDir());
		}
	}

	/** * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * @param filePath */
	public static void cleanCustomCache(String filePath) {
		deleteFilesByDirectory(new File(filePath));
	}

	/** * 清除本应用所有的数据 * * @param context * @param filepath */
	public static void cleanApplicationData(Context context, String... filepath) {
		cleanInternalCache(context);
		cleanExternalCache(context);
		cleanDatabases(context);
		// cleanSharedPreference(context);
		cleanFiles(context);
		for (String filePath : filepath) {
			cleanCustomCache(filePath);
		}
		clearWebViewCache(context);
	}

	/** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
	private static void deleteFilesByDirectory(File directory) {
		if (directory != null && directory.exists() && directory.isDirectory()) {
			for (File item : directory.listFiles()) {
				item.delete();
			}
		}
	}

	/**
	 * 清除WebView缓存
	 */
	public static void clearWebViewCache(Context context) {
		// WebView 缓存文件
		File appCacheDir = new File(context.getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME);
		if (appCacheDir.exists()) {
			deleteFile(appCacheDir);
		}// 清除cookie即可彻底清除缓存
		CookieSyncManager.createInstance(context);
		CookieManager.getInstance().removeAllCookie();
	}
	/**
	 * 递归删除 文件/文件夹
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		}
	}

//	public void clearWebViewCache(Context context) {
//		// 清除cookie即可彻底清除缓存
//		CookieSyncManager.createInstance(context);
//		CookieManager.getInstance().removeAllCookie();
//		
////		CookieSyncManager.createInstance(this); 
////		CookieSyncManager.getInstance().startSync(); 
////		CookieManager.getInstance().removeSessionCookie();
//		}
	
}
