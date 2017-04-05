package com.zrb.houserental.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.base.BaseRecyclerViewAdapter;
import com.zrb.houserental.Entity.ResultRoomQuertEntity;
import com.zrb.houserental.Entity.ResultTenantQueryEntity;
import com.zrb.houserental.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/1.
 */

public class ResultRoomQuertActivity extends BaseActivity {
    @BindView(R.id.activity_resulttenantquery_title)
    TextView activityResulttenantqueryTitle;
    @BindView(R.id.view_iv)
    ImageView viewIv;
    @BindView(R.id.view_listview)
    XRecyclerView viewListview;

    private MyAdapter myAdapter;
    private List<ResultRoomQuertEntity> entities = new ArrayList<>();

    @Override
    public void init() {
        addConView(R.layout.activity_resulttenantquery);
        ButterKnife.bind(this);

        titleTV.setText("查询结果");

        activityResulttenantqueryTitle.setText(String.format("%s楼    %s号", "五", "七"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewListview.setLayoutManager(linearLayoutManager);


        for (int i = 0; i < 10; i++) {
            ResultRoomQuertEntity resultTenantQueryEntity = new ResultRoomQuertEntity();
            entities.add(resultTenantQueryEntity);
        }
        myAdapter = new MyAdapter(entities);
        viewListview.setAdapter(myAdapter);
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


    private class MyViewHolder extends BaseRecyclerViewAdapter.SparseArrayViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class MyAdapter extends BaseRecyclerViewAdapter<ResultRoomQuertEntity, MyViewHolder> {

        /**
         * @param list the datas to attach the adapter
         */
        public MyAdapter(List<ResultRoomQuertEntity> list) {
            super(list);
        }

        @Override
        protected void bindDataToItemView(MyViewHolder myViewHolder, ResultRoomQuertEntity item) {
            myViewHolder.setText(R.id.adapter_resultroomquert_item_room, String.format("房号:%s", "周桐同"))
                    .setText(R.id.adapter_resultroomquert_item_day, String.format("空置%s天", "11"))
            ;

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(inflateItemView(parent, R.layout.adapter_resultroomquert_item));
        }
    }
}
