package com.qtq.Configs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by luanhui on 2016/3/3.
 */
public class ServerConfig extends ConfigBase {

    public ServerConfig(Context context) {
        super(context, "");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void GetValues(SharedPreferences store) {
        mServerUri = store.getString("ServerUri", "");
        // TODO Auto-generated method stub

    }

    @Override
    public void SetValues(SharedPreferences.Editor store) {
        store.putString("ServerUri", getServerUri());
        // TODO Auto-generated method stub

    }

    private String mServerUri;

    public String getServerUri() {
        return mServerUri;
    }

    public void setServerUri(String value) {
        mServerUri = value;
    }
}
