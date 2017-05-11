package com.zrb.houserental.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.base.BaseRecyclerViewAdapter;
import com.zrb.baseapp.constant.Constant_C;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    @BindView(R.id.activity_startrent_network_num_tv)
    TextView activityStartrentNetworkNumTv;
    @BindView(R.id.activity_startrent_network_provider_tv)
    TextView activityStartrentNetworkProviderTv;
    @BindView(R.id.activity_startrent_contract_months_tv)
    TextView activityStartrentContractMonthsTv;

    private List<FloorEntity> itemEntities;
    private int type = -1;//0 楼号 1房号 2  3电话

    private String building_id = "";
    private String building_name = "";
    private String room_id = "";
    private String room_name = "";
    private RoomEntity roomEntity;


    private String phone;
    private String password;

    @Override
    public void init() {
        addConView(R.layout.activity_stoprent);
        ButterKnife.bind(this);

        phone = sp.getString(Constant_C.SPPATH.USER_NAME, "");
        password = sp.getString(Constant_C.SPPATH.USER_PW, "");

        titleTV.setText("房客退租");
        itemEntities = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        activityStoprentNoTv.setText(simpleDateFormat.format(new Date()));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            activityStoprentNowwaterTv.setText(savedInstanceState.getString("activityStoprentNowwaterTv"));
            activityStoprentNowpowerTv.setText(savedInstanceState.getString("activityStoprentNowpowerTv"));
            activityStoprentOtherinTv.setText(savedInstanceState.getString("activityStoprentOtherinTv"));
            activityStoprentOtheroutTv.setText(savedInstanceState.getString("activityStoprentOtheroutTv"));
            activityStoprentReceverphoneTv.setText(savedInstanceState.getString("activityStoprentReceverphoneTv"));
            activityStoprentOtherphoneTv.setText(savedInstanceState.getString("activityStoprentOtherphoneTv"));

            MyLogUtils.e(savedInstanceState.getString("test"));
            setRoomMessage((RoomEntity) savedInstanceState.getSerializable("RoomEntity"));
        } else {
            MyLogUtils.e("savedInstanceState == null");
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
                type = 9;
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
                if (roomEntity == null)
                    return;
                if (roomEntity.getRoom() == null) {
                    return;
                }
                for (String s : roomEntity.getRoom().getPhone()) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName(s);
                    itemEntities.add(itemEntity);
                }

                if (itemEntities.size() == 0) {
                    toastIfActive("没有接受手机");
                    return;
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

        try {
            double d_userW = Double.parseDouble(MyTextUtil.getNumberFromString(userW));
            double d_userP = Double.parseDouble(MyTextUtil.getNumberFromString(userP));
            double d_needin = Double.parseDouble(MyTextUtil.getNumberFromString(needin));
            double d_otherin = Double.parseDouble(MyTextUtil.getNumberFromString(otherin));
            double d_otherout = Double.parseDouble(MyTextUtil.getNumberFromString(otherout));

            String totleprice = d_userW + d_userP + d_otherin - d_otherout + d_needin + "";
            activityStoprentAllneedinTv.setText(String.format("￥%s", totleprice));
            return totleprice;
        } catch (Exception e) {
            activityStoprentAllneedinTv.setText("");
            return "";
        }


    }

    private void sandmessage() {
        final String s_water = activityStoprentNowwaterTv.getText().toString();
        final String s_no = activityStoprentNoTv.getText().toString();
        final String s_power = activityStoprentNowpowerTv.getText().toString();
        String s_remark = activityStoprentRemarkTv.getText().toString();
        String s_otherin = activityStoprentOtherinTv.getText().toString();
        String s_otherout = activityStoprentOtheroutTv.getText().toString();
        final String s_outtime = activityStoprentOuttimeTv.getText().toString();

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

        if (isOutTime(roomEntity.getRoom().getContract_months())) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("提示");
            alertDialog.setMessage("合同期未到，是否结算？");
            final String finalS_remark = s_remark;
            final String finalS_otherout = s_otherout;
            final String finalS_otherin = s_otherin;
            alertDialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MyHttpTool.creat(TenantStopRentActivity.this)
                            .setContent("building_id", building_id)
                            .setContent("room_id", room_id)
                            .setContent("number", s_no)
                            .setContent("user_id", roomEntity.getRoom().getLodger() == null ? 0 : roomEntity.getRoom().getLodger().getId())
                            .setContent("rent_date_start", roomEntity.getRoom().getRent_date_start())
                            .setContent("rent_date_end", roomEntity.getRoom().getRent_date_end())
                            .setContent("prev_water", roomEntity.getRoom().getWater_init())
                            .setContent("prev_electric", roomEntity.getRoom().getElectric_init())
                            .setContent("water", s_water)
                            .setContent("electric", s_power)
                            .setContent("days", s_outtime)
                            .setContent("remark", finalS_remark)
                            .setContent("phone", TextUtil.isEmptyString(activityStoprentReceverphoneTv.getText().toString()) ?
                                    "0" : activityStoprentReceverphoneTv.getText().toString())
                            .setContent("rent_fee", roomEntity.getRoom().getRental())
                            .setContent("payable", finalS_otherout)
                            .setContent("sms_content", getSms())
                            .setContent("receivable", finalS_otherin)
                            .setContent("keys", roomEntity.getRoom().getKeys())
                            .setContent("total_fee", activityStoprentAllneedinTv.getText().toString())
                            .postShowDialog(1, URL_Constant.exitRent, TenantStopRentActivity.this);
                }
            });
            alertDialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertDialog.show();

        } else {
            MyHttpTool.creat(this)
                    .setContent("building_id", building_id)
                    .setContent("room_id", room_id)
                    .setContent("number", s_no)
                    .setContent("user_id", roomEntity.getRoom().getLodger() == null ? 0 : roomEntity.getRoom().getLodger().getId())
                    .setContent("rent_date_start", roomEntity.getRoom().getRent_date_start())
                    .setContent("rent_date_end", roomEntity.getRoom().getRent_date_end())
                    .setContent("prev_water", roomEntity.getRoom().getWater_init())
                    .setContent("prev_electric", roomEntity.getRoom().getElectric_init())
                    .setContent("water", s_water)
                    .setContent("electric", s_power)
                    .setContent("days", s_outtime)
                    .setContent("remark", s_remark)
                    .setContent("phone", TextUtil.isEmptyString(activityStoprentReceverphoneTv.getText().toString()) ?
                            "0" : activityStoprentReceverphoneTv.getText().toString())
                    .setContent("rent_fee", roomEntity.getRoom().getRental())
                    .setContent("payable", s_otherout)
                    .setContent("sms_content", getSms())
                    .setContent("receivable", s_otherin)
                    .setContent("keys", roomEntity.getRoom().getKeys())
                    .setContent("total_fee", activityStoprentAllneedinTv.getText().toString())
                    .postShowDialog(1, URL_Constant.exitRent, this);
        }


    }


    private boolean isOutTime(int moth) {
        DateFormat simpleDateFormat = MyTextUtil.getSimpleDateFormat();
        Date date = null;
        try {
            date = simpleDateFormat.parse(roomEntity.getRoom().getLodger().getRent_date_start());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int om = calendar.get(Calendar.MONTH);
            int oy = calendar.get(Calendar.YEAR);
            calendar.setTime(new Date());
            int nm = calendar.get(Calendar.MONTH);
            int ny = calendar.get(Calendar.YEAR);
            int dm = (ny - oy) * 12 + nm - om;
            return dm < moth;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private String getSms() {
        String login_response = sp.getString("Login_response", "");
        LoginEntity loginEntity = gson.fromJson(JsonParsing.getData(login_response), LoginEntity.class);
        return String.format("退租：尊敬的%s的房客您好，" +
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
                TextUtil.isEmptyString(activityStoprentOtherinTv.getText().toString()) ? "0" : activityStoprentOtherinTv.getText().toString(),
                TextUtil.isEmptyString(activityStoprentOtheroutTv.getText().toString()) ? "0" : activityStoprentOtheroutTv.getText().toString(),
                activityStoprentNeedinTv.getText().toString(),
                activityStoprentAllneedinTv.getText().toString(),
                loginEntity.getAdmin().getRealname(),
                MyTextUtil.getSimpleDateFormat().format(new Date()).replace("-", "年").replace("-", "月") + "日"
        );

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
//                scrollView.setVisibility(View.GONE);
                return true;
        }
        switch (type) {
            case 0:
                roomEntity = gson.fromJson(JsonParsing.getData(json), RoomEntity.class);
                if (roomEntity != null && roomEntity.getRoom() != null) {
                    scrollView.setVisibility(View.VISIBLE);
                    setRoomMessage(roomEntity);
                } else {
                    activityStoprentNameTv.setText(String.format("%s", ""));
                    toastIfActive("暂无房客");
                    scrollView.setVisibility(View.GONE);
                }
                break;
            case 1:
                MyHttpTool.creat(this)
                        .setContent("passwd", password)
                        .setContent("phone", phone)
                        .postShowDialog(0, URL_Constant.Login, new MyHttpTool.IOAuthCallBack() {
                            @Override
                            public boolean getIOAuthCallBack(int type, String json, boolean isSuccess) {
                                sp.edit().putString("Login_response", json).
                                        commit();
                                LoginEntity loginEntity = gson.fromJson(JsonParsing.getData(json), LoginEntity.class);
                                Constant_C.AID = loginEntity.getAdmin().getId() + "";
                                Constant_C.TOKEN = loginEntity.getToken();
                                finish();
                                return false;
                            }
                        });

                MyTextUtil.sendMessage(this, activityStoprentReceverphoneTv.getText().toString() +
                        "," + activityStoprentOtherphoneTv.getText().toString(), getSms());


                break;
        }

        return false;
    }

    private void setRoomMessage(RoomEntity roomEntity) {
        if (roomEntity == null)
            return;
        this.roomEntity = roomEntity;
        activityStoprentNameTv.setText(String.format("%s", roomEntity.getRoom().getName()));
        activityStoprentUnitTv.setText(String.format("￥ %s", roomEntity.getRoom().getRental()));
        activityStoprentWaterTv.setText(String.format("￥ %s/吨", roomEntity.getRoom().getWater_rate()));
        activityStoprentPowerTv.setText(String.format("￥ %s/度", roomEntity.getRoom().getElectric_rate()));
        activityStoprentDepositTv.setText(String.format("￥ %s", roomEntity.getRoom().getDeposit()));
        activityStoprentStartdayTv.setText(String.format("%s", MyTextUtil.getDate(roomEntity.getRoom().getRent_date_start())));
        activityStoprentEnddayTv.setText(String.format("%s", MyTextUtil.getDate(roomEntity.getRoom().getRent_date_end())));
        activityStartrentNetworkNumTv.setText(String.format("%s", roomEntity.getRoom().getNetwork_num()));
        activityStartrentNetworkProviderTv.setText(String.format("%s", roomEntity.getRoom().getNetwork_provider()));
        activityStartrentContractMonthsTv.setText(String.format("%s", roomEntity.getRoom().getContract_months() + "个月"));
        activityStoprentBeforewaterTv.setText(String.format("%s吨", roomEntity.getRoom().getWater_init()));
        activityStoprentBeforewpowerTv.setText(String.format("%s度", roomEntity.getRoom().getElectric_init()));
        activityStartrentKeyTv.setText(String.format("%s个", roomEntity.getRoom().getKeys()));
        activityStoprentOuttimeTv.setText(String.format("%s天", roomEntity.getRoom().getDays()));
        double i_needin = Integer
                .parseInt(MyTextUtil.getNumberFromString(activityStoprentOuttimeTv.getText().toString()));
        if (i_needin > 0) {
            double i_unit = Double.parseDouble(MyTextUtil.getNumberFromString(roomEntity.getRoom().getRental()));
            double all = i_unit * i_needin / 30 + i_unit * i_needin % 30 / 30;
            activityStoprentNeedinTv.setText(String.format("￥ %s元", all));
        } else {
            activityStoprentNeedinTv.setText("￥ 0元");
        }


        if (roomEntity.getRoom().getPhone() != null && roomEntity.getRoom().getPhone().size() > 0)
            activityStoprentReceverphoneTv.setText(String.format("%s", roomEntity.getRoom().getPhone().get(0)));
        else
            activityStoprentReceverphoneTv.setText(String.format("%s", ""));
        if (roomEntity.getRoom().getLodger() != null) {
            activityStoprentNameTv.setText(String.format("%s", roomEntity.getRoom().getLodger().getName()));
        } else {
            activityStoprentNameTv.setText(String.format("%s", "无房客"));
        }

        totleGetPrice();
        String login_response = sp.getString("Login_response", "");
        LoginEntity loginEntity = gson.fromJson(JsonParsing.getData(login_response), LoginEntity.class);
        for (LoginEntity.AdminBean.BuildingsBean buildingsBean : loginEntity.getAdmin().getBuildings()) {
            if (roomEntity.getRoom().getBuilding_id() == buildingsBean.getId()) {
                activityStoprentFloorTv.setText(buildingsBean.getName());
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("activityStoprentNowwaterTv", activityStoprentNowwaterTv.getText().toString());
        outState.putString("activityStoprentNowpowerTv", activityStoprentNowpowerTv.getText().toString());
        outState.putString("activityStoprentOtherinTv", activityStoprentOtherinTv.getText().toString());
        outState.putString("activityStoprentOtheroutTv", activityStoprentOtheroutTv.getText().toString());
        outState.putString("activityStoprentReceverphoneTv", activityStoprentReceverphoneTv.getText().toString());
        outState.putString("activityStoprentOtherphoneTv", activityStoprentOtherphoneTv.getText().toString());
        outState.putString("test", "test");
        sp.edit().putInt("activity_close_type", 2).commit();
        if (roomEntity != null)
            outState.putSerializable("RoomEntity", roomEntity);
        MyLogUtils.e("onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
