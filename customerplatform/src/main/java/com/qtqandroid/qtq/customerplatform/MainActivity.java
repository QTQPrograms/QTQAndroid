package com.qtqandroid.qtq.customerplatform;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.readystatesoftware.viewbadger.BadgeView;

import java.util.List;

public class MainActivity extends LoginedFragmentActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private LinearLayout mTabOrder;
    private LinearLayout mTabShoppingCart;
    private LinearLayout mTabMessage;
    private LinearLayout mTabMy;

    private ImageButton mImgOrder;
    private ImageButton mImgShoppingCart;
    private ImageButton mImgMessage;
    private ImageButton mImgMy;

    BadgeView badgeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initView();
//        initEvent();
//        setSelect(0);
    }

    @Override
    public void onClick(View v) {

    }
}
