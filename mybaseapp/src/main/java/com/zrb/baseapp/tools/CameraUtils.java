package com.zrb.baseapp.tools;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.zrb.baseapp.constant.Constant_C;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * 调用系统相机相册工具
 * 
 * @author Administrator
 *
 */
public class CameraUtils {
	
	private static File crop_file;
	private static Uri uri;

	private CameraUtils() {}
	
	/**
	 * 打开相机拍照
	 * @param activity
	 */
	public static void openCamera(Activity activity, int requestCode) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file = new File(Constant_C.SAVEPATH.IMGPATH, FileUtils.getFileName());
//		if (file.exists()) {
//			file.delete();
//		}
		uri = Uri.fromFile(file);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		activity.startActivityForResult(intent, requestCode);
	}
	
	/**
	 * 拍照返回结果裁剪
	 * 
	 * @param data
	 */
	public static void cameraResultCrop(Intent data, Activity activity, int aspectX, int aspectY, int outputX, int outputY, int requestCode) {
		crop_file=new File(Constant_C.SAVEPATH.IMGPATH, "crop_"+FileUtils.getFileName());//此为截图后的文件
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		if(aspectX>0&&aspectY>0){
			intent.putExtra("aspectX", aspectX); // 设置截图宽高的比例
			intent.putExtra("aspectY", aspectY);	
		}
		if(outputX>0&&outputY>0){
			intent.putExtra("outputX", outputX); // 设置宽高
			intent.putExtra("outputY", outputY);
		}
		intent.putExtra("scale", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(crop_file));
		intent.putExtra("return-data", false);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		activity.startActivityForResult(intent, requestCode);
	}
	
	/**
	 * 调用相册返回后裁剪
	 */
	public static void openPhotosCrop(Activity activity, int aspectX, int aspectY, int outputX, int outputY, int requestCode){
		crop_file=new File(Constant_C.SAVEPATH.IMGPATH, "crop_"+FileUtils.getFileName());//此为截图后的文件
		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("scale", true);// 去黑边
		// aspectX aspectY 是宽高的比例
		if(aspectX>0&&aspectY>0){
			intent.putExtra("aspectX", aspectX); // 设置截图宽高的比例
			intent.putExtra("aspectY", aspectY);	//输出是Y方向的比例
		}
		// outputX outputY 是裁剪图片宽高，切忌不要再改动下列数字，会卡死
		if(outputX>0&&outputY>0){
			intent.putExtra("outputX", outputX);//输出X方向的像素
			intent.putExtra("outputY", outputY);
		}
		intent.putExtra("return-data", false);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(crop_file));
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", false);
		activity.startActivityForResult(intent, requestCode);
	}
	
	//获取到文件
	public static File getCropFlile(){
		return crop_file;
	}
	
	/**
	 * 设置图片 uri获取的
	 */
	@SuppressWarnings("deprecation")
	public static void setImageView(Activity activity, ImageView imgview, Uri imageUri) {
		if (imageUri == null) {
			return;
		}
		Bitmap bitmap = decodeUriAsBitmap(activity, imageUri);
		if (bitmap != null) {
			int bitmapSize = FileUtils.getBitmapSize(bitmap);
			if (bitmapSize == 0) {
				return;
			}
			Drawable drawable = new BitmapDrawable(bitmap);
			imgview.setImageDrawable(drawable);
		}
	}

	/**
	 * 保存裁剪之后的图片数据 data获取的数据  中兴手机uri解析出bitmap会为空
	 * 
	 * @param picdata
	 */
	@SuppressWarnings("deprecation")
	public static void setImageView(ImageView imgview, Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			Drawable drawable = new BitmapDrawable(photo);
			imgview.setImageDrawable(drawable);
		}
	}

	/**
	 * 将得到的uri解析为bitmap图片
	 */
	public static Bitmap decodeUriAsBitmap(Activity activity, Uri uri) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(uri));
		} catch (FileNotFoundException e) {
			// L.e("找不到文件 " + e.toString());
		}
		return bitmap;
	}
	
//	/**
//	 * 压缩图片
//	 *
//	 * @param picPath
//	 * @return 图片路径
//	 */
//	public static String compressImage(String picPath) {// 对图片进行压缩
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//
//		// 获取这个图片的宽和高
//		Bitmap bitmap = BitmapFactory.decodeFile(picPath, options);// 此时返回bm为空
//		options.inJustDecodeBounds = false;
//		// 计算缩放比
//		int be = (int) (options.outHeight / (float) 200);
//		if (be <= 0)
//			be = 1;
//		options.inSampleSize = be;
//		// 重新读入图片，注意这次要把options.inJustDecodeBounds设为false哦
//		bitmap = BitmapFactory.decodeFile(picPath, options);
//
//		File file=new File(Constant_C.SAVEPATH.IMGPATH, "compress_"+FileUtils.getFileName());
//		try {
//			FileOutputStream out = new FileOutputStream(file);
//			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
//				out.flush();
//				out.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return file.getPath();
//	}

}
