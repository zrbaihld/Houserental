package com.zrb.houserental.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.constant.Constant_C;
import com.zrb.baseapp.tools.JsonParsing;
import com.zrb.baseapp.tools.MyHttpTool;
import com.zrb.baseapp.tools.TextUtil;
import com.zrb.houserental.Entity.LoginEntity;
import com.zrb.houserental.R;
import com.zrb.houserental.constant.URL_Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/31.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.activity_login_et_phone)
    EditText activityLoginEtPhone;
    @BindView(R.id.activity_login_et_password)
    EditText activityLoginEtPassword;
    @BindView(R.id.activity_login_btn_login)
    AppCompatButton activityLoginBtnLogin;

    @Override
    public void init() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        activityLoginEtPhone.setText("root");
        activityLoginEtPassword.setText("123456");
    }

    @Override
    public void setListenner() {

    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {

    }


    @OnClick(R.id.activity_login_btn_login)
    public void onViewClicked() {
        String phone = activityLoginEtPhone.getText().toString();
        String password = activityLoginEtPassword.getText().toString();
        if (TextUtil.isEmptyString(phone)) {
            toastIfActive("请输入手机号");
            return;
        }
        if (TextUtil.isEmptyString(password)) {
            toastIfActive("请输入密码");
            return;
        }
        MyHttpTool.creat(this)
                .setContent("passwd", password)
                .setContent("phone", phone)
                .postShowDialog(0, URL_Constant.Login, this);

//
    }

    @Override
    public boolean getIOAuthCallBack(int type, String json, boolean isSuccess) {
        if (super.getIOAuthCallBack(type, json, isSuccess)) return true;
        switch (type) {
            case 0:
                if (JsonParsing.getState(json)) {
                    sp.edit().putString("Login_response", json).
                            commit();
                    startActivity(new Intent(this, MainActivity.class));
                }


                break;
        }

        return false;
    }
}
