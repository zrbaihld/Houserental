package com.zrb.houserental.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.zrb.baseapp.base.BaseActivity;
import com.zrb.houserental.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_login_btn_login)
    public void onViewClicked() {
        startActivity(new Intent(this, MainActivity.class));
    }


}
