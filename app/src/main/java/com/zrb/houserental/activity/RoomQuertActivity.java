package com.zrb.houserental.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class RoomQuertActivity extends BaseActivity {
    @BindView(R.id.next)
    ImageView next;
    @BindView(R.id.activity_roomquert_floor)
    RelativeLayout activityRoomquertFloor;
    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;
    @BindView(R.id.activity_roomquert_floor_tv)
    TextView activityRoomquertFloorTv;

    private List<FloorEntity> itemEntities;

    @Override
    public void init() {
        addConView(R.layout.activity_roomquert);
        ButterKnife.bind(this);
        titleTV.setText("空房查询");

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


    @OnClick({R.id.activity_roomquert_floor, R.id.activity_roomquert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_roomquert_floor:
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("测试数据" + i);
                    itemEntities.add(itemEntity);
                }
                DialogUntil.getInstance().selectString(this, 0, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityRoomquertFloorTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_roomquert_confirm:

                intent = new Intent(this, ResultRoomQuertActivity.class);
                startActivity(intent);
                break;
        }
    }


}
