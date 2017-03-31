package com.zrb.baseapp.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zrb.baseapp.base.AppManager;

/**
 * Created by Administrator on 2016/3/9.
 */
public class MyImageTool {

    public static void getImg(Context context, final ImageView view, final String Url) {
        MyLogUtils.e("getImg Url" + Url);
        Glide.with(context).load(Url).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                view.setImageBitmap(resource);
                MyLogUtils.e("getImg sucess");
                view.setImageDrawable(resource);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                MyLogUtils.e("getImg e" + e.toString());
            }
        });
    }

//    public static void getImg(Context context, final ImageView view, final String Url) {
//        MyLogUtils.e("getImg Url" + Url);

//        Glide.with(context).load(Url).listener(new RequestListener<String, GlideDrawable>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                MyLogUtils.e("getImg e" + e.toString());
//            }
//

//            @Override
//            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                MyLogUtils.e("getImg success");
//                MyLogUtils.e("getImg isFromMemoryCache" + isFromMemoryCache);
//                MyLogUtils.e("getImg isFirstResource" + isFirstResource);
//                return false;
//            }
//
//        }).into(view);
//    }

    public static void getImg(ImageView view, String Url) {
        Glide.with(AppManager.applicationContext).load(Url).into(view);
    }

    public static void getroundImg(ImageView view, String Url) {
        Glide.with(AppManager.applicationContext).load(Url).asBitmap()
                .transform(new GlideCircleTransform(AppManager.applicationContext, 3))
                .into(view);
    }

    public static void getvagueImg(final View view, String Url) {
        Glide.with(AppManager.applicationContext).load(Url).asBitmap()
                .transform(new VagueTransform(AppManager.applicationContext))
                .into(new SimpleTarget<Bitmap>(100, 100) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        view.setBackground(new BitmapDrawable(bitmap));
                    }
                });
    }
}
