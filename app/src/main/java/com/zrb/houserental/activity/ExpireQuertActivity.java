package com.zrb.houserental.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.tools.TextUtil;
import com.zrb.houserental.Entity.FloorEntity;
import com.zrb.houserental.R;
import com.zrb.houserental.dialog.DialogUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/1.
 */

public class ExpireQuertActivity extends BaseActivity {

    @BindView(R.id.activity_roomquert_floor)
    RelativeLayout activityRoomquertFloor;
    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;
    @BindView(R.id.activity_roomquert_floor_tv)
    TextView activityRoomquertFloorTv;

    private List<FloorEntity> itemEntities;


    private String building_id;
    private String building_name;

    @Override
    public void init() {
        addConView(R.layout.activity_roomquert);
        ButterKnife.bind(this);
        titleTV.setText("到期查询");

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

                DialogUntil.getInstance().selectString(this, 0, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityRoomquertFloorTv.setText(entity.getName());
                        building_id = entity.getId();
                        building_name=entity.getName();
                    }
                });
                break;
            case R.id.activity_roomquert_confirm:
                if (TextUtil.isEmptyString(building_id)) {
                    toastIfActive("未选楼号");
                    return;
                }
                intent = new Intent(this, ResultExpireQuertActivity.class);
                intent.putExtra("building_id", building_id);
                intent.putExtra("building_name", building_name);
                startActivity(intent);
                break;
        }
    }


}
