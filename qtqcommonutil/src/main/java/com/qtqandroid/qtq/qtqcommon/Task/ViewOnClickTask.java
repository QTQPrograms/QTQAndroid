package com.qtqandroid.qtq.qtqcommon.Task;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import com.qtqandroid.qtq.qtqcommon.Utils.DialogUtil;
import com.qtqandroid.qtq.qtqcommon.Utils.StringUtil;


public abstract class ViewOnClickTask extends CallableTask implements
        View.OnClickListener {

    private String mPrompt;

    public ViewOnClickTask(Context context, String message) {
        super(context, message);
    }

    public void setPrompt(String prompt) {
        mPrompt = prompt;
    }

    public void onClick(View v) {

        if (!StringUtil.IsNullOrEmpty(mPrompt)) {
            DialogUtil.Confirm(v.getContext(), mPrompt, "确定", "取消",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ViewOnClickTask.this.execute();
                        }
                    });
        } else {
            super.execute();
        }

    }

}
