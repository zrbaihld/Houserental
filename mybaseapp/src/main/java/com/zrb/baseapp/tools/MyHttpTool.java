package com.zrb.baseapp.tools;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.google.gson.GsonBuilder;
import com.zrb.baseapp.base.AppManager;
import com.zrb.baseapp.constant.Constant_C;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Administrator on 2016/3/1.
 */
public class MyHttpTool {
    private MediaType MEDIA_TYPE_PLAIN = MediaType.parse("application/json;charset=utf-8");
    private static MyHttpTool myHttp;
    private HashMap<String, String> map;
    private HashMap<String, File> fileMap;
    private OkHttpClient client = new OkHttpClient();
    private Context mContext;
    private Dialog dialog;
    private Handler handler;

    private MyHttpTool() {

    }

    public static MyHttpTool creat(Context context) {
        if (myHttp == null) {
            myHttp = new MyHttpTool();
            myHttp.map = new HashMap<String, String>();
        }
        myHttp.mContext = context;
        myHttp.map.clear();
        if (!TextUtil.isEmptyString(Constant_C.TOKEN))
            myHttp.map.put("token", Constant_C.TOKEN);
        if (!TextUtil.isEmptyString(Constant_C.AID))
            myHttp.map.put("aid", Constant_C.AID);

        myHttp.handler = new Handler(context.getMainLooper());
        return myHttp;
    }

    public MyHttpTool setUserInfor(String... infors) {
        myHttp.map.put("user_id", infors[0]);
        if (infors.length == 2) {
            myHttp.map.put("session_id", infors[1]);
        }
        return myHttp;
    }

    public MyHttpTool setContent(String key, String value) {
        myHttp.map.put(key, value);
        return myHttp;
    }

    public MyHttpTool setContent(String key, int value) {
        myHttp.map.put(key, "" + value);
        return myHttp;
    }

    public MyHttpTool setFile(String key, File file) {
        if (myHttp.fileMap == null) myHttp.fileMap = new HashMap<>();
        fileMap.put(key, file);
        return myHttp;
    }

    public void postShowDialog(final int type, String url, final IOAuthCallBack ioCallBack) {
        handler.post(showDialogRun);

        GsonBuilder gb = new GsonBuilder();
        RequestBody body = RequestBody.create(MEDIA_TYPE_PLAIN, gb.create().toJson(map));

        MyLogUtils.d(gb.create().toJson(map));
        MyLogUtils.d(url);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        MyLogUtils.d(request.header("content-type"));
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(dismissDialogRun);
                MyLogUtils.d(e.getMessage() + "");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (ioCallBack != null)
                            ioCallBack.getIOAuthCallBack(type, e.getMessage(), false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
                MyLogUtils.d(response.toString() + s);
                handler.post(dismissDialogRun);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (ioCallBack != null)
                            ioCallBack.getIOAuthCallBack(type, s, true);
                    }
                });
            }
        });
    }

    public void post(final int type, String url, final IOAuthCallBack callback) {
        GsonBuilder gb = new GsonBuilder();
        RequestBody body = RequestBody.create(MEDIA_TYPE_PLAIN, gb.create().toJson(map));
        MyLogUtils.d(map.toString());
        MyLogUtils.d(url);
        Request request = new Request.Builder().addHeader("content-type", "application/json")
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, final IOException e) {
                MyLogUtils.d(e.getMessage());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null)
                            callback.getIOAuthCallBack(type, e.getMessage(), false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
                MyLogUtils.d(response.toString() + s);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null)
                            callback.getIOAuthCallBack(type, s, true);
                    }
                });
            }
        });
    }


    /**
     * 文件上传，暂没做实现多个文件上传
     *
     * @param type
     * @param url
     * @param callback
     */
    public void postFileShowDialog(final int type, String url, final IOAuthCallBack callback) {
        handler.post(showDialogRun);

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map != null && !map.isEmpty()) {
            for (String key : map.keySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""), RequestBody.create(null, map.get(key)));
            }
        }
        if (fileMap != null && !fileMap.isEmpty()) {
            for (String key : fileMap.keySet()) {
                okhttp3.RequestBody fileBody = okhttp3.RequestBody.create(null, fileMap.get(key));
                builder.addFormDataPart(key, "1", fileBody);
            }
        }

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                fileMap.clear();
                MyLogUtils.d(e.getMessage());

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        if (callback != null)
                            callback.getIOAuthCallBack(type, e.getMessage(), false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                fileMap.clear();
                final String s = response.body().string();
                MyLogUtils.d(response.toString() + s);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        if (callback != null)
                            callback.getIOAuthCallBack(type, s, false);
                    }
                });
            }
        });
    }


    /**
     * 加载进度
     *
     * @param context
     * @return
     */
    public Dialog creatDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ProgressBar progressBar = new ProgressBar(context);
        dialog.setContentView(progressBar);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        if (dialog != null) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * @param dialog
     */
    public void dissmissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    Runnable showDialogRun = new Runnable() {
        @Override
        public void run() {
            dialog = creatDialog(mContext);
        }
    };
    Runnable dismissDialogRun = new Runnable() {
        @Override
        public void run() {
            try {
                dialog.dismiss();
            }catch (Exception e){

            }

        }
    };

    public interface IOAuthCallBack {
        boolean getIOAuthCallBack(int type, String json, boolean isSuccess);
    }

    public void get(final int type, String url, final IOAuthCallBack callback, final ImageView iv) {
        Request request = new Request.Builder()
                .url("http://images.csdn.net/20150817/1.jpg")
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, final IOException e) {
                Log.e("2222222222222", "22222222222222222");
                MyLogUtils.d(e.getMessage());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null)
                            callback.getIOAuthCallBack(type, e.getMessage(), false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.e("1111111111111111", "111111111111111");

                final String s = response.body().string();
                MyLogUtils.d(s);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bt = BitmapFactory.decodeStream(response.body().byteStream());
                        iv.setImageBitmap(bt);
//                        if (callback!=null)
//                            callback.getIOAuthCallBack(type, s, true);
                    }
                });
            }
        });
    }

}
