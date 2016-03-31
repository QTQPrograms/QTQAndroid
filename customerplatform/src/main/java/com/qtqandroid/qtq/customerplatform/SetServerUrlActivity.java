package com.qtqandroid.qtq.customerplatform;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qtqandroid.qtq.qtqcommon.Configs.ServerConfig;
import com.qtqandroid.qtq.qtqcommon.Utils.ConfigUtil;
import com.qtqandroid.qtq.qtqcommon.Utils.DialogUtil;
import com.qtqandroid.qtq.qtqcommon.Utils.StringUtil;

public class SetServerUrlActivity extends AppCompatActivity {

    EditText et;
    ServerConfig config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_server_url);


        et = (EditText) findViewById(R.id.setserverurl_tvUrl);

        config=new ServerConfig(SetServerUrlActivity.this);
        ConfigUtil.Fill(config);
        et.setText(config.getServerUri());

        Button btnOk = (Button) findViewById(R.id.setserverurl_btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url=et.getText().toString();
                if(StringUtil.IsNullOrEmpty(url))
                {
                    DialogUtil.AutoHiddenAlert(SetServerUrlActivity.this,"服务器地址不能为空");
                    return;
                }
                if( !url.startsWith("http://"))
                {
                    DialogUtil.AutoHiddenAlert(SetServerUrlActivity.this,"服务器地址格式不正确");
                    return;
                }

                config.setServerUri(url);
                ConfigUtil.Save(config);

                DialogUtil.AutoHiddenAlert(SetServerUrlActivity.this,"设置完成");
                finish();
            }
        });
    }
}
