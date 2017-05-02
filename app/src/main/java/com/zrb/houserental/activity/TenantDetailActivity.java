package com.zrb.houserental.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.zrb.baseapp.base.BaseActivity;
import com.zrb.houserental.Entity.ResultTenantQueryEntity;
import com.zrb.houserental.R;
import com.zrb.houserental.util.MyTextUtil;

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

    private String phone;

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
        ResultTenantQueryEntity.LodgersBean lodgersBean = (ResultTenantQueryEntity.LodgersBean) getIntent().getSerializableExtra("LodgersBean");
        switch (lodgersBean.getStatus()) {
            case 0:
                activityTenantdetailState.setText(String.format("状态 : %s", "新租"));
                activityTenantdetailState.setTextColor(ContextCompat.getColor(this, R.color.black));
                break;
            case 1:
                activityTenantdetailState.setText(String.format("状态 : %s", "租住中"));
                activityTenantdetailState.setTextColor(ContextCompat.getColor(this, R.color.tenant_on));
                break;
            case 2:
                activityTenantdetailState.setText(String.format("状态 : %s", "退租"));
                activityTenantdetailState.setTextColor(ContextCompat.getColor(this, R.color.tenant_out));
                break;
        }
        activityTenantdetailSex.setText(String.format("性别 : %s", MyTextUtil.getSexString(lodgersBean.getSex())));

        phone = lodgersBean.getPhone();

        activityTenantdetailName.setText(String.format("姓名 : %s", lodgersBean.getName()));

        activityTenantdetailIdcare.setText(String.format("证件号码 : %s", lodgersBean.getId_card()));
        activityTenantdetailAddress.setText(String.format("房客籍贯 : %s", lodgersBean.getHometown()));
        activityTenantdetailBirthday.setText(String.format("出生日期 : %s", MyTextUtil.getDate(lodgersBean.getBirthday())));
        activityTenantdetailPhone.setText(String.format("电话号码 : %s", phone));
        activityTenantdetailFloor.setText(String.format("楼号 : %s", getIntent().getStringExtra("building_name")));
        activityTenantdetailRoom.setText(String.format("房号 : %s", lodgersBean.getRoom().getName()));
        activityTenantdetailPermitday.setText(String.format("居住证是否到期 : %s", "1".equals(lodgersBean.getResidence_permit_expire()) ? "是" : "否"));
        activityTenantdetailStartday.setText(String.format("起租日期 : %s", MyTextUtil.getDate(lodgersBean.getRent_date_start())));
        activityTenantdetailStopday.setText(String.format("结束日期 : %s", MyTextUtil.getDate(lodgersBean.getRent_date_end())));

    }


    @OnClick(R.id.activity_roomquert_confirm)
    public void onViewClicked() {
        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
