package com.qtq.qtqandroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qtq.Configs.LoginUserConfig;
import com.qtq.Configs.ServerConfig;
import com.qtq.Task.ViewOnClickTask;
import com.qtq.Utils.ActivityUtil;
import com.qtq.Utils.ConfigUtil;
import com.qtq.Utils.DialogUtil;
import com.qtq.Utils.StringUtil;

import org.forks.jsonrpc.RpcFacade;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;

    private LoginUserConfig config = new LoginUserConfig(this);


    ViewOnClickTask loginTask = new ViewOnClickTask(this, "登录中...") {
        private Boolean featurEnable=true;

        public Object call() throws Exception {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();
            config.Username = username;
            config.Password = password;

            ConfigUtil.Save(config);

            RpcFacade.login(username, password);

            return null;
        }

        @Override
        protected void successUI() {
            if (featurEnable) {
                QTQAndroidApplication.SaveLogin(config.Username,
                        config.Password);
                ActivityUtil.Go(LoginActivity.this, MainActivity.class);
            } else {
                DialogUtil.AlertAndFinish(LoginActivity.this, "没有使用此客户端的授权");
            }
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnTest= (Button) findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        usernameInput = (EditText) findViewById(R.id.login_et_username);
        passwordInput = (EditText) findViewById(R.id.login_et_password);

        QTQAndroidApplication.ClearLogin();

        ConfigUtil.Fill(config);
        usernameInput.setText(config.Username);
        passwordInput.setText(config.Password);

        if (!StringUtil.IsNullOrEmpty(config.Username)
                && StringUtil.IsNullOrEmpty(config.Password)) {
            passwordInput.requestFocus();
        }



        TextView evSetUrl=(TextView)findViewById(R.id.login_ev_seturl);
        evSetUrl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,ServerConfigActivity.class);
                startActivity(intent);
            }
        });

        Button signInButton = (Button) findViewById(R.id.email_sign_in_button);
        signInButton.setOnClickListener(loginTask);


        checkUpdate();
        ExitApplication.getInstance().addActivity(this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        checkUpdate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        org.forks.ui.UIHelper.shutdowApp();
    }

    // TODO: 2016/3/3  更新
    private void checkUpdate() {
//        Intent intent = new Intent();
//        intent.setClass(this, CheckUpdateService.class);
//        startService(intent);
    }

}

