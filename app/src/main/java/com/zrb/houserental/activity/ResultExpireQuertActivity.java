package com.zrb.houserental.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.base.BaseRecyclerViewAdapter;
import com.zrb.baseapp.tools.JsonParsing;
import com.zrb.baseapp.tools.MyHttpTool;
import com.zrb.houserental.Entity.ResultRoomQuertEntity;
import com.zrb.houserental.Entity.ResultRoomQuertEntity.RoomsBean;
import com.zrb.houserental.R;
import com.zrb.houserental.constant.URL_Constant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/1.
 */

public class ResultExpireQuertActivity extends BaseActivity {
    @BindView(R.id.activity_resulttenantquery_title)
    TextView activityResulttenantqueryTitle;
    @BindView(R.id.view_iv)
    ImageView viewIv;
    @BindView(R.id.view_listview)
    XRecyclerView viewListview;


    private MyAdapter myAdapter;
    private List<RoomsBean> entities = new ArrayList<>();

    @Override
    public void init() {
        addConView(R.layout.activity_resulttenantquery);
        ButterKnife.bind(this);

        titleTV.setText("查询结果");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewListview.setLayoutManager(linearLayoutManager);


        myAdapter = new MyAdapter(entities);
        viewListview.setAdapter(myAdapter);
    }

    @Override
    public void setListenner() {

    }

    @Override
    public void getData() {
        String building_id = getIntent().getStringExtra("building_id");
        String building_name = getIntent().getStringExtra("building_name");
        activityResulttenantqueryTitle.setText(String.format("%s楼   ", building_name));

        MyHttpTool.creat(this)
                .setContent("building_id", building_id)
                .setContent("type", 1)
                .postShowDialog(0, URL_Constant.query, this);
    }

    @Override
    public void onClick(View v) {

    }


    private class MyViewHolder extends BaseRecyclerViewAdapter.SparseArrayViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class MyAdapter extends BaseRecyclerViewAdapter<RoomsBean, MyViewHolder
            > {

        /**
         * @param list the datas to attach the adapter
         */
        public MyAdapter(List<RoomsBean> list) {
            super(list);
        }

        @Override
        protected void bindDataToItemView(MyViewHolder myViewHolder, RoomsBean item) {
            myViewHolder.setText(R.id.adapter_resultroomquert_item_room, String.format("房号:%s", item.getName()))
                    .setText(R.id.adapter_resultroomquert_item_day, String.format("超期%s天", item.getDays()))
            ;

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(inflateItemView(parent, R.layout.adapter_resultroomquert_item));
        }
    }

    @Override
    public boolean getIOAuthCallBack(int type, String json, boolean isSuccess) {
        if (super.getIOAuthCallBack(type, json, isSuccess)) return true;
        switch (type) {
            case 0:
                ResultRoomQuertEntity resultRoomQuertEntity = gson.
                        fromJson(JsonParsing.getData(json), ResultRoomQuertEntity.class);
                entities.clear();
                if (resultRoomQuertEntity.getRooms() == null || resultRoomQuertEntity.getRooms().size() == 0) {
                    viewIv.setVisibility(View.VISIBLE);
                    viewListview.setVisibility(View.GONE);
                } else {
                    viewIv.setVisibility(View.GONE);
                    viewListview.setVisibility(View.VISIBLE);
                    entities.addAll(resultRoomQuertEntity.getRooms());
                    Collections.sort(entities, new Comparator<RoomsBean>() {
                        @Override
                        public int compare(RoomsBean roomsBean, RoomsBean t1) {
                            if (roomsBean.getDays() < t1.getDays())
                                return 1;
                            else if (roomsBean.getDays() > t1.getDays())
                                return -1;
                            return 0;
                        }
                    });
                    myAdapter.notifyDataSetChanged();
                }

                break;
        }
        return false;
    }
}
