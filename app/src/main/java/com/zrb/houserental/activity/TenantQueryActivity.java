package com.zrb.houserental.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.base.BaseRecyclerViewAdapter;
import com.zrb.baseapp.constant.Constant_C;
import com.zrb.baseapp.tools.JsonParsing;
import com.zrb.baseapp.tools.TextUtil;
import com.zrb.houserental.Entity.FloorEntity;
import com.zrb.houserental.Entity.LoginEntity;
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

public class TenantQueryActivity extends BaseActivity {
    @BindView(R.id.activity_tenantquert_change_floor)
    RelativeLayout activityTenantquertChangeFloor;
    @BindView(R.id.activity_tenantquert_change_room)
    RelativeLayout activityTenantquertChangeRoom;
    @BindView(R.id.activity_changepassword_confirm)
    AppCompatButton activityChangepasswordConfirm;
    @BindView(R.id.activity_tenantquert_change_floor_tv)
    TextView activityTenantquertChangeFloorTv;
    @BindView(R.id.activity_tenantquert_change_room_tv)
    TextView activityTenantquertChangeRoomTv;

    private List<FloorEntity> itemEntities;
    private int type = -1;

    private String building_id = "";
    private String building_name = "";
    private String room_id = "";
    private String room_name = "";

    @Override
    public void init() {
        addConView(R.layout.activity_tenantquery);
        ButterKnife.bind(this);
        titleTV.setText("房客查询");
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


    @OnClick({R.id.activity_tenantquert_change_floor, R.id.activity_tenantquert_change_room, R.id.activity_changepassword_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_tenantquert_change_floor:
                type = 0;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityTenantquertChangeFloorTv.setText(entity.getName());
                        activityTenantquertChangeRoomTv.setText("请选择房号");
                        building_id = entity.getId();
                        building_name = entity.getName();
                        itemEntities.clear();
                        itemEntities.add(entity);
                    }
                });
                break;
            case R.id.activity_tenantquert_change_room:
                if (itemEntities == null ) {
                    toastIfActive("请先选择楼号");
                    break;
                }
                type = 1;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        room_id = entity.getId();
                        room_name = entity.getName();
                        activityTenantquertChangeRoomTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_changepassword_confirm:

                if (TextUtil.isEmptyString(building_id)) {
                    toastIfActive("还未选楼号");
                    return;
                }
                if (TextUtil.isEmptyString(room_id)) {
                    toastIfActive("还未选房号");
                    return;
                }
                intent = new Intent(this, ResultTenantQueryActivity.class);
                intent.putExtra("building_id", building_id);
                intent.putExtra("building_name", building_name);
                intent.putExtra("room_id", room_id);
                intent.putExtra("room_name", room_name);
                startActivity(intent);
                break;
        }
    }


}
