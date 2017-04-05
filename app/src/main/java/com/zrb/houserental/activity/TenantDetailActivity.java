package com.zrb.houserental.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.zrb.baseapp.base.BaseActivity;
import com.zrb.houserental.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/1.
 */

public class TenantDetailActivity extends BaseActivity {
    @BindView(R.id.activity_tenantdetail_name)
    TextView activityTenantdetailName;
    @BindView(R.id.activity_tenantdetail_sex)
    TextView activityTenantdetailSex;
    @BindView(R.id.activity_tenantdetail_state)
    TextView activityTenantdetailState;
    @BindView(R.id.activity_tenantdetail_idcare)
    TextView activityTenantdetailIdcare;
    @BindView(R.id.activity_tenantdetail_address)
    TextView activityTenantdetailAddress;
    @BindView(R.id.activity_tenantdetail_birthday)
    TextView activityTenantdetailBirthday;
    @BindView(R.id.activity_tenantdetail_phone)
    TextView activityTenantdetailPhone;
    @BindView(R.id.activity_tenantdetail_floor)
    TextView activityTenantdetailFloor;
    @BindView(R.id.activity_tenantdetail_room)
    TextView activityTenantdetailRoom;
    @BindView(R.id.activity_tenantdetail_permitday)
    TextView activityTenantdetailPermitday;
    @BindView(R.id.activity_tenantdetail_startday)
    TextView activityTenantdetailStartday;
    @BindView(R.id.activity_tenantdetail_stopday)
    TextView activityTenantdetailStopday;
    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;

    @Override
    public void init() {
        addConView(R.layout.activity_temantdetail);
        ButterKnife.bind(this);
        titleTV.setText("房客信息");
        reFreashUI();
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

    private void reFreashUI() {
        activityTenantdetailName.setText(String.format("姓名:%s", "石麒麟"));
        activityTenantdetailSex.setText(String.format("性别:%s", "男"));
        activityTenantdetailState.setText(String.format("状态:%s", "租住中"));
        activityTenantdetailState.setTextColor(ContextCompat.getColor(this, R.color.tenant_on));
        activityTenantdetailIdcare.setText(String.format("证件号码:%s", "350724199999999999"));
        activityTenantdetailAddress.setText(String.format("房客籍贯:%s", "福建省厦门市湖里区蔡塘社148号"));
        activityTenantdetailBirthday.setText(String.format("出生日期:%s", "2017-15-15"));
        activityTenantdetailPhone.setText(String.format("电话号码:%s", "15659810000"));
        activityTenantdetailFloor.setText(String.format("楼号:%s", "5号"));
        activityTenantdetailRoom.setText(String.format("房号:%s", "5号"));
        activityTenantdetailPermitday.setText(String.format("暂住证日期:%s", "2007-48-84"));
        activityTenantdetailStartday.setText(String.format("起租日期:%s", "2017-12-10"));
        activityTenantdetailStopday.setText(String.format("结束日期:%s", "2017-12-13"));

    }


    @OnClick(R.id.activity_roomquert_confirm)
    public void onViewClicked() {
    }
}
