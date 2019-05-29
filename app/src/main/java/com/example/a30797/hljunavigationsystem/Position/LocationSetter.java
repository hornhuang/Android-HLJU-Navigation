package com.example.a30797.hljunavigationsystem.Position;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.example.a30797.hljunavigationsystem.ImageProcessing.ImageProcessing;
import com.example.a30797.hljunavigationsystem.activities.MainActivity;
import com.example.a30797.hljunavigationsystem.R;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LocationSetter extends Handler {

    private BaiduMap mBaiduMap;
    private BDLocation location=new BDLocation();
    private float mXDirection;
    public LocationSetter(BaiduMap mBaiduMap,float mXDirection){
        this.mBaiduMap=mBaiduMap;
        this.mXDirection = mXDirection;
    }


    @SuppressLint("MissingPermission")
    public void handleMessage(Message msg){
        location.setLatitude(msg.getData().getDouble("la"));
        location.setLongitude(msg.getData().getDouble("lo"));

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
//                .accuracy(50)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(mXDirection)
                .latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        // 设置定位数据
        mBaiduMap.setMyLocationData(locData);BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.mainActivity.getResources(),R.drawable.daohangdirection);
        // 设置自定义图标
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromBitmap(ImageProcessing.ChangBitmapSize(bitmap));

        MyLocationConfiguration config = new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfigeration(config);

        Log.v("myTag","set ok "+location.getLatitude()+" "+location.getLongitude());
    }

    @Override
    public void publish(LogRecord record) {

    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }

    public void sendMessage(Message msg) {
        handleMessage(msg);
    }

    public BDLocation getLocation() {
        return location;
    }

    public void setMx(float mXDirection){
        this.mXDirection = mXDirection;
    }

    public  float getmXDirection() {
        return mXDirection;
    }


}
