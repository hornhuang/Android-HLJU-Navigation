package com.example.a30797.hljunavigationsystem.position;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

public class ReloacateAnimationForAll extends Thread{

    private final int dur=80;
    double lat2;
    double lng2;
    float rot2;
    float ol2;
    float zo2;
    private BaiduMap mBaiduMap;
    private double latStart;
    private double latDis;
    private float rotStart;
    private float rotDis;
    private float olStart;
    private float olDis;
    private float zoStart;
    private float zoDis;

    public ReloacateAnimationForAll(BaiduMap mBaiduMap,float ol2,float rot2,float zo2,double lat2,double lng2) {
        this.lat2 = lat2;
        this.lng2 = lng2;
        this.rot2 = rot2;
        this.ol2 = ol2;
        this.zo2 = zo2;
        this.mBaiduMap = mBaiduMap;
    }

    public double fun(double now, double start, double dis){
        double t=(now-start)/dis;
        if(t<0.7)
            return 1;
        return (1-t)/0.3;
    }

    @Override
    public void run() {

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

        float ol=mBaiduMap.getMapStatus().overlook;
        olStart=ol;
        olDis=ol2-ol;
        double speedOl=(ol2-ol)/dur;

        float zo=mBaiduMap.getMapStatus().zoom;
        zoStart=zo;
        zoDis=zo2-zo;
        double speedZo=(zo2-zo)/dur;

        MapStatus.Builder builder = new MapStatus.Builder();

        while (DistanceUtil.getDistance(mBaiduMap.getMapStatus().target,new LatLng(lat2,lng2))>5) {

            double latRate=fun(lat,latStart,latDis);
            double rotRate=fun(rot,rotStart,rotDis);
            double olRate=fun(ol,olStart,olDis);
            double zoRate=fun(zo,zoStart,zoDis);

            lat += speedLat*latRate;
            lng += speedLng*latRate;
            rot += speedRot*rotRate;
            ol+=speedOl*olRate;
            zo+=speedZo*zoRate;

            builder.target(new LatLng(lat,lng))
                    .rotate(rot)
                    .overlook(ol)
                    .zoom(zo);
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
