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
    private FloorAdapter floorAdapter;
    private SelectFloorDialog selectFloorDialog;
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
                showSelectFloorDialog();
                break;
            case R.id.activity_tenantquert_change_room:
                itemEntities.clear();
                for (int i = 0; i < 10; i++) {
                    FloorEntity itemEntity = new FloorEntity();
                    itemEntity.setName("测试" + i);
                    itemEntities.add(itemEntity);
                }
                type = 1;
                showSelectFloorDialog();
                break;
            case R.id.activity_changepassword_confirm:
                intent = new Intent(this, ResultTenantQueryActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void showSelectFloorDialog() {
        switch (type) {
            case 0:
                selectFloorDialog = new SelectFloorDialog(this, "选择楼号", new SelectFloorDialog.RecyclerViewInterface() {
                    @Override
                    public void initRecycleView(XRecyclerView xRecyclerView) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(TenantQueryActivity.this);
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
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(TenantQueryActivity.this, 4);
                        xRecyclerView.setLayoutManager(gridLayoutManager);
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
                                        activityTenantquertChangeFloorTv.setText(item.getName());
                                        break;
                                    case 1:
                                        activityTenantquertChangeRoomTv.setText(item.getName());
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
