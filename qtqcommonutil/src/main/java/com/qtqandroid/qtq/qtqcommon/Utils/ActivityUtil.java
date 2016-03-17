package com.qtqandroid.qtq.qtqcommon.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityUtil {
    public String GetText(Activity activity, int id) {
        View view = activity.findViewById(id);
        if (view instanceof EditText) {
            return ((EditText) view).getText().toString();
        }

        return String.format("未支持得到类型%s的文本", view.getClass());
    }

    public static Button FindButton(Activity activity, int id) {
        return (Button) activity.findViewById(id);
    }

    public static void Go(Context context, Class<?> class1) {
        Intent intent = new Intent();
        intent.setClass(context, class1);
        context.startActivity(intent);
    }

    public static void Go(View arg0, Class<?> class1) {
        Go(arg0.getContext(), class1);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T Find(Activity activity, int id) {
        return (T) activity.findViewById(id);
    }

    public static void Finish(Activity activity, Intent data) {
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

}