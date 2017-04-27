package com.zrb.houserental.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.base.BaseRecyclerViewAdapter;
import com.zrb.baseapp.tools.JsonParsing;
import com.zrb.baseapp.tools.MyHttpTool;
import com.zrb.baseapp.tools.TextUtil;
import com.zrb.houserental.Entity.FloorEntity;
import com.zrb.houserental.Entity.ListSmsEntity;
import com.zrb.houserental.Entity.LoginEntity;
import com.zrb.houserental.Entity.RoomEntity;
import com.zrb.houserental.R;
import com.zrb.houserental.constant.URL_Constant;
import com.zrb.houserental.dialog.DialogUntil;
import com.zrb.houserental.dialog.SelectFloorDialog;
import com.zrb.houserental.util.MyTextUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/1.
 */

public class ShotMessageQuertActivity extends BaseActivity {
    @BindView(R.id.activity_shotmessagequert_floor_tv)
    TextView activityShotmessagequertFloorTv;
    @BindView(R.id.activity_shotmessagequert_room_tv)
    TextView activityShotmessagequertRoomTv;
    @BindView(R.id.activity_shotmessagequert_no_tv)
    TextView activityShotmessagequertNoTv;
    @BindView(R.id.activity_shotmessagequert_unity_tv)
    TextView activityShotmessagequertUnityTv;
    @BindView(R.id.activity_shotmessagequert_deposit_tv)
    TextView activityShotmessagequertDepositTv;
    @BindView(R.id.activity_shotmessagequert_water_tv)
    TextView activityShotmessagequertWaterTv;
    @BindView(R.id.activity_shotmessagequert_power_tv)
    TextView activityShotmessagequertPowerTv;
    @BindView(R.id.activity_shotmessagequert_startday_tv)
    TextView activityShotmessagequertStartdayTv;
    @BindView(R.id.activity_shotmessagequert_endday_tv)
    TextView activityShotmessagequertEnddayTv;
    @BindView(R.id.activity_shotmessagequert_beforewater_tv)
    TextView activityShotmessagequertBeforewaterTv;
    @BindView(R.id.activity_shotmessagequert_beforewpower_tv)
    TextView activityShotmessagequertBeforewpowerTv;
    @BindView(R.id.activity_shotmessagequert_nowwater_tv)
    TextView activityShotmessagequertNowwaterTv;
    @BindView(R.id.activity_shotmessagequert_nowpower_tv)
    TextView activityShotmessagequertNowpowerTv;
    @BindView(R.id.activity_shotmessagequert_userwater_tv)
    TextView activityShotmessagequertUserwaterTv;
    @BindView(R.id.activity_shotmessagequert_userpower_tv)
    TextView activityShotmessagequertUserpowerTv;
    @BindView(R.id.activity_shotmessagequert_waterprice_tv)
    TextView activityShotmessagequertWaterpriceTv;
    @BindView(R.id.activity_shotmessagequert_powerprice_tv)
    TextView activityShotmessagequertPowerpriceTv;
    @BindView(R.id.activity_shotmessagequert_otherin_tv)
    TextView activityShotmessagequertOtherinTv;
    @BindView(R.id.activity_shotmessagequert_otherout_tv)
    TextView activityShotmessagequertOtheroutTv;
    @BindView(R.id.activity_shotmessagequert_needin_tv)
    TextView activityShotmessagequertNeedinTv;
    @BindView(R.id.activity_shotmessagequert_allneedin_tv)
    TextView activityShotmessagequertAllneedinTv;
    @BindView(R.id.activity_shotmessagequert_remark_tv)
    TextView activityShotmessagequertRemarkTv;
    @BindView(R.id.activity_startrent_getman_tv)
    TextView activityStartrentGetmanTv;
    @BindView(R.id.activity_shotmessagequert_receverphone_tv)
    TextView activityShotmessagequertReceverphoneTv;
    @BindView(R.id.activity_shotmessagequert_otherphone_tv)
    EditText activityShotmessagequertOtherphoneTv;
    @BindView(R.id.scrollview)
    View scrollview;

    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;

    private List<FloorEntity> itemEntities;
    private int type = -1;//0 楼号 1房号 2编号

    private String building_id = "";
    private String building_name = "";
    private String room_id = "";
    private String room_name = "";
    private ListSmsEntity roomEntity;

    @Override
    public void init() {
        addConView(R.layout.activity_shotmessagequert);
        ButterKnife.bind(this);

        titleTV.setText("短信查询");
        itemEntities = new ArrayList<>();
    }

    @Override
    public void setListenner() {
//        activityShotmessagequertNowwaterTv.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                activityShotmessagequertUserwaterTv.setText(
//                        MyTextUtil.useNum(editable.toString(),
//                                activityShotmessagequertBeforewaterTv.getText().toString(),
//                                "本月用电少于上月用水")
//                );
//                activityShotmessagequertWaterpriceTv.setText(String.format("￥%s",
//                        MyTextUtil.totlePrice(activityShotmessagequertUserwaterTv.getText().toString(),
//                                activityShotmessagequertWaterTv.getText().toString()))
//
//                );
//                totleGetPrice();
//            }
//        });
//        activityShotmessagequertNowpowerTv.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                activityShotmessagequertUserpowerTv.setText(
//                        MyTextUtil.useNum(editable.toString(),
//                                activityShotmessagequertBeforewpowerTv.getText().toString(),
//                                "本月用电少于上月用电")
//                );
//                activityShotmessagequertPowerpriceTv.setText(String.format("￥%s",
//                        MyTextUtil.totlePrice(activityShotmessagequertUserpowerTv.getText().toString(),
//                                activityShotmessagequertPowerTv.getText().toString()))
//
//                );
//                totleGetPrice();
//            }
//        });
//
//        activityShotmessagequertOtherinTv.addTextChangedListener(new MyTextWatcher());
//        activityShotmessagequertOtheroutTv.addTextChangedListener(new MyTextWatcher());

    }

    private String totleGetPrice() {
        String userW = activityShotmessagequertWaterpriceTv.getText().toString();
        String userP = activityShotmessagequertPowerpriceTv.getText().toString();
        String otherin = activityShotmessagequertOtherinTv.getText().toString();
        String otherout = activityShotmessagequertOtheroutTv.getText().toString();
        String needin = activityShotmessagequertNeedinTv.getText().toString();

        try {
            double d_userW = Double.parseDouble(MyTextUtil.getNumberFromString(userW));
            double d_userP = Double.parseDouble(MyTextUtil.getNumberFromString(userP));
            double d_needin = Double.parseDouble(MyTextUtil.getNumberFromString(needin));
            double d_otherin = Double.parseDouble(MyTextUtil.getNumberFromString(otherin));
            double d_otherout=Double.parseDouble(MyTextUtil.getNumberFromString(otherout));

            String totleprice = d_userW + d_userP + d_otherin - d_otherout + d_needin + "";
            activityShotmessagequertAllneedinTv.setText(String.format("￥%s", totleprice));
            return totleprice;
        } catch (Exception e) {
            activityShotmessagequertAllneedinTv.setText("");
            return "";
        }


    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {

    }

    @OnClick({R.id.activity_shotmessagequert_floor, R.id.activity_shotmessagequert_room, R.id.activity_shotmessagequert_no, R.id.activity_shotmessagequert_receverphone, R.id.activity_roomquert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_shotmessagequert_floor:
                type = 0;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityShotmessagequertFloorTv.setText(entity.getName());
                        activityShotmessagequertRoomTv.setText("请选择房号");
                        building_id = entity.getId();
                        building_name = entity.getName();
                        itemEntities.clear();
                        itemEntities.add(entity);
                    }
                });
                break;
            case R.id.activity_shotmessagequert_room:
                if (itemEntities == null || itemEntities.size() == 0) {
                    toastIfActive("请先选择楼号");
                    break;
                }
                type = 8;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityShotmessagequertRoomTv.setText(entity.getName());
                        room_id = entity.getId();
                        room_name = entity.getName();
                        MyHttpTool.creat(ShotMessageQuertActivity.this)
                                .setContent("building_id", building_id)
                                .setContent("room_id", room_id)
                                .postShowDialog(0, URL_Constant.listSms, ShotMessageQuertActivity.this);
                    }
                });
                break;

            case R.id.activity_shotmessagequert_receverphone:
                List<FloorEntity> itemEntities = new ArrayList<>();
                type = 3;
                if (roomEntity != null && roomEntity.getRoom() != null && roomEntity.getRoom().getRent_records() != null) {
                    for (ListSmsEntity.RoomBean.RentRecordsBean rentRecordsBean : roomEntity.getRoom().getRent_records()) {
                        FloorEntity itemEntity = new FloorEntity();
                        itemEntity.setName(rentRecordsBean.getPhone());
                        itemEntities.add(itemEntity);
                    }
                    DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                        @Override
                        public void onPositiveActionClicked(FloorEntity entity) {
                            activityShotmessagequertReceverphoneTv.setText(entity.getName());
                        }
                    });
                }

                break;
            case R.id.activity_shotmessagequert_no:
                itemEntities = new ArrayList<>();
                FloorEntity itemEntity = new FloorEntity();
                if (roomEntity == null) {
                    toastIfActive("请先选择楼号房号");
                    return;
                }
                if (roomEntity.getRoom() == null) {
                    return;
                }
                type = 5;
                if (roomEntity != null && roomEntity.getRoom() != null && roomEntity.getRoom().getRent_records() != null) {
                    for (ListSmsEntity.RoomBean.RentRecordsBean rentRecordsBean : roomEntity.getRoom().getRent_records()) {
                        itemEntity = new FloorEntity();
                        itemEntity.setName(rentRecordsBean.getNumber());
                        itemEntity.setI_id(rentRecordsBean.getId());
                        itemEntities.add(itemEntity);
                    }
                    DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                        @Override
                        public void onPositiveActionClicked(FloorEntity entity) {
                            activityShotmessagequertNoTv.setText(entity.getName());
                            for (ListSmsEntity.RoomBean.RentRecordsBean rentRecordsBean : roomEntity.getRoom().getRent_records()) {
                                if (rentRecordsBean.getId()==entity.getI_id()) {
                                    initData(rentRecordsBean);
                                }
                            }
                        }
                    });
                }


                break;
            case R.id.activity_roomquert_confirm:
                sendShotMessage();
                break;
        }
    }

    private void initData(ListSmsEntity.RoomBean.RentRecordsBean rentRecordsBean) {
        activityShotmessagequertNoTv.setText(String.format("%s", rentRecordsBean.getNumber()));
        activityShotmessagequertStartdayTv.setText(String.format("%s",MyTextUtil.getDate( rentRecordsBean.getStart_date())));
        activityShotmessagequertEnddayTv.setText(String.format("%s", MyTextUtil.getDate(rentRecordsBean.getEnd_date())));
        activityShotmessagequertBeforewaterTv.setText(String.format("%s吨", rentRecordsBean.getPrev_water()));
        activityShotmessagequertBeforewpowerTv.setText(String.format("%s度", rentRecordsBean.getPrev_electric()));
        activityShotmessagequertNowpowerTv.setText(String.format("%s度", rentRecordsBean.getElectric()));
        activityShotmessagequertNowwaterTv.setText(String.format("%s吨", rentRecordsBean.getWater()));
        activityShotmessagequertUserpowerTv.setText(String.format("%s度", rentRecordsBean.getElectric() - rentRecordsBean.getPrev_electric()));
        activityShotmessagequertUserwaterTv.setText(String.format("%s吨", rentRecordsBean.getWater() - rentRecordsBean.getPrev_water()));
        activityShotmessagequertWaterpriceTv.setText(String.format("￥ %s", (rentRecordsBean.getWater() - rentRecordsBean.getPrev_water()) * roomEntity.getRoom().getWater_rate()));
        activityShotmessagequertPowerpriceTv.setText(String.format("￥ %s", (rentRecordsBean.getElectric() - rentRecordsBean.getPrev_electric()) * roomEntity.getRoom().getElectric_rate()));
        activityShotmessagequertOtherinTv.setText(String.format("￥ %s", rentRecordsBean.getReceivable()));
        activityShotmessagequertOtheroutTv.setText(String.format("￥ %s", rentRecordsBean.getPayable()));
        totleGetPrice();
        activityShotmessagequertRemarkTv.setText(String.format("%s", rentRecordsBean.getRemark()));
        activityShotmessagequertReceverphoneTv.setText(rentRecordsBean.getPhone());
    }

    private void sendShotMessage() {
        String s_nowwater = activityShotmessagequertNowwaterTv.getText().toString();
        String s_nowpower = activityShotmessagequertNowpowerTv.getText().toString();
        String s_allprice = activityShotmessagequertAllneedinTv.getText().toString();
//        if (TextUtil.isEmptyString(s_nowwater)) {
//            toastIfActive("本月水表未填");
//            return;
//        }
//        if (TextUtil.isEmptyString(s_nowpower)) {
//            toastIfActive("本月电表未填");
//            return;
//        }
//        if (TextUtil.isEmptyString(s_allprice)) {
//            toastIfActive("输入有误　请检查");
//            return;
//        }
        if (roomEntity.getRoom().getRent_records() == null || roomEntity.getRoom().getRent_records().size() == 0) {
            toastIfActive("没有出租信息");
            return;
        }
        String message_body = String.format("查询：尊敬的%s的房客您好，" +
                        "从%s至%s，合计应收%s元，其中租金%s元，" +
                        "水费%s元（上月水表%s，本月水表%s，水费费率%s元/吨），" +
                        "电费%s元（上月电表%s，本月电表%s，电费费率%s元/度），" +
                        "其他应收%s元，其他应付%s元。" +
                        "收租人：%s，收租日期：%s",
                activityShotmessagequertRoomTv.getText().toString(),
                activityShotmessagequertStartdayTv.getText().toString().substring(0, 10).replace("-", "年").replace("-", "月") + "日",
                activityShotmessagequertEnddayTv.getText().toString().substring(0, 10).replace("-", "年").replace("-", "月") + "日",
                activityShotmessagequertAllneedinTv.getText().toString(),
                activityShotmessagequertUnityTv.getText().toString(),
                activityShotmessagequertWaterpriceTv.getText().toString(),
                activityShotmessagequertBeforewaterTv.getText().toString(),
                activityShotmessagequertNowwaterTv.getText().toString(),
                activityShotmessagequertWaterTv.getText().toString(),
                activityShotmessagequertPowerpriceTv.getText().toString(),
                activityShotmessagequertBeforewpowerTv.getText().toString(),
                activityShotmessagequertNowpowerTv.getText().toString(),
                activityShotmessagequertPowerTv.getText().toString(),
                activityShotmessagequertOtherinTv.getText().toString(),
                activityShotmessagequertOtheroutTv.getText().toString(),
                activityStartrentGetmanTv.getText().toString(),
                MyTextUtil.getSimpleDateFormat().format(new Date()).replace("-", "年").replace("-", "月") + "日"
        );


        MyTextUtil.sendMessage(this, activityShotmessagequertReceverphoneTv.getText().toString()
                + "," + activityShotmessagequertOtherphoneTv.getText(), message_body);
    }

    @Override
    public boolean getIOAuthCallBack(int type, String json, boolean isSuccess) {
        if (super.getIOAuthCallBack(type, json, isSuccess)) {
            if (type == 0)
                scrollview.setVisibility(View.GONE);
            return true;
        }
        switch (type) {
            case 0:
                roomEntity = gson.fromJson(JsonParsing.getData(json), ListSmsEntity.class);
                if (roomEntity != null && roomEntity.getRoom() != null && roomEntity.getRoom().getRent_records() != null) {
                    scrollview.setVisibility(View.VISIBLE);
                    String login_response = sp.getString("Login_response", "");
                    LoginEntity loginEntity = gson.fromJson(JsonParsing.getData(login_response), LoginEntity.class);
                    activityShotmessagequertUnityTv.setText(String.format("￥ %s", roomEntity.getRoom().getRental()));
                    activityShotmessagequertDepositTv.setText(String.format("￥ %s", roomEntity.getRoom().getRental()));
                    activityShotmessagequertWaterTv.setText(String.format("￥ %s/吨", roomEntity.getRoom().getWater_rate()));
                    activityShotmessagequertPowerTv.setText(String.format("￥ %s/度", roomEntity.getRoom().getElectric_rate()));
                    activityStartrentGetmanTv.setText(loginEntity.getAdmin().getRealname());
                    activityShotmessagequertNeedinTv.setText(String.format("￥ %s", roomEntity.getRoom().getRental()));
                    if (roomEntity.getRoom().getRent_records().size() > 0) {
                        ListSmsEntity.RoomBean.RentRecordsBean rentRecordsBean = roomEntity.getRoom().getRent_records().get(0);
                        initData(rentRecordsBean);
                    } else {
                        toastIfActive("没有出租记录");
                        activityShotmessagequertNoTv.setText("没有出租记录");
                        scrollview.setVisibility(View.GONE);
                    }
                } else {
                    scrollview.setVisibility(View.GONE);
                }
                break;
            case 1:
                break;
        }

        return false;
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
