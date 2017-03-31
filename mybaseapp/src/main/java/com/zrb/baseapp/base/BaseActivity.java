package com.zrb.baseapp.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zrb.baseapp.R;
import com.zrb.baseapp.constant.Constant_C;
import com.zrb.baseapp.permiss.PermissionsActivity;
import com.zrb.baseapp.permiss.PermissionsChecker;
import com.zrb.baseapp.dialogpop.PromptPop;
import com.zrb.baseapp.tools.JsonParsing;
import com.zrb.baseapp.tools.MyHttpTool;
import com.zrb.baseapp.tools.MyLogUtils;


public abstract class BaseActivity extends AutoLayoutActivity implements MyHttpTool.IOAuthCallBack, BaseActivityInterface, View.OnClickListener {

    private Toast mToast;//吐司
    private boolean mIsPause;
    /**
     * 上下文
     */
    protected Context context;
    protected Activity activity;
    protected Intent intent;
    /**
     * activity
     */
    protected Gson gson = new Gson();
    public String Uid;//用户表示
    public String Session;//用户表示验证
    protected SharedPreferences sp;//缓存对象

    protected ViewGroup view;
    protected TextView titleTV;
    protected Toolbar toolbar;
    protected TextView next;
    protected Message msg;
    protected LinearLayout base_linearlayout;

    protected int pager = Constant_C.PAGER;
    protected int index = Constant_C.INDEX;
//	 "share_type": "1",  "类型(1-店铺 2-商品)"
//	  "share_to": "1",    "目标(1-微信 2-朋友圈 3-QQ好友 4-QQ空间 5-新浪微博)",
//	  "share_info": "100001",   "店铺编号 或 商品编号"
//	  "is_qrcode": "0"   "是否是二维码(1-是 0-否)"
    /**
     * 类型(1-店铺 2-商品)
     */
    public static String share_type;
    /**
     * 目标(1-微信 2-朋友圈 3-QQ好友 4-QQ空间 5-新浪微博)"
     */
    public static String share_to;
    /**
     * 店铺编号 或 商品编号
     */
    public static String share_info;
    /**
     * 是否是二维码(1-是 0-否)
     */
    public static String is_qrcode;
//	private static Dialog dialog;


    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    private PermissionsChecker mPermissionsChecker; // 权限检测器
    private static final int REQUEST_CODE = 900; // 请求码

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mPermissionsChecker = new PermissionsChecker(this);

        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        } else {
            msg = handler.obtainMessage();
            sp = getSharedPreferences(Constant_C.SPPATH.USER_MSG_SPPATH, Context.MODE_PRIVATE);
            Uid = sp.getString(Constant_C.SPPATH.USERID, "");
            Session = sp.getString(Constant_C.SPPATH.SESSION, "");
            baseInit();
            init();
            setListenner();
        }

    }

    protected void baseInit() {
        view = (ViewGroup) getLayoutInflater().from(this).inflate(R.layout.activity_base, null);
        setContentView(view);
        context = this;
        activity = this;

        base_linearlayout = findView(R.id.base_linearlayout);
        AppManager.getAppManager().addActivity(this);//添加activity
        titleTV = (TextView) findViewById(R.id.base_toolbar_title);
        next = findView(R.id.base_toolbar_right_tv);
        next.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setTitle(String title) {
        titleTV.setText(title);
    }

    protected void addConView(View layout) {
        LinearLayout.LayoutParams s = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        base_linearlayout.addView(layout, s);
    }

    protected View addConView(int layoutID) {
        LinearLayout.LayoutParams s = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        base_linearlayout.addView(getLayoutInflater().inflate(layoutID, null), s);
        return getLayoutInflater().inflate(layoutID, null);
    }

    protected void addConViewNullParams(int layoutID) {
        base_linearlayout.addView(getLayoutInflater().inflate(layoutID, null));
    }

    @Override
    protected void onStart() {
        if (!mIsPause) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getData();
                }
            }).start();
        }
        MyLogUtils.e("onStart = " + mIsPause);
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsPause = false;
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        }
    }

    @Override
    protected void onPause() {
        mIsPause = true;
        super.onPause();
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

//	@Override
//	public void onBackPressed() {
//		if(AppManager.getAppManager().getActivityStackSize() == 1){
//        	AppManager.getAppManager().finishAllActivity();
////        	AppManager.getAppManager().AppExit(this);
//        }else{
//        	AppManager.getAppManager().finishActivity(this);
//        }
//		super.onBackPressed();
//	}

    /**
     * toast打印 String；类型
     *
     * @param text
     */
    public void toastIfActive(String text) {
        if (!this.mIsPause) {
            if (mToast == null) {
                mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
                mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
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

    @Override
    public boolean getIOAuthCallBack(int type, String json, boolean isSuccess) {
        if (JsonParsing.getState(json)) {
            return false;
        } else if (!isSuccess) {
            toastIfActive("加载失败！");
            return true;
        } else if (JsonParsing.isLogin(json)) {//异地登录回登录页
            PromptPop promptPop = new PromptPop(context, new PromptPop.MyPromptPopListent() {
                @Override
                public void confirm() {
                    AppManager.getAppManager().finishAllActivity();
//                    startActivity(new Intent(activity, LoginActivity.class));
                }
            });
            promptPop.tv_title.setText(JsonParsing.getMsg(json));
            promptPop.tv_confirm.setText("确定");
            promptPop.tv_cancel.setVisibility(View.GONE);
            promptPop.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

            return true;
        } else if (!JsonParsing.getState(json) && !JsonParsing.getStateIsEmpty(json)) {
            toastIfActive("" + JsonParsing.getMsg(json));
            return true;
        }
        return false;
    }

    /**
     * toast打印从资源文件获取
     */
    public void toastIfActive(int resId) {
        if (!this.mIsPause) {
            if (mToast == null) {
                mToast = Toast.makeText(this, resId, Toast.LENGTH_SHORT);
                mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            } else {
                mToast.setText(resId);
            }
            mToast.show();
        }
    }

    /**
     * 初始化控件
     */
    public abstract void init();

    /**
     * 设置监听
     */
    public abstract void setListenner();

    /**
     * 获取数据或执行一些耗时操作
     */
    public abstract void getData();

    public <T extends View> T findView(int resId) {
        return (T) findViewById(resId);
    }


    public void RunOnUI(Message msg) {
    }

    protected Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            RunOnUI(msg);
            super.handleMessage(msg);
        }
    };
//	@Override
//	public abstract onClick(View v);

    @Override
    public abstract void onClick(View v);


    protected void setGoneView(boolean isNullDate) {
        if (isNullDate) {
            findViewById(R.id.view_iv).setVisibility(View.VISIBLE);
            findViewById(R.id.view_listview).setVisibility(View.GONE);
        } else {
            findViewById(R.id.view_iv).setVisibility(View.GONE);
            findViewById(R.id.view_listview).setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {
//            this.recreate();
            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();

            overridePendingTransition(0, 0);
            startActivity(intent);
        }

    }
}
