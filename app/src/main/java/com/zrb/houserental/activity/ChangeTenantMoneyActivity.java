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
import com.zrb.houserental.Entity.FloorEntity;
import com.zrb.houserental.R;
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
    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;

    private List<FloorEntity> itemEntities;
    private int type = -1;//0 楼号 1房号 2性别


    @Override
    public void init() {
        addConView(R.layout.activity_changetenantmoney);

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
            case R.id.activity_addtenant_room:
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


        MyLogUtils.e("sssss");

    }
}
