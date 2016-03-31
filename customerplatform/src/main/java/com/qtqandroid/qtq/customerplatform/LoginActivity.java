package com.qtqandroid.qtq.customerplatform;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qtqandroid.qtq.qtqcommon.Configs.LoginUserConfig;
import com.qtqandroid.qtq.qtqcommon.Task.ViewOnClickTask;
import com.qtqandroid.qtq.qtqcommon.Utils.ActivityUtil;
import com.qtqandroid.qtq.qtqcommon.Utils.ConfigUtil;

import org.forks.jsonrpc.RpcFacade;

public class LoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText etName, etPass;

    private LoginUserConfig config = new LoginUserConfig(this);

    ViewOnClickTask loginTask = new ViewOnClickTask(this, "登录中...") {

        public Object call() throws Exception {
            String username = etName.getText().toString();
            String password = etPass.getText().toString();
            config.Username = username;
            config.Password = password;

            ConfigUtil.Save(config);
            RpcFacade.login(username, password);

//            String method = "/Sale/Sale/Rpcs/MobileOrderClientRpc/FeatureEnable";

            return null;
        }

        @Override
        protected void successUI() {
            QTQAndroidApplication.SaveLogin(config.Username, config.Password);
            ActivityUtil.Go(LoginActivity.this, MainActivity.class);
//                SaleOrderApplication .SaveLogin(config.Username, config.Password);
//                Util.ActivityUtil.Go(LoginActivity.this, MainActivity.class);

        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //漂浮按钮
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        etName = (EditText) findViewById(R.id.login_main_etName);
        etPass = (EditText) findViewById(R.id.login_main_etPass);

        QTQAndroidApplication.ClearLogin();

        ConfigUtil.Fill(config);
        etName.setText(config.Username);
        etPass.setText(config.Password);

        Button btnLogin = (Button) findViewById(R.id.login_main_btnLogin);
        btnLogin.setOnClickListener(loginTask);


        ExitApplication.getInstance().addActivity(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.url_settings) {

            ActivityUtil.Go(LoginActivity.this, SetServerUrlActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.url_settings) {
//            // Handle the camera action
//        }
// else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
