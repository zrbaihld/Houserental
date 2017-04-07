package com.zrb.houserental.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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

public class TenantStopRentActivity extends BaseActivity {
    @BindView(R.id.activity_stoprent_floor_tv)
    TextView activityStoprentFloorTv;
    @BindView(R.id.next)
    ImageView next;
    @BindView(R.id.activity_stoprent_room_tv)
    TextView activityStoprentRoomTv;
    @BindView(R.id.activity_stoprent_name_tv)
    TextView activityStoprentNameTv;
    @BindView(R.id.activity_stoprent_no_tv)
    TextView activityStoprentNoTv;
    @BindView(R.id.activity_stoprent_deposit_tv)
    TextView activityStoprentDepositTv;
    @BindView(R.id.activity_stoprent_unit_tv)
    TextView activityStoprentUnitTv;
    @BindView(R.id.activity_stoprent_water_tv)
    TextView activityStoprentWaterTv;
    @BindView(R.id.activity_stoprent_power_tv)
    TextView activityStoprentPowerTv;
    @BindView(R.id.activity_stoprent_startday_tv)
    TextView activityStoprentStartdayTv;
    @BindView(R.id.activity_stoprent_endday_tv)
    TextView activityStoprentEnddayTv;
    @BindView(R.id.activity_stoprent_beforewater_tv)
    TextView activityStoprentBeforewaterTv;
    @BindView(R.id.activity_stoprent_beforewpower_tv)
    TextView activityStoprentBeforewpowerTv;
    @BindView(R.id.activity_stoprent_nowwater_tv)
    EditText activityStoprentNowwaterTv;
    @BindView(R.id.activity_stoprent_nowpower_tv)
    EditText activityStoprentNowpowerTv;
    @BindView(R.id.activity_stoprent_userwater_tv)
    TextView activityStoprentUserwaterTv;
    @BindView(R.id.activity_stoprent_userpower_tv)
    TextView activityStoprentUserpowerTv;
    @BindView(R.id.activity_stoprent_waterprice_tv)
    TextView activityStoprentWaterpriceTv;
    @BindView(R.id.activity_stoprent_powerprice_tv)
    TextView activityStoprentPowerpriceTv;
    @BindView(R.id.activity_stoprent_otherin_tv)
    EditText activityStoprentOtherinTv;
    @BindView(R.id.activity_stoprent_otherout_tv)
    EditText activityStoprentOtheroutTv;
    @BindView(R.id.activity_stoprent_outtime_tv)
    TextView activityStoprentOuttimeTv;
    @BindView(R.id.activity_stoprent_needin_tv)
    TextView activityStoprentNeedinTv;
    @BindView(R.id.activity_stoprent_allneedin_tv)
    TextView activityStoprentAllneedinTv;
    @BindView(R.id.activity_stoprent_remark_tv)
    EditText activityStoprentRemarkTv;
    @BindView(R.id.activity_startrent_key_tv)
    TextView activityStartrentKeyTv;
    @BindView(R.id.activity_stoprent_receverphone_tv)
    TextView activityStoprentReceverphoneTv;
    @BindView(R.id.activity_stoprent_otherphone_tv)
    EditText activityStoprentOtherphoneTv;

    private List<FloorEntity> itemEntities;
    private int type = -1;//0 楼号 1房号 2  3电话

    @Override
    public void init() {
        addConView(R.layout.activity_stoprent);
        ButterKnife.bind(this);

        titleTV.setText("房客退租");
        itemEntities = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        activityStoprentNoTv.setText(simpleDateFormat.format(new Date()));

        activityStoprentOuttimeTv.setText(
                MyTextUtil.getOutOfMoth(activityStoprentEnddayTv.getText().toString())
        );

        double i_needin = Integer.parseInt(MyTextUtil.getNumberFromString(activityStoprentOuttimeTv.getText().toString()));
        if (i_needin > 0) {
            double i_unit = Integer.parseInt(MyTextUtil.getNumberFromString(activityStoprentUnitTv.getText().toString()));
            double all = i_unit * i_needin / 30 + i_unit * i_needin % 30 / 30;
            activityStoprentNeedinTv.setText(String.format("￥ %s元", all));
        } else {
            activityStoprentNeedinTv.setText("￥ 0元");
        }

    }

    @Override
    public void setListenner() {
        activityStoprentNowwaterTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                activityStoprentUserwaterTv.setText(
                        MyTextUtil.useNum(editable.toString(),
                                activityStoprentBeforewpowerTv.getText().toString(),
                                "本月用电少于上月用电")
                );
                activityStoprentWaterpriceTv.setText(String.format("￥%s",
                        MyTextUtil.totlePrice(activityStoprentUserwaterTv.getText().toString(),
                                activityStoprentWaterTv.getText().toString()))

                );
                totleGetPrice();
            }
        });
        activityStoprentNowpowerTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                activityStoprentUserpowerTv.setText(
                        MyTextUtil.useNum(editable.toString(),
                                activityStoprentBeforewpowerTv.getText().toString(),
                                "本月用电少于上月用电")
                );
                activityStoprentPowerpriceTv.setText(String.format("￥%s",
                        MyTextUtil.totlePrice(activityStoprentUserpowerTv.getText().toString(),
                                activityStoprentPowerTv.getText().toString()))

                );
                totleGetPrice();
            }
        });

        activityStoprentOtherinTv.addTextChangedListener(new MyTextWatcher());
        activityStoprentOtheroutTv.addTextChangedListener(new MyTextWatcher());
    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {

    }


    @OnClick({R.id.activity_stoprent_floor, R.id.activity_stoprent_room, R.id.activity_stoprent_receverphone, R.id.activity_roomquert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_stoprent_floor:
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
                        activityStoprentFloorTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_stoprent_room:
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
                        activityStoprentRoomTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_stoprent_receverphone:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("电话" + i);
                    itemEntities.add(itemEntity);
                }
                type = 3;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityStoprentReceverphoneTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_roomquert_confirm:
                sandmessage();
                break;
        }
    }

    private String totleGetPrice() {
        String userW = activityStoprentWaterpriceTv.getText().toString();
        String userP = activityStoprentPowerpriceTv.getText().toString();
        String otherin = activityStoprentOtherinTv.getText().toString();
        String otherout = activityStoprentOtheroutTv.getText().toString();
        String needin = activityStoprentNeedinTv.getText().toString();

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
            activityStoprentAllneedinTv.setText(String.format("￥%s", totleprice));
            return totleprice;
        } catch (Exception e) {
            activityStoprentAllneedinTv.setText("");
            return "";
        }


    }

    private void sandmessage() {
        String s_floor = activityStoprentFloorTv.getText().toString();
        String s_room = activityStoprentRoomTv.getText().toString();
        String s_no = activityStoprentNoTv.getText().toString();//自动获取
        String s_unit = activityStoprentUnitTv.getText().toString();//自动获取
        String s_deposit = activityStoprentDepositTv.getText().toString();
        String s_water = activityStoprentWaterTv.getText().toString();//自动获取
        String s_power = activityStoprentPowerTv.getText().toString();//自动获取
        String s_startday = activityStoprentStartdayTv.getText().toString();
        String s_endday = activityStoprentEnddayTv.getText().toString();//自动计算
        String s_remark = activityStoprentRemarkTv.getText().toString();

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
        if (s_startday.equals("请选择起始日期")) {
            toastIfActive("还未选择起始日期");
            return;
        }

        if (TextUtil.isEmptyString(s_remark)) {
            s_remark = "新租";
        }

        MyLogUtils.e("ssss");

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
