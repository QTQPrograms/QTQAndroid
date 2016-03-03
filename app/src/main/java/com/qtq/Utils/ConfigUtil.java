package com.qtq.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.qtq.Configs.ConfigBase;
import com.qtq.Configs.ServerConfig;

/**
 * Created by luanhui on 2016/3/3.
 */
public class ConfigUtil {
    public static <T extends ConfigBase> void Fill(T config) {
        SharedPreferences store = config.getStore();
        config.GetValues(store);
    }

    public static <T extends ConfigBase> void Save(T config) {
        SharedPreferences store = config.getStore();
        SharedPreferences.Editor editor = store.edit();
        editor.clear();
        config.SetValues(editor);
        editor.commit();
    }

    public static String getServerUri(Context context) {
        ServerConfig config = new ServerConfig(context);
        Fill(config);
        return config.getServerUri();
    }

}

