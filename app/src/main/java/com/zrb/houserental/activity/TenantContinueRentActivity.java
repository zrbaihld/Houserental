package com.zrb.houserental.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zrb.baseapp.base.BaseActivity;
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
    @BindView(R.id.scrollview)
    ScrollView scrollView;
    @BindView(R.id.activity_startrent_network_num_tv)
    EditText activityStartrentNetworkNumTv;
    @BindView(R.id.activity_startrent_network_provider_tv)
    EditText activityStartrentNetworkProviderTv;
    @BindView(R.id.activity_startrent_contract_months_tv)
    TextView activityStartrentContractMonthsTv;

    private List<FloorEntity> itemEntities;
    private int type = -1;//0 楼号 1房号 2预付月数 3电话

    private String building_id = "";
    private String building_name = "";
    private String room_id = "";
    private String room_name = "";
    private RoomEntity roomEntity;

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


    @OnClick({R.id.activity_continuerent_floor, R.id.activity_continuerent_room, R.id.activity_continuerent_name,
            R.id.activity_continuerent_advancemonths, R.id.activity_continuerent_remark,
            R.id.activity_continuerent_receverphone, R.id.activity_roomquert_confirm,
            R.id.activity_startrent_contract_months})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_continuerent_floor:
                type = 0;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityContinuerentFloorTv.setText(entity.getName());
                        activityContinuerentRoomTv.setText("请选择房号");
                        activityContinuerentNameTv.setText("");
                        scrollView.setVisibility(View.GONE);
                        building_id = entity.getId();
                        building_name = entity.getName();
                        itemEntities.clear();
                        itemEntities.add(entity);

                    }
                });
                break;
            case R.id.activity_continuerent_room:
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
                        activityContinuerentRoomTv.setText(entity.getName());
                        MyHttpTool.creat(TenantContinueRentActivity.this)
                                .setContent("building_id", building_id)
                                .setContent("room_id", room_id)
                                .setContent("type", 1)
                                .postShowDialog(0, URL_Constant.room, TenantContinueRentActivity.this);

                    }
                });

                break;
            case R.id.activity_continuerent_receverphone:
                List<FloorEntity> itemEntities = new ArrayList<>();
                FloorEntity itemEntity = new FloorEntity();
                itemEntity.setName(roomEntity.getRoom().getLodger().getPhone());
                itemEntities.add(itemEntity);
                type = 3;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityContinuerentReceverphoneTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_continuerent_advancemonths:
                itemEntities = new ArrayList<>();
                for (int i = 1; i < 13; i++) {
                    itemEntity = new FloorEntity();
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
            case R.id.activity_startrent_contract_months:
                itemEntities = new ArrayList<>();
                for (int i = 1; i < 37; i++) {
                    itemEntity = new FloorEntity();
                    itemEntity.setName("合同期" + i + "个月");
                    itemEntities.add(itemEntity);
                }
                type = 6;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityStartrentContractMonthsTv.setText(entity.getName());
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
        String s_water = activityContinuerentNowwaterTv.getText().toString();//自动获取
        String s_power = activityContinuerentNowpowerTv.getText().toString();//自动获取
        String s_startday = activityContinuerentStartdayTv.getText().toString();
        String s_endday = activityContinuerentEnddayTv.getText().toString();//自动计算
        String s_remark = activityContinuerentRemarkTv.getText().toString();
        String s_allneedin = activityContinuerentAllneedinTv.getText().toString();
        String s_otherin = activityContinuerentOtherinTv.getText().toString();
        String s_otherout = activityContinuerentOtheroutTv.getText().toString();
        String s_NetworkNum = activityStartrentNetworkNumTv.getText().toString();
        String s_NetworkProvider = activityStartrentNetworkProviderTv.getText().toString();
        String s_ContractMonths = activityStartrentContractMonthsTv.getText().toString();

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
        if (TextUtil.isEmptyString(s_NetworkNum)) {
            toastIfActive("还未填宽带条数");
            return;
        }
        if (TextUtil.isEmptyString(s_NetworkProvider)) {
            toastIfActive("还未填写宽带品牌");
            return;
        }
        if (s_floor.equals("请选择合同月数")) {
            toastIfActive("还未选择合同月数");
            return;
        }


        if (TextUtil.isEmptyString(s_remark)) {
            s_remark = "续租";
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
//        months | int | 是 | 2 | 预付月数
//        rent_date_start | date | 是 | 12 | 出租日期
//        rent_date_end | date | 是 | 12 | 结束日期
//        prev_water | float | 是 | 12 | 上个月水表
//        prev_electric |float| 是 | 12 | 上个月电表
//        water | float | 是 | 12 | 本月水表
//        electric |float| 是 | 12 | 本月电表
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
                .setContent("months", MyTextUtil.getNumberFromString(s_months))
                .setContent("rent_date_start", roomEntity.getRoom().getLodger().getRent_date_start())
                .setContent("rent_date_end", s_endday)
                .setContent("prev_water", roomEntity.getRoom().getLodger().getPrev_water())
                .setContent("prev_electric", roomEntity.getRoom().getLodger().getPrev_electric())
                .setContent("water", s_water)
                .setContent("electric", s_power)
                .setContent("network_num", s_NetworkNum)
                .setContent("network_provider", s_NetworkProvider)
                .setContent("contract_months", MyTextUtil.getNumberFromString(s_ContractMonths))
                .setContent("sms_content", getSms())
                .setContent("remark", s_remark)
                .setContent("phone", roomEntity.getRoom().getLodger().getPhone())
                .setContent("rent_fee", roomEntity.getRoom().getRental())
                .setContent("payable", s_otherin)
                .setContent("receivable", s_otherout)
                .setContent("total_fee", s_allneedin)
                .postShowDialog(1, URL_Constant.xuRent, this);

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
            double d_otherin = Double.parseDouble(MyTextUtil.getNumberFromString(otherin));
            double d_otherout = Double.parseDouble(MyTextUtil.getNumberFromString(otherout));


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
                    activityContinuerentNameTv.setText(String.format("%s", roomEntity.getRoom().getLodger().getName()));
                    activityContinuerentUnitTv.setText(String.format("￥ %s", roomEntity.getRoom().getRental()));
                    activityContinuerentWaterTv.setText(String.format("￥ %s/吨", roomEntity.getRoom().getWater_rate()));
                    activityContinuerentPowerTv.setText(String.format("￥ %s/度", roomEntity.getRoom().getElectric_rate()));
                    activityContinuerentDepositTv.setText(String.format("%s", roomEntity.getRoom().getLodger().getDeposit()));
                    activityContinuerentStartdayTv.setText(String.format("%s", MyTextUtil.getDate(roomEntity.getRoom().getRent_date_start())));
                    activityContinuerentEnddayTv.setText(String.format("%s", MyTextUtil.getDate(roomEntity.getRoom().getRent_date_end())));
                    activityStartrentNetworkNumTv.setText(String.format("%s", roomEntity.getRoom().getNetwork_num()));
                    activityStartrentNetworkProviderTv.setText(String.format("%s", roomEntity.getRoom().getNetwork_provider()));
                    activityStartrentContractMonthsTv.setText(String.format("%s", roomEntity.getRoom().getContract_months()+"个月"));
                    activityContinuerentBeforewaterTv.setText(String.format("%s吨", roomEntity.getRoom().getLodger().getPrev_water()));
                    activityContinuerentBeforewpowerTv.setText(String.format("%s度", roomEntity.getRoom().getLodger().getPrev_electric()));
                    activityContinuerentReceverphoneTv.setText(String.format("%s", roomEntity.getRoom().getLodger().getPhone()));
                } else {
                    activityContinuerentNameTv.setText(String.format("%s", ""));
                    toastIfActive("暂无房客");
                    scrollView.setVisibility(View.GONE);
                }
                break;
            case 1:


                MyTextUtil.sendMessage(this, activityContinuerentReceverphoneTv.getText().toString()
                        + "," + activityContinuerentOtherphoneTv.getText(), getSms());

                break;
        }

        return false;
    }

    private String getSms() {
        String login_response = sp.getString("Login_response", "");
        LoginEntity loginEntity = gson.fromJson(JsonParsing.getData(login_response), LoginEntity.class);
        return String.format("续租：尊敬的%s的房客您好，" +
                        "从%s至%s，合计应收%s元，其中租金%s元，押金%s元，" +
                        "水费%s元（上月水表%s，本月水表%s，水费费率%s元/吨），" +
                        "电费%s元（上月电表%s，本月电表%s，电费费率%s元/度），" +
                        "其他应收%s元，其他应付%s元。" +
                        "收租人：%s，收租日期：%s",
                activityContinuerentRoomTv.getText().toString(),
                activityContinuerentStartdayTv.getText().toString().substring(0, 10).replace("-", "年").replace("-", "月") + "日",
                activityContinuerentEnddayTv.getText().toString().replace("-", "年").replace("-", "月") + "日",
                activityContinuerentAllneedinTv.getText().toString(),
                activityContinuerentUnitTv.getText().toString(),
                activityContinuerentDepositTv.getText().toString(),
                activityContinuerentWaterpriceTv.getText().toString(),
                activityContinuerentBeforewaterTv.getText().toString(),
                activityContinuerentNowwaterTv.getText().toString(),
                activityContinuerentWaterTv.getText().toString(),
                activityContinuerentPowerpriceTv.getText().toString(),
                activityContinuerentBeforewpowerTv.getText().toString(),
                activityContinuerentNowpowerTv.getText().toString(),
                activityContinuerentPowerTv.getText().toString(),
                TextUtil.isEmptyString(activityContinuerentOtherinTv.getText().toString()) ? "0" : activityContinuerentOtherinTv.getText().toString(),
                TextUtil.isEmptyString(activityContinuerentOtheroutTv.getText().toString()) ? "0" : activityContinuerentOtheroutTv.getText().toString(),
                loginEntity.getAdmin().getRealname(),
                MyTextUtil.getSimpleDateFormat().format(new Date()).replace("-", "年").replace("-", "月") + "日"
        );


    }
}
