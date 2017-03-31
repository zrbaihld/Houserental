package com.zrb.baseapp.tools;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import com.zrb.baseapp.constant.Constant_C;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileUtils {
	private static String TAG = "FileUtils";
	private static StringBuffer tempPhoneFileName; // 保存照片的文件名称

	/**
	 * 获取文件名 格式为：20140718_221839.jpg
	 * @return
	 */
	public static String getFileName(){
		String name = getSysTime();
		String[] names = name.replaceAll(" ", "#").split("#");
		String dateStr = names[0].replaceAll("-", "");
		String timeStr = names[1].replaceAll(":", "");
		tempPhoneFileName = new StringBuffer();
		tempPhoneFileName.append(dateStr);
		tempPhoneFileName.append("_");
		tempPhoneFileName.append(timeStr);
		tempPhoneFileName.append(".jpg");
		return tempPhoneFileName.toString();
	}
	
	/**
	 * 获取Bitmap大小(在内存中占的大小是文件本身大小的4倍，所以计算实际大小时 /4)
	 * @param bitmap
	 * @return
	 */
	@SuppressLint("NewApi")
	public static int getBitmapSize(Bitmap bitmap){
		if(bitmap == null){
			return 0;
		}
		int bitmapSize = 0;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			bitmapSize = bitmap.getByteCount();
		} else {
			bitmapSize = bitmap.getRowBytes() * bitmap.getHeight(); // HC-MR1 以前
		}
		return bitmapSize;
	}
	
	/**
	 * 保存图片文件
	 */
	public static String saveMyBitmap(Bitmap mBitmap, Context context){
		  File f = new File(Constant_C.SAVEPATH.LOADIMGPATH + getFileName());
		  try {
		   f.createNewFile();
		  } catch (IOException e) {
		  }
		  FileOutputStream fOut = null;
		  try {
		   fOut = new FileOutputStream(f);
		  } catch (FileNotFoundException e) {
		   e.printStackTrace();
		  }
		  mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		// 其次把文件插入到系统图库
			try {
				MediaStore.Images.Media.insertImage(context.getContentResolver(),f.getAbsolutePath(), getFileName(), null);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			// 最后通知图库更新
			context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+ f.getPath())));
		  
		  try {
		   fOut.flush();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  try {
		   fOut.close();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		return f.getPath();
		 }
	
	//////////////////////
	
	/**
	 * 复制文件
	 * 
	 * @param savePath 要保存到哪里
	 */
	public static void copyFile(InputStream is, File savePath){
		if(is == null && savePath == null){
			printLogi("复制文件时缺少参数 FileUtils.copyFile()");
			return;
		}
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(is);
			if(!savePath.exists()){
				savePath.mkdirs();
			}
			out = new BufferedOutputStream(new FileOutputStream(savePath));
			byte[] bytes = new byte[1024];
			int by = 0;
			while ((by = in.read(bytes)) != -1) {
				out.write(bytes, 0, by);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeIn(in, is);
			closeOut(out);
		}
	}
	
	public static String getSysTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * 复制文件
	 * 
	 * @param is 要复制的文件InputStream
	 * @param savePath 要保存到哪里 String类型
	 */
	public static void copyFile(InputStream is, String savePath){
		if(is == null && savePath == null){
			printLogi("复制文件时缺少参数 FileUtils.copyFile()");
			return;
		}
		Log.e(TAG, "开始复制文件");
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(is);
			File sP = new File(Environment.getExternalStorageDirectory(), savePath);
			long length = sP.length();
			if(sP.exists()){
				sP.delete();
			}
			sP.createNewFile();
			out = new BufferedOutputStream(new FileOutputStream(sP));
			byte[] bytes = new byte[1024];
			int by = 0;
			while ((by = in.read(bytes)) != -1) {
				out.write(bytes, 0, by);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeIn(in, is);
			closeOut(out);
		}
	}
	
	private static void printLogi(String msg){
		Log.e(TAG, msg);
	}

	/**
	 * 关闭输入流
	 * @param ins 输入流数组
	 */
	public static void closeIn(InputStream... ins){
		try {
			for (int i = 0; i < ins.length; i++) {
				if (ins[i] != null) {
					ins[i].close();
					ins[i] = null;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭输出流
	 * @param outs 输出流数组
	 */
	public static void closeOut(OutputStream... outs){
		try {
			for (int i = 0; i < outs.length; i++) {
				if (outs[i] != null) {
					outs[i].close();
					outs[i] = null;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载本地图片
	 * @param url
	 * @return
	 */
	public static Bitmap getLoacalBitmap(String url){
		if (url == null) {
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 加载本地图片
	 * @return
	 */
	public static Bitmap getLoacalBitmap(InputStream is){
		if (is == null) {
			return null;
		}
		return BitmapFactory.decodeStream(is);  // 把流转化为Bitmap图片
	}
	/**
	 * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
	 *
	 * @param context
	 * @param uri
	 * @author yaoxing
	 * @date 2014-10-12
	 */
	// 以下是关键，原本uri返回的是file:///...来着的，android4.4返回的是content:///...
	@SuppressLint("NewApi")
	public static String getPath(final Context context, final Uri uri) {

		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/"
							+ split[1];
				}

			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {
				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));

				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				return getDataColumn(context, contentUri, selection,
						selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {
			// Return the remote address
			if (isGooglePhotosUri(uri))
				return uri.getLastPathSegment();

			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 *
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @param selection
	 *            (Optional) Filter used in the query.
	 * @param selectionArgs
	 *            (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri,
									   String selection, String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try {
			cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}
	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri
				.getAuthority());
	}

}


