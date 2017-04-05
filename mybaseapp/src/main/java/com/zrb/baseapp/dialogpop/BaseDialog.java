package com.zrb.baseapp.dialogpop;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import com.zrb.baseapp.R;


public class BaseDialog extends Dialog {

    private Context context;

    private void init(Context context) {
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    public BaseDialog(Context context) {
        // �?定要加上主题，否则不显示
        super(context, R.style.Dialog);
        this.context = context;
        init(context);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        init(context);
    }

    public Context getCurrentContext() {
        return context;
    }

    @Override
    public void show() {
        if (((Activity) getCurrentContext()).isFinishing()) {
            return;
        }
        super.show();
    }

    @Override
    public void dismiss() {
        if (((Activity) context).isFinishing()) {
            return;
        }
        super.dismiss();
    }

}
