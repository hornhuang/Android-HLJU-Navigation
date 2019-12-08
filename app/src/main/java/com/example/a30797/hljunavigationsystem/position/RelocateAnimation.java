package com.example.a30797.hljunavigationsystem.position;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.a30797.hljunavigationsystem.imageProcessing.ImageProcessing;
import com.example.a30797.hljunavigationsystem.activities.MainActivity;
import com.example.a30797.hljunavigationsystem.R;

public class RelocateAnimation extends Thread{

    private BaiduMap mBaiduMap;
    private final int dur=80;
    private double startLat;
    private double latDis;
    double lat2 ;
    double lng2 ;
    float rot2 ;
    private double latStart;
    private float rotStart;
    private float rotDis;

    public RelocateAnimation(BaiduMap mBaiduMap) {
        this.mBaiduMap = mBaiduMap;
    }

    public double fun(double lat){
        double t=(lat-startLat)/latDis;
        if(t<0.7)
            return 1;
        return (1-t)/0.3;
    }

    public double fun(double now,double start,double dis){
        double t=(now-start)/dis;
        if(t<0.7)
            return 1;
        return (1-t)/0.3;
    }

    @Override
    public void run() {

        lat2 = MainActivity.mainActivity.locationSetter.getLocation().getLatitude() ;
        lng2 = MainActivity.mainActivity.locationSetter.getLocation().getLongitude() ;
        rot2 = MainActivity.mainActivity.locationSetter.getmXDirection();

        double lat=mBaiduMap.getMapStatus().target.latitude;
        latStart=lat;
        double lng=mBaiduMap.getMapStatus().target.longitude;
        latDis=lat2-lat;
        double speedLat=(lat2-lat)/dur;
        double speedLng=(lng2-lng)/dur;

        float rot=mBaiduMap.getMapStatus().rotate;
        rotStart=rot;
        rotDis=rot2-rot;
        double speedRot=rotDis/dur;



        MapStatus.Builder builder = new MapStatus.Builder();

        while (DistanceUtil.getDistance(mBaiduMap.getMapStatus().target,new LatLng(lat2,lng2))>5) {

            double latRate=fun(lat,latStart,latDis);
            double rotRate=fun(rot,rotStart,rotDis);

            lat += speedLat*latRate;
            lng += speedLng*latRate;
            rot += speedRot*rotRate;

            builder.target(new LatLng(lat,lng))
                    .rotate(rot);
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder().accuracy(50)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(MainActivity.mainActivity.locationSetter.getmXDirection())
                .latitude(lat)
                .longitude(lng).build();
        // 设置定位数据
        mBaiduMap.setMyLocationData(locData);BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.mainActivity.getResources(),R.drawable.daohangdirection);
        // 设置自定义图标
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromBitmap(ImageProcessing.ChangBitmapSize(bitmap));

        MyLocationConfiguration config = new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfigeration(config);
    }
}
