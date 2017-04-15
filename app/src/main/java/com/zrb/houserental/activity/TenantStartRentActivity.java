package com.zrb.houserental.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.base.BaseRecyclerViewAdapter;
import com.zrb.baseapp.tools.JsonParsing;
import com.zrb.baseapp.tools.MyHttpTool;
import com.zrb.baseapp.tools.MyLogUtils;
import com.zrb.baseapp.tools.TextUtil;
import com.zrb.houserental.Entity.FloorEntity;
import com.zrb.houserental.Entity.LoginEntity;
import com.zrb.houserental.Entity.RoomEntity;
import com.zrb.houserental.R;
import com.zrb.houserental.constant.URL_Constant;
import com.zrb.houserental.dialog.DialogUntil;
import com.zrb.houserental.dialog.SelectFloorDialog;
import com.zrb.houserental.util.MyTextUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/1.
 */

public class TenantStartRentActivity extends BaseActivity {
    @BindView(R.id.activity_startrent_floor_tv)
    TextView activityAddtenantFloorTv;
    @BindView(R.id.activity_startrent_room_tv)
    TextView activityAddtenantRoomTv;
    @BindView(R.id.activity_startrent_no_tv)
    TextView activityAddtenantNoTv;
    @BindView(R.id.activity_startrent_unit_tv)
    TextView activityAddtenantUnitTv;
    @BindView(R.id.activity_startrent_deposit_tv)
    EditText activityAddtenantDepositTv;
    @BindView(R.id.activity_startrent_advancemonths_tv)
    TextView activityAddtenantAdvancemonthsTv;
    @BindView(R.id.activity_startrent_water_tv)
    TextView activityAddtenantWaterTv;
    @BindView(R.id.activity_startrent_power_tv)
    TextView activityAddtenantPowerTv;
    @BindView(R.id.activity_startrent_startday_tv)
    TextView activityAddtenantStartdayTv;
    @BindView(R.id.activity_startrent_endday_tv)
    TextView activityAddtenantEnddayTv;
    @BindView(R.id.activity_startrent_waterstart_tv)
    EditText activityAddtenantWaterstartTv;
    @BindView(R.id.activity_startrent_powerstart_tv)
    TextView activityAddtenantPowerstartTv;
    @BindView(R.id.activity_startrent_remark_tv)
    EditText activityAddtenantRemarkTv;
    @BindView(R.id.activity_startrent_allprice_tv)
    TextView activityAddtenantAllpriceTv;
    @BindView(R.id.activity_startrent_status_tv)
    TextView activityAddtenantStatusTv;
    @BindView(R.id.activity_startrent_key_tv)
    EditText activityAddtenantKeyTv;
    @BindView(R.id.activity_startrent_phone_tv)
    EditText activityAddtenantPhoneTv;
    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;
    @BindView(R.id.scrollview)
    ScrollView scrollView;


    private List<FloorEntity> itemEntities;
    private int type = -1;

    private String building_id = "";
    private String building_name = "";
    private String room_id = "";
    private String room_name = "";
    private RoomEntity roomEntity;

    @Override
    public void init() {
        addConView(R.layout.activity_startrent);
        ButterKnife.bind(this);
        titleTV.setText("房客起租");
        itemEntities = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        activityAddtenantNoTv.setText(simpleDateFormat.format(new Date()));

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


    @OnClick({R.id.activity_startrent_floor, R.id.activity_startrent_room, R.id.activity_startrent_advancemonths, R.id.activity_startrent_startday, R.id.activity_roomquert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_startrent_floor:
                type = 0;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityAddtenantFloorTv.setText(entity.getName());
                        activityAddtenantRoomTv.setText("请选择房号");
                        scrollView.setVisibility(View.GONE);
                        building_id = entity.getId();
                        building_name = entity.getName();
                        itemEntities.clear();
                        itemEntities.add(entity);

                    }
                });
                break;
            case R.id.activity_startrent_room:
                if (itemEntities == null) {
                    toastIfActive("请先选择楼号");
                    break;
                }
                type = 1;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        room_id = entity.getId();
                        room_name = entity.getName();
                        activityAddtenantRoomTv.setText(entity.getName());
                        MyHttpTool.creat(TenantStartRentActivity.this)
                                .setContent("building_id", building_id)
                                .setContent("room_id", room_id)
                                .postShowDialog(0, URL_Constant.room, TenantStartRentActivity.this);

                    }
                });
                break;
            case R.id.activity_startrent_advancemonths:
                List<FloorEntity> itemEntities = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("月数" + i);
                    itemEntities.add(itemEntity);
                }
                type = 2;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityAddtenantAdvancemonthsTv.setText(entity.getName());
                        activityAddtenantEnddayTv.setText(
                                MyTextUtil.getEndDate(activityAddtenantStartdayTv.getText().toString(),
                                        activityAddtenantAdvancemonthsTv.getText().toString())
                        );
                        activityAddtenantAllpriceTv.setText(String.format("￥%s",
                                MyTextUtil.totlePrice(activityAddtenantUnitTv.getText().toString(),
                                        activityAddtenantAdvancemonthsTv.getText().toString()))

                        );
                    }
                });

                break;
            case R.id.activity_startrent_startday:
                DialogUntil.getInstance().selectDate(getSupportFragmentManager(), new DialogUntil.DialogUtilDateDao() {
                    @Override
                    public void onPositiveActionClicked(String date) {
                        if (!TextUtil.isEmptyString(date)) {
                            activityAddtenantStartdayTv.setText(date);
                            activityAddtenantEnddayTv.setText(
                                    MyTextUtil.getEndDate(activityAddtenantStartdayTv.getText().toString(),
                                            activityAddtenantAdvancemonthsTv.getText().toString())
                            );
                            activityAddtenantAllpriceTv.setText(String.format("￥%s",
                                    MyTextUtil.totlePrice(activityAddtenantUnitTv.getText().toString(),
                                            activityAddtenantAdvancemonthsTv.getText().toString()))

                            );
                        }
                    }
                });

                break;
            case R.id.activity_roomquert_confirm://租金收取并登记
                sandmessage();
                break;
        }
    }

    private void sandmessage() {
        String s_floor = activityAddtenantFloorTv.getText().toString();
        String s_room = activityAddtenantRoomTv.getText().toString();
        String s_no = activityAddtenantNoTv.getText().toString();//自动获取
        String s_unit = activityAddtenantUnitTv.getText().toString();//自动获取
        String s_deposit = activityAddtenantDepositTv.getText().toString();
        String s_months = activityAddtenantAdvancemonthsTv.getText().toString();
        String s_water = activityAddtenantWaterTv.getText().toString();//自动获取
        String s_power = activityAddtenantPowerTv.getText().toString();//自动获取
        String s_startday = activityAddtenantStartdayTv.getText().toString();
        String s_endday = activityAddtenantEnddayTv.getText().toString();//自动计算
        String s_waterstaer = activityAddtenantWaterstartTv.getText().toString();
        String s_powerstart = activityAddtenantPowerstartTv.getText().toString();
        String s_remark = activityAddtenantRemarkTv.getText().toString();
        String s_price = activityAddtenantAllpriceTv.getText().toString();//自动计算
        String s_key = activityAddtenantKeyTv.getText().toString();
        String s_phone = activityAddtenantPhoneTv.getText().toString();

        if (s_floor.equals("请选择楼号")) {
            toastIfActive("还未选择楼号");
            return;
        }
        if (s_room.equals("请选择房号")) {
            toastIfActive("还未选择楼号");
            return;
        }
        if (TextUtil.isEmptyString(s_deposit)) {
            toastIfActive("还未填写出租押金");
            return;
        } else {
            if (!MyTextUtil.textIsNumber(s_deposit)) {
                toastIfActive("出租押金必须大于0");
                return;
            }
        }
        if (s_months.equals("请选择预付月数")) {
            toastIfActive("还未选择预付月数");
            return;
        }
        if (s_startday.equals("请选择起始日期")) {
            toastIfActive("还未选择起始日期");
            return;
        }

        if (TextUtil.isEmptyString(s_waterstaer)) {
            toastIfActive("还未填写起始水表");
            return;
        } else {
            if (!MyTextUtil.textIsNumber(s_waterstaer)) {
                toastIfActive("起始水表必须大于0");
                return;
            }
        }
        if (TextUtil.isEmptyString(s_powerstart)) {
            toastIfActive("还未选择填写起始电表");
            return;
        } else {
            if (!MyTextUtil.textIsNumber(s_powerstart)) {
                toastIfActive("起始电表必须大于0");
                return;
            }
        }
        if (TextUtil.isEmptyString(s_remark)) {
            s_remark = "新租";
        }
        if (TextUtil.isEmptyString(s_key)) {
            toastIfActive("还未输入钥匙卡个数");
            return;
        } else {
            if (!MyTextUtil.textIsNumber(s_key)) {
                toastIfActive("钥匙卡个数必须大于0");
                return;
            }
        }
        if (TextUtil.isEmptyString(s_phone)) {
            toastIfActive("还未输入接收手机");
            return;
        } else {
            if (!TextUtil.isPhoneNumber(s_phone)) {
                toastIfActive("接收手机格式错误");
                return;
            }
        }
//        building_id |int| 是 | 12 | 楼号id
//        room_id |int| 是 | 12 | 房号id
//        number |string| 是 | 20171021| 出租编号
//        user_id | int | 是 | 12 | 租客id
//        months | int | 是 | 2 | 预付月数
//        `rent_date_start` | date | 是 | 12 | 出租日期
//        `rent_date_end` | date | 是 | 12 | 结束日期
//        water | float | 是 | 12 | 初始水表
//        electric |float| 是 | 12 | 初始电表
//        deposit |int| 是 | 12 | 押金
//        remark |string| 是 | 12 | 备注
//        phone | string | 是 | 6 | 接受号码
//        keys | int | 是 | 6 | 要是个数
//        total_fee | float | 是 | 66.89 | 应收金额两位小数

        MyHttpTool.creat(this)
                .setContent("building_id", building_id)
                .setContent("room_id", room_id)
                .setContent("number", s_no)
//                .setContent("user_id", user_id)
                .setContent("months", MyTextUtil.getNumberFromString(s_months))
                .setContent("rent_date_start", s_startday)
                .setContent("rent_date_end", s_endday)
                .setContent("water", s_waterstaer)
                .setContent("electric", s_powerstart)
                .setContent("deposit", s_deposit)
                .setContent("remark", s_remark)
                .setContent("phone", s_phone)
                .setContent("keys", s_key)
                .setContent("total_fee", s_price)
                .postShowDialog(1, URL_Constant.initRent, this);

    }

    @Override
    public boolean getIOAuthCallBack(int type, String json, boolean isSuccess) {
        if (super.getIOAuthCallBack(type, json, isSuccess)) {
            if (type == 0)
            scrollView.setVisibility(View.GONE);
            return true;
        }
        switch (type) {
            case 0:
                roomEntity = gson.fromJson(JsonParsing.getData(json), RoomEntity.class);
                if (roomEntity != null && roomEntity.getRoom() != null && roomEntity.getRoom().getLodger() != null) {
                    scrollView.setVisibility(View.VISIBLE);
                    activityAddtenantUnitTv.setText(String.format("￥ %s", roomEntity.getRoom().getRental()));
                    activityAddtenantWaterTv.setText(String.format("￥ %s/吨", roomEntity.getRoom().getWater_rate()));
                    activityAddtenantPowerTv.setText(String.format("￥ %s/度", roomEntity.getRoom().getElectric_rate()));
                    activityAddtenantStatusTv.setText(String.format("%s", MyTextUtil.getStatusString(roomEntity.getRoom().getStatus())));
                } else {
                    scrollView.setVisibility(View.GONE);
                }
                break;
            case 1:
                activityAddtenantStatusTv.setText(String.format("%s", MyTextUtil.getStatusString(1)));
                String login_response = sp.getString("Login_response", "");
               LoginEntity loginEntity = gson.fromJson(JsonParsing.getData(login_response), LoginEntity.class);
                String message_body=String.format("起租：尊敬的%s的房客您好，" +
                        "从%s至%s，合计应收%s元，其中租金%s元，押金%s元，" +
                        "水费%s元（本月水表%s，水费费率%s元/吨），" +
                        "电费%s元（本月电表%s，电费费率%s元/度），" +
                        "收租人：%s，收租日期：%s",
                        activityAddtenantRoomTv.getText().toString(),
                        activityAddtenantStartdayTv.getText().toString().replace("-","年").replace("-","月") + "日",
                        activityAddtenantEnddayTv.getText().toString().replace("-","年").replace("-","月") + "日",
                        activityAddtenantAllpriceTv.getText().toString(),
                        activityAddtenantUnitTv.getText().toString(),
                        activityAddtenantDepositTv.getText().toString(),
                        "0",
                        activityAddtenantWaterstartTv.getText().toString(),
                        activityAddtenantWaterTv.getText().toString(),
                        "0",
                        activityAddtenantPowerstartTv.getText().toString(),
                        activityAddtenantPowerTv.getText().toString(),
                        loginEntity.getAdmin().getRealname(),
                        MyTextUtil.getSimpleDateFormat().format(new Date()).replace("-","年").replace("-","月") + "日"
                        );




                MyTextUtil.sendMessage(this, activityAddtenantPhoneTv.getText().toString(), message_body);

                break;

        }

        return false;
    }

}
