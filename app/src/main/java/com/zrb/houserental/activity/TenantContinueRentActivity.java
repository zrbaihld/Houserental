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
import com.zrb.houserental.dialog.SelectFloorDialog;

import java.util.ArrayList;
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
    TextView activityContinuerentRemarkTv;
    @BindView(R.id.activity_continuerent_receverphone_tv)
    TextView activityContinuerentReceverphoneTv;
    @BindView(R.id.activity_continuerent_otherphone_tv)
    EditText activityContinuerentOtherphoneTv;
    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;


    private List<FloorEntity> itemEntities;
    private FloorAdapter floorAdapter;
    private SelectFloorDialog selectFloorDialog;
    private int type = -1;//0 楼号 1房号 2预付月数 3电话

    @Override
    public void init() {
        addConView(R.layout.activity_continuerent);
        ButterKnife.bind(this);

        titleTV.setText("房客续租");
        itemEntities = new ArrayList<>();
        floorAdapter = new FloorAdapter(itemEntities);
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


    @OnClick({R.id.activity_continuerent_floor, R.id.activity_continuerent_room, R.id.activity_continuerent_name, R.id.activity_continuerent_advancemonths, R.id.activity_continuerent_remark, R.id.activity_continuerent_receverphone, R.id.activity_roomquert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_continuerent_floor:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("楼号" + i);
                    itemEntities.add(itemEntity);
                }
                type = 0;
                showSelectFloorDialog();
                break;
            case R.id.activity_continuerent_room:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("房号" + i);
                    itemEntities.add(itemEntity);
                }
                type = 1;
                showSelectFloorDialog();
                break;
            case R.id.activity_continuerent_name:
                break;
            case R.id.activity_continuerent_advancemonths:
                itemEntities.clear();
                for (int i = 0; i < 12; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("月数" + i);
                    itemEntities.add(itemEntity);
                }
                type = 2;
                showSelectFloorDialog();
                break;
            case R.id.activity_continuerent_remark:
                break;
            case R.id.activity_continuerent_receverphone:
                itemEntities.clear();
                for (int i = 0; i < 12; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("手机" + i);
                    itemEntities.add(itemEntity);
                }
                type = 3;
                showSelectFloorDialog();
                break;
            case R.id.activity_roomquert_confirm:
                break;
        }
    }

    private void showSelectFloorDialog() {
        switch (type) {
            case 0:
                selectFloorDialog = new SelectFloorDialog(this, "选择楼号", new SelectFloorDialog.RecyclerViewInterface() {
                    @Override
                    public void initRecycleView(XRecyclerView xRecyclerView) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(TenantContinueRentActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        xRecyclerView.setLayoutManager(layoutManager);
                        xRecyclerView.setAdapter(floorAdapter);
                    }
                });
                break;
            case 1:
                selectFloorDialog = new SelectFloorDialog(this, "选择房号", new SelectFloorDialog.RecyclerViewInterface() {
                    @Override
                    public void initRecycleView(XRecyclerView xRecyclerView) {
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(TenantContinueRentActivity.this, 4);
                        xRecyclerView.setLayoutManager(gridLayoutManager);
                        xRecyclerView.setAdapter(floorAdapter);
                    }
                });
                break;
            case 2:
                selectFloorDialog = new SelectFloorDialog(this, "选择预付月数", new SelectFloorDialog.RecyclerViewInterface() {
                    @Override
                    public void initRecycleView(XRecyclerView xRecyclerView) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(TenantContinueRentActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        xRecyclerView.setLayoutManager(layoutManager);
                        xRecyclerView.setAdapter(floorAdapter);
                    }
                });
                break;
            case 3:
                selectFloorDialog = new SelectFloorDialog(this, "选择接收手机", new SelectFloorDialog.RecyclerViewInterface() {
                    @Override
                    public void initRecycleView(XRecyclerView xRecyclerView) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(TenantContinueRentActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        xRecyclerView.setLayoutManager(layoutManager);
                        xRecyclerView.setAdapter(floorAdapter);
                    }
                });
                break;
            default:
                selectFloorDialog = null;
                break;
        }
        if (selectFloorDialog != null)
            selectFloorDialog.show();
    }


    private class MyViewHolder extends BaseRecyclerViewAdapter.SparseArrayViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class FloorAdapter extends BaseRecyclerViewAdapter<FloorEntity, MyViewHolder> {

        /**
         * @param list the datas to attach the adapter
         */
        public FloorAdapter(List<FloorEntity> list) {
            super(list);
        }

        @Override
        protected void bindDataToItemView(MyViewHolder myViewHolder, FloorEntity item) {
            myViewHolder.setText(R.id.adapter_tenantquery_item_title, item.getName())
                    .setTag(R.id.adapter_tenantquery_item_title, item)
                    .setOnClickListener(R.id.adapter_tenantquery_item_title, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (selectFloorDialog != null) {
                                FloorEntity item = (FloorEntity) view.getTag();
                                switch (type) {
                                    case 0:
                                        activityContinuerentFloorTv.setText(item.getName());
                                        break;
                                    case 1:
                                        activityContinuerentRoomTv.setText(item.getName());
                                        break;
                                    case 2:
                                        activityContinuerentAdvancemonthsTv.setText(item.getName());
                                        break;
                                    case 3:
                                        activityContinuerentReceverphoneTv.setText(item.getName());
                                        break;
                                }
                                selectFloorDialog.dismiss();
                            }
                        }
                    });
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(inflateItemView(parent, R.layout.adapter_tenantquery_item));
        }
    }


}
