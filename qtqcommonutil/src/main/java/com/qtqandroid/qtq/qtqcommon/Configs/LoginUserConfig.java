package com.qtqandroid.qtq.qtqcommon.Configs;

import android.content.Context;
import android.content.SharedPreferences;


public class LoginUserConfig extends ConfigBase {

    public LoginUserConfig(Context context) {
        super(context, "LoginUserConfig");
    }

    @Override
    public void GetValues(SharedPreferences store) {
        Username = store.getString("Username", "");
        Password = store.getString("Password", "");
    }

    @Override
    public void SetValues(SharedPreferences.Editor store) {
        store.putString("Username", Username);
        store.putString("Password", Password);
    }

    public String Username;
    public String Password;


}
