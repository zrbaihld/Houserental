package com.zrb.houserental.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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

public class TenantStopRentActivity extends BaseActivity {
    @BindView(R.id.activity_stoprent_floor_tv)
    TextView activityStoprentFloorTv;
    @BindView(R.id.next)
    ImageView next;
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
    EditText activityStoprentOuttimeTv;
    @BindView(R.id.activity_stoprent_needin_tv)
    TextView activityStoprentNeedinTv;
    @BindView(R.id.activity_stoprent_allneedin_tv)
    TextView activityStoprentAllneedinTv;
    @BindView(R.id.activity_stoprent_remark_tv)
    TextView activityStoprentRemarkTv;
    @BindView(R.id.activity_startrent_key_tv)
    EditText activityStartrentKeyTv;
    @BindView(R.id.activity_stoprent_receverphone_tv)
    TextView activityStoprentReceverphoneTv;
    @BindView(R.id.activity_stoprent_otherphone_tv)
    EditText activityStoprentOtherphoneTv;

    private List<FloorEntity> itemEntities;
    private FloorAdapter floorAdapter;
    private SelectFloorDialog selectFloorDialog;
    private int type = -1;//0 楼号 1房号 2  3电话

    @Override
    public void init() {
        addConView(R.layout.activity_stoprent);
        ButterKnife.bind(this);

        titleTV.setText("房客退租");
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


    @OnClick({R.id.activity_stoprent_floor, R.id.activity_stoprent_room, R.id.activity_stoprent_receverphone, R.id.activity_roomquert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_stoprent_floor:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("楼号" + i);
                    itemEntities.add(itemEntity);
                }
                type = 0;
                showSelectFloorDialog();
                break;
            case R.id.activity_stoprent_room:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("房号" + i);
                    itemEntities.add(itemEntity);
                }
                type = 1;
                showSelectFloorDialog();
                break;
            case R.id.activity_stoprent_receverphone:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("电话" + i);
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
                        LinearLayoutManager layoutManager = new LinearLayoutManager(TenantStopRentActivity.this);
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
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(TenantStopRentActivity.this, 4);
                        xRecyclerView.setLayoutManager(gridLayoutManager);
                        xRecyclerView.setAdapter(floorAdapter);
                    }
                });
                break;
            case 2:
                selectFloorDialog = new SelectFloorDialog(this, "选择预付月数", new SelectFloorDialog.RecyclerViewInterface() {
                    @Override
                    public void initRecycleView(XRecyclerView xRecyclerView) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(TenantStopRentActivity.this);
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
                        LinearLayoutManager layoutManager = new LinearLayoutManager(TenantStopRentActivity.this);
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
                                        activityStoprentFloorTv.setText(item.getName());
                                        break;
                                    case 1:
                                        activityStoprentRoomTv.setText(item.getName());
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        activityStoprentReceverphoneTv.setText(item.getName());
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
