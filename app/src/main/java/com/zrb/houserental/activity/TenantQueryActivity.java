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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

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
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("测试数据" + i);
                    itemEntities.add(itemEntity);
                }
                type = 0;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityTenantquertChangeFloorTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_tenantquert_change_room:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("测试" + i);
                    itemEntities.add(itemEntity);
                }
                type = 1;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityTenantquertChangeRoomTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_changepassword_confirm:
                intent = new Intent(this, ResultTenantQueryActivity.class);
                startActivity(intent);
                break;
        }
    }


}
