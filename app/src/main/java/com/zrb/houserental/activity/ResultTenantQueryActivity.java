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
import com.zrb.baseapp.constant.Constant_C;
import com.zrb.baseapp.tools.JsonParsing;
import com.zrb.baseapp.tools.MyHttpTool;
import com.zrb.houserental.Entity.LoginEntity;
import com.zrb.houserental.Entity.ResultTenantQueryEntity;
import com.zrb.houserental.Entity.ResultTenantQueryEntity.LodgersBean;
import com.zrb.houserental.R;
import com.zrb.houserental.constant.URL_Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/1.
 */

public class ResultTenantQueryActivity extends BaseActivity {
    @BindView(R.id.activity_resulttenantquery_title)
    TextView activityResulttenantqueryTitle;
    @BindView(R.id.view_iv)
    ImageView viewIv;
    @BindView(R.id.view_listview)
    XRecyclerView viewListview;

    private MyAdapter myAdapter;
    private List<LodgersBean> entities = new ArrayList<>();
    String building_name;

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

        intent = getIntent();
        String building_id = intent.getStringExtra("building_id");
        building_name = intent.getStringExtra("building_name");
        String room_id = intent.getStringExtra("room_id");
        String room_name = intent.getStringExtra("room_name");


        activityResulttenantqueryTitle.setText(String.format("%s号", room_name));

        MyHttpTool.creat(this)
                .setContent("building_id", building_id)
                .setContent("room_id", room_id)
                .postShowDialog(0, URL_Constant.listLodger, this);
    }

    @Override
    public void onClick(View v) {

    }


    private class MyViewHolder extends BaseRecyclerViewAdapter.SparseArrayViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class MyAdapter extends BaseRecyclerViewAdapter<LodgersBean, MyViewHolder> {

        /**
         * @param list the datas to attach the adapter
         */
        public MyAdapter(List<LodgersBean> list) {
            super(list);
        }

        @Override
        protected void bindDataToItemView(MyViewHolder myViewHolder, LodgersBean item) {
            myViewHolder.setText(R.id.adapter_resulttenantquery_name, String.format("房客:%s", item.getName()))
                    .setText(R.id.adapter_resulttenantquery_phone, String.format("手机号码:%s", item.getPhone()))
                    .setStatus(R.id.adapter_resulttenantquery_state, item.getStatus())
                    .setTag(R.id.adapter_resulttenantquery_ll, item)
                    .setOnClickListener(R.id.adapter_resulttenantquery_ll, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LodgersBean item = (LodgersBean) view.getTag();
                            intent = new Intent(ResultTenantQueryActivity.this, TenantDetailActivity.class);
                            intent.putExtra("LodgersBean", item);
                            intent.putExtra("building_name", building_name);
                            startActivity(intent);
                        }
                    })
            ;

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(inflateItemView(parent, R.layout.adapter_resulttenantquery));
        }
    }

    @Override
    public boolean getIOAuthCallBack(int type, String json, boolean isSuccess) {
        if (super.getIOAuthCallBack(type, json, isSuccess)) return true;
        switch (type) {
            case 0:
                ResultTenantQueryEntity resultTenantQueryEntity = gson.
                        fromJson(JsonParsing.getData(json), ResultTenantQueryEntity.class);
                entities.clear();
                if (resultTenantQueryEntity.getLodgers() == null || resultTenantQueryEntity.getLodgers().size() == 0) {
                    viewIv.setVisibility(View.VISIBLE);
                    viewListview.setVisibility(View.GONE);
                } else {
                    viewIv.setVisibility(View.GONE);
                    viewListview.setVisibility(View.VISIBLE);
                    entities.addAll(resultTenantQueryEntity.getLodgers());
                    myAdapter.notifyDataSetChanged();
                }

                break;
        }

        return false;
    }
}
