package com.qtqandroid.qtq.qtqcommon.Configs;

import android.content.Context;
import android.content.SharedPreferences;

public class LastLoginInfo extends ConfigBase {

    public LastLoginInfo(Context context) {
        super(context, "LastLoginUserInfo");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void GetValues(SharedPreferences store) {
        Username = store.getString("Username", "");
        Password = store.getString("Password", "");
        CurrentTimeMillis = store.getLong("CurrentTimeMillis", 0);
    }

    @Override
    public void SetValues(SharedPreferences.Editor store) {
        store.putString("Username", Username);
        store.putString("Password", Password);
        store.putLong("CurrentTimeMillis", System.currentTimeMillis());
    }

    public String Username;
    public String Password;
    public long CurrentTimeMillis;

}
