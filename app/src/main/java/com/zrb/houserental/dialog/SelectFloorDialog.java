package com.zrb.houserental.dialog;

import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zrb.baseapp.dialogpop.BaseDialog;
import com.zrb.houserental.R;

/**
 * Created by Administrator on 2017/4/5.
 */

public class SelectFloorDialog extends BaseDialog {
    private String title;
    private RecyclerViewInterface initRecycleView;


    public SelectFloorDialog(Context context, String title, RecyclerViewInterface initRecycleView) {
        super(context);
        this.title = title;
        this.initRecycleView = initRecycleView;
        initUI(context);
    }


    public SelectFloorDialog(Context context, int theme, String title, RecyclerViewInterface initRecycleView) {
        super(context, theme);
        this.title = title;
        this.initRecycleView = initRecycleView;
        initUI(context);
    }

    private void initUI(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_selectfloor, null);
        setContentView(view);
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            dialogWindow.setGravity(Gravity.CENTER);
//            dialogWindow.setBackgroundDrawableResource(R.color.transparent);
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            Display display = getWindow().getWindowManager().getDefaultDisplay();
            lp.width = (int) (display.getWidth() * 0.8);
            lp.height = lp.width;
            dialogWindow.setAttributes(lp);
        }
        XRecyclerView xRecyclerView = (XRecyclerView) view.findViewById(R.id.view_listview);
        TextView titlt = (TextView) view.findViewById(R.id.title);
        titlt.setText(this.title);
        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        xRecyclerView.setPullRefreshEnabled(false);
        xRecyclerView.setLoadingMoreEnabled(false);
        initRecycleView.initRecycleView(xRecyclerView);
    }

    public interface RecyclerViewInterface {
        void initRecycleView(XRecyclerView xRecyclerView);
    }
}
