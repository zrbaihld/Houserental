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
    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;

    private List<FloorEntity> itemEntities;
    private int type = -1;//0 楼号 1房号 2编号

    @Override
    public void init() {
        addConView(R.layout.activity_shotmessagequert);
        ButterKnife.bind(this);

        titleTV.setText("短信查询");
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

    @OnClick({R.id.activity_shotmessagequert_floor, R.id.activity_shotmessagequert_room, R.id.activity_shotmessagequert_no, R.id.activity_shotmessagequert_receverphone, R.id.activity_roomquert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_shotmessagequert_floor:
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
                        activityShotmessagequertFloorTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_shotmessagequert_room:
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
                        activityShotmessagequertRoomTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_shotmessagequert_no:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("编号" + i);
                    itemEntities.add(itemEntity);
                }
                type = 5;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityShotmessagequertNoTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_shotmessagequert_receverphone:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("手机" + i);
                    itemEntities.add(itemEntity);
                }
                type = 3;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityShotmessagequertNoTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_roomquert_confirm:
                sendShotMessage();
                break;
        }
    }

    private void sendShotMessage() {

    }

}
