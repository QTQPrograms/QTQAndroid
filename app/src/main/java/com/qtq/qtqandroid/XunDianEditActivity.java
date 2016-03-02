package com.qtq.qtqandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by luanhui on 2016/3/2.
 */
public class XunDianEditActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xundian_edit);
        LinearLayout llselectcustomer=(LinearLayout)findViewById(R.id.llayout_xundianedid_selectcustomer);
        llselectcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XunDianEditActivity.this,"sss",Toast.LENGTH_LONG).show();
            }
        });
    }
}
