package com.zrb.houserental.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.tools.MyLogUtils;
import com.zrb.baseapp.tools.TextUtil;
import com.zrb.houserental.Entity.FloorEntity;
import com.zrb.houserental.R;
import com.zrb.houserental.dialog.DialogUntil;
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

public class TenantContinueRentActivity extends BaseActivity {
    @BindView(R.id.activity_continuerent_floor_tv)
    TextView activityContinuerentFloorTv;
    @BindView(R.id.activity_continuerent_room_tv)
    TextView activityContinuerentRoomTv;
    @BindView(R.id.activity_continuerent_name_tv)
    TextView activityContinuerentNameTv;
    @BindView(R.id.activity_continuerent_no_tv)
    TextView activityContinuerentNoTv;
    @BindView(R.id.activity_continuerent_deposit_tv)
    TextView activityContinuerentDepositTv;
    @BindView(R.id.activity_continuerent_unit_tv)
    TextView activityContinuerentUnitTv;
    @BindView(R.id.activity_continuerent_water_tv)
    TextView activityContinuerentWaterTv;
    @BindView(R.id.activity_continuerent_power_tv)
    TextView activityContinuerentPowerTv;
    @BindView(R.id.activity_continuerent_startday_tv)
    TextView activityContinuerentStartdayTv;
    @BindView(R.id.activity_continuerent_advancemonths_tv)
    TextView activityContinuerentAdvancemonthsTv;
    @BindView(R.id.activity_continuerent_endday_tv)
    TextView activityContinuerentEnddayTv;
    @BindView(R.id.activity_continuerent_beforewater_tv)
    TextView activityContinuerentBeforewaterTv;
    @BindView(R.id.activity_continuerent_beforewpower_tv)
    TextView activityContinuerentBeforewpowerTv;
    @BindView(R.id.activity_continuerent_nowwater_tv)
    EditText activityContinuerentNowwaterTv;
    @BindView(R.id.activity_continuerent_nowpower_tv)
    EditText activityContinuerentNowpowerTv;
    @BindView(R.id.activity_continuerent_otherin_tv)
    EditText activityContinuerentOtherinTv;
    @BindView(R.id.activity_continuerent_otherout_tv)
    EditText activityContinuerentOtheroutTv;
    @BindView(R.id.activity_continuerent_needin_tv)
    TextView activityContinuerentNeedinTv;
    @BindView(R.id.activity_continuerent_allneedin_tv)
    TextView activityContinuerentAllneedinTv;
    @BindView(R.id.activity_continuerent_remark_tv)
    EditText activityContinuerentRemarkTv;
    @BindView(R.id.activity_continuerent_receverphone_tv)
    TextView activityContinuerentReceverphoneTv;
    @BindView(R.id.activity_continuerent_otherphone_tv)
    EditText activityContinuerentOtherphoneTv;
    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;
    @BindView(R.id.activity_continuerent_usewater_tv)
    TextView activityContinuerentUsewaterTv;
    @BindView(R.id.activity_continuerent_usepower_tv)
    TextView activityContinuerentUsepowerTv;
    @BindView(R.id.activity_continuerent_waterprice_tv)
    TextView activityContinuerentWaterpriceTv;
    @BindView(R.id.activity_continuerent_powerprice_tv)
    TextView activityContinuerentPowerpriceTv;


    private List<FloorEntity> itemEntities;
    private int type = -1;//0 楼号 1房号 2预付月数 3电话

    @Override
    public void init() {
        addConView(R.layout.activity_continuerent);
        ButterKnife.bind(this);

        titleTV.setText("房客续租");
        itemEntities = new ArrayList<>();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        activityContinuerentNoTv.setText(simpleDateFormat.format(new Date()));


    }

    @Override
    public void setListenner() {
        activityContinuerentNowwaterTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                activityContinuerentUsewaterTv.setText(
                        MyTextUtil.useNum(editable.toString(),
                                activityContinuerentBeforewaterTv.getText().toString(),
                                "本月用水少于上月用水")
                );
                activityContinuerentWaterpriceTv.setText(String.format("￥%s",
                        MyTextUtil.totlePrice(activityContinuerentUsewaterTv.getText().toString(),
                                activityContinuerentWaterTv.getText().toString()))

                );
                totleGetPrice();
            }
        });
        activityContinuerentNowpowerTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                activityContinuerentUsepowerTv.setText(
                        MyTextUtil.useNum(editable.toString(),
                                activityContinuerentBeforewpowerTv.getText().toString(),
                                "本月用电少于上月用电")
                );
                activityContinuerentPowerpriceTv.setText(String.format("￥%s",
                        MyTextUtil.totlePrice(activityContinuerentUsepowerTv.getText().toString(),
                                activityContinuerentPowerTv.getText().toString()))

                );
                totleGetPrice();
            }
        });

        activityContinuerentOtherinTv.addTextChangedListener(new MyTextWatcher());
        activityContinuerentOtheroutTv.addTextChangedListener(new MyTextWatcher());
    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {

    }


    @OnClick({R.id.activity_continuerent_floor, R.id.activity_continuerent_room, R.id.activity_continuerent_name, R.id.activity_continuerent_advancemonths, R.id.activity_continuerent_remark, R.id.activity_continuerent_receverphone, R.id.activity_roomquert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_continuerent_floor:
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
                        activityContinuerentFloorTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_continuerent_room:
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
                        activityContinuerentRoomTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_continuerent_advancemonths:
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
                        activityContinuerentAdvancemonthsTv.setText(entity.getName());
                        activityContinuerentEnddayTv.setText(
                                MyTextUtil.getEndDate(activityContinuerentStartdayTv.getText().toString(),
                                        activityContinuerentAdvancemonthsTv.getText().toString())
                        );
                        activityContinuerentNeedinTv.setText(String.format("￥%s",
                                MyTextUtil.totlePrice(activityContinuerentUnitTv.getText().toString(),
                                activityContinuerentAdvancemonthsTv.getText().toString()))

                        );
                        totleGetPrice();

                    }
                });
                break;
            case R.id.activity_continuerent_receverphone:
                itemEntities.clear();
                for (int i = 0; i < 12; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("手机" + i);
                    itemEntities.add(itemEntity);
                }
                type = 3;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityContinuerentReceverphoneTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_roomquert_confirm:
                sandmessage();
                break;
        }
    }

    private void sandmessage() {
        String s_floor = activityContinuerentFloorTv.getText().toString();
        String s_room = activityContinuerentRoomTv.getText().toString();
        String s_name = activityContinuerentNameTv.getText().toString();
        String s_no = activityContinuerentNoTv.getText().toString();//自动获取
        String s_unit = activityContinuerentUnitTv.getText().toString();//自动获取
        String s_deposit = activityContinuerentDepositTv.getText().toString();
        String s_months = activityContinuerentAdvancemonthsTv.getText().toString();
        String s_water = activityContinuerentWaterTv.getText().toString();//自动获取
        String s_power = activityContinuerentPowerTv.getText().toString();//自动获取
        String s_startday = activityContinuerentStartdayTv.getText().toString();
        String s_endday = activityContinuerentEnddayTv.getText().toString();//自动计算
        String s_remark = activityContinuerentRemarkTv.getText().toString();

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

        if (TextUtil.isEmptyString(s_remark)) {
            s_remark = "续租";
        }

        MyLogUtils.e("ssss");

    }

    private String totleGetPrice() {
        String userW = activityContinuerentWaterpriceTv.getText().toString();
        String userP = activityContinuerentPowerpriceTv.getText().toString();
        String otherin = activityContinuerentOtherinTv.getText().toString();
        String otherout = activityContinuerentOtheroutTv.getText().toString();
        String needin = activityContinuerentNeedinTv.getText().toString();

        MyLogUtils.e(userW + "  " +
                userP + "  " +
                otherin + "  " +
                otherout + "  " +
                needin + "  ");
        try {
            double d_userW = Double.parseDouble(MyTextUtil.getNumberFromString(userW));
            double d_userP = Double.parseDouble(MyTextUtil.getNumberFromString(userP));
            double d_needin = Double.parseDouble(MyTextUtil.getNumberFromString(needin));

            double d_otherin = 0;
            if (!TextUtil.isEmptyString(MyTextUtil.getNumberFromString(otherin)))
                d_otherin = Double.parseDouble(otherin);
            double d_otherout = 0;
            if (!TextUtil.isEmptyString(MyTextUtil.getNumberFromString(otherout)))
                d_otherout = Double.parseDouble(otherout);

            String totleprice = d_userW + d_userP + d_otherin - d_otherout + d_needin + "";
            activityContinuerentAllneedinTv.setText(String.format("￥%s", totleprice));
            return totleprice;
        } catch (Exception e) {
            activityContinuerentAllneedinTv.setText("");
            return "";
        }


    }

    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            totleGetPrice();
        }
    }
}
