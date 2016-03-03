package com.qtq.qtqandroid;

import android.app.Activity;
import android.os.Bundle;

import org.forks.jsonrpc.RpcFacade;

/**
 * Created by luanhui on 2016/3/3.
 */
public class LoginedActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!RpcFacade.isLogedIn()) {
            finish();
            return;
        }

        QTQAndroidApplication.UpdateLastActiveTime();
        ExitApplication.getInstance().addActivity(this);
    }

}