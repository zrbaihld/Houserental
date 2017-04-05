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
import com.zrb.houserental.Entity.ResultTenantQueryEntity;
import com.zrb.houserental.R;

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
    private List<ResultTenantQueryEntity> entities = new ArrayList<>();

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
            ResultTenantQueryEntity resultTenantQueryEntity = new ResultTenantQueryEntity();
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

    private class MyAdapter extends BaseRecyclerViewAdapter<ResultTenantQueryEntity, MyViewHolder> {

        /**
         * @param list the datas to attach the adapter
         */
        public MyAdapter(List<ResultTenantQueryEntity> list) {
            super(list);
        }

        @Override
        protected void bindDataToItemView(MyViewHolder myViewHolder, ResultTenantQueryEntity item) {
            myViewHolder.setText(R.id.adapter_resulttenantquery_name, String.format("房客:%s", "周桐同"))
                    .setText(R.id.adapter_resulttenantquery_phone, String.format("手机号码:%s", "15659810000"))
                    .setText(R.id.adapter_resulttenantquery_state, String.format("状态:%s", "租住中"))
                    .setTextColor(R.id.adapter_resulttenantquery_state, ContextCompat.getColor(ResultTenantQueryActivity.this, R.color.tenant_on))
                    .setTag(R.id.adapter_resulttenantquery_ll, item)
                    .setOnClickListener(R.id.adapter_resulttenantquery_ll, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ResultTenantQueryEntity item = (ResultTenantQueryEntity) view.getTag();
                            intent=new Intent(ResultTenantQueryActivity.this,TenantDetailActivity.class);
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

}
