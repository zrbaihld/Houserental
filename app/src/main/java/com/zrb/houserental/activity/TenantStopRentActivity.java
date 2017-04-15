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

public class TenantStopRentActivity extends BaseActivity {
    @BindView(R.id.activity_stoprent_floor_tv)
    TextView activityStoprentFloorTv;
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
    @BindView(R.id.scrollview)
    ScrollView scrollView;

    private List<FloorEntity> itemEntities;
    private int type = -1;//0 楼号 1房号 2  3电话

    private String building_id = "";
    private String building_name = "";
    private String room_id = "";
    private String room_name = "";
    private RoomEntity roomEntity;

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
                                activityStoprentBeforewaterTv.getText().toString(),
                                "本月用电少于上月用水")
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
                type = 0;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityStoprentFloorTv.setText(entity.getName());
                        activityStoprentRoomTv.setText("请选择房号");
                        activityStoprentNameTv.setText("");
                        scrollView.setVisibility(View.GONE);
                        building_id = entity.getId();
                        building_name = entity.getName();
                        itemEntities.clear();
                        itemEntities.add(entity);

                    }
                });
                break;
            case R.id.activity_stoprent_room:
                if (itemEntities == null || itemEntities.size() == 0) {
                    toastIfActive("请先选择楼号");
                    break;
                }
                type = 1;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        room_id = entity.getId();
                        room_name = entity.getName();
                        activityStoprentRoomTv.setText(entity.getName());
                        MyHttpTool.creat(TenantStopRentActivity.this)
                                .setContent("building_id", building_id)
                                .setContent("room_id", room_id)
                                .postShowDialog(0, URL_Constant.room, TenantStopRentActivity.this);

                    }
                });
                break;
            case R.id.activity_stoprent_receverphone:
                List<FloorEntity> itemEntities = new ArrayList<>();
                FloorEntity itemEntity = new FloorEntity();
                itemEntity.setName(roomEntity.getRoom().getLodger().getPhone());
                itemEntities.add(itemEntity);
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
        String s_water = activityStoprentNowwaterTv.getText().toString();
        String s_no = activityStoprentNoTv.getText().toString();
        String s_power = activityStoprentNowpowerTv.getText().toString();
        String s_remark = activityStoprentRemarkTv.getText().toString();
        String s_otherin = activityStoprentOtherinTv.getText().toString();
        String s_otherout = activityStoprentOtheroutTv.getText().toString();
        String s_outtime = activityStoprentOuttimeTv.getText().toString();

        if (TextUtil.isEmptyString(building_id)) {
            toastIfActive("还未选择楼号");
            return;
        }
        if (TextUtil.isEmptyString(room_id)) {
            toastIfActive("还未选择楼号");
            return;
        }
        if (TextUtil.isEmptyString(s_water)) {
            toastIfActive("还未填写本月水表");
            return;
        }
        if (TextUtil.isEmptyString(s_power)) {
            toastIfActive("还未填写本月电表");
            return;
        }


        if (TextUtil.isEmptyString(s_remark)) {
            s_remark = "退租";
        }
        if (TextUtil.isEmptyString(s_otherin)) {
            s_otherin = "0";
        }
        if (TextUtil.isEmptyString(s_otherout)) {
            s_otherout = "0";
        }

//        building_id |int| 是 | 12 | 楼号id
//        room_id |int| 是 | 12 | 房号id
//        number |string| 是 | 20171021| 出租编号
//        user_id | int | 是 | 12 | 租客id
//        rent_date_start | date | 是 | 12 | 出租日期
//        rent_date_end | date | 是 | 12 | 结束日期
//        prev_water | float | 是 | 12 | 上个月水表
//        prev_electric |float| 是 | 12 | 上个月电表
//        water | float | 是 | 12 | 本月水表
//        electric |float| 是 | 12 | 本月电表
//        days |int| 是 | 12 | 超期天数
//        remark |string| 是 | 12 | 备注
//        phone | string | 是 | 6 | 接受号码
//        rent_fee | float | 是 | 61.80 | 租金
//        payable | float | 是 | 6 | 应付金额
//        receivable | float | 是 | 80 | 应收金额
//        total_fee | float | 是 | 66.89 | 应收金额两位小数


        MyHttpTool.creat(this)
                .setContent("building_id", building_id)
                .setContent("room_id", room_id)
                .setContent("number", s_no)
                .setContent("user_id", roomEntity.getRoom().getLodger().getId())
                .setContent("rent_date_start", roomEntity.getRoom().getLodger().getRent_date_start())
                .setContent("rent_date_end", roomEntity.getRoom().getLodger().getRent_date_end())
                .setContent("prev_water", roomEntity.getRoom().getLodger().getPrev_water())
                .setContent("prev_electric", roomEntity.getRoom().getLodger().getPrev_electric())
                .setContent("water", s_water)
                .setContent("electric", s_power)
                .setContent("days", s_outtime)
                .setContent("remark", s_remark)
                .setContent("phone", roomEntity.getRoom().getLodger().getPhone())
                .setContent("rent_fee", roomEntity.getRoom().getRental())
                .setContent("payable", s_otherout)
                .setContent("receivable", s_otherin)
                .setContent("keys", roomEntity.getRoom().getLodger().getKeys())
                .setContent("total_fee", activityStoprentAllneedinTv.getText().toString())
                .postShowDialog(1, URL_Constant.exitRent, this);

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
                    activityStoprentNameTv.setText(String.format("%s", roomEntity.getRoom().getLodger().getName()));
                    activityStoprentUnitTv.setText(String.format("￥ %s", roomEntity.getRoom().getRental()));
                    activityStoprentWaterTv.setText(String.format("￥ %s/吨", roomEntity.getRoom().getWater_rate()));
                    activityStoprentPowerTv.setText(String.format("￥ %s/度", roomEntity.getRoom().getElectric_rate()));
                    activityStoprentDepositTv.setText(String.format("￥ %s", roomEntity.getRoom().getLodger().getDeposit()));
                    activityStoprentStartdayTv.setText(String.format("%s", roomEntity.getRoom().getLodger().getRent_date_start()));
                    activityStoprentEnddayTv.setText(String.format("%s", roomEntity.getRoom().getLodger().getRent_date_end()));
                    activityStoprentBeforewaterTv.setText(String.format("%s吨", roomEntity.getRoom().getLodger().getPrev_water()));
                    activityStoprentBeforewpowerTv.setText(String.format("%s度", roomEntity.getRoom().getLodger().getPrev_electric()));
                    activityStartrentKeyTv.setText(String.format("%s个", roomEntity.getRoom().getLodger().getKeys()));
                    activityStoprentReceverphoneTv.setText(String.format("%s", roomEntity.getRoom().getLodger().getPhone()));
                } else {
                    activityStoprentNameTv.setText(String.format("%s",""));
                    toastIfActive("暂无房客");
                    scrollView.setVisibility(View.GONE);
                }
                break;
            case 1:
//                水费171-171=0X5=0.0元 电费4797-4750=47X1=47.0元
                String login_response = sp.getString("Login_response", "");
                LoginEntity loginEntity = gson.fromJson(JsonParsing.getData(login_response), LoginEntity.class);
                String message_body = String.format("退租：尊敬的%s的房客您好，" +
                                "从%s至%s，" +
                                "水费%s元 电费%s元 " +
                                "其他应收%s，其他应付%s，超期租金%s，" +
                                "合计%s元 收租人：%s，收租日期：%s",
                        activityStoprentRoomTv.getText().toString(),
                        activityStoprentStartdayTv.getText().toString().substring(0, 10).replace("-", "年").replace("-", "月") + "日",
                        activityStoprentEnddayTv.getText().toString().substring(0, 10).replace("-", "年").replace("-", "月") + "日",
                        activityStoprentNowwaterTv.getText().toString() +
                                "-" + activityStoprentBeforewaterTv.getText().toString() +
                                "=" + activityStoprentUserwaterTv.getText().toString() +
                                "*" + activityStoprentWaterTv.getText().toString() +
                                "=" + activityStoprentWaterpriceTv.getText().toString(),
                        activityStoprentNowpowerTv.getText().toString() +
                                "-" + activityStoprentBeforewpowerTv.getText().toString() +
                                "=" + activityStoprentUserpowerTv.getText().toString() +
                                "*" + activityStoprentPowerTv.getText().toString() +
                                "=" + activityStoprentPowerpriceTv.getText().toString(),
                        activityStoprentOtherinTv.getText().toString(),
                        activityStoprentOtheroutTv.getText().toString(),
                        activityStoprentNeedinTv.getText().toString(),
                        activityStoprentAllneedinTv.getText().toString(),
                        loginEntity.getAdmin().getRealname(),
                        MyTextUtil.getSimpleDateFormat().format(new Date()).replace("-", "年").replace("-", "月") + "日"
                );


                MyTextUtil.sendMessage(this, activityStoprentReceverphoneTv.getText().toString() +
                        "," + activityStoprentOtherphoneTv.getText().toString(), message_body);

                break;
        }

        return false;
    }
}
