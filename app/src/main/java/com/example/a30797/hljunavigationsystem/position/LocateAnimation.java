package com.example.a30797.hljunavigationsystem.position;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;

public class LocateAnimation extends Thread{

    private LocationSetter locationSetter;
    private BaiduMap mBaiduMap;

    public LocateAnimation(LocationSetter locationSetter, BaiduMap mBaiduMap){
        this.locationSetter = locationSetter;
        this.mBaiduMap=mBaiduMap;
    }

    public double fun(double now, double start, double dis){
        double t=(now-start)/dis;
        if(t<0.5)
            return 1;
        return (1-t)/0.5;
    }

    public void run() {

        //新建builder
        MapStatus.Builder builder = new MapStatus.Builder();
        //设置数据
        builder.target(new LatLng(locationSetter.getLocation().getLatitude(), locationSetter.getLocation().getLongitude()));
        //应用biulder
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        float zoom=13.5f;
        while(zoom<18.5-0.1) {
            try {
                Thread.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double rate=fun(zoom,13.5,5);
            zoom += 0.08*rate;
            builder.zoom(zoom);
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }

}
