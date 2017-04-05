package com.zrb.houserental.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
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

public class ExpireQuertActivity extends BaseActivity {

    @BindView(R.id.activity_roomquert_floor)
    RelativeLayout activityRoomquertFloor;
    @BindView(R.id.activity_roomquert_confirm)
    AppCompatButton activityRoomquertConfirm;
    @BindView(R.id.activity_roomquert_floor_tv)
    TextView activityRoomquertFloorTv;

    private List<FloorEntity> itemEntities;
    private MyAdapter myAdapter;
    private SelectFloorDialog selectFloorDialog;

    @Override
    public void init() {
        addConView(R.layout.activity_roomquert);
        ButterKnife.bind(this);
        titleTV.setText("到期查询");

        itemEntities = new ArrayList<>();
        myAdapter = new MyAdapter(itemEntities);
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
                selectFloorDialog = new SelectFloorDialog(this, "选择楼号", new SelectFloorDialog.RecyclerViewInterface() {
                    @Override
                    public void initRecycleView(XRecyclerView xRecyclerView) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(ExpireQuertActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        xRecyclerView.setLayoutManager(layoutManager);
                        xRecyclerView.setAdapter(myAdapter);
                    }
                });
                break;
            case R.id.activity_roomquert_confirm:
                intent = new Intent(this, ResultExpireQuertActivity.class);
                startActivity(intent);
                break;
        }
    }


    private class MyViewHolder extends BaseRecyclerViewAdapter.SparseArrayViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class MyAdapter extends BaseRecyclerViewAdapter<FloorEntity, MyViewHolder> {

        /**
         * @param list the datas to attach the adapter
         */
        public MyAdapter(List<FloorEntity> list) {
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
                                activityRoomquertFloorTv.setText(item.getName());
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
