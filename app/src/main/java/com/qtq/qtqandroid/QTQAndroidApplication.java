package com.qtq.qtqandroid;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.qtq.Configs.LastLoginInfo;
import com.qtq.Utils.ConfigUtil;
import com.qtq.Utils.StringUtil;

import org.forks.jsonrpc.JsonRpcException;
import org.forks.jsonrpc.RpcFacade;
import org.forks.jsonrpc.platform.AndroidPlatform;
import org.forks.jsonrpc.platform.Platform;

/**
 * Created by luanhui on 2016/3/3.
 */
public class QTQAndroidApplication extends Application {

    static String Name = "QTQAndroidApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        //为了能够在主线程 使用网络  否则 RpcFacade.login(mLoginInfo.Username, mLoginInfo.Password); 报错，其他地方应该用 线程去做
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());


        Platform.init(new AndroidPlatform(this));
        RpcFacade.init(Name);
        mLoginInfo = new LastLoginInfo(this);
        ConfigUtil.Fill(mLoginInfo);
        reLogin();

        // TODO: 2016/3/3 自动登录跳转的 
//        if(!StringUtil.IsNullOrEmpty(ConfigUtil.getServerUri(getApplicationContext())))
//        {
//
//            LastLoginConfig config=new LastLoginConfig(getApplicationContext());
//            ConfigUtil.Fill(config);
//            Intent intent=new Intent();
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            if(!StringUtil.IsNullOrEmpty(config.Username))
//            {
//                intent.setClass(getApplicationContext(), MainActivity.class);
//            }
//            else
//            {
//                intent.setClass(getApplicationContext(), LoginActivity.class);
//            }
//
//            startActivity(intent);
//
//        }
    }

    private void reLogin() {

        if (RpcFacade.isLogedIn()) {
            return;
        }

        if (StringUtil.IsNullOrEmpty(mLoginInfo.Username)) {
            return;
        }

        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - mLoginInfo.CurrentTimeMillis > 30 * 60 * 1000) {
            return;
        }

        try {
            RpcFacade.login(mLoginInfo.Username, mLoginInfo.Password);
        } catch (JsonRpcException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static LastLoginInfo mLoginInfo;

    public static void UpdateLastActiveTime() {
        if (StringUtil.IsNullOrEmpty(mLoginInfo.Username)) {
            Log.e(Name, "还没有登录，不能调用UpdateLastActiveTime()方法");
            return;
        }
        mLoginInfo.CurrentTimeMillis = System.currentTimeMillis();
        ConfigUtil.Save(mLoginInfo);
    }

    public static void ClearLogin() {
        mLoginInfo.Username = "";
        ConfigUtil.Save(mLoginInfo);
    }

    public static void SaveLogin(String username, String password) {
        mLoginInfo.Username = username;
        mLoginInfo.Password = password;
        mLoginInfo.CurrentTimeMillis = System.currentTimeMillis();
        ConfigUtil.Save(mLoginInfo);
    }
}
