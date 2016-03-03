package com.qtq.qtqandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.qtq.Utils.MapGaoDeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by luanhui on 2016/3/2.
 */
public class XunDianEditActivity  extends Activity implements AMapLocationListener {


    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    TextView tvMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xundian_edit);

        LinearLayout llselectcustomer=(LinearLayout)findViewById(R.id.llayout_xundianedid_selectcustomer);
        llselectcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XunDianEditActivity.this, "sss",Toast.LENGTH_LONG).show();
            }
        });

        tvMap=(TextView)findViewById(R.id.tv_map);


        initMap();



    }

    private void initMap() {

        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位监听
        locationClient.setLocationListener(this);

        //设置是否返回地址信息（默认返回地址信息）
        locationOption.setNeedAddress(true);
        locationOption.setGpsFirst(true);
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    Handler mHandler=new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what)
            {
                //开始定位
                case MapGaoDeUtil.MSG_LOCATION_START:
                    tvMap.setText("正在定位...");
                    break;
                // 定位完成
                case MapGaoDeUtil.MSG_LOCATION_FINISH:
                    AMapLocation loc = (AMapLocation) msg.obj;
                    String result = MapGaoDeUtil.getLocationStr(loc);
                    tvMap.setText(result);
                    break;
                //停止定位
                case MapGaoDeUtil.MSG_LOCATION_STOP:
                    tvMap.setText("定位停止");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

        if (null != aMapLocation) {
            Message msg = mHandler.obtainMessage();
            msg.obj = aMapLocation;
            msg.what = MapGaoDeUtil.MSG_LOCATION_FINISH;
            mHandler.sendMessage(msg);
        }
//        if (aMapLocation != null) {
//            if (aMapLocation.getErrorCode() == 0) {
//                //定位成功回调信息，设置相关消息
//                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                aMapLocation.getLatitude();//获取纬度
//                aMapLocation.getLongitude();//获取经度
//                aMapLocation.getAccuracy();//获取精度信息
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date = new Date(aMapLocation.getTime());
//                df.format(date);//定位时间
//                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                aMapLocation.getCountry();//国家信息
//                aMapLocation.getProvince();//省信息
//                aMapLocation.getCity();//城市信息
//                aMapLocation.getDistrict();//城区信息
//                aMapLocation.getStreet();//街道信息
//                aMapLocation.getStreetNum();//街道门牌号信息
//                aMapLocation.getCityCode();//城市编码
//                aMapLocation.getAdCode();//地区编码
//            } else {
//                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
//                Log.e("AmapError", "location Error, ErrCode:"
//                        + aMapLocation.getErrorCode() + ", errInfo:"
//                        + aMapLocation.getErrorInfo());
//            }
//        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }
}
