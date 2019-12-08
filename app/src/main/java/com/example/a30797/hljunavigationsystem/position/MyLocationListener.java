package com.example.a30797.hljunavigationsystem.position;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;

public class MyLocationListener extends BDAbstractLocationListener{

    public LocationSetter locationSetter;
    public BaiduMap mBaiduMap;
    public LocationClient locationClient;
    private boolean flag=true;

    public MyLocationListener(LocationSetter locationSetter, BaiduMap mBaiduMap, LocationClient locationClient) {
        this.locationSetter = locationSetter;
        this.mBaiduMap = mBaiduMap;
        this.locationClient = locationClient;
    }

    @Override
    public void onReceiveLocation(BDLocation location){
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取经纬度相关（常用）的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

        //获取纬度信息
        double latitude = location.getLatitude();
        //获取经度信息
        double longitude = location.getLongitude();
        Log.v("myTag","update "+latitude+" "+longitude);
        //获取定位精度，默认值为0.0f
        float radius = location.getRadius();
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
        String coorType = location.getCoorType();
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        int errorCode = location.getLocType();

        Bundle bundle=new Bundle();
        bundle.putDouble("la",latitude);
        bundle.putDouble("lo",longitude);
        Message msg=new Message();
        msg.setData(bundle);

        locationSetter.sendMessage(msg);
        //开始定位动画
        if(flag) {
            new LocateAnimation(locationSetter, mBaiduMap).start();
            flag=false;
        }

        Log.v("myTag",errorCode+"");

    }

}