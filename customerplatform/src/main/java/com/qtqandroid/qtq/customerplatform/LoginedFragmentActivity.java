package com.qtqandroid.qtq.customerplatform;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.qtqandroid.qtq.qtqcommon.Utils.DialogUtil;

import org.forks.jsonrpc.RpcFacade;

/**
 * Created by luanhui on 2016/3/30.
 */
public class LoginedFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!RpcFacade.isLogedIn()) {
            DialogUtil.AutoHiddenAlert(LoginedFragmentActivity.this,"登录失败");
            finish();
            return;
        }

        QTQAndroidApplication.UpdateLastActiveTime();
        ExitApplication.getInstance().addActivity(this);
    }


}
