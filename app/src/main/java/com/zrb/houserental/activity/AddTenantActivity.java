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

public class AddTenantActivity extends BaseActivity {
    @BindView(R.id.activity_addtenant_floor_tv)
    TextView activityAddtenantFloorTv;
    @BindView(R.id.activity_addtenant_room_tv)
    TextView activityAddtenantRoomTv;
    @BindView(R.id.activity_addtenant_no_tv)
    TextView activityAddtenantNoTv;
    @BindView(R.id.activity_addtenant_unit_tv)
    TextView activityAddtenantUnitTv;
    @BindView(R.id.activity_addtenant_deposit_tv)
    EditText activityAddtenantDepositTv;
    @BindView(R.id.activity_addtenant_advancemonths_tv)
    TextView activityAddtenantAdvancemonthsTv;
    @BindView(R.id.activity_addtenant_water_tv)
    TextView activityAddtenantWaterTv;
    @BindView(R.id.activity_addtenant_power_tv)
    TextView activityAddtenantPowerTv;
    @BindView(R.id.activity_addtenant_startday_tv)
    TextView activityAddtenantStartdayTv;
    @BindView(R.id.activity_addtenant_endday_tv)
    EditText activityAddtenantEnddayTv;
    @BindView(R.id.activity_addtenant_waterstart_tv)
    EditText activityAddtenantWaterstartTv;
    @BindView(R.id.activity_addtenant_powerstart_tv)
    TextView activityAddtenantPowerstartTv;
    @BindView(R.id.activity_addtenant_remark_tv)
    TextView activityAddtenantRemarkTv;
    @BindView(R.id.activity_addtenant_allprice_tv)
    TextView activityAddtenantAllpriceTv;
    @BindView(R.id.activity_addtenant_key_tv)
    EditText activityAddtenantKeyTv;
    @BindView(R.id.activity_addtenant_phone_tv)
    EditText activityAddtenantPhoneTv;
    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;


    private List<FloorEntity> itemEntities;
    private FloorAdapter floorAdapter;
    private SelectFloorDialog selectFloorDialog;
    private int type = -1;

    @Override
    public void init() {
        addConView(R.layout.activity_addtenant);
        ButterKnife.bind(this);

        titleTV.setText("房客起租");
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.activity_addtenant_floor, R.id.activity_addtenant_room, R.id.activity_addtenant_advancemonths, R.id.activity_addtenant_startday, R.id.activity_roomquert_confirm})
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
                showSelectFloorDialog();
                break;
            case R.id.activity_addtenant_room:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("房号" + i);
                    itemEntities.add(itemEntity);
                }
                type = 1;
                showSelectFloorDialog();
                break;
            case R.id.activity_addtenant_advancemonths:
                itemEntities.clear();
                for (int i = 0; i < 12; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("月数" + i);
                    itemEntities.add(itemEntity);
                }
                type = 2;
                showSelectFloorDialog();
                break;
            case R.id.activity_addtenant_startday:

                break;
            case R.id.activity_roomquert_confirm://发短信

                break;
        }
    }

    private void showSelectFloorDialog() {
        switch (type) {
            case 0:
                selectFloorDialog = new SelectFloorDialog(this, "选择楼号", new SelectFloorDialog.RecyclerViewInterface() {
                    @Override
                    public void initRecycleView(XRecyclerView xRecyclerView) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(AddTenantActivity.this);
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
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(AddTenantActivity.this, 4);
                        xRecyclerView.setLayoutManager(gridLayoutManager);
                        xRecyclerView.setAdapter(floorAdapter);
                    }
                });
                break;
            case 2:
                selectFloorDialog = new SelectFloorDialog(this, "选择预付月数", new SelectFloorDialog.RecyclerViewInterface() {
                    @Override
                    public void initRecycleView(XRecyclerView xRecyclerView) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(AddTenantActivity.this);
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
                                        activityAddtenantFloorTv.setText(item.getName());
                                        break;
                                    case 1:
                                        activityAddtenantRoomTv.setText(item.getName());
                                        break;
                                    case 2:
                                        activityAddtenantAdvancemonthsTv.setText(item.getName());
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
