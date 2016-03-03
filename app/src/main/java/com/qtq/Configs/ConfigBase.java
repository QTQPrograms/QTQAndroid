package com.qtq.Configs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qtq.Utils.StringUtil;

/**
 * Created by luanhui on 2016/3/3.
 */
public abstract class ConfigBase {
    String mName;
    Context mContext;

    public ConfigBase(Context context, String name) {
        mName = name;
        mContext = context;
    }

    public SharedPreferences getStore() {
        if (StringUtil.IsNullOrEmpty(mName)) {
            return PreferenceManager.getDefaultSharedPreferences(mContext);
        } else {
            return mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
        }
    }

    public abstract void GetValues(SharedPreferences store);

    public abstract void SetValues(SharedPreferences.Editor store);

}
