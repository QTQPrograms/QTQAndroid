package com.qtqandroid.qtq.customerplatform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.forks.jsonrpc.RpcFacade;


public class LoginedActivity extends AppCompatActivity {
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