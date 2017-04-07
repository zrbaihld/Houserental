package com.zrb.houserental.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zrb.baseapp.base.BaseRecyclerViewAdapter;
import com.zrb.baseapp.tools.MyLogUtils;
import com.zrb.houserental.Entity.FloorEntity;
import com.zrb.houserental.R;
import com.zrb.houserental.activity.TenantContinueRentActivity;
import com.zrb.houserental.activity.TenantStartRentActivity;
import com.zrb.houserental.meterial.app.DatePickerDialog;
import com.zrb.houserental.meterial.app.Dialog;
import com.zrb.houserental.meterial.app.DialogFragment;
import com.zrb.houserental.meterial.app.ThemeManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */

public class DialogUntil {
    private List<FloorEntity> itemEntities;
    private FloorAdapter floorAdapter;
    private SelectFloorDialog selectFloorDialog;
    private DialogUtilEntityDao dao;

    private static DialogUntil dialogUntil;

    public static DialogUntil getInstance() {
        if (dialogUntil == null)
            dialogUntil = new DialogUntil();
        return dialogUntil;
    }


    public interface DialogUtilDateDao {
        void onPositiveActionClicked(String date);
    }

    public interface DialogUtilEntityDao {
        void onPositiveActionClicked(FloorEntity entity);
    }

    public void selectDate(FragmentManager manager, final DialogUtilDateDao dao) {
        boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
        Dialog.Builder builder = new DatePickerDialog.Builder(isLightTheme ? R.style.Material_App_Dialog_DatePicker_Light : R.style.Material_App_Dialog_DatePicker) {
            @Override
            public void onPositiveActionClicked(DialogFragment fragment) {
                DatePickerDialog dialog = (DatePickerDialog) fragment.getDialog();
                String date = dialog.getFormattedDate(SimpleDateFormat.getDateInstance());
                dao.onPositiveActionClicked(date);
                super.onPositiveActionClicked(fragment);
            }

            @Override
            public void onNegativeActionClicked(DialogFragment fragment) {
//                Toast.makeText(mActivity, "Cancelled", Toast.LENGTH_SHORT).show();
                dao.onPositiveActionClicked("");
                super.onNegativeActionClicked(fragment);
            }
        };
        builder.positiveAction("OK")
                .negativeAction("CANCEL");
        DialogFragment fragment = DialogFragment.newInstance(builder);
        fragment.show(manager, null);
    }

    public void selectString(final Context context, int type, List<FloorEntity> items, DialogUtilEntityDao dao) {
        this.dao = dao;
        if (itemEntities == null)
            itemEntities = new ArrayList<>();
        if (floorAdapter == null)
            floorAdapter = new FloorAdapter(itemEntities);
        itemEntities.clear();
        itemEntities.addAll(items);
        String title = "提示";
        if (type == 2) {
            title = "选择预付月数";
            selectFloorDialog = new SelectFloorDialog(context, title, new SelectFloorDialog.RecyclerViewInterface() {
                @Override
                public void initRecycleView(XRecyclerView xRecyclerView) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    xRecyclerView.setLayoutManager(layoutManager);
                    xRecyclerView.setAdapter(floorAdapter);
                }
            });
        } else {
            switch (type) {
                case 0://楼号
                    title = "请选择楼号";
                    break;
                case 1://房号
                    title = "请选择房号";
                    break;
                case 3://手机
                    title = "请选择接收手机";
                    break;
                case 4://手机
                    title = "请选择性别";
                    break;
                case 5://手机
                    title = "请选择编号";
                    break;
            }
            selectFloorDialog = new SelectFloorDialog(context, title, new SelectFloorDialog.RecyclerViewInterface() {
                @Override
                public void initRecycleView(XRecyclerView xRecyclerView) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    xRecyclerView.setLayoutManager(layoutManager);
                    xRecyclerView.setAdapter(floorAdapter);
                }
            });
        }
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
                                if (dao != null) {
                                    dao.onPositiveActionClicked(item);
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
