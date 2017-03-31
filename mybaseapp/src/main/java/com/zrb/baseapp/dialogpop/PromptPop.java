package com.zrb.baseapp.dialogpop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zrb.baseapp.R;


/**
 * 基本的提示对话框
 * Created by Administrator on 2016/8/17.
 */
public class PromptPop extends PopupWindow implements View.OnClickListener {
    private Context mContext;
    public TextView tv_title;
    public TextView tv_cancel, tv_confirm;
    private MyPromptPopListent myPromptPopListent;

    public PromptPop(Context mContext, MyPromptPopListent myPromptPopListent) {
        this.mContext = mContext;
        this.myPromptPopListent = myPromptPopListent;
        init();
    }

    private void init() {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.pop_prompt, null);
        mView.findViewById(R.id.pop_all_ll).setOnClickListener(this);
        mView.findViewById(R.id.pop_prompt_cancel).setOnClickListener(this);
        mView.findViewById(R.id.pop_prompt_confirm).setOnClickListener(this);
        tv_title = (TextView) mView.findViewById(R.id.pop_prompt_title);
        tv_cancel = (TextView) mView.findViewById(R.id.pop_prompt_cancel);
        tv_confirm = (TextView) mView.findViewById(R.id.pop_prompt_confirm);

        this.setContentView(mView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 点击外面的控件也可以使得PopUpWindow dimiss
        this.setOutsideTouchable(true);
        // 设置半透明灰色
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        this.setClippingEnabled(true);
//        this.setBackgroundDrawable(new BitmapDrawable());
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.pop_all_ll){
            dismiss();
        }else if (v.getId()==R.id.pop_prompt_cancel){
            dismiss();
        }else if (v.getId()==R.id.pop_prompt_confirm){
            myPromptPopListent.confirm();
            dismiss();
        }
    }

    public interface MyPromptPopListent {
        void confirm();
    }
}
