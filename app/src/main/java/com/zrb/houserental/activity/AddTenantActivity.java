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
import com.zrb.baseapp.tools.JsonParsing;
import com.zrb.baseapp.tools.MyHttpTool;
import com.zrb.baseapp.tools.MyLogUtils;
import com.zrb.baseapp.tools.TextUtil;
import com.zrb.houserental.Entity.FloorEntity;
import com.zrb.houserental.Entity.ResultTenantQueryEntity;
import com.zrb.houserental.R;
import com.zrb.houserental.constant.URL_Constant;
import com.zrb.houserental.dialog.DialogUntil;
import com.zrb.houserental.dialog.SelectFloorDialog;
import com.zrb.houserental.util.MyTextUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/1.
 */

public class AddTenantActivity extends BaseActivity {


    @BindView(R.id.activity_addtenant_name_tv)
    EditText activityAddtenantNameTv;
    @BindView(R.id.activity_addtenant_sex_tv)
    TextView activityAddtenantSexTv;
    @BindView(R.id.activity_addtenant_id_tv)
    EditText activityAddtenantIdTv;
    @BindView(R.id.activity_addtenant_adress_tv)
    EditText activityAddtenantAdressTv;
    @BindView(R.id.activity_addtenant_birthday_tv)
    TextView activityAddtenantBirthdayTv;
    @BindView(R.id.activity_addtenant_phone_tv)
    EditText activityAddtenantPhoneTv;
    @BindView(R.id.activity_addtenant_floor_tv)
    TextView activityAddtenantFloorTv;
    @BindView(R.id.activity_addtenant_room_tv)
    TextView activityAddtenantRoomTv;
    @BindView(R.id.activity_addtenant_outtime_tv)
    TextView activityAddtenantOuttimeTv;

    private List<FloorEntity> itemEntities;
    private int type = -1;//0 楼号 1房号 2性别

    private String building_id = "";
    private String building_name = "";
    private String room_id = "";
    private String room_name = "";

    @Override
    public void init() {
        addConView(R.layout.activity_addtenant);
        ButterKnife.bind(this);

        titleTV.setText("新增房客");
        itemEntities = new ArrayList<>();
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


    @OnClick({R.id.activity_addtenant_sex, R.id.activity_addtenant_birthday, R.id.activity_addtenant_floor, R.id.activity_addtenant_room, R.id.activity_addtenant_outtime, R.id.activity_roomquert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_addtenant_floor:
                type = 0;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityAddtenantFloorTv.setText(entity.getName());
                        activityAddtenantRoomTv.setText("请选择房号");
                        building_id = entity.getId();
                        building_name = entity.getName();
                        itemEntities.clear();
                        itemEntities.add(entity);
                    }
                });
                break;
            case R.id.activity_addtenant_room:
                if (itemEntities == null || itemEntities.size() == 0) {
                    toastIfActive("请先选择楼号");
                    break;
                }
                type = 1;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityAddtenantRoomTv.setText(entity.getName());
                        room_id = entity.getId();
                        room_name = entity.getName();
                    }
                });
                break;
            case R.id.activity_addtenant_sex:
                List<FloorEntity> itemEntities = new ArrayList<>();
                FloorEntity itemEntity = new FloorEntity();
                itemEntity.setName("男");
                itemEntities.add(itemEntity);
                itemEntity = new FloorEntity();
                itemEntity.setName("女");
                itemEntities.add(itemEntity);
                type = 4;
                DialogUntil.getInstance().selectString(this, type, itemEntities, new DialogUntil.DialogUtilEntityDao() {
                    @Override
                    public void onPositiveActionClicked(FloorEntity entity) {
                        activityAddtenantSexTv.setText(entity.getName());
                    }
                });
                break;
            case R.id.activity_addtenant_birthday:
                DialogUntil.getInstance().selectDate(getSupportFragmentManager(), new DialogUntil.DialogUtilDateDao() {
                    @Override
                    public void onPositiveActionClicked(String date) {
                        activityAddtenantBirthdayTv.setText(date);
                    }
                });
                break;
            case R.id.activity_addtenant_outtime:
                DialogUntil.getInstance().selectDate(getSupportFragmentManager(), new DialogUntil.DialogUtilDateDao() {
                    @Override
                    public void onPositiveActionClicked(String date) {
                        activityAddtenantOuttimeTv.setText(date);
                    }
                });
                break;
            case R.id.activity_roomquert_confirm:
                sendMessage();
                break;
        }
    }

    private void sendMessage() {
        String s_name = activityAddtenantNameTv.getText().toString();
        String s_sex = activityAddtenantSexTv.getText().toString();
        String s_id = activityAddtenantIdTv.getText().toString();
        String s_adress = activityAddtenantAdressTv.getText().toString();
        String s_bitth = activityAddtenantBirthdayTv.getText().toString();
        String s_phone = activityAddtenantPhoneTv.getText().toString();
        String s_floor = activityAddtenantFloorTv.getText().toString();
        String s_room = activityAddtenantRoomTv.getText().toString();
        String s_outtime = activityAddtenantOuttimeTv.getText().toString();

        if (TextUtil.isEmptyString(s_name)) {
            toastIfActive("未输入房客姓名");
            return;
        }
        if (TextUtil.isEmptyString(s_id)) {
            toastIfActive("未输入房客身份证号码");
            return;
        }
        if (TextUtil.isEmptyString(s_adress)) {
            toastIfActive("未输入房客籍贯地址");
            return;
        }
        if (TextUtil.isEmptyString(s_phone)) {
            toastIfActive("未输入房客手机号码");
            return;
        }
        if ("请选择性别".equals(s_sex)) {
            toastIfActive("未选择性别");
            return;
        }
        if ("请选择出生年月".equals(s_bitth)) {
            toastIfActive("未选择出生年月");
            return;
        }
        if ("请选择楼号".equals(s_floor)) {
            toastIfActive("未选择楼号");
            return;
        }
        if ("请选择房号".equals(s_room)) {
            toastIfActive("未选择房号");
            return;
        }
        if ("请选择日期".equals(s_outtime)) {
            toastIfActive("未选择日期");
            return;
        }
//        building_id |int| 是 | 12 | 楼号id
//        room_id |int| 是 | 12 | 房号id
//        name | string | 是 | 12 |
//                id_card | string | 是 | 12 |
//                sex |int| 是 | 12 |
//                phone | string | 是 | 12 |
//                hometown | string | 是 | 12 |
//                birthday |date| 是 | 12 |
//        `residence_permit_expire` |date| 是 | 12 |

        MyHttpTool.creat(this)
                .setContent("building_id", building_id)
                .setContent("room_id", room_id)
                .setContent("name", s_name)
                .setContent("id_card", s_id)
                .setContent("sex", MyTextUtil.getSexInt(s_sex))
                .setContent("phone", s_phone)
                .setContent("hometown", s_adress)
                .setContent("birthday", s_bitth)
                .setContent("residence_permit_expire", s_outtime)
                .postShowDialog(0, URL_Constant.newLodger, this);
    }

    @Override
    public boolean getIOAuthCallBack(int type, String json, boolean isSuccess) {
        if (super.getIOAuthCallBack(type, json, isSuccess)) return true;
        switch (type) {
            case 0:
                toastIfActive("新增成功");
                clearUI();
                break;
        }

        return false;
    }

    private void clearUI() {
        activityAddtenantNameTv.setText("");
        activityAddtenantSexTv.setText("请选择性别");
        activityAddtenantIdTv.setText("");
        activityAddtenantAdressTv.setText("");
        activityAddtenantBirthdayTv.setText("请选择出生年月");
        activityAddtenantPhoneTv.setText("");
        activityAddtenantFloorTv.setText("请选择楼号");
        activityAddtenantRoomTv.setText("请选择房号");
        activityAddtenantOuttimeTv.setText("请选择日期");
        building_id = "";
        room_id = "";


    }
}
