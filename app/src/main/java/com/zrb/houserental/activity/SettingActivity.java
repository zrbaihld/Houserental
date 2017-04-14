package com.zrb.houserental.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.zrb.baseapp.base.AppManager;
import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.constant.Constant_C;
import com.zrb.baseapp.tools.MyHttpTool;
import com.zrb.houserental.R;
import com.zrb.houserental.constant.URL_Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/1.
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.activity_setting_changepassword)
    AppCompatButton activitySettingChangepassword;
    @BindView(R.id.activity_setting_exitting)
    AppCompatButton activitySettingExitting;

    @Override
    public void init() {
        titleTV.setText("设置");
        addConView(R.layout.activity_setting);
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


    @OnClick({R.id.activity_setting_changepassword, R.id.activity_setting_exitting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_setting_changepassword:
                intent = new Intent(this, ChangePassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_setting_exitting:
                MyHttpTool.creat(this)
                        .setContent("token", sp.getString(Constant_C.TOKEN, ""))
                        .postShowDialog(0, URL_Constant.logout, this);
                intent = new Intent(this, LoginActivity.class);
                AppManager.finishAllActivity();
                startActivity(intent);
                break;
        }
    }
}
