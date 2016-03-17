package com.qtq.qtqandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sch.rfview.AnimRFRecyclerView;

import org.forks.jsonrpc.RpcObject;

/**
 * Created by luanhui on 2016/3/4.
 */
public class XunDianInOutActionActivity extends LoginedActivity {


    ImageView ivToolRetrun,ivToolRight;
    TextView tvToolTitle;

    private long mID;
    private RpcObject mDmo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xundian_inoutaction);
        initView();

        
    }

    private void initView() {
        initToolBar();

        AnimRFRecyclerView  ss= new AnimRFRecyclerView(this);

    }

    private void initToolBar() {
        ivToolRetrun= (ImageView) findViewById(R.id.toolbar_iv_return);

        ivToolRetrun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XunDianInOutActionActivity.this, "haha", Toast.LENGTH_SHORT).show();
            }
        });
        
        ivToolRight= (ImageView) findViewById(R.id.toolbar_iv_right);
        tvToolTitle= (TextView) findViewById(R.id.toolbar_tv_title);
    }
}
