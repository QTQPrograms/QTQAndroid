package com.qtqandroid.qtq.qtqcommon.Utils;

import android.content.Context;

import java.math.BigDecimal;

public class StringUtil{
    public static Boolean IsNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public String GetResource(Context context, int resId) {
        return context.getString(resId);
    }

    public static String ToString(Object object) {
        if (object == null) {
            return "";
        }
        return String.format("%s", object);
    }

    public static BigDecimal ParseMoney(String strValue) {
        if (IsNullOrEmpty(strValue)) {
            return null;
        }
        return new BigDecimal(strValue);
    }

}
