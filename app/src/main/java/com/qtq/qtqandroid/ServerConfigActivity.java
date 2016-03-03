package com.qtq.qtqandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qtq.Configs.ServerConfig;
import com.qtq.Utils.ActivityUtil;
import com.qtq.Utils.ConfigUtil;

/**
 * Created by luanhui on 2016/3/3.
 */
public class ServerConfigActivity extends Activity {
    private EditText urlInput;
    private ServerConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.serverconfig);

        urlInput = ActivityUtil.Find(this, R.id.EditText_Url);

        config = new ServerConfig(getBaseContext());
        ConfigUtil.Fill(config);
        urlInput.setText(config.getServerUri());

        Button saveButton = ActivityUtil.FindButton(this, R.id.Button_OK);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String serverUrl=urlInput.getText().toString();
                if(serverUrl.length()==0)
                {
                    Toast.makeText(ServerConfigActivity.this, "不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!serverUrl.contains("http://"))
                {
                    Toast.makeText(ServerConfigActivity.this,"地址前需要加'http://'",Toast.LENGTH_LONG).show();
                    return;
                }

                config.setServerUri(serverUrl);
                ConfigUtil.Save(config);
                Toast.makeText(ServerConfigActivity.this,"保存成功",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        Button cancelButton = ActivityUtil.FindButton(this, R.id.Button_Cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }

        });
    }

}
