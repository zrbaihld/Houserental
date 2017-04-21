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
import com.zrb.baseapp.tools.JsonParsing;
import com.zrb.baseapp.tools.MyHttpTool;
import com.zrb.baseapp.tools.MyLogUtils;
import com.zrb.baseapp.tools.TextUtil;
import com.zrb.houserental.Entity.FloorEntity;
import com.zrb.houserental.Entity.ResultTenantQueryEntity;
import com.zrb.houserental.Entity.RoomEntity;
import com.zrb.houserental.R;
import com.zrb.houserental.constant.URL_Constant;
import com.zrb.houserental.dialog.DialogUntil;
import com.zrb.houserental.dialog.SelectFloorDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/1.
 */

public class ChangeTenantMoneyActivity extends BaseActivity {
    @BindView(R.id.activity_addtenant_floor_tv)
    TextView activityAddtenantFloorTv;
    @BindView(R.id.activity_addtenant_room_tv)
    TextView activityAddtenantRoomTv;
    @BindView(R.id.activity_startrent_unity_tv)
    TextView activityStartrentUnityTv;
    @BindView(R.id.activity_startrent_water_tv)
    TextView activityStartrentWaterTv;
    @BindView(R.id.activity_startrent_power_tv)
    TextView activityStartrentPowerTv;
    @BindView(R.id.activity_addtenant_month_tv)
    EditText activityAddtenantMonthTv;
    @BindView(R.id.activity_addtenant_newwater_tv)
    EditText activityAddtenantNewwaterTv;
    @BindView(R.id.activity_addtenant_newpower_tv)
    EditText activityAddtenantNewpowerTv;
    @BindView(R.id.scrollview)
    View scrollview;

    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;

    private List<FloorEntity> itemEntities;
    private int type = -1;//0 楼号 1房号 2性别


    private String building_id = "";
    private String building_name = "";
    private String room_id = "";
    private String room_name = "";

    private RoomEntity roomEntity;

    @Override
    public void init() {
        addConView(R.layout.activity_changetenantmoney);
        ButterKnife.bind(this);

        titleTV.setText("租金变更");
        itemEntities = new ArrayList<>();
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

    @OnClick({R.id.activity_addtenant_floor, R.id.activity_addtenant_room, R.id.activity_roomquert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_addtenant_floor:
                type = 0;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityAddtenantFloorTv.setText(entity.getName());
                        activityAddtenantRoomTv.setText("请选择房号");
                        scrollview.setVisibility(View.GONE);
                        building_id = entity.getId();
                        building_name = entity.getName();
                        itemEntities.clear();
                        itemEntities.add(entity);
                    }
                });
                break;
            case R.id.activity_addtenant_room:
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
                        activityAddtenantRoomTv.setText(entity.getName());
                        MyHttpTool.creat(ChangeTenantMoneyActivity.this)
                                .setContent("building_id", building_id)
                                .setContent("room_id", room_id)
                                .postShowDialog(0, URL_Constant.room, ChangeTenantMoneyActivity.this);


                    }
                });
                break;
            case R.id.activity_roomquert_confirm:
                sendMessage();
                break;
        }
    }

    private void sendMessage() {
        String s_floor = activityAddtenantFloorTv.getText().toString();
        String s_room = activityAddtenantRoomTv.getText().toString();

        String s_month = activityAddtenantMonthTv.getText().toString();
        String s_water = activityAddtenantNewwaterTv.getText().toString();
        String s_power = activityAddtenantNewpowerTv.getText().toString();


        if ("请选择楼号".equals(s_floor)) {
            toastIfActive("未选择楼号");
            return;
        }
        if ("请选择房号".equals(s_room)) {
            toastIfActive("未选择房号");
            return;
        }
        if (roomEntity != null) {
            if (TextUtil.isEmptyString(s_month)) {
                s_month = roomEntity.getRoom().getRental() + "";
            } else {
                if (!(Double.parseDouble(s_month) > 0)) {
                    toastIfActive("月租必须大于0");
                }
            }
            if (TextUtil.isEmptyString(s_water)) {
                s_water = roomEntity.getRoom().getWater_rate() + "";
            } else {
                if (!(Double.parseDouble(s_water) > 0)) {
                    toastIfActive("水费必须大于0");
                }
            }
            if (TextUtil.isEmptyString(s_power)) {
                s_power = roomEntity.getRoom().getElectric_rate() + "";
            } else {
                if (!(Double.parseDouble(s_power) > 0)) {
                    toastIfActive("电费必须大于0");
                }
            }

            MyHttpTool.creat(ChangeTenantMoneyActivity.this)
                    .setContent("building_id", building_id)
                    .setContent("room_id", room_id)
                    .setContent("rental", s_month)
                    .setContent("water_rate", s_water)
                    .setContent("electric_rate", s_power)
                    .postShowDialog(1, URL_Constant.updateRoom, ChangeTenantMoneyActivity.this);
        }
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
                roomEntity = gson.fromJson(JsonParsing.getData(json), RoomEntity.class);
                if (roomEntity != null && roomEntity.getRoom() != null) {
                    scrollview.setVisibility(View.VISIBLE);
                    activityStartrentUnityTv.setText(String.format("￥ %s", roomEntity.getRoom().getRental()));
                    activityStartrentWaterTv.setText(String.format("￥ %s/吨", roomEntity.getRoom().getWater_rate()));
                    activityStartrentPowerTv.setText(String.format("￥ %s/度", roomEntity.getRoom().getElectric_rate()));
                } else {
                    scrollview.setVisibility(View.GONE);
                }
                break;
            case 1:
                toastIfActive("修改成功");
                activityStartrentUnityTv.setText(String.format("￥ %s",activityAddtenantMonthTv.getText().toString()));
                activityStartrentWaterTv.setText(String.format("￥ %s/吨",activityAddtenantNewwaterTv.getText().toString()));
                activityStartrentPowerTv.setText(String.format("￥ %s/度", activityAddtenantNewpowerTv.getText().toString()));
                break;
        }
        return false;
    }
}
