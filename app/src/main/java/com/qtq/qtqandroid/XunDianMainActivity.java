package com.qtq.qtqandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by luanhui on 2016/3/2.
 */
public class XunDianMainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.xundian_main);
        Button btnadd= (Button) findViewById(R.id.btn_xundian_add);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(XunDianMainActivity.this,XunDianEditActivity.class);
                startActivity(intent);
            }
        });
    }
}
