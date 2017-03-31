package com.zrb.baseapp.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zrb.baseapp.R;
import com.zrb.baseapp.constant.Constant_C;
import com.zrb.baseapp.dialogpop.PromptPop;
import com.zrb.baseapp.tools.JsonParsing;
import com.zrb.baseapp.tools.MyHttpTool;


@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment implements MyHttpTool.IOAuthCallBack {
    /**
     * 上下文
     */
    protected Context context;
    /**
     * activity
     */
    protected Activity activity;
    protected Gson gson;
    protected View view;

    protected String Session;
    protected String Uid;
    private View onCreateView;
    protected int pager = Constant_C.PAGER;
    protected int index = Constant_C.INDEX;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        gson = new Gson();
        context = getActivity();
        activity = getActivity();
        Session = ((BaseActivity) activity).Session;
        Uid = ((BaseActivity) activity).Uid;
        super.onCreate(savedInstanceState);
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onCreateView = super.onCreateView(inflater, container, savedInstanceState);
        init();
        setListenner();
        getData();
        return onCreateView;
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
            promptPop.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);

            return true;
        } else if (!JsonParsing.getState(json) && !JsonParsing.getStateIsEmpty(json)) {
            toastIfActive("" + JsonParsing.getMsg(json));
            return true;
        }
        return false;
    }

    @Override
    public void onStart() {
        Log.e("BaseFragment", "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e("BaseFragment", "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        Log.e("BaseFragment", "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        Log.e("BaseFragment", "onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.e("BaseFragment", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.e("BaseFragment", "onDestroyView");
        super.onDestroyView();
    }

    /**
     * toast打印从资源文件获取
     */
    private Toast mToast;

    public void toastIfActive(String s) {
//        if (!this.mIsPause) {
        if (mToast == null) {
            mToast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        } else {
            mToast.setText(s);
        }
        mToast.show();
//        }
    }

    protected void setListViewNullDate(boolean isVisitionImg) {
        if (view != null && view.findViewById(R.id.view_iv) != null) {
            view.findViewById(R.id.view_iv).setVisibility(isVisitionImg ? View.VISIBLE : View.GONE);
            view.findViewById(R.id.view_listview).setVisibility(isVisitionImg ? View.GONE : View.VISIBLE);
        }
    }

    protected <V extends View> V findViewById(int id) {
        return (V)view.findViewById(id);
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
     * 获取数据
     */
    public abstract void getData();
}
