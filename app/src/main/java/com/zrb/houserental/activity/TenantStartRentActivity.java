package com.zrb.houserental.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.base.BaseRecyclerViewAdapter;
import com.zrb.baseapp.tools.MyLogUtils;
import com.zrb.baseapp.tools.TextUtil;
import com.zrb.houserental.Entity.FloorEntity;
import com.zrb.houserental.R;
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
    @BindView(R.id.activity_startrent_key_tv)
    EditText activityAddtenantKeyTv;
    @BindView(R.id.activity_startrent_phone_tv)
    EditText activityAddtenantPhoneTv;
    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;


    private List<FloorEntity> itemEntities;
    private int type = -1;

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
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("楼号" + i);
                    itemEntities.add(itemEntity);
                }
                type = 0;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityAddtenantFloorTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_startrent_room:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("房号" + i);
                    itemEntities.add(itemEntity);
                }
                type = 1;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityAddtenantRoomTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_startrent_advancemonths:
                itemEntities.clear();
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

        MyLogUtils.e("ssss");

    }


}
