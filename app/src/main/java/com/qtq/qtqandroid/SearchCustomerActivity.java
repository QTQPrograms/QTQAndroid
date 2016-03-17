package com.qtq.qtqandroid;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sch.rfview.AnimRFRecyclerView;
import com.sch.rfview.manager.AnimRFLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luanhui on 2016/3/4.
 */
public class SearchCustomerActivity extends LoginedActivity {



    //github https://github.com/shichaohui/AnimRefreshRecyclerView
    private AnimRFRecyclerView mRecyclerView;
    private View headerView;
    private View footerView;

    AnimRFRecyclerViewLinearFragment mLinearFrament;
    private List<String> mCustomerList;
    private Handler mHandler = new Handler();
    FrameLayout mFrameLayout;

    public SearchCustomerActivity()
    {
        mCustomerList=new ArrayList<String>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qtq_selectcustomer);

        mLinearFrament=new AnimRFRecyclerViewLinearFragment();

        getFragmentManager().beginTransaction().replace(
                R.id.search_customer_framelayout, mLinearFrament).commit();


    }


}

