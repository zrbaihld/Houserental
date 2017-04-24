package com.zrb.houserental.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zrb.baseapp.base.BaseActivity;
import com.zrb.baseapp.base.BaseRecyclerViewAdapter;
import com.zrb.baseapp.constant.Constant_C;
import com.zrb.baseapp.tools.JsonParsing;
import com.zrb.baseapp.tools.TextUtil;
import com.zrb.houserental.Entity.LoginEntity;
import com.zrb.houserental.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.base_toolbar_title)
    TextView baseToolbarTitle;
    @BindView(R.id.base_toolbar_setting)
    ImageView baseToolbarSetting;
    @BindView(R.id.activity_main_houseperson_qury)
    View activityMainHousepersonQury;
    @BindView(R.id.activity_main_housenull_qury)
    View activityMainHousenullQury;
    @BindView(R.id.activity_main_houseintime_qury)
    View activityMainHouseintimeQury;
    @BindView(R.id.view_iv)
    ImageView viewIv;
    @BindView(R.id.view_listview)
    XRecyclerView viewListview;

    private MyAdapter myAdapter;
    private String[] item_s = {"房客起租", "房客续租", "房客退租",
            "新增房客", "租金变更", "短信查询"};
    private int[] img_i = {R.drawable.btn_sy_icon_pm, R.drawable.btn_sy_icon_xf, R.drawable.btn_sy_icon_zf,
            R.drawable.btn_sy_icon_fj, R.drawable.btn_sy_icon_mf, R.drawable.btn_sy_icon_bnzf};
    private Class[] activity_class = {TenantStartRentActivity.class, TenantContinueRentActivity.class, TenantStopRentActivity.class
            , AddTenantActivity.class, ChangeTenantMoneyActivity.class, ShotMessageQuertActivity.class};


    private LoginEntity loginEntity;


    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LayoutManager layoutManager = new GridLayoutManager(this, 3);
        viewListview.setLayoutManager(layoutManager);
        viewListview.setLoadingMoreEnabled(false);
        viewListview.setPullRefreshEnabled(false);
        viewListview.setOverScrollMode(View.OVER_SCROLL_NEVER);

        List<ItemObject> strings = new ArrayList<>();
        int i = 0;
        for (String item_ : item_s) {
            ItemObject itemObject = new ItemObject();
            itemObject.setTitle(item_);
            itemObject.setIma(img_i[i]);
            itemObject.setPostion(i++);
            strings.add(itemObject);
        }
        myAdapter = new MyAdapter(strings);
        viewListview.setAdapter(myAdapter);

        initDate();
    }

    private void initDate() {
        String login_response = sp.getString("Login_response", "");
        if (TextUtil.isEmptyString(login_response)) {
            intent = new Intent(this, LoginEntity.class);
            startActivity(intent);
            finish();
        }
        loginEntity = gson.fromJson(JsonParsing.getData(login_response), LoginEntity.class);

        Constant_C.AID = loginEntity.getAdmin().getId()+"";
        Constant_C.TOKEN = loginEntity.getToken();

        if (loginEntity != null && loginEntity.getAdmin() != null) {
            baseToolbarTitle.setText(String.format("管理员:%s", loginEntity.getAdmin().getUsername()));
        } else {

        }

    }

    @Override
    public void setListenner() {

    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.adapter_mainactivity_item_list_rl) {
            int postion = (int) v.getTag();
            intent = new Intent(this, activity_class[postion]);
            startActivity(intent);
        }
    }


    @OnClick({R.id.base_toolbar_setting, R.id.activity_main_houseperson_qury, R.id.activity_main_housenull_qury, R.id.activity_main_houseintime_qury})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_toolbar_setting:
                intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_main_houseperson_qury:
                intent = new Intent(this, TenantQueryActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_main_housenull_qury:
                intent = new Intent(this, RoomQuertActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_main_houseintime_qury:
                intent = new Intent(this, ExpireQuertActivity.class);
                startActivity(intent);
                break;
        }
    }


    private class MyAdapter extends BaseRecyclerViewAdapter<ItemObject, MyViewHolder> {


        /**
         * @param list the datas to attach the adapter
         */
        public MyAdapter(List<ItemObject> list) {
            super(list);
        }

        @Override
        protected void bindDataToItemView(MyViewHolder myViewHolder, ItemObject itemObject) {
            myViewHolder.setText(R.id.adapter_mainactivity_item_list_tv, itemObject.getTitle())
                    .setTag(R.id.adapter_mainactivity_item_list_rl, itemObject.getPostion())
                    .setImageResource(R.id.adapter_mainactivity_item_list_iv, itemObject.getIma())
                    .setOnClickListener(R.id.adapter_mainactivity_item_list_rl, MainActivity.this)
            ;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(inflateItemView(parent, R.layout.adapter_mainactivity_list_item));
        }
    }

    private class MyViewHolder extends BaseRecyclerViewAdapter.SparseArrayViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class ItemObject {
        String title;
        int ima;
        int postion;

        public int getPostion() {
            return postion;
        }

        public void setPostion(int postion) {
            this.postion = postion;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIma() {
            return ima;
        }

        public void setIma(int ima) {
            this.ima = ima;
        }
    }
}
