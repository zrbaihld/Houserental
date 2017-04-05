package com.zrb.houserental.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.tools.TextUtil;
import com.zrb.houserental.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/1.
 */

public class ChangePassWordActivity extends BaseActivity {

    @BindView(R.id.activity_changepassword_old)
    EditText activityChangepasswordOld;
    @BindView(R.id.activity_changepassword_new)
    EditText activityChangepasswordNew;
    @BindView(R.id.activity_changepassword_renew)
    EditText activityChangepasswordRenew;
    @BindView(R.id.activity_changepassword_confirm)
    AppCompatButton activityChangepasswordConfirm;

    @Override
    public void init() {
        addConView(R.layout.activity_changepassword);
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

    @OnClick(R.id.activity_changepassword_confirm)
    public void onViewClicked() {
        String s_old=activityChangepasswordOld.getText().toString();
        if (TextUtil.isEmptyString(s_old)){
            toastIfActive("请输入原密码");
            return;
        }
        String s_new=activityChangepasswordNew.getText().toString();
        if (TextUtil.isEmptyString(s_new)){
            toastIfActive("请输入新密码");
            return;
        }
        String s_renew=activityChangepasswordRenew.getText().toString();
        if (TextUtil.isEmptyString(s_renew)){
            toastIfActive("请再次输入新密码");
            return;
        }


    }
}
